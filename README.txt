hornetq-spring-examples
=========================
The origin example is created by Zemian Deng (saltnlight5 at mail dot com).

This project provide examples on how use HornetQ and Spring framework
together.

The HornetQ server can be bootstrap from a Spring container without
the JBossMC, which is to replace the hornetq-beans.xml.

And Now

We have extended the example to boostrap both hornetq and activemq.

How to build it from source
===========================
To compile example source
C:\mq-test> mvn compile


How to run the MQ Server
=============================
# Do this at least once:
# Copy all the needed jars into target/dependency directory
C:\mq-test> mvn dependency:copy-dependencies

# Now you may start the server
bin\server.bat activemq default

bin\server.bat hornetq async
bin\server.bat hornetq no-persistence
bin\server.bat hornetq default
bin\server.bat hornetq no-durable-queue
bin\server.bat hornetq no-consumer-buffer
bin\server.bat hornetq redelivery
bin\server.bat hornetq master-slave\slave
bin\server.bat hornetq master-slave\master

How to run the client examples
=============================
# Do this at least once:
# Copy all the needed jars into target/dependency directory
C:\mq-test> mvn dependency:copy-dependencies

# Now you may start any example like this:
set JAVA_OPTS=-Dcount=100000
set JAVA_OPTS=-Dms=1000
set JAVA_OPTS=-DwaitForShutdown=true

bin\client.bat activemq jndiConnectionExample.xml
bin\client.bat activemq jmsQueueSender.xml
bin\client.bat activemq jmsQueueReceiver.xml

bin\client.bat hornetq jndiConnectionExample.xml
bin\client.bat hornetq jmsQueueSender.xml
bin\client.bat hornetq jmsQueueReceiver.xml
bin\client.bat hornetq mapSender.xml
bin\client.bat hornetq memoryQueueSender.xml
bin\client.bat hornetq memoryQueueReceiver.xml
bin\client.bat hornetq jmsNoPersistenceSender.xml
bin\client.bat hornetq serialReceiver.xml
bin\client.bat hornetq groupMessageSender.xml
bin\client.bat hornetq serialReceiver.xml
bin\client.bat hornetq redeliveryListener.xml
bin\client.bat hornetq failoverSender.xml