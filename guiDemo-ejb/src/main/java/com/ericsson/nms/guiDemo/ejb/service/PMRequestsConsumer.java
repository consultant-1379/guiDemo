package com.ericsson.nms.guiDemo.ejb.service;

import javax.ejb.*;
import javax.jms.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ericsson.nms.guiDemo.api.ResultCollectorLocal;
import com.ericsson.oss.services.pm.service.api.SingleFileTransferDetails;

/**
 * Message-Driven Bean implementation class for: PMRequestsConsumer
 *
 */
@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "queue/JobsReceivedQueue") })
public class PMRequestsConsumer implements MessageListener {

	Logger log = LoggerFactory.getLogger(PMResultsConsumer.class
			.getCanonicalName());

	@EJB
	private ResultCollectorLocal collector;

	/**
	 * Default constructor.
	 */
	public PMRequestsConsumer() {
		log.debug("Initialization of PM request observer");
	}

	/**
	 * @see MessageListener#onMessage(Message)
	 */
	public void onMessage(Message message) {
		log.debug("Message has been sent to PMService");
		try {
			if (message instanceof ObjectMessage) {
				ObjectMessage objectMessage = (ObjectMessage) message;
				SingleFileTransferDetails response = (SingleFileTransferDetails) objectMessage
						.getObject();
				collector.successSend();
			}
		} catch (JMSException e) {
			log.error(e.getMessage());
		}
	}

}
