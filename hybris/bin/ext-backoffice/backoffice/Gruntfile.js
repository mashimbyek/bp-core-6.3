module.exports = function (grunt) {

    grunt.loadNpmTasks('grunt-sass');
    grunt.loadNpmTasks('grunt-contrib-copy');
    grunt.loadNpmTasks('grunt-zip');
    grunt.loadNpmTasks('grunt-postcss');

    // Available grunt.option: BACKOFFICE_HOME, EXTENSION_NAME, EXTENSION_PATH, RESOURCES_PATH, IS_SOURCE_MAP_ENABLED, DESTINATION, SASS_VARIABLES_FILE, EXTENSION_SASS_VARIABLES_FILE, TEMP_DIR
    // If you need to read/use additional value from local.properties, you have to pass it in buildcallbacks.xml#backoffice_grunt_invocation line: "arg line"
    var BACKOFFICE_SASS_VARIABLES_FILENAME = "backoffice-variables.scss";
    var BACKOFFICE_SASS_VARIABLES_FILE_PATH = grunt.option('BACKOFFICE_HOME') + '/resources/backoffice/scss/' + BACKOFFICE_SASS_VARIABLES_FILENAME;
    var SASS_GENERATED_DIR = "/resources/backoffice/generated";
    var BACKOFFICE_GENERATED_DIR_PATH = grunt.option('BACKOFFICE_HOME') + SASS_GENERATED_DIR;
    var USER_SASS_VARIABLES_FILE = grunt.option('SASS_VARIABLES_FILE');
    var EXTENSION_SASS_VARIABLES_FILE = grunt.option('EXTENSION_SASS_VARIABLES_FILE');
    var IS_SOURCE_MAP_ENABLED = grunt.option('IS_SOURCE_MAP_ENABLED');
    var SASS_FILE_EXTENSION = "scss";

    var cockpitNgTempDir = grunt.option('TEMP_DIR') + '/cockpitng';
    var cockpitNgDest = BACKOFFICE_GENERATED_DIR_PATH;
    var extensionTempDir = replaceSlashes(grunt.option('TEMP_DIR') + '/' + grunt.option('EXTENSION_NAME'));
    var extensionBofPath = replaceSlashes(grunt.option('EXTENSION_PATH') + '/resources/backoffice/' + grunt.option('EXTENSION_NAME') + '_bof.jar');

    var destinationPath = grunt.option('DESTINATION') || '';

    // Checks whether string ends with given suffix
    function endsWith(str, suffix) {
        return str.indexOf(suffix, str.length - suffix.length) !== -1;
    };

    // replaces all \ to /
    function replaceSlashes(str) {
        return str.replace(/\\/g, '/');
    };

    // Checks whether given file is a variable file (cockpit-globals.scss or variable file defined in local.properties by user)
    function isVariablesFile(path) {
        path = replaceSlashes(path);
        return (path === replaceSlashes(USER_SASS_VARIABLES_FILE) || endsWith(path, 'cockpitng-globals.scss'));
    };

    // This function tries to extract all lines which contains '@import '
    // The function returns value as a string which contains all @import's separated by \n
    function extractAllImports(content) {
        var importRegex = /^@import.*$/mg;
        var imports = content.match(importRegex) || [];
        return imports.join('\n');
    };

    // Removes lines which contains '@import'
    function removeAllImports(content) {
        return content.replace(/^@import.*$/mg, "");
    };

    //
    function appendImportsToSassFile(content, srcpath) {
        if (!endsWith(srcpath, SASS_FILE_EXTENSION) || isVariablesFile(srcpath)) {
            return content;
        }

        // remove old cockpitNg import. This import should be replaced by import with absolute path to cockpitng-globals.scss file
        content = content.replace(/^@import.*cockpitng-globals.scss.*$/mg, "");

        // get all imports as a string
        var imports = extractAllImports(content);

        // remove imports from scss file. All imports are stored in imports variable
        content = removeAllImports(content);

        // add placeholder for extension-variables.scss file.
        // When all files are copied to 'generated' directory, the placeholder will be replaced by absolute path to 'extension-variables.scss'
        // or it will be remove in case when the import file doesn't exist
        // Example placeholder: "EXTENSION_VARIABLES_PLACEHOLDER platformbackoffice-variables.scss\n"
        imports = "EXTENSION_VARIABLES_PLACEHOLDER " + grunt.option('EXTENSION_NAME') +'-variables.scss\n' + imports;

        // Add backoffice-variables.scss import
        imports = '@import \'' + BACKOFFICE_SASS_VARIABLES_FILE_PATH + '\'; \n' + imports;

        // add cockpitng-globals.scss import
        imports = '@import \''+ BACKOFFICE_GENERATED_DIR_PATH + '/cockpitng/cng/scss/cockpitng-globals.scss\'; \n' + imports;

        // Check whether import file defined in local.properties exist (backoffice.sass.preffered.extension and backoffice.sass.preffered.variables.file)
        // If so, the appropriate import is added
        if(grunt.file.exists(replaceSlashes(USER_SASS_VARIABLES_FILE))) {
            imports += '\n@import \'' + USER_SASS_VARIABLES_FILE + '\';\n';
        }

        grunt.log.writeln("Processing: " + srcpath);
        return replaceSlashes(imports) + content;
    };

    // Prepares appropriate import statement: "@import 'pathToImportedFile.scss';\n"
    function importFormat(path) {
        return '@import \'' + path + "\'; \n";
    }

    // Replaces "EXTENSION_VARIABLES_PLACEHOLDER platformbackoffice-variables.scss\n" to "@import 'platformbackoffice-variables.scss'\n"
    function organizeExtensionImports(content, srcpath) {
        var generatedDirPath = grunt.option('BACKOFFICE_HOME') + SASS_GENERATED_DIR;
        var importPlaceholderPattern = /^EXTENSION_VARIABLES_PLACEHOLDER.*$/mg;
        // find array of lines contained pattern 'EXTENSION_VARIABLES_PLACEHOLDER'
        var importPlaceholder = content.match(importPlaceholderPattern) || [];
        if(importPlaceholder.length > 0) {
            for(var i = 0; i < importPlaceholder.length; i++) {
                if(endsWith(srcpath, '-variables.scss')) {
                    content = content.replace(importPlaceholder[i], "");
                } else {
                    // extract eg: platformbackoffice-variables.scss from "EXTENSION_VARIABLES_PLACEHOLDER platformbackoffice-variables.scss\n" and set it to extensionVariablesFile
                    var extensionVariablesFile = importPlaceholder[i].substring('EXTENSION_VARIABLES_PLACEHOLDER'.length).trim();
                    // extensionVariableFileBackofficePath contains path to eg: absolutePathToGenerated/backoffice/scss/platformbackoffice-variables.scss
                    var extensionVariableFileBackofficePath = replaceSlashes(generatedDirPath + '/backoffice/scss/' + extensionVariablesFile);
                    // extensionVariableFileBackofficePath contains path to eg: absolutePathToGenerated/cockpitng/scss/platformbackoffice-variables.scss
                    var extensionVariableFileCockpitNgPath = replaceSlashes(generatedDirPath + '/cockpitng/scss/' + extensionVariablesFile);
                    if(grunt.file.exists(extensionVariableFileBackofficePath)) {
                        content = content.replace(importPlaceholder[i], importFormat(extensionVariableFileBackofficePath));
                    } else if(grunt.file.exists(extensionVariableFileCockpitNgPath)) {
                        content = content.replace(importPlaceholder[i], importFormat(extensionVariableFileCockpitNgPath));
                    } else {
                        content = content.replace(importPlaceholder[i], "");
                    }
                }
            }
        }

        return content;
    };

    grunt.initConfig({
        // unzips specified jars to temp directory
        unzip: {
            backofficecore: {
                src: 'web/webroot/WEB-INF/lib/backoffice-core-*.jar',
                dest: cockpitNgTempDir
            },
            backofficeWidgets: {
                src: 'web/webroot/WEB-INF/lib/backoffice-widgets-*.jar',
                dest: cockpitNgTempDir
            },
            cockpitAdmin: {
                src: 'web/webroot/WEB-INF/lib/cockpitadmin-*.jar',
                dest: cockpitNgTempDir
            },
            cockpitFramework: {
                src: 'web/webroot/WEB-INF/lib/cockpitframework-*.jar',
                dest: cockpitNgTempDir
            },
            cockpitStandardEditors: {
                src: 'web/webroot/WEB-INF/lib/cockpit-standard-editors-*.jar',
                dest: cockpitNgTempDir
            },
            cockpitWidgets: {
                src: 'web/webroot/WEB-INF/lib/cockpitwidgets-*.jar',
                dest: cockpitNgTempDir
            },
            backofficeExtension: {
                src : extensionBofPath,
                dest: extensionTempDir
            }
        },
        copy: {
            // copies scss files from backoffice extension
            backoffice: {
                files: [
                    {
                        expand: true,
                        cwd: "resources/",
                        src: ["**/*.scss", "!backoffice/generated/**/*.scss"],
                        dest: grunt.option('BACKOFFICE_HOME') + SASS_GENERATED_DIR
                    }
                ],
                options: {
                    process: appendImportsToSassFile
                }
            },
            // copies scss from unzip jar files (from temp directory)
            cockpitNg: {
                files: [
                    {
                        expand: true,
                        cwd: cockpitNgTempDir,
                        src: ["**/*.scss"],
                        dest: cockpitNgDest
                    }
                ],
                options: {
                    process: appendImportsToSassFile
                }
            },
            // copies scss from cockpit resources (variable backoffice.cockpitng.additionalResourceLoader.path from local.properties)
            cockpitNgFromSources: {
                files: [
                    {
                        expand: true,
                        cwd: grunt.option("RESOURCES_PATH"),
                        src: ["**/*.scss"],
                        dest: grunt.option('BACKOFFICE_HOME') + SASS_GENERATED_DIR
                    }
                ],
                options: {
                    process: appendImportsToSassFile
                }
            },
            // copies scss files from backoffice's extension. The first declaration copies them from unzip extension_bof.jar
            // The second declaration copies them from resources directory
            extensions_bof: {
                files: [
                    {
                        expand: true,
                        cwd: extensionTempDir,
                        src: ["**/*.scss"],
                        dest: grunt.option('BACKOFFICE_HOME') + SASS_GENERATED_DIR
                    }
                ],
                options: {
                    process: appendImportsToSassFile
                }
            },
            extensions_source: {
                files: [
                    {
                        expand: true,
                        cwd: grunt.option("EXTENSION_PATH") + "/" + grunt.option("RESOURCES_PATH"),
                        src: ["**/*.scss"],
                        dest: grunt.option('BACKOFFICE_HOME') + SASS_GENERATED_DIR + "/" + destinationPath
                    }
                ],
                options: {
                    process: appendImportsToSassFile
                }
            },
            // Replaces EXTENSION_VARIABLES_PLACEHOLDER to absolute path
            extensionsOrganizeImports : {
                files: [
                    {
                        expand: true,
                        cwd: grunt.option('BACKOFFICE_HOME') + SASS_GENERATED_DIR ,
                        src: ["**/*.scss"],
                        dest: grunt.option('BACKOFFICE_HOME') + SASS_GENERATED_DIR
                    }
                ],
                options: {
                    process: organizeExtensionImports
                }
            }
        },
        sass: {
            options: {
                sourceMap: IS_SOURCE_MAP_ENABLED,
                sourceMapEmbed: IS_SOURCE_MAP_ENABLED
            },
            dist: {
                files: [
                    {
                        expand: true,
                        cwd: grunt.option('BACKOFFICE_HOME') + SASS_GENERATED_DIR,
                        src: ["**/*.scss", "!**/_*.scss"],
                        dest: grunt.option('BACKOFFICE_HOME') + SASS_GENERATED_DIR,
                        ext: ".css"
                    }
                ]
            }
        },
        postcss: {
            options: {
                map: IS_SOURCE_MAP_ENABLED,
                processors: [
                    // list of supported browsers: https://github.com/ai/browserslist
                    require('autoprefixer')({browsers: '> 1%, ie 9-11, Edge >= 12, last 2 Chrome versions, last 2 Firefox versions, last 2 Safari versions'})
                ]
            },
            dist: {
                files: [
                    {
                        expand: true,
                        cwd: grunt.option('BACKOFFICE_HOME') + SASS_GENERATED_DIR,
                        src: ["**/*.css"],
                        dest: grunt.option('BACKOFFICE_HOME') + SASS_GENERATED_DIR,
                        ext: ".css"
                    }
                ]
            }
        }

    });

    grunt.registerTask('copy_backoffice_sass', ['copy:backoffice']);
    grunt.registerTask('copy_cockpitng_sass', ['unzip:backofficecore', 'unzip:backofficeWidgets', 'unzip:cockpitAdmin', 'unzip:cockpitFramework', 'unzip:cockpitStandardEditors', 'unzip:cockpitWidgets', 'copy:cockpitNg']);
    grunt.registerTask('copy_cockpitng_from_sources_sass', ['copy:cockpitNgFromSources']);
    grunt.registerTask('copy_extension_sass', 'Copies files from backoffice extension', function() {
        grunt.task.run('unzip:backofficeExtension');
        // files from bof.jar should be copied only in case of 'backoffice/resources' path, because -bof.jar contains files from backoffice/resources.
        if(grunt.option("RESOURCES_PATH") === 'backoffice/resources') {
            grunt.task.run('copy:extensions_bof');
        }
        grunt.task.run('copy:extensions_source');
    });
    grunt.registerTask('compile_sass', ['copy:extensionsOrganizeImports', 'sass', 'postcss']);

};
