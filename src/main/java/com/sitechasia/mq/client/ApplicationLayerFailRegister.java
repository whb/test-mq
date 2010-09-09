package com.sitechasia.mq.client;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.ExceptionListener;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;

public class ApplicationLayerFailRegister {
	private Connection connection;
	private Session session;
	private MessageConsumer consumer;

	public ApplicationLayerFailRegister(ConnectionFactory cf,
			Destination destination, MessageListener listener) {
		try {
			connection = cf.createConnection();
			connection.setExceptionListener(new ExampleListener());
			session = connection.createSession(false,
					Session.CLIENT_ACKNOWLEDGE);
			consumer = session.createConsumer(destination);
			consumer.setMessageListener(listener);
			connection.start();
		} catch (JMSException e) {
			throw new RuntimeException(e);
		}
	}

	public void close() throws JMSException {
		connection.stop();
		if (consumer != null) {
			consumer.close();
		}
		if (session != null) {
			session.close();
		}
		if (connection != null) {
			connection.close();
		}
	}

	private class ExampleListener implements ExceptionListener {
		public void onException(final JMSException exception) {
			try {
				System.out
						.println("Connection failure has been detected on a the client.");

			} catch (Exception e) {
				System.err.println("Failed to handle failover");
				e.printStackTrace();
			}
		}
	}
}
