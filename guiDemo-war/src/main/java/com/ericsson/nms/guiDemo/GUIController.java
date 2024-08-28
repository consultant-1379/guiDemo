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
package com.ericsson.nms.guiDemo;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.naming.InitialContext;

import com.ericsson.oss.services.pm.service.api.*;

@ManagedBean
@SessionScoped
public class GUIController implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3886602945167976010L;
	
	private String fileName;
	private String sourceNodAddress;
	private String sourceDirectory;
	private String newName;
	private String destinationNodAddress;
	private String destinationDirectory;
	
	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}
	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(final String fileName) {
		this.fileName = fileName;
	}
	/**
	 * @return the sourceNodAddress
	 */
	public String getSourceNodAddress() {
		return sourceNodAddress;
	}
	/**
	 * @param sourceNodAddress the sourceNodAddress to set
	 */
	public void setSourceNodAddress(final String sourceNodAddress) {
		this.sourceNodAddress = sourceNodAddress;
	}
	/**
	 * @return the sourceDirectory
	 */
	public String getSourceDirectory() {
		return sourceDirectory;
	}
	/**
	 * @param sourceDirectory the sourceDirectory to set
	 */
	public void setSourceDirectory(final String sourceDirectory) {
		this.sourceDirectory = sourceDirectory;
	}
	/**
	 * @return the newName
	 */
	public String getNewName() {
		return newName;
	}
	/**
	 * @param newName the newName to set
	 */
	public void setNewName(final String newName) {
		this.newName = newName;
	}
	/**
	 * @return the destinationNodAddress
	 */
	public String getDestinationNodAddress() {
		return destinationNodAddress;
	}
	/**
	 * @param destinationNodAddress the destinationNodAddress to set
	 */
	public void setDestinationNodAddress(final String destinationNodAddress) {
		this.destinationNodAddress = destinationNodAddress;
	}
	/**
	 * @return the destinationDirectory
	 */
	public String getDestinationDirectory() {
		return destinationDirectory;
	}
	/**
	 * @param destinationDirectory the destinationDirectory to set
	 */
	public void setDestinationDirectory(final String destinationDirectory) {
		this.destinationDirectory = destinationDirectory;
	}
	
	public void collectFiles() {
		try {
			final SingleFileTransferDetails sftd = new SingleFileTransferDetails(
					"1", this.fileName, this.sourceDirectory, this.newName,
					this.destinationDirectory);
			final List<SingleFileTransferDetails> ls = new LinkedList<SingleFileTransferDetails>();
			ls.add(sftd);
			final FileCollectionDetails fcd = new FileCollectionDetails(
					this.destinationNodAddress, ls);

			InitialContext ic = new InitialContext();
			FileCollectionServiceRemote fcsl = (FileCollectionServiceRemote) ic
					.lookup("ejb:PMService/PMService-ejb/FileCollectionServiceImpl!com.ericsson.oss.services.pm.service.api.FileCollectionServiceRemote");
			fcsl.collectFiles(fcd);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}	
}
