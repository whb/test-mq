#!/bin/sh
#Usage: server [activemq|hornetq] config_name

usage() {
  echo "=================================="
  echo "Usage: $0 activemq  config_name"
  echo "       OR"
  echo "Usage: $0 hornetq   config_name"
  echo "=================================="
  exit 1
}

[ "$1" = "activemq" ] || [ "$1" = "hornetq" ] || usage


APP_HOME=~/test-mq
if [ "$1" = "activemq" ]; then
  MQ_OPTS="-Dactivemq.base=$APP_HOME/target/activemq -Dlogfile=$APP_HOME/target/activemq/data/activemq.log"
else
  MQ_OPTS="-Ddata.dir=$APP_HOME/target/hornetq/data -Dlogfile=$APP_HOME/target/hornetq/data/hornetq.log"
fi

FILE_DIR=config/server/$1/$2
FILE=boot.xml
CP=$FILE_DIR:$APP_HOME/lib/*:$APP_HOME/target/dependency/*:$APP_HOME/target/classes
JAVA_OPTS=-DwaitForShutdown=true
JVM_ARGS="-XX:+UseParallelGC -XX:+AggressiveOpts -XX:+UseFastAccessorMethods -Xms256M -Xmx256M"
#echo java $JVM_ARGS $JAVA_OPTS $MQ_OPTS -cp $CP com.sitechasia.mq.bootstrap.SpringContainer "$FILE_DIR/$FILE"
java $JVM_ARGS $JAVA_OPTS $MQ_OPTS -cp $CP com.sitechasia.mq.bootstrap.SpringContainer "$FILE_DIR/$FILE"

