package exercise5.hotelbeds;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

@Test
public class CheckRoomAvailability extends CreateSignature {

	public void checkAvailability() {
		Response response = RestAssured
				.given()
				.headers(headerMap)
				.log().all()
				.body("{\r\n" + 
						"    \"stay\": {\r\n" + 
						"        \"checkIn\": \"2020-06-20\",\r\n" + 
						"        \"checkOut\": \"2020-06-21\"\r\n" + 
						"    },\r\n" + 
						"    \"occupancies\": [\r\n" + 
						"        {\r\n" + 
						"            \"rooms\": 1,\r\n" + 
						"            \"adults\": 1,\r\n" + 
						"            \"children\": 0\r\n" + 
						"        }\r\n" + 
						"    ],\r\n" + 
						"    \"destination\": {\r\n" + 
						"        \"code\": \"MCO\"\r\n" + 
						"    },\r\n" + 
						"    \"filter\": {\r\n" + 
						"			\"minRate\": 100.000,\r\n" + 
						"			\"maxRate\": 102.000\r\n" + 
						"		}\r\n" + 
						"}")
				.post("hotels");

		response.prettyPrint();
		System.out.println("Response code is :" + response.getStatusCode());

		JsonPath jsonPath = response.jsonPath();
		int totalRoom,totalHotel;
		String hotelName, roomName;
		ArrayList<String> bookableRateKey=new ArrayList<String>();  


		//to get the list of hotels in the json response
		List<Object> hotelList = jsonPath.getList("hotels.hotels");
		totalHotel = hotelList.size();
		System.out.println(" Number of hotels : " + totalHotel);


		// for loop to get each hotel
		for(int i=1 ; i< totalHotel ; i++) {

			//to get the hotel name
			hotelName = jsonPath.get("hotels.hotels["+i+"].name");

			if (hotelName!=null) {

				System.out.println("Hotel Name: " + hotelName);

				//to get the total number of rooms
				List<Object> roomList= jsonPath.getList("hotels.hotels["+i+"].rooms");

				totalRoom = roomList.size();

				System.out.println("Number of rooms :" + totalRoom);

				for (int j=0 ; j< totalRoom; j++) {

					roomName = jsonPath.get("hotels.hotels["+i+"].rooms["+j+"].name");

					System.out.println(" Room name :" + roomName);

					if (roomName!=null) {

						if (jsonPath.getList("hotels.hotels["+i+"].rooms["+j+"].rates")!=null) {

							List<Map<Object, Object>> roomRate = jsonPath.getList("hotels.hotels["+i+"].rooms["+j+"].rates");

							for (Map<Object, Object> map : roomRate) {

								String keyRate = map.get("rateKey").toString();
								String typeRate = map.get("rateType").toString();
								System.out.println(" Rate Key :" + keyRate);
								System.out.println(" Rate Type : " + typeRate);
								System.out.println(" Room Rate in Net Price : " + map.get("net").toString());
								System.out.println(" Number of Rooms available -Allotment  : " + map.get("allotment").toString());

								if (typeRate.equals("BOOKABLE")) {

									System.out.println("inside if");
									bookableRateKey.add(keyRate);									

								}

							}
						}
					}
				}

			}

			System.out.println("---------------------------------------");
		}

		firstBookableRoomRate =bookableRateKey.get(0);
		System.out.println(" First Bookable rate key :" +firstBookableRoomRate );

	}

}


