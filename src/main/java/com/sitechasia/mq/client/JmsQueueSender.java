package com.sitechasia.mq.client;

import java.util.Date;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class JmsQueueSender {
	private static Log log = LogFactory.getLog(JmsQueueSender.class);

	private ConnectionFactory connectionFactory;
	private Destination destination;

	public void setConnectionFactory(ConnectionFactory cf) {
		this.connectionFactory = cf;
	}
	
	public void setDestination(Destination destination) {
		this.destination = destination;
	}



	public void start() {
		log.trace("Starting Example.");
		sendMessage();
	}

	private void sendMessage() {
		Connection connection = null;
		try {
			connection = connectionFactory.createConnection();
			log.info("Got a connection : " + connection.getClass());
			log.info("Got a destination : " + destination.getClass());
			Session session = connection.createSession(false,
					Session.AUTO_ACKNOWLEDGE);
			MessageProducer producer = session.createProducer(destination); 
			connection.start();
			
			Date begin = new Date();
			for (int i = 0; i < 1000; i++) {
				TextMessage msg = session.createTextMessage("Message" + i);
				//log.trace("Sending msg: " + msg);	
				producer.send(msg);	
			}
			Date end = new Date();
			
			long seconds = (end.getTime() - begin.getTime())/1000;
			long countPersecond = 1000/seconds;
			log.info("Sending 1000 message in: " + seconds + "seconds");
			log.info("messages/sec: " + countPersecond );
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (JMSException e) {
					throw new RuntimeException(e);
				}
			}
		}
	}

	public void stop() {
		log.trace("Exiting Example.");
	}
}
