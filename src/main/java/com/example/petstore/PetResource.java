package com.example.petstore;

import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

import com.example.petstore.db.DB;

@Path("/v1/pets")
@Produces("application/json")
public class PetResource {

	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "All Pets", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))) })
	@GET
	public Response getPets() {
		return Response.ok(DB.getPetTable()).build();
	}

	
	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "Pet for id", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))),
			@APIResponse(responseCode = "404", description = "No Pet found for the id.") })
	@GET
	@Path("{petId}")
	public Response getPet(@PathParam("petId") int petId) {
		if (petId < 1 || petId > DB.getPetsCount()) {
			return Response.status(Status.NOT_FOUND).build();
		}
		
		return Response.ok(DB.getPet(petId)).build();
	}
	
	
	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "Pet added", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))),
			@APIResponse(responseCode = "404", description = "Pet adding failed.") })		
	@POST		
	@Path("addPet")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional
	public Response addPet(@RequestBody Pet pet) {
		Pet savedPet=DB.savePet(pet);
		return Response.ok(savedPet).build();
	}
	
	
	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "Pet updated", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))),
			@APIResponse(responseCode = "404", description = "Pet update failed.") })		
	@PUT		
	@Path("updatePet")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional
	public Response updatePet(@RequestBody Pet pet) {
		if (pet.getPetId() < 1 || pet.getPetId() > DB.getPetsCount()) {
			return Response.status(Status.NOT_FOUND).build();
		}
		
		return Response.ok(DB.updatePet(pet)).build();
	}
	
	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "Pet deleted", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))),
			@APIResponse(responseCode = "404", description = "Pet delete failed.") })		
	@DELETE		
	@Path("deletePet/{petId}")
	public Response deletePet(@PathParam("petId") int petId) {
		if (petId < 1 || petId > DB.getPetsCount()) {
			return Response.status(Status.NOT_FOUND).build();
		}
		
		return Response.ok(DB.deletePet(petId)).build();
	}
}
