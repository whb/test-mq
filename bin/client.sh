#!/bin/sh
#Usage: client [activemq|hornetq] <spring_file.xml>


APP_HOME=/usr/local/test-mq
CONFIG_DIR=config/client/horneq
FILE_DIR=config/client
FILE=jmsQueueSender.xml
CP=$CONFIG_DIR:$FILE_DIR:$APP_HOME/lib/*:$APP_HOME/target/dependency/*:$APP_HOME/target/classes
echo java $JAVA_OPTS -cp $CP com.sitechasia.mq.bootstrap.SpringContainer "$FILE_DIR/$FILE"

