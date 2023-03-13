package resources;

//enum is a special class in Java which has collection of Constants or methods
public enum APIResources {
	
	AddPlaceAPI("/maps/api/place/add/json"),
	getPlaceAPI("/maps/api/place/get/json"),
	deletePlaceAPI("/maps/api/place/delete/json");

	private String resource;

	private APIResources(String resource) {
		this.resource = resource;
	}
	
	public String getResource()
	{
		return resource;
	}
	
}
