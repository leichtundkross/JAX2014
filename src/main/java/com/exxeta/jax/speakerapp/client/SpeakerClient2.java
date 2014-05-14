package com.exxeta.jax.speakerapp.client;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

import com.exxeta.jax.speakerapp.common.HttpUtils;
import com.exxeta.jax.speakerapp.common.SpeakerTranslator;
import com.exxeta.jax.speakerapp.server.Speaker;

/**
 * 2. (etwas bessere) Implementierung, Verwendung von Injection zum Mocken des
 * {@link SpeakerTranslator} in Unit-Tests.
 */
public class SpeakerClient2 {

	@Inject
	private SpeakerTranslator speakerTranslator;

	public Speaker getSpeaker(String name) {
		Response response = HttpUtils.request(name);
		return speakerTranslator.fromJson((String) response.getEntity());
	}
}