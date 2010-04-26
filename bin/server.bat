@echo off
@rem Usage server [activemq|hornetq]

set APP_HOME=%~dp0..

if activemq==%1 (
	set FILE_DIR=config\server\activemq
	set FILE=boot.xml
	set SERVER_OPTS=-Dactivemq.base=%APP_HOME%\target\activemq
) else (
	set FILE_DIR=config\server\hornetq
	set FILE=boot.xml
	set SERVER_OPTS=-Dhornetq.data.dir=%APP_HOME%\target\hornetq\data
)

set CP=%FILE_DIR%;%APP_HOME%\lib\*;%APP_HOME%\target\dependency\*;%APP_HOME%\target\classes;
set JAVA_OPTS=-DwaitForShutdown=true

echo java %JAVA_OPTS% %SERVER_OPTS% -cp %CP% com.sitechasia.mq.bootstrap.SpringContainer "%FILE_DIR%\%FILE%"
java %JAVA_OPTS% %SERVER_OPTS% -cp %CP% com.sitechasia.mq.bootstrap.SpringContainer "%FILE_DIR%\%FILE%"
