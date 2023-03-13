package stepDefinations;

import static io.restassured.RestAssured.given;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlace;
import pojo.Location;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Util;

public class stepDefination extends Util {

	RequestSpecification res;
	ResponseSpecification resspec;
	Response response;
	TestDataBuild data = new TestDataBuild();
	public static String place_id;


	@Given("Add Place Payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String language, String address) throws IOException {
		// Write code here that turns the phrase above into concrete actions
		res = given().spec(requestSpecification()).body(data.addPlacePayload(name, language, address));
	}

	@When("user calls {string} using {string} http request")
	public void user_calls_using_http_request(String resource, String method) {
		// Write code here that turns the phrase above into concrete actions

		// Write enum name and use it
		APIResources resourceApi = APIResources.valueOf(resource);
		System.out.println(resourceApi.getResource());
		resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

		if (method.equalsIgnoreCase("POST")) {

			response = res.when().post(resourceApi.getResource());

		} else if (method.equalsIgnoreCase("GET")) {
			response = res.when().post(resourceApi.getResource());
		}
	}

	@Then("the API call got success with status code {int}")
	public void the_api_call_got_success_with_status_code(Integer int1) {
		// Write code here that turns the phrase above into concrete actions
		assertEquals(response.getStatusCode(), 200);

	}

	@And("{string} in response body is {string}")
	public void in_response_body_is(String key, String value) {
		// Write code here that turns the phrase above into concrete actions
		
		assertEquals(getJsonPath(response,key),value);
	}
	
	@And("verify place_id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expectedName, String resourceName) throws IOException {
	    // Write code here that turns the phrase above into concrete actions
	    
		// prepare req spec
		place_id = getJsonPath(response, "place_id");
		res = given().spec(requestSpecification()).queryParam("place_id", place_id);
		user_calls_using_http_request(resourceName,"GET");
		
		//call the getjsonPath method again to get the name from the response body to verify
		String actualName = getJsonPath(response, "name");
		assertEquals(actualName, expectedName);
		
	}
	
	@Given("DeletePlace Payload")
	public void delete_place_payload() throws IOException {
	    // Write code here that turns the phrase above into concrete actions
		res = given().spec(requestSpecification().body(data.DeletePayload(place_id)));
		
	}

}
