package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.Location;

public class TestDataBuild {

	
	public AddPlace addPlacePayload(String name, String language, String address) {
		
		AddPlace p = new AddPlace();
		p.setAccuracy(50);
		p.setAddress(address);
		p.setLanguage(language);
		p.setPhoneNumber("120316");
		p.setWebsite("Google");
		p.setName(name);
		List<String> myList = new ArrayList<>();
		myList.add("shop");
		p.setTypes(myList);

		Location loc = new Location();
		loc.setLat(200.84);
		loc.setLng(500.21);

		p.setLocation(loc);
		
		return p;

	}
	
	public String DeletePayload(String placeId)
	{
		return "{\r\n    \"place_id\": \""+placeId+"\"\r\n}";
	}
}
