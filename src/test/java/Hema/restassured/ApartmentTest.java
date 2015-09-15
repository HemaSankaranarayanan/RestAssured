package Hema.restassured;

import static com.jayway.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;

public class ApartmentTest {
	
	@BeforeClass
	public static void initialize() {
		RestAssured.baseURI = "http://localhost:8080";
	}
	
	String id = null;
	String aptId = null;
	
	@Test(priority=0)
	public void postLandLord() {
		LandLord landlord = new LandLord("Minnie", "Mouse");
		
		 id = given()
				.contentType(ContentType.JSON)
				.body(landlord)
			.when()
				.post("/landlords")
			.then()
				.statusCode(201)
			.extract()
				.path("id");
		
	}
	
	@Test(dependsOnMethods="postLandLord")
	public void postApartment() {
		Apartment apartment = new Apartment("Las Vegas, Suite 1", 8000, 100);
		aptId = given()
			.contentType(ContentType.JSON)
			.body(apartment)
			.pathParam("id", id)
		.when()
			.post("/landlords/{id}/apartments")
		.then()
			.statusCode(201)
			.body("address", is(apartment.getAddress()))
			.body("price", is(8000f))
			.body("square", is(apartment.getSquare()))
			.body("features", is(empty()))
			.body("active", is(apartment.getActive()))
		.extract()
			.path("id");
		
	}
	
	@DataProvider(name="apartment list")
	public Object[][] createData() {
		return new Object[][] {
			{new Apartment("Las Vegas, Suite 3", 9000, 34)},
			{new Apartment("Las Vegas, Suite 4", 8000, 47)},
			{new Apartment("Las Vegas, Suite 5", 7000, 64)},
			{new Apartment("Las Vegas, Suite 6", 6000, 27)},
			{new Apartment("Las Vegas, Suite 7", 5000, 54)},
			{new Apartment("Las Vegas, Suite 8", 4000, 87)},
			{new Apartment("Las Vegas, Suite 9", 3000, 92)},
			{new Apartment("Las Vegas, Suite 10", 2000, 38)},
		};
		
	}
	
	@Test(priority=1, dependsOnMethods="postLandLord", dataProvider="apartment list")
	public void postApartmentSet(Apartment apartment) {
		
		given()
			.contentType(ContentType.JSON)
			.body(apartment)
			.pathParam("id", id)
		.when()
			.post("/landlords/{id}/apartments")
		.then()
			.statusCode(201);
		
	}
	
	
	
	

}
