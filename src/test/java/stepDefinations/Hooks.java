package stepDefinations;

import java.io.IOException;

import io.cucumber.java.Before;
import io.cucumber.plugin.event.StepDefinition;

public class Hooks {

	@Before("@DeletePlace")
	public void beforeScenario() throws IOException
	{
		//Write a code that will give us a place Id
		//execute this code , only when placeid is null
		
		stepDefination m = new stepDefination();
		
		if (stepDefination.place_id ==null) {
			m.add_place_payload_with("deepak", "French", "Asia");
			m.user_calls_using_http_request("AddPlaceAPI", "POST");
			m.verify_place_id_created_maps_to_using("deepak", "getPlaceAPI");	
		}
	}
}
