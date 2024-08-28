/*------------------------------------------------------------------------------
 *******************************************************************************
 * COPYRIGHT Ericsson 2012
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *******************************************************************************
 *----------------------------------------------------------------------------*/
package com.ericsson.nms.guiDemo.ejb.service;

import java.util.Stack;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.*;
import javax.inject.Inject;

import org.slf4j.Logger;

import com.ericsson.nms.guiDemo.api.ResultCollectorLocal;

@Singleton
@Startup
@Local(ResultCollectorLocal.class)
public class ResultsCollector implements ResultCollectorLocal {

	@Inject
	private Logger logger;

	private Stack<String> received;
	private Stack<String> success;
	private Stack<String> nonSupported;
	private Stack<String> failed;

	private Integer suc;
	private Integer nonS;
	private Integer fail;
	private Integer send;

	@PostConstruct
	void onServiceStart() {
		logger.info("Starting service");
		suc = 0;
		nonS = 0;
		fail = 0;
		send = 0;
		success = new Stack<String>();
		initialDatafill(success);
		nonSupported = new Stack<String>();
		initialDatafill(nonSupported);
		failed = new Stack<String>();
		initialDatafill(failed);
		received = new Stack<String>();
		initialDatafill(received);
	}

	/**
	 * @param success2
	 */
	private void initialDatafill(Stack<String> stack) {
		for (int i = 0; i < 30; i++) {
			stack.add("0");
		}
	}

	@Override
	public Stack<String> generateSuccess() {
		return getFreshStack(success, suc);
	}

	@Override
	public Stack<String> generateNonSupported() {
		return getFreshStack(nonSupported, nonS);
	}

	@Override
	public Stack<String> generateFailure() {
		return getFreshStack(failed, fail);
	}

	@Override
	public Stack<String> generateReceived() {
		return getFreshStack(received, send);
	}

	@Override
	public void successReceived() {
		suc++;
	}

	@Override
	public void successSend() {
		send++;
	}

	private Stack<String> getFreshStack(Stack<String> stack, Integer value) {
		stack.removeElementAt(0);
		stack.add(value.toString());
		return stack;
	}

	@PreDestroy
	void onServiceStop() {
		this.logger.info("Stopping service");
	}

}
