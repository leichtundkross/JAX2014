package com.exxeta.jax.speakerapp.client;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

import com.exxeta.jax.speakerapp.common.SpeakerTranslator;
import com.exxeta.jax.speakerapp.server.Speaker;

/**
 * 3. (finale) Implementierung, alle verwendeten Komponenten koennen im
 * Unit-Test gemockt werden.
 */
public class SpeakerClient3 {

	@Inject
	private RestClient restClient;

	@Inject
	private SpeakerTranslator speakerTranslator;

	public Speaker getSpeaker(String name) {
		Response response = restClient.get(name);
		return speakerTranslator.fromJson((String) response.getEntity());
	}
}