package com.exxeta.jax.speakerapp.server;

import java.io.StringWriter;
import java.net.URI;
import java.util.Collection;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;
import javax.persistence.NoResultException;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("speaker")
public class SpeakerResource {

	@Inject
	private SpeakerService speakerService;

	@GET
	@Produces("application/json")
	public String index() {
		Collection<Speaker> speakers = speakerService.listSpeakers();

		JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
		for (Speaker speaker : speakers) {
			JsonObjectBuilder model = Json.createObjectBuilder()
					.add("name", speaker.getName())
					.add("company", speaker.getCompany());
			jsonArrayBuilder.add(model);
		}

		StringWriter stWriter = new StringWriter();
		JsonWriter jsonWriter = Json.createWriter(stWriter);
		jsonWriter.writeArray(jsonArrayBuilder.build());
		jsonWriter.close();

		return stWriter.toString();
	}

	@GET
	@Path("{name}")
	@Produces("application/json")
	public Response speaker(@PathParam("name") String name) throws Exception {
		try {
			Speaker speaker = speakerService.findSpeaker(name);
			JsonObjectBuilder model = Json.createObjectBuilder()
					.add("name", speaker.getName())
					.add("company", speaker.getCompany());

			StringWriter stWriter = new StringWriter();
			JsonWriter jsonWriter = Json.createWriter(stWriter);
			jsonWriter.writeObject(model.build());
			jsonWriter.close();
			return Response.ok(stWriter.toString()).build();
		} catch (Exception e) {
			if (e.getCause() instanceof NoResultException) {
				return null;
			}

			throw e;
		}
	}

	@GET
	@Path("create")
	@Produces(MediaType.TEXT_HTML)
	public Response foo() {
		return Response
				.ok("<form action='create' method='post'><p>Name : <input type='text' name='name' /></p>"
						+ "<p>Age : <input type='text' name='company' /></p><input type='submit' value='Add Speaker' /></form>")
				.build();
	}

	@POST
	@Path("create")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response createSpeaker(@FormParam("name") String name,
			@FormParam("company") String company) {
		speakerService.createSpeaker(name, company);
		return Response.seeOther(URI.create("speaker")).build();
	}
}