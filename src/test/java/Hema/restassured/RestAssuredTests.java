package Hema.restassured;

import static com.jayway.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.junit.BeforeClass;
import org.junit.Test;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;



public class RestAssuredTests {
	
	@BeforeClass
	public static void initialize() {
		RestAssured.baseURI = "http://localhost:8080";
	}
	
	
	@Test
	public void getLandLords() {
	
		when()
			.get("/landlords")
		.then()
			.statusCode(200)
			.body("", is(empty()));
		
	}
	
	@Test
	public void postLandLord1() {
	
		LandLord landlord = new LandLord("Sid", "Arun");
		
		String id = given()
			.contentType(ContentType.JSON)
			.body(landlord)
		.when()
			.post("/landlords")
		.then()
			.statusCode(201)
			.body("firstName", is(landlord.getFirstName()))
			.body("lastName", is(landlord.getLastName()))
			.body("trusted", is(false))
			.body("apartments",is(empty()))
		.extract()
			.path("id");
		
		
		given()
			.pathParam("id", id)
		.when()
			.get("/landlords/{id}")
		.then()
			.statusCode(200)
			.body("id", is(id))
			.body("firstName", is(landlord.getFirstName()))
			.body("lastName", is(landlord.getLastName()))
			.body("trusted", is(false))
			.body("apartments",is(empty()));	
			
	}
	
	
	@Test
	public void postLandLord2() {
	
		LandLord landlord = new LandLord("Hema", "Sankaranarayanan", true);
		
		String id = given()
			.contentType(ContentType.JSON)
			.body(landlord)
		.when()
			.post("/landlords")
		.then()
			.statusCode(201)
			.body("firstName", is(landlord.getFirstName()))
			.body("lastName", is(landlord.getLastName()))
			.body("trusted", is(landlord.getTrusted()))
			.body("apartments",is(empty()))
		.extract()
			.path("id");
		
		
		given()
			.pathParam("id", id)
		.when()
			.get("/landlords/{id}")
		.then()
			.statusCode(200)
			.body("id", is(id))
			.body("firstName", is(landlord.getFirstName()))
			.body("lastName", is(landlord.getLastName()))
			.body("trusted", is(landlord.getTrusted()))
			.body("apartments",is(empty()));	
			
	}
	
	
	@Test
	public void postLandLordWithErrors1() {
	
		LandLord landlord = new LandLord("", "");
		
		given()
			.contentType(ContentType.JSON)
			.body(landlord)
		.when()
			.post("/landlords")
		.then()
			.statusCode(400)
			.body("message", is("Fields are with validation errors"))
			.body("fieldErrorDTOs[0].fieldName", is("firstName"))
			.body("fieldErrorDTOs[0].fieldError", is("First name can not be empty"))
			.body("fieldErrorDTOs[1].fieldName", is("lastName"))
			.body("fieldErrorDTOs[1].fieldError", is("Last name can not be empty"));
			

}
	
	
	@Test
	public void putLandLord() {
		
		LandLord landlord = new LandLord("Sid", "Arun", true);
		LandLord landlordnew = new LandLord("Sidharth", "Arun Prasad", true);
		
		String id = given()
			.contentType(ContentType.JSON)
			.body(landlord)
		.when()
			.post("/landlords")
		.then()
			.statusCode(201)
		.extract()
			.path("id");
		
		
		given()
			.contentType(ContentType.JSON)
			.body(landlordnew)
			.pathParam("id", id)
		.when()
			.put("/landlords/{id}")
		.then()
			.statusCode(200)
			.body("message", is("LandLord with id: " + id + " successfully updated"));
		
		
		given()
			.pathParam("id", id)
		.when()
			.get("/landlords/{id}")
		.then()
			.statusCode(200)
			.body("id", is(id))
			.body("firstName", is(landlordnew.getFirstName()))
			.body("lastName", is(landlordnew.getLastName()));
	}
	
	
	@Test
	public void deleteLandLord() {
		
		LandLord landlord = new LandLord("H", "Sank", true);
		
		String id = given()
			.contentType(ContentType.JSON)
			.body(landlord)
		.when()
			.post("/landlords")
		.then()
			.statusCode(201)
		.extract()
			.path("id");
		
		
		given()
			.pathParam("id", id)
		.when()
			.delete("/landlords/{id}")
		.then()
			.statusCode(200)
			.body("message", is("LandLord with id: " + id + " successfully deleted"));
		
		
		given()
			.pathParam("id", id)
		.when()
			.get("/landlords/{id}")
		.then()
			.statusCode(404)
			.body("message", is("There is no LandLord with id: " + id));
		
		
	}
	
	
	
}
