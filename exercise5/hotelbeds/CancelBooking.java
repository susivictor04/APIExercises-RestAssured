package exercise5.hotelbeds;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class CancelBooking extends CreateSignature{

	@Test(dependsOnMethods= {"exercise5.hotelbeds.RoomBooking.roomBooking"})
	public void cancelBooking() {

		//		{{endpoint}}/hotel-api/1.0/bookings/{{bookingReference}}?cancellationFlag=CANCELLATION

		Response response = RestAssured
				.given()
				.headers(headerMap)
				.log().all()
				.param("cancellationFlag", "CANCELLATION")
				.delete("/bookings/"+ bookingRef);

		response.prettyPrint();


	}

}
