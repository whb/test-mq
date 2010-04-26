@echo off
@rem Usage: server [activemq|hornetq]

if "%1"=="activemq" goto :MAIN
if "%1"=="hornetq"  goto :MAIN

:USAGE
echo ====================================================
echo Usage: %0 activemq 
echo        OR
echo Usage: %0 hornetq
echo =====================================================
goto :EOF

:MAIN
set APP_HOME=%~dp0..

if activemq==%1 (
	set MQ_OPTS=-Dactivemq.base=%APP_HOME%\target\activemq
) else (
	set MQ_OPTS=-Dhornetq.data.dir=%APP_HOME%\target\hornetq\data
)
set FILE_DIR=config\server\%1
set FILE=boot.xml
set CP=%FILE_DIR%;%APP_HOME%\lib\*;%APP_HOME%\target\dependency\*;%APP_HOME%\target\classes;
set JAVA_OPTS=-DwaitForShutdown=true

java %JAVA_OPTS% %MQ_OPTS% -cp %CP% com.sitechasia.mq.bootstrap.SpringContainer "%FILE_DIR%\%FILE%"

:EOF