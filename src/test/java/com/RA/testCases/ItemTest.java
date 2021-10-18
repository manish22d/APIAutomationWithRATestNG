package com.RA.testCases;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import java.lang.reflect.Method;

import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import com.RA.APIs.Auth;
import com.RA.Constant.Endpoint;
import com.RA.testBase.BaseClass;

public class ItemTest extends BaseClass {

	@Test
	public void testItemList(Method method) {
		extentTest = extent.startTest(method.getName());
		testBase.initiateTest();
		testBase.setAPIEndpoint(Endpoint.GET_ITEM_LIST);
		testBase.getResource();
		assertThat(testBase.getResponseStatusCode(), is(equalTo(HttpStatus.SC_OK)));
	}

	@Test
	public void testItemDetails(Method method) {
		extentTest = extent.startTest(method.getName());
		testBase.initiateTest();
		testBase.setAPIEndpoint(Endpoint.GET_ITEM_DETAILS);
		testBase.setHeader("Authorization", "JWT ".concat(Auth.getAccessToken()));
		testBase.updatePathParam("itemId", "test");
		testBase.getResource();
		assertThat(testBase.getResponseStatusCode(), is(equalTo(HttpStatus.SC_OK)));
	}
}
