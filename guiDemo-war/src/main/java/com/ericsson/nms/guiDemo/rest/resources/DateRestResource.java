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

package com.ericsson.nms.guiDemo.rest.resources;

import java.util.Iterator;
import java.util.Stack;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;

import com.ericsson.nms.guiDemo.api.ResultCollectorLocal;
/*
 * Rest Services 
 *  
 * Resources are served relative to the servlet path specified in the {@link ApplicationPath}
 * annotation.
 * 
 */
@Path("/resource")
@RequestScoped
public class DateRestResource {

	@Inject
	private Logger logger;

	@EJB
	private ResultCollectorLocal results;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
	@Path("getTransfered")
	public String getTransfered() {
		logger.debug("Check for transfered files");
		Stack<String> data = results.generateSuccess();
		return data.lastElement();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("getUnsupported")
	public String getUnsupported() {
		logger.debug("Check for non supported files");
		Stack<String> data = results.generateNonSupported();
		return data.lastElement();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("getFailed")
	public String getFailed() {
		logger.debug("Check for failed ones");
		Stack<String> data = results.generateFailure();
		return data.lastElement();
    }

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("getReceived")
	public String getReceived() {
		logger.debug("Check for Received ones");
		Stack<String> data = results.generateReceived();
		return data.lastElement();
	}
    
	/**
	 * @return
	 */
	private String jsonCreator(Stack<String> data) {
		StringBuilder sb = new StringBuilder();
		sb.append("{\"cols\": [{\"id\":\"\",\"label\":\"\",\"pattern\":\"\",\"type\":\"string\"},{\"id\":\"\",\"label\":\"Processed Elements\",\"pattern\":\"\",\"type\":\"number\"}],\"rows\": [");
		String temp = "";
		for (Iterator<String> i = data.iterator(); i.hasNext();) {
			String elemnts = ",{\"c\":[{\"v\":\"\",\"f\":null},{\"v\":"
					+ i.next() + ",\"f\":null}]}";
			temp += elemnts;
		}
		sb.append(temp.substring(1));
		sb.append("]}");
		return sb.toString();
	}
    
}