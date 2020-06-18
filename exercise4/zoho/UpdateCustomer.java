package exercise4.zoho;

import java.io.File;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class UpdateCustomer extends BaseRequest {
	
	@Test(dependsOnMethods= {"exercise4.zoho.CreateCustomer.createCustomer"})
	public void updateCustomer() {
		
		Response response = (Response) RestAssured
				.given()
				.log().all()
				.headers(HeaderMap)
				.body(new File("./data/updatecustomer.json"))
				.put(custId);

		response.prettyPrint();	

	
		
	}
	

}
