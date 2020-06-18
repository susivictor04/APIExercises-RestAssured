package exercise4.zoho;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class DeleteCustomer extends BaseRequest {

	@Test(dependsOnMethods= {"exercise4.zoho.UpdateCustomer.updateCustomer"})
//@Test
	public void deleteCustomer() {
		{

			Response response = (Response) RestAssured
					.given()
					.log().all()
					.headers(HeaderMap)
					.delete(custId);

			response.prettyPrint();	

			JsonPath jsonPath = response.jsonPath();
			String msg = jsonPath.get("message");

			if(msg.contains("deleted")) {

				System.out.println("Customer has been deleted");
			}


		}

	}
}
