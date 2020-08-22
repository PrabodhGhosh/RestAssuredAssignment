package com.assignment.rest.tests;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.assignment.rest.pojo.PostRequest;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class VerifyPostRequest {

	@Test

	/* Verify the posts by sending the body as String using POST request
	 * Verifications - 
	 * 1. Status Code 2. Response Schema by utilizing POJO class 3. Created post
	 * @author: Prabodh G
	 *  
	 */
	public void verifyCreatePosts() {
		
		PostRequest postRequest = new PostRequest();
		postRequest.setTitle("foo");
		postRequest.setBody("bar");
		postRequest.setUserId(1);

		RestAssured.basePath = "/posts";
		RequestSpecification request = RestAssured.given();
		Response response = request.header("Content-Type", "application/json").header("charset","UTF-8").body("{\r\n" + 
				"    \"title\":\"foo\",\r\n" + 
				"    \"body\":\"bar\",\r\n" + 
				"    \"userId\":1\r\n" + 
				"}").when().post();
		response.then().assertThat().statusCode(201);
		JsonPath json = new JsonPath(response.asString());
		Assert.assertEquals(json.getString("title"), "foo");
		Assert.assertEquals(json.getString("body"), "bar");
		Assert.assertEquals(Integer.parseInt(json.getString("userId")), 1);
		Assert.assertEquals(Integer.parseInt(json.getString("id")), 101);

		request.header("Content-Type", "application/json").header("charset","UTF-8").body(postRequest).when().post(RestAssured.baseURI + "/posts")
				.then().assertThat().body(matchesJsonSchemaInClasspath("createPosts.json"));

	}

}
