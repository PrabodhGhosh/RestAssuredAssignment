package com.assignment.rest.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.assignment.rest.utils.Utils;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.*;

public class VerifyGetRequests {

	@BeforeSuite

	/* Set the base URI by reading the value from property file.
	 * The base URI value will remain unchanged in the entire suite execution.
	 * @author: Prabodh G
	 *  
	 */
	public void setUp() {
		String OS = Utils.getOS();
		if (OS.toLowerCase().contains("windows")) {
			String home = System.getProperty("user.dir");
			RestAssured.baseURI = (Utils.getValue(home + "\\src\\test\\resources\\endPoints\\getEndPoints.properties",
					"BaseURI"));
		}

		else if (OS.toLowerCase().contains("mac")) {
			String home = System.getProperty("user.dir");
			RestAssured.baseURI = (Utils.getValue(home + "/src/test/resources/endPoints/getEndPoints.properties",
					"BaseURI"));
		}
	
	}

	
	
	@Test
	/* Verify the posts using GET request
	 * Verifications - 
	 * 1. Status Code 2. Response Schema 3. Size of the response
	 * @author: Prabodh G
	 *  
	 */

	public void verifyGetPosts() {

		RestAssured.basePath = "/posts";
		RequestSpecification request = RestAssured.given().header("Content-Type", "application/json").header("charset","UTF-8");
		Response response = request.get();
		response.then().assertThat().statusCode(200);
		get(RestAssured.baseURI + "/posts").then().assertThat().body(matchesJsonSchemaInClasspath("getPosts.json"));
		JsonPath json = new JsonPath(response.asString());
		Assert.assertEquals(Integer.parseInt(json.get("userId.size").toString()), 100);

	}
	
	
	@Test

	/* Verify specific post using GET request
	 * Verifications - 
	 * 1. Status Code 2. Response Schema 3. Size of the response
	 * @author: Prabodh G
	 *  
	 */
	public void verifyGetSpecificPost() {

		RestAssured.basePath = "/posts/1";
		RequestSpecification request = RestAssured.given().header("Content-Type", "application/json").header("charset",
				"UTF-8");
		Response res = request.get();
		res.then().assertThat().statusCode(200);
		get(RestAssured.baseURI + "/posts/1").then().assertThat().body(matchesJsonSchemaInClasspath("getSpecificPost.json"));
		JsonPath json = new JsonPath(res.asString());
		Assert.assertEquals( json.get("userId").toString().length(), 1);
		Assert.assertEquals(Integer.parseUnsignedInt((json.get("id").toString())),1);
	}
	
	@Test

	/* Verify invalid post using GET request
	 * Verifications - 
	 * 1. Status Code 
	 * @author: Prabodh G
	 *  
	 */
	public void verifyInvalidPosts() {

		RestAssured.basePath = "invalidposts";
		RequestSpecification request = RestAssured.given().log().all().header("Content-Type", "application/json").header("charset",
				"UTF-8");
		Response res = request.get();
		res.then().assertThat().statusCode(404);
	}
	 

}
