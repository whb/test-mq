package com.sitechasia.mq.client;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;

public class TransactionListenerRegister {
	private Connection connection;
	private Session session;
	private MessageConsumer consumer;

	public TransactionListenerRegister(ConnectionFactory cf,
			Destination destination, RedeliveryListener listener) {
		try {
			connection = cf.createConnection();
			session = connection
					.createSession(true, Session.SESSION_TRANSACTED);
			consumer = session.createConsumer(destination);
			listener.setSession(session);
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
}
