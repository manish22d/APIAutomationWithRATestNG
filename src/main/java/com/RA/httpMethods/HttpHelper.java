package com.RA.httpMethods;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.google.gson.JsonObject;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/**
 * 
 * Execution is suppose to start from httpHelper class.
 * 
 * @author Manish
 *
 */
public class HttpHelper {
	public RequestSpecification request;

	public String basePath;
	public String endPoint;
	public Response response;
	public Map<String, String> headers;
	public Map<String, String> queryParams;
	public Map<String, String> pathParams;
	public static Properties property;

	public HttpHelper() {
		headers = new HashMap<String, String>();
		queryParams = new HashMap<String, String>();
		pathParams = new HashMap<String, String>();
		RestAssured.baseURI = System.getenv("URL");
		RestAssured.basePath = "";

//		RestAssured.baseURI = System.getProperty("");
	}

	/**
	 * start point to make a request
	 */
	public void initiateRequest() {
		request = RestAssured.given();
		request.log().all();
		request.basePath(basePath);
		request.pathParams(pathParams);
		request.queryParams(queryParams);
		request.headers(headers);
	}

	/**
	 * This method is use to update endpoint
	 * 
	 * @param basePath
	 */
	public void setAPIEndpoint(String basePath) {
		this.basePath = basePath;
	}

	/**
	 * set header to request
	 * 
	 * @param headerKey
	 * @param headerValue
	 */
	public void setHeader(String headerKey, String headerValue) {
		headers.put(headerKey, headerValue);
	}

	/**
	 * update query param in map
	 * 
	 * @param queryParam
	 */
	public void setQueryParam(Map<String, String> queryParam) {
		queryParams.putAll(queryParam);
	}

	/**
	 * update path param
	 * 
	 * @param paramName
	 * @param paramValue
	 */
	public void updatePathParam(String paramName, String paramValue) {
		pathParams.put(paramName, paramValue);
	}

	/**
	 * returns response body
	 * 
	 * @return
	 */
	public String getResponseString() {
		return response.getBody().asString();
	}

	/**
	 * update request body
	 * 
	 * @param requestPayload
	 */
	public void updateRequest(JsonObject requestPayload) {
		request.body(requestPayload);
	}

	/**
	 * trigger Get Request
	 */
	public void getResource() {
		initiateRequest();
		response = request.get();
		response.then().log().all();
	}

	/**
	 * trigger POST Request
	 * 
	 * @param requestPayload
	 */
	public void PostRequest(String requestPayload) {
		initiateRequest();
		response = request.body(requestPayload).post();
		response.then().log().all();

	}

}
