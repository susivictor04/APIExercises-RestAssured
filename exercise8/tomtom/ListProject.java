package exercise8.tomtom;

import java.util.List;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ListProject extends BaseRequest {

	//	https://api.tomtom.com/geofencing/1/projects?key=JqFqv3YucymhfIGvYAoveZz9YEzHUIun

	@Test(dependsOnMethods= {"exercise8.tomtom.AddNewProject.addNewProject"})
	public void listProject() {

		Response  response  =  RestAssured
				.given()
				.headers(HeaderMap)
				.log().all()
				.param("key",  key)
				.get("projects");

		response.prettyPrint();

		JsonPath jsonPath = response.jsonPath();
		List<Object> listProjects = jsonPath.getList("projects");

		int size = listProjects.size();

		for (int i=0 ; i< size; i++) {

			//		response.projects[0].name;

			String pID =jsonPath.get("projects["+i+"].id");

			System.out.println(" Project ID :" + pID);
			System.out.println(" Project Name : " + jsonPath.get("projects["+i+"].name"));
			
			System.out.println("-----------------------------");

			if (pID.contains(projectID)) {

				System.out.println("Newly created project is available");
			}

		}

	}
}
