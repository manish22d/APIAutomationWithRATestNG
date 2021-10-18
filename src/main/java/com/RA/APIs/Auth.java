package com.RA.APIs;

import com.RA.Constant.Endpoint;
import com.RA.testBase.TestBase;
import com.RA.util.Files;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Implementation to call get Terminal details API
 * 
 * @author Manish
 *
 */
public class Auth {

	static JsonObject response;
	static TestBase testBase = new TestBase();
	static Files files = new Files();

	/**
	 * submit get request for get Terminal Details API
	 * 
	 * @param termialID
	 * @return 
	 */
	public static String getAccessToken() {
		testBase.initiateTest();
		testBase.setAPIEndpoint(Endpoint.GET_ACCESS_TOKEN);
		testBase.setHeader("Accept", "application/json");
		testBase.setHeader("Content-Type", "application/json");
		testBase.PostRequest(files.readJson("auth.json"));
		response = JsonParser.parseString(testBase.getResponseString()).getAsJsonObject();
		return response.get("access_token").getAsString();
	}

}
