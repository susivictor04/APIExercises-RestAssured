package exercise4.zoho;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.BeforeSuite;

import io.restassured.RestAssured;


public class BaseRequest {

	public static Map<String, String> HeaderMap = new HashMap<String, String>();
	public static String custId;

	@BeforeSuite
	public void baseRequest() {

		//		create customer - Post - https://subscriptions.zoho.com/api/v1/customers
		//		update customer - Put - https://subscriptions.zoho.com/api/v1/customers/{{CustomeridEnv}}
		//		customerList - Get https://subscriptions.zoho.com/api/v1/customers
		//		Delete Customer - Delete - https://subscriptions.zoho.com/api/v1/customers/{{CustomeridEnv}}

		RestAssured.baseURI = "https://subscriptions.zoho.com/api/v1/customers";

		HeaderMap.put("Authorization","Zoho-oauthtoken 1000.a7e277e6046893aea956ba92b2baef7c.bf73010f580dc9982a18ac0219d5426c");
		HeaderMap.put("X-com-zoho-subscriptions-organizationid","716955563");
		HeaderMap.put("Content-Type","application/json;charset=UTF-8");




	}

}
