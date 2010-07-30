package com.sitechasia.mq.client;

import java.lang.management.ManagementFactory;
import java.util.Date;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SerialReceiver {
	private static Log log = LogFactory.getLog(SerialReceiver.class);

	private ConnectionFactory connectionFactory;
	private Destination destination;
	private long count = Long.parseLong(System.getProperty("count", "1000"));
	private long ms = Long.parseLong(System.getProperty("ms", "0"));

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
			String pName = ManagementFactory.getRuntimeMXBean().getName() ;
			connection = connectionFactory.createConnection();
			log.info(pName + ":Got a connection : " + connection.getClass());
			log.info(pName + ":Got a destination : " + destination.getClass());
			Session session = connection.createSession(false,
					Session.CLIENT_ACKNOWLEDGE);
			MessageConsumer receiver = session.createConsumer(destination);

			connection.start();
			Date begin = new Date();
			for (int i = 0; i < count; i++) {
				TextMessage message = (TextMessage)receiver.receive();
				log.trace(pName + ": Receiving msg: " + message.getText());
				Thread.sleep(ms);
				message.acknowledge();
			}
			Date end = new Date();

			double seconds = (double)(end.getTime() - begin.getTime()) / 1000;
			double countPersecond = count / seconds;
			log.info("====================================");
			log.info("Receiver " + count + " messages in : " + seconds
					+ " seconds");
			log.info("messages/sec : " + countPersecond);
			log.info("====================================");
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
