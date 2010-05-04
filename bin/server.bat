@echo off
@rem Usage: server [activemq|hornetq] config_name

if "%1"=="activemq" goto :MAIN
if "%1"=="hornetq"  goto :MAIN

:USAGE
echo ====================================================
echo Usage: %0 activemq config_name
echo        OR
echo Usage: %0 hornetq config_name
echo =====================================================
goto :EOF

:MAIN
set APP_HOME=%~dp0..

if activemq==%1 (
	set MQ_OPTS=-Dactivemq.base=%APP_HOME%\target\activemq
) else (
	set MQ_OPTS=-Dhornetq.data.dir=%APP_HOME%\target\hornetq\data
)
set FILE_DIR=config\server\%1\%2
set FILE=boot.xml
set CP=%FILE_DIR%;%APP_HOME%\lib\*;%APP_HOME%\target\dependency\*;%APP_HOME%\target\classes;
set JAVA_OPTS=-DwaitForShutdown=true

set JVM_ARGS=-XX:+UseParallelGC  -XX:+AggressiveOpts -XX:+UseFastAccessorMethods -Xms512M -Xmx1024M
java %JVM_ARGS% %JAVA_OPTS% %MQ_OPTS% -cp %CP% com.sitechasia.mq.bootstrap.SpringContainer "%FILE_DIR%\%FILE%"

:EOF