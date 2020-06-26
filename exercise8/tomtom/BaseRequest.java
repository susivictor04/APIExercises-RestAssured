package exercise8.tomtom;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.BeforeSuite;

import io.restassured.RestAssured;

public class BaseRequest {

	public static Map<String, String> HeaderMap = new HashMap<String, String>();
	public static String projectID, adminKey, fenceID;
	public static String key="JqFqv3YucymhfIGvYAoveZz9YEzHUIun";

	@BeforeSuite
	public void baseRequest() {

		RestAssured.baseURI = "https://api.tomtom.com/geofencing/1";
		HeaderMap.put("Content-Type","application/json");
		HeaderMap.put("Accept","application/json");

	}

}
