package com.RA.testBase;

import org.apache.log4j.Logger;

import com.RA.httpMethods.HttpHelper;

/**
 * This class is used to store instace all class which will be used in test
 * class
 * 
 * @author Manish
 *
 */

public class TestBase {

	HttpHelper httpHelper;
	public Logger log = Logger.getLogger(TestBase.class.getClass());

	public void initiateTest() {
		log.info("initialized baseClass");
		httpHelper = new HttpHelper();
	}

	public void setAPIEndpoint(String endpoint) {
		log.info("Setting Endpoint to : " + endpoint);
		this.httpHelper.setAPIEndpoint(endpoint);
	}

	public void setHeader(String headerKey, String headerValue) {
		log.info("updating header  : " + headerKey + " with " + headerValue);
		this.httpHelper.setHeader(headerKey, headerValue);
	}

	public void updatePathParam(String paramName, String paramValue) {
		log.info("updating path param  : " + paramName + " to " + paramValue);
		this.httpHelper.updatePathParam(paramName, paramValue);
	}

	public void getResource() {
		this.httpHelper.getResource();
	}

	public void PostRequest(String requestPayload) {
		this.httpHelper.PostRequest(requestPayload);
	}

	public String getResponseString() {
		return this.httpHelper.getResponseString();
	}

	public int getResponseStatusCode() {
		return this.httpHelper.response.getStatusCode();
	}

}
