package com.sitechasia.mq.client;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class GroupMessageSender {
	private static Log log = LogFactory.getLog(GroupMessageSender.class);

	private ConnectionFactory connectionFactory;
	private Destination destination;
	private long count = Long.parseLong(System.getProperty("count", "1000"));

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
			for (int i = 0; i < count; i++) {
				TextMessage msg;
				if (i >= 700 && i <= 750) {
					msg = session.createTextMessage("Group" + i);
					msg.setStringProperty("JMSXGroupID", "Group-700_750");
				}
				else if (i >= 800 && i <= 900) {
					msg = session.createTextMessage("Group" + i);
					msg.setStringProperty("JMSXGroupID", "Group-800_900");
				}
				else if (i >= 990 && i <= 995) {
					msg = session.createTextMessage("Group" + i);
					msg.setStringProperty("JMSXGroupID", "Group-990_995");
				} else {
					msg = session.createTextMessage("Message" + i);
				}
				producer.send(msg);
				// log.trace("Sending msg: " + i);
			}
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
