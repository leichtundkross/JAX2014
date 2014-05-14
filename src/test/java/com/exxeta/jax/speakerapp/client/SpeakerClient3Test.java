package com.exxeta.jax.speakerapp.client;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.ws.rs.core.Response;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.exxeta.jax.speakerapp.common.SpeakerTranslator;
import com.exxeta.jax.speakerapp.server.Speaker;

/**
 * 3. Implementierung des SpeakerClient-Test.<br>
 * Die Abhaengigkeit zu einem laufenden Server wurde aufgeloest, da die
 * Implementierung nun den {@link RestClient} verwendet und dieser gemockt
 * werden kann.
 */
@RunWith(MockitoJUnitRunner.class)
public class SpeakerClient3Test {

	@InjectMocks
	private SpeakerClient3 speakerClient;

	@Mock
	private RestClient restClient;

	@Mock
	private SpeakerTranslator speakerTranslator;

	@Test
	public void getSpeaker() {
		// mock restClient/response
		Response response = Mockito.mock(Response.class);
		Mockito.when(response.getEntity()).thenReturn("valides JSON");
		Mockito.when(restClient.get("Andreas")).thenReturn(response);
		// mock translator
		Mockito.when(speakerTranslator.fromJson(Matchers.anyString()))
				.thenReturn(new Speaker("Guenzel", "EXXETA"));

		Speaker speaker = speakerClient.getSpeaker("Andreas");

		assertNotNull(speaker);
		assertEquals(new Speaker("Guenzel", "EXXETA"), speaker);
	}
}