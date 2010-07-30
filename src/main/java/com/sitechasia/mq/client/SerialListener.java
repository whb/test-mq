package com.sitechasia.mq.client;

import java.lang.management.ManagementFactory;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SerialListener implements MessageListener {
	private static Log log = LogFactory.getLog(SerialListener.class);
	private long ms = Long.parseLong(System.getProperty("ms", "0"));

	@Override
	public void onMessage(Message message) {
		String pName = ManagementFactory.getRuntimeMXBean().getName();
		if (message instanceof TextMessage) {
			TextMessage textMessage = (TextMessage) message;
			try {
				log.trace(pName + ": Receiving msg: " + textMessage.getText());
				Thread.sleep(ms);
				message.acknowledge();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}
}
