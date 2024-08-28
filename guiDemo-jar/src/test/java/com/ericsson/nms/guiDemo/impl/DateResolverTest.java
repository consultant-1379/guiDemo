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
package com.ericsson.nms.guiDemo.impl;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.ericsson.nms.guiDemo.impl.DateResolver;
/*
 * Junit
 */
public class DateResolverTest {

	private DateResolver resolver;

	@Before
	public void setUp() {
		this.resolver = new DateResolver();
	}

	@Test
	public void test() {
		Assert.assertNotNull("Resolver should not be null", this.resolver);
		Assert.assertNotNull("Resolved date should not be null",
				this.resolver.resolveCurrentDate());
	}

}
