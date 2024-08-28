package com.ericsson.nms.guiDemo.ejb.service;

import javax.ejb.*;
import javax.jms.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ericsson.nms.guiDemo.api.ResultCollectorLocal;
import com.ericsson.oss.services.pm.service.events.FileCollectionResponse;

/**
 * Message-Driven Bean implementation class for: PMResultsConsumer
 *
 */
@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "queue/FileCollectionResponseQueue") })
public class PMResultsConsumer implements MessageListener {

	Logger log = LoggerFactory.getLogger(PMResultsConsumer.class
			.getCanonicalName());

	@EJB
	private ResultCollectorLocal collector;
	/**
	 * Default constructor.
	 */
	public PMResultsConsumer() {
		log.debug("Initialization of observer");
	}

	/**
	 * @see MessageListener#onMessage(Message)
	 */
	public void onMessage(Message message) {
		log.debug("Message has been receiveed");
		try {
		if (message instanceof ObjectMessage) {
			ObjectMessage objectMessage = (ObjectMessage) message;
				FileCollectionResponse response = (FileCollectionResponse) objectMessage
						.getObject();
				collector.successReceived();
			}
		} catch (JMSException e) {
			log.error(e.getMessage());
		}
	}

}
