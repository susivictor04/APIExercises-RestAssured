package exercise5.hotelbeds;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class GetBookingDetails extends CreateSignature{

	@Test(dependsOnMethods= {"exercise5.hotelbeds.RoomBooking.roomBooking"})
	public void getBookingDetails() {

		//		{{endpoint}}/hotel-api/1.0/bookings/{{bookingReference}}

		
		System.out.println(" Booking Reference inside get booking details : " +bookingRef );
		Response response = RestAssured
				.given()
				.headers(headerMap)
				.log().all()
				//.get(bookingRef);
				.get("/bookings/"+ bookingRef);

		response.prettyPrint();

	}

}
