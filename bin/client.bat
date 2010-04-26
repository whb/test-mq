@echo off
@rem Usage: client [activemq|hornetq] <spring_file.xml>

if "%1"=="activemq" goto :MAIN
if "%1"=="hornetq"  goto :MAIN

:USAGE
echo ====================================================
echo Usage: %0 activemq spring_file.xml 
echo        OR
echo Usage: %0 hornetq spring_file.xml
echo =====================================================
goto :EOF

:MAIN
set APP_HOME=%~dp0..
set CONFIG_DIR=config\client\%1
set FILE_DIR=config\client
set FILE=%2
set CP=%CONFIG_DIR%;%FILE_DIR%;%APP_HOME%\lib\*;%APP_HOME%\target\dependency\*;%APP_HOME%\target\classes;
echo java %JAVA_OPTS% -cp %CP% com.sitechasia.mq.bootstrap.SpringContainer "%FILE_DIR%\%FILE%"
java %JAVA_OPTS% -cp %CP% com.sitechasia.mq.bootstrap.SpringContainer "%FILE_DIR%\%FILE%"

:EOF
