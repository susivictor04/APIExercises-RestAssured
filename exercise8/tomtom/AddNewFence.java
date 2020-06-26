package exercise8.tomtom;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class AddNewFence extends BaseRequest {

	@Test(dependsOnMethods= {"exercise8.tomtom.AddNewProject.addNewProject"})
	public void addFence() {

		//		api.tomtom.com/geofencing/1/projects/25256d0b-812c-4839-a34a-1209aa3ebb89/fence?key=JqFqv3YucymhfIGvYAoveZz9YEzHUIun&adminKey=Y6LRo3mx5FzdFIy8PiJRJTKsUZ6kN0ZVQV2S1KabA62oYFz9
//		https://api.tomtom.com/geofencing/1/projects/4ea5b208-0bd1-4e39-be6a-cc6f4a1fc5e4fence?key=JqFqv3YucymhfIGvYAoveZz9YEzHUIun&adminKey=FZTOBwn13ZZKff9XdxQ7hTOiTa0JxjM8oWpN5vK8hUeEkOU8

		Response  response  =  RestAssured
				.given()
				.headers(HeaderMap)
				.log().all()
				.queryParam("key",  key)
				.queryParam("adminKey", adminKey)
				.body("{\r\n" + 
						"    \"name\": \"fence3\",\r\n" + 
						"    \"type\": \"Feature\",\r\n" + 
						"    \"geometry\": {\r\n" + 
						"        \"radius\": 75,\r\n" + 
						"        \"type\": \"Point\",\r\n"+ 
						"        \"shapeType\": \"Circle\",\r\n" + 
						"        \"coordinates\": [\r\n" + 
						"            20.45,\r\n" + 
						"            45.32\r\n" + 
						"        ]\r\n" + 
						"    },\r\n" + 
						"    \"properties\": {\r\n" + 
						"        \"key\": \"value\"\r\n" + 
						"    }\r\n" + 
						"}")
				.post("projects/"+ projectID+ "/fence");
		
		response.prettyPrint();
		

		JsonPath jsonPath = response.jsonPath();

		fenceID = jsonPath.get("id");

		System.out.println("New Fence Created ID : " + fenceID);
		System.out.println("New Fence Created Name: " + jsonPath.get("name"));


	}

}
