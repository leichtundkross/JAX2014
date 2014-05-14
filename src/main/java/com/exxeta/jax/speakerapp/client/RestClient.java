package com.exxeta.jax.speakerapp.client;

import javax.ws.rs.core.Response;

import com.exxeta.jax.speakerapp.common.HttpUtils;

/**
 * Adapter fuer Klasse {@link HttpUtils} um im Code/Test nicht mit static
 * kaempfen zu muessen.
 */
public class RestClient {

	public Response get(String pathInSpeakerApp) {
		return HttpUtils.request(pathInSpeakerApp);
	}
}
