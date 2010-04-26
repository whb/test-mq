@echo off
@rem Usage run-spring-container <path_to_file> <spring_file.xml>
@rem 
@rem We force user to give two argument because DOS doens't have basename command,
@rem and we needed the path to add to Java's classpath.
 
set APP_HOME=%~dp0..
set HORNETQ_DATA=%APP_HOME%\target\hornetq\data
set ACTIVEMQ_BASE=%APP_HOME%\target\activemq
set FILE_DIR=%1
set FILE=%2
set CP=%FILE_DIR%;%APP_HOME%\config\server;%APP_HOME%\lib\*;%APP_HOME%\target\dependency\*;%APP_HOME%\target\classes;

java %JAVA_OPTS% -Dhornetq.data.dir=%HORNETQ_DATA% -Dactivemq.base="%ACTIVEMQ_BASE%" -cp %CP% com.sitechasia.mq.bootstrap.SpringContainer "%FILE_DIR%\%FILE%"
