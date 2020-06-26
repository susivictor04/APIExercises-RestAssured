package exercise8.tomtom;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class AddNewProject extends BaseRequest {

	@Test(dependsOnMethods= {"exercise8.tomtom.RegenerateAdminKey.regenerateAdminKey"})
	public void addNewProject() {

		//		https://api.tomtom.com/geofencing/1/projects/project?key=JqFqv3YucymhfIGvYAoveZz9YEzHUIun&adminKey=YPwE4bCLmIMVGmOfSsk8zFqWDgQjOq3OG4zaUCezlh7Uu0SW


		Response  response  =  RestAssured
				.given()
				.headers(HeaderMap)
				.log().all()
				.queryParam("key",  key)
				.queryParam("adminKey", adminKey)
				.body("{\r\n" + 
						"  \"name\": \"MySeventh_TomTom_Project\"\r\n" + 
						"}")
				.post("projects/project");

		response.prettyPrint();

		JsonPath jsonPath = response.jsonPath();


		projectID = jsonPath.get("id");

		System.out.println("Project ID :" + projectID);



	}

}
