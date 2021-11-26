package org.acme;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import com.example.petstore.Pet;

import static io.restassured.RestAssured.given;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

@QuarkusTest
public class PetResourceTest {

	@Test
    public void testPetEndpoint() {
        given()
          .when().get("/v1/pets")
          .then()
             .statusCode(200);
    }
	
	@Test
	public void testPetTypesEndPoint() {
		given()
			.when().get("/v1/pets/types")
			.then()
				.statusCode(200);
	}
	
	@Test
	public void testAddPetEndPoint() {
		final Pet pet=new Pet();
		pet.setPetType("cat");
		pet.setPetName("shedy");
		pet.setPetAge(2);
		
		given()
			.body(pet)
			.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
	        .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON)
			.when().post("/v1/pets/addPet")
			.then()
				.statusCode(200);
	}
	
	@Test
	public void testGetPetEndPoint() {
		given()
	        .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON)
			.when().get("/v1/pets/1")
			.then()
				.statusCode(200);
	}
	
	@Test
	public void testUpdatePetEndPoint() {
		final Pet pet=new Pet();
		pet.setPetId(1);
		pet.setPetType("dog");
		pet.setPetName("shedy");
		pet.setPetAge(2);
		
		given()
			.body(pet)
			.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
	        .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON)
			.when().put("/v1/pets/updatePet")
			.then()
				.statusCode(200);
	}
	
	@Test
	public void testDeletePetEndPoint() {
		given()
			.when().delete("/v1/pets/deletePet/1")
			.then()
				.statusCode(200);
	}
}