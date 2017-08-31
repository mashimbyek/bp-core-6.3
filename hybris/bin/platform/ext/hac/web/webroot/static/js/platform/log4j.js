$(function() {
	$('#loggers').dataTable({
		"iDisplayLength" : 50
	});
	
	$("#configureLogLevelButton").live('click', function(e) {
		var loggerName = $("#loggerToConfigure").val();
		var levelName = $("#logLevelToSet").val();
		
		configureLogger(loggerName, levelName, function() {
			window.location.reload(true);
		});
	});
	
	$('.loggerLevels').live('change', function() {
		var loggerName = $(this).attr('data-loggerName');
		var levelName = $(this).val();
	
		configureLogger(loggerName, levelName);
	});
	
	function configureLogger(loggerName, levelName, myCallback) {
		var url = $('#loggers').attr('data-changeLoggerLevelUrl');
		debug.log("loggerName -> " + loggerName);
		debug.log("levelName -> " + levelName);
        var token = $("meta[name='_csrf']").attr("content");

        $.ajax({
			url : url,
			type : 'POST',
			data: 'loggerName=' + loggerName + "&levelName=" + levelName,
			headers : {
				'Accept' : 'application/json',
                'X-CSRF-TOKEN' : token
			},
			success : function(data) {
				if(typeof myCallback == 'function') { 
					myCallback(); 
				}
				if (data.changingResult) {
					hac.global.notify("Log level for logger " + data.loggerName + " changed to " + data.levelName);
					$.each($('.loggerLevels'), function(index, select) {
						var currentLoggerName = $(select).attr('data-loggerName');
						var currentLevel = data.loggers[currentLoggerName];
						$(select).val(currentLevel);
					});
				} else {
					hac.global.notify("Failed to change level for logger " + data.loggerName);
				}
			},
			error : hac.global.err
		});
	}
})