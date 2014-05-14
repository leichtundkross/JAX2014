package com.exxeta.jax.speakerapp.client;

import javax.ws.rs.core.Response;

import com.exxeta.jax.speakerapp.common.HttpUtils;
import com.exxeta.jax.speakerapp.common.SpeakerTranslator;
import com.exxeta.jax.speakerapp.server.Speaker;

/**
 * 1. (schlechte) Implementierung, ohne Injection und Verwendung von
 * static-Methoden.
 */
public class SpeakerClient1 {

	public Speaker getSpeaker(String name) {
		Response response = HttpUtils.request(name);
		return new SpeakerTranslator().fromJson((String) response.getEntity());
	}
}