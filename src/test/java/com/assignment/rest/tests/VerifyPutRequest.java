package com.assignment.rest.tests;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class VerifyPutRequest {

	

	@SuppressWarnings("unchecked")
	@Test

	/* Verify update of record using PUT request (request body sent as JSON)
	 * Verifications - 
	 * 1. Status Code 2. Response Schema 3. Updated paramters of the Post
	 * @author: Prabodh G
	 *  
	 */
	public void verifyUpdatePosts() {

		RestAssured.basePath = "/posts/1";
		JSONObject requestParams = new JSONObject();
		requestParams.put("id", "1");
		requestParams.put("title", "abc");
		requestParams.put("body", "xyz");
		requestParams.put("userId", "1");

		RequestSpecification request = RestAssured.given();
		Response response = request.header("Content-Type", "application/json").header("charset", "UTF-8")
				.body(requestParams.toJSONString()).when().put();
		response.then().assertThat().statusCode(200);
		JsonPath json = new JsonPath(response.asString());
		Assert.assertEquals(json.getString("title"), "abc");
		Assert.assertEquals(json.getString("body"), "xyz");
		Assert.assertEquals(Integer.parseInt(json.getString("userId")), 1);
		Assert.assertEquals(Integer.parseInt(json.getString("id")), 1);

		request.header("Content-Type", "application/json").header("charset", "UTF-8").body(requestParams.toJSONString())
				.when().put(RestAssured.baseURI + "/posts/1").then().assertThat()
				.body(matchesJsonSchemaInClasspath("putPost.json"));

	}

}
