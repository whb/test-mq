package deng.hornetq.spring.examples.client;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.ProducerCallback;
import org.springframework.jms.core.SessionCallback;

public class JmsTemplateExample {
	private static Log log = LogFactory.getLog(JmsTemplateExample.class);
	

	private JmsTemplate jmsTemplate;
	
	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}
	
	public void start() {
		log.trace("Starting Example.");		

		log.info("Sending msg to ExampleQueue.");
		jmsTemplate.execute("ExampleQueue", new ProducerCallback<Object>() {
			@Override
			public Object doInJms(Session session, MessageProducer producer)
					throws JMSException {
				for (int i = 0; i < 10; i++) {
					TextMessage msg = session.createTextMessage("Message" + i);
					log.trace("Sending msg: " + msg);	
					producer.send(msg);	
				}
				return null;
			}
		});

		log.info("Receiving msg from ExampleQueue.");
		boolean startConn = true;
		jmsTemplate.execute(new SessionCallback<Object>() {
			@Override
			public Object doInJms(Session session) throws JMSException {
				Queue queue = session.createQueue("ExampleQueue");
				MessageConsumer consumer = session.createConsumer(queue);
				for (int i = 0; i < 10; i++) {
					Message msg = consumer.receive();
					log.trace("Received msg: " + msg);
				}
				return null;
			}
		}, startConn);
	}
	
	public void stop() {
		log.trace("Exiting Example.");
	}
}
