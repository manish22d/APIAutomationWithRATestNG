package com.RA.httpMethods;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

/**
 * Class used for request manipulation
 * 
 * @author manish
 *
 */
public class Request {
	public String requestPayload;

	/**
	 * setter method for requestPAyload
	 * 
	 * @param requestPayload
	 */
	public void setRequestPayload(String requestPayload) {
		this.requestPayload = requestPayload;
	}

	/**
	 * return request as string
	 * 
	 * @return
	 */
	public String getRequestPayload() {
		return requestPayload;
	}

	/**
	 * Convenience method to update value in json using JsonPath
	 * 
	 * @param path
	 * @param value
	 */
	public void updateJsonRequest(String path, Object value) {
		DocumentContext jsonContext = JsonPath.parse(requestPayload);
		jsonContext.set(path, value);
		setRequestPayload(jsonContext.jsonString());
	}

	/**
	 * Convenience method to delete value in json using JsonPath
	 * 
	 * @param path
	 */
	public void deleteNodeInJsonRequest(String path) {
		DocumentContext jsonContext = JsonPath.parse(requestPayload);
		jsonContext.delete(path);
		setRequestPayload(jsonContext.jsonString());
	}

	/**
	 * Convenience method to add a node with value in json using JsonPath
	 * 
	 * @param path
	 * @param key
	 * @param value
	 */
	public void addNodeInJsonRequest(String path, String key, Object value) {
		DocumentContext jsonContext = JsonPath.parse(requestPayload);
		jsonContext.put(path, key, value);
		setRequestPayload(jsonContext.jsonString());
	}

}
