package com.exxeta.jax.speakerapp.common;

import java.io.StringReader;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

import com.exxeta.jax.speakerapp.server.Speaker;

public class SpeakerTranslator {

	public Speaker fromJson(String entity) {
		JsonReader jsonReader = Json.createReader(new StringReader(entity));
		JsonObject jsonObject = jsonReader.readObject();

		String name = jsonObject.getString("name");
		String company = jsonObject.getString("company");
		return new Speaker(name, company);
	}
}