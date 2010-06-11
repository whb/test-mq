#!/bin/sh
#Usage: client [activemq|hornetq] <spring_file.xml>

usage() {
  echo "=================================="
  echo "Usage: $0 activemq <spring_file.xml>"
  echo "       OR"
  echo "Usage: $0 hornetq <spring_file.xml>"
  echo "=================================="
  exit 1
}

[ "$1" = "activemq" ] || [ "$1" = "hornetq" ] || usage

APP_HOME=~/test-mq
CONFIG_DIR=config/client/$1
FILE_DIR=config/client
FILE=$2
CP=$CONFIG_DIR:$FILE_DIR:$APP_HOME/lib/*:$APP_HOME/target/dependency/*:$APP_HOME/target/classes
#echo java $JAVA_OPTS -cp $CP com.sitechasia.mq.bootstrap.SpringContainer "$FILE_DIR/$FILE"
java $JAVA_OPTS -cp $CP com.sitechasia.mq.bootstrap.SpringContainer "$FILE_DIR/$FILE"

