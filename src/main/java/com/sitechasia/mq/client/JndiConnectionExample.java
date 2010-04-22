package com.sitechasia.mq.client;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.naming.Context;
import javax.naming.InitialContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class JndiConnectionExample {
	private static Log log = LogFactory.getLog(JndiConnectionExample.class);
	
	public void start() {
		log.trace("Starting Example.");
		
		Context context = null;
		Connection connection = null;
		try {
			log.info("Getting ConnectionFactory.");
			context = new InitialContext();
			ConnectionFactory cf = (ConnectionFactory)context.lookup("ConnectionFactory");
			connection = cf.createConnection();
			
			log.info("Got a connection : " + connection.getClass());
		} catch (Exception e) {
			throw new RuntimeException(e);			
		} finally {
			if (context != null) {
				try {
					context.close();
				} catch (Exception e) {
					log.error("Failed to close naming context.", e);
					// Do not re throw exception here so resource below can be clean up as well.
				}
			}
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
