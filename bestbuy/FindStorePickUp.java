package exercise3.bestbuy;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class FindStorePickUp {


	@Test
	public void findStorePickup() {


		//		Finding the stores having store pick-up availability of iPhone 11 Pro in stores in RI region

		RestAssured.baseURI= "https://api.bestbuy.com/v1/";

		String product = "products(manufacturer=apple&name=Apple - iPhone 11 Pro*&inStorePickup=true)";

		Map<String, String> parametersMap = new HashMap<String, String>();
		parametersMap.put("show","sku,name,stores.storeId,stores.name,stores.address,stores.city,stores.region");
		parametersMap.put("apiKey","qUh3qMK14GdwAs9bO59QRSCJ");

		Response response = RestAssured
				.given()
				.header("Content-Type","application/json")
				.log().all()
				.contentType(ContentType.JSON)
				.queryParams(parametersMap)
				.get(product+"+stores(region=RI)?format=json");

		response.prettyPrint();

		System.out.println(" Response code : " + response.getStatusCode());

	}
}
