hornetq-spring-examples
=========================
This example is created by Zemian Deng (saltnlight5 at mail dot com).

This project provide examples on how use HornetQ and Spring framework
together.

The HornetQ server can be bootstrap from a Spring container without
the JBossMC, which is to replace the hornetq-beans.xml.


How to build it from source
===========================
To compile example source
C:\hornetq-spring-examples> mvn compile


How to run the HornetQ Server
=============================

# Do this at least once:
# Copy all the needed jars into target/dependency directory
C:\mq-test> mvn dependency:copy-dependencies

# Now you may start the server
set JAVA_OPTS=-DwaitForShutdown=true
bin\run-spring-container.bat config\server\hornetq spring.xml
bin\run-spring-container.bat config\server\activemq activemq-throughput.xml


How to run the client examples
=============================

# Do this at least once:
# Copy all the needed jars into target/dependency directory
C:\mq-test> mvn dependency:copy-dependencies

# Now you may start any example like this:

C:\mq-test> bin\run-spring-container.bat config\client jndiConnectionExample.xml

# Another example:
C:\mq-test> bin\run-spring-container.bat config\client jmsTemplateExample.xml


bin\run-spring-container.bat config\client jmsQueueSender.xml
bin\run-spring-container.bat config\client jmsQueueReceiver.xml

bin\server.bat activemq
bin\server.bat hornetq

bin\client.bat activemq jmsQueueSender.xml
bin\client.bat activemq jmsQueueReceiver.xml

bin\client.bat hornetq jmsQueueSender.xml
bin\client.bat hornetq jmsQueueReceiver.xml

