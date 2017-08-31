hybris Entitlement & Metering Service (EMS) Version 5.6 08/06/2015- Installation Guide
=================================================================

The Entitlement & Metering Service (EMS) is based on the Core+ library. Hence, it requires several configuration procedures before it can be first used. This document shows the steps you have to follow to install the Entitlement & Metering Service.

Refer to the Core+ documentation for more information. (https://wiki.hybris.com/pages/viewpage.action?pageId=193236375)

Note:
This document uses the following terms:

* {E&M_HOME} refers to the hybris-ems directory that holds the following folders: binary, documentation, sample-configs, and source. 

* {HYBRIS_HOME} refers to the directory where you unzipped the hybris Commerce Suite package.

* {MONGODB_INSTALLATION_DIRECTORY} refers to the directory where you installed the MongoDB.

* {TOMCAT_INSTALLATION_DIRECTORY} refers to the directory where you installed the Apache Tomcat.

* {DATABASE_DIRECTORY}  refers to the directory where you created the database.

Prerequisites:
--------------
The Entitlement & Metering Service requires several pieces of software to fully function. Ensure that you have downloaded and installed the following on your machine:

* Oracle Java JDK 8 (http://www.oracle.com/technetwork/java/javase/downloads/index.html)

* MongoDB 2.4.6 or higher (http://www.mongodb.org/downloads)

* Apache Tomcat 7 or higher (http://tomcat.apache.org/download-70.cgi)

Running MongoDB
----------------
Before you can use the EMS, you need to run MongoDB. Perform the steps below to start the database:

1. Navigate to the {MONGODB_INSTALLATION_DIRECTORY} /bin folder. 

2. Using the command prompt, run the database. 

* Microsoft Windows
mongod.exe --dpath={DATABASE_DIRECTORY}
Example: mongod.exe --dbpath=C:\data\db

* Unix-based Systems
mongod --dpath={DATABASE_DIRECTORY}
Example: mongod --dpath=/data/db

Note: If you wish to create a new database, the {DATABASE_DIRECTORY} path has to point to an existing folder.


Using the Entitlement & Metering Service
----------------------------------------
You may use the Entitlement & Metering Service as a stand-alone service or as a built-in service in the hybris Telco Accelerator installer. Select one of the following methods and perform its procedure based on your requirements:

* Deploying the EMS with the hybris Telco Accelerator Installer
* Building the EMS .war file in to the hybris Commerce Suite
* Building the EMS using a Maven-built .war file

Deploying the EMS with the hybris Telco Accelerator Installer
-------------------------------------------------------------
The hybris Telco Accelerator installer includes the Entitlement & Metering Service. To have the service successfully running upon deployment using the installer, ensure that you have the MongoDB running. Refer to the Installation using the hybris Installer section in the Telco Accelerator Quick Installation Guide for the instruction in running the installer with pre-configured EMS. 

Deploying the EMS .war file with the hybris Server 
--------------------------------------------------
The Entitlement & Metering Service can run on the instance of the Apache Tomcat shipped with the hybris Commerce Suite and start up with the platform simultaneously. This feature allows you to use an out-of-the-box .war file provided with the Entitlement & Metering Service package. This approach is aimed at customers who do not want to extend the EMS. Thus, you are not required to install and configure Maven in this method. To use the .war file delivered with the EMS, perform the following steps:

Note: Ensure that the MongoDB is running before performing the steps below.

1. Follow the steps in the Download and Unpack the Package section of the Telco Accelerator Quick Installation document. (https://wiki.hybris.com/display/release5/Quick+Installation)
 
2. Perform the steps in the Build the hybris Commerce Suite section of the Telco Accelerator Quick Installation document. (https://wiki.hybris.com/display/release5/Quick+Installation)
 
3. Follow the steps in the Configure Available Extensions and Build the Telco Accelerator of the Telco Accelerator Quick Installation document. (https://wiki.hybris.com/display/release5/Quick+Installation)
 
4. Perform the steps in the Initialize the Telco Accelerator of the Telco Accelerator Quick Installation document. (https://wiki.hybris.com/display/release5/Quick+Installation)
 
5. Navigate to the {HYBRIS_HOME} /hybris-ems/sample-configs directory and copy all the files in the folder. The folder includes the following files:

6. Navigate to the {HYBRIS_HOME}/hybris/bin/platform/tomcat/lib directory and paste all the files in this folder. Change the properties file, if needed. 
 
7. Navigate to the {HYBRIS_HOME}/hybris/config folder and open the localextensions.xml file.

8. In the localextensions.xml file, ensure that you have updated the correct path to the generated .war file inside the <extensions> tags. Your webapp entry in the localextensions.xml file should look as follows:

	<extensions>
	<webapp contextroot="entitlements-web" path="{E&M_HOME}/source/entitlements/entitlements-web/target/entitlements-web.war" />
	</extensions>

9. Follow the instructions in the Starting the Entitlement & Metering Service section below. 

Building the EMS using a Maven Built .war File
----------------------------------------------

If you wish to extend the Entitlement & Metering Service, you can build the EMS by generating a  .war  file required to install the server. Prior to installing the server, you need to download and install Maven.

Prerequisite:
-------------
Maven (http://maven.apache.org/download.cgi)

Note: Ensure that the MongoDB is running before performing the steps below.

To generate the .war file, follow the steps below:

1. Follow the steps in the Download and Unpack the Package section of the Telco Accelerator Quick Installation document. (https://wiki.hybris.com/display/release5/Quick+Installation)

 
2. Perform the steps in the Build the hybris Commerce Suite section of the Telco Accelerator Quick Installation document. (https://wiki.hybris.com/display/release5/Quick+Installation)

 
3. Follow the steps in the Configure Available Extensions and Build the Telco Accelerator of the Telco Accelerator Quick Installation document. (https://wiki.hybris.com/display/release5/Quick+Installation)

 
4. Perform the steps in the Initialize the Telco Accelerator of the Telco Accelerator Quick Installation document. (https://wiki.hybris.com/display/release5/Quick+Installation)

 
5. In the command prompt, navigate to the {E&M_HOME} /source.

6. Run the mvn install -DskipTests -Dmaven.repo.local=..\..\hybris-dependencies  -o command from within the directory. Running this command results in creating a  .war  file. You can find the .war file in the {E&M_HOME}/source/entitlements/entitlements-web/target directory.
 
7. Navigate to the {HYBRIS_HOME}/hybris/config folder and open the localextensions.xml file.
 
8. In the localextensions.xml file, ensure that you have updated the correct path to the generated .war file inside the <extensions> tags. Your webapp entry in the localextensions.xml file should look as follows: 


	<extensions>
	<webapp contextroot="entitlements-web" path="{E&M_HOME}/source/entitlements/entitlements-web/target/entitlements-web.war" />
	</extensions>

9. Navigate to the {HYBRIS_HOME} /hybris-ems/sample-configs and copy all files to the {HYBRIS_HOME} /hybris/bin/platform/tomcat/lib folder. Edit these files if you want to change any of the properties. The following list the files in the sample-configs folder:

	*com.hybris.services.entitlements_entitlements-web-logback.xml
	*com.hybris.services.entitlements_entitlements-web.properties
	*README.txt

10. Follow the instructions in the Starting the Entitlement & Metering Service section below.

Starting the Entitlement & Metering Service
-------------------------------------------

Starting the hybris Telco Accelerator
-------------------------------------
To perform this, follow the steps in the Start the hybris Server section of the Telco Accelerator Quick Installation document. ((https://wiki.hybris.com/display/release5/Quick+Installation)
 
Initializing the Entitlement & Metering Service
-----------------------------------------------
To create an initial tenant and tenant data, follow the steps below:

1. On the machine where the Entitlement & Metering Service is installed, open the main Init-App console, for example: http://localhost:9001/entitlements-web/init-app-web/console/main. The Init-App Web Console displays.

2. In the Init-App Web Console, click the Initialize System button. When the system initialization is finished successfully, you receive the message Server Returned Response: ok!

3. In the Tenant Name field, type in single as the tenant name and tick the Project data checkbox.

4. Click the Initialize Schema button. When the schema initialization is finished successfully, you receive the message Server Returned Response: ok!

Accessing the Entitlement & Metering Service User Interface
------------------------------------------------------------
1. In your browser, go to the http://localhost:9001/emsui/ address.
 
2. The user interface is displayed. For more information, see the emsui Extension - Technical Guide and Using Entitlement & Metering Service User Interface documents.
 

Accessing the Documentation
---------------------------
You can find the following documentation in the {E&M_HOME} directory:

* Java API Documentation: {E&M_HOME}/documentation/apidocs/enunciate/index.html 
* REST Documentation: {E&M_HOME}/documentation/apidocs/entitlements-web/enunciate/index.html
