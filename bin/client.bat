@echo off
@rem Usage client [activemq|hornetq] <spring_file.xml>

set APP_HOME=%~dp0..

if activemq==%1 (
	set CONFIG_DIR=config\client\activemq
) else (
	set CONFIG_DIR=config\client\hornetq
)

set FILE_DIR=config\client
set FILE=%2
set CP=%CONFIG_DIR%;%FILE_DIR%;%APP_HOME%\lib\*;%APP_HOME%\target\dependency\*;%APP_HOME%\target\classes;

echo java %JAVA_OPTS% -cp %CP% com.sitechasia.mq.bootstrap.SpringContainer "%FILE_DIR%\%FILE%"
java %JAVA_OPTS% -cp %CP% com.sitechasia.mq.bootstrap.SpringContainer "%FILE_DIR%\%FILE%"
