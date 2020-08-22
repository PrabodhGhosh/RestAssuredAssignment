package com.assignment.rest.tests;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class VerifyDeleteRequest {


	@Test
	/* Verify deleting a Post using DELETE request
	 * Verifications - 
	 * 1. Status Code 2. Response Schema is empty
	 * @author: Prabodh G
	 *  
	 */
	public void verifyDeletePosts() {

		RestAssured.basePath = "/posts/1";
		RequestSpecification request = RestAssured.given();
		Response response = request.header("Content-Type", "application/json").header("charset", "UTF-8").when().delete();
		response.then().assertThat().statusCode(200).body("isEmpty()", Matchers.is(true));

	}

}
