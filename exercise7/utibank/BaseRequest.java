package exercise7.utibank;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.BeforeSuite;

import io.restassured.RestAssured;

public class BaseRequest {

	public static Map<String, String> HeaderMap = new HashMap<String, String>();
	public static String idLogin, userIdLogin,createUserId,emailID,createdQuoteID ;
	@BeforeSuite
	public void baseRequest() {

		RestAssured.baseURI="https://uibank-api.azurewebsites.net";

		HeaderMap.put("Content-Type","application/json");
		HeaderMap.put("Accept","application/json, text/plain, */*");
		HeaderMap.put("Accept","application/json, text/plain, */*");

	}
}
