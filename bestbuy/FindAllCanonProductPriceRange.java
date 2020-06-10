package exercise3.bestbuy;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class FindAllCanonProductPriceRange {


	@Test
	public void getAllCanonProduct() {



		//		Finding all the canon products of price range between $1000-$1500

		//		products(manufacturer=canon&(salePrice>=1000&salePrice<=1500))?format=json&show=sku,name,salePrice

		RestAssured.baseURI= "https://api.bestbuy.com/v1/";


		//		products(manufacturer=apple&search=iPhone 11 Pro)?format=json&show=sku,name,salePrice,regularPrice&apiKey=qUh3qMK14GdwAs9bO59QRSCJ
		//      products(manufacturer=apple&search=iPhone 11 64GB&inStorePickup=true)+stores(region=RI)?format=json&show=sku,name,stores.storeId,stores.name,stores.address&apiKey=qUh3qMK14GdwAs9bO59QRSCJ

		Map<String, String> parametersMap = new HashMap<String, String>();
		parametersMap.put("fomat","json");
		parametersMap.put("show","sku,name,salePrice,regularPrice");
		parametersMap.put("apiKey","qUh3qMK14GdwAs9bO59QRSCJ");

		Response response = RestAssured
				.given()
				.header("Content-Type","application/json")
				.log().all()
				.contentType(ContentType.JSON)
				.params(parametersMap)
				.get("products(manufacturer=canon&(salePrice>=1000&salePrice<=1500))");

		response.prettyPrint();

		System.out.println(" Response code : " + response.getStatusCode());


	}

}
