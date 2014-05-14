package com.exxeta.jax.speakerapp.client;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.ws.rs.core.Response;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.exxeta.jax.speakerapp.common.SpeakerTranslator;
import com.exxeta.jax.speakerapp.server.Speaker;

/**
 * 4. Implementierung des SpeakerClient-Test.<br>
 * Mit Hilfe von {@link Mockito#verify(Object)} und {@link ArgumentCaptor}
 * pruefen wir, welche Objekte innerhalb der Methode erzeugt und weitergereicht
 * werden.
 */
@RunWith(MockitoJUnitRunner.class)
public class SpeakerClient4Test {

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
		Mockito.when(restClient.get("Guenzel")).thenReturn(response);
		// mock translator
		ArgumentCaptor<String> translatorCaptor = ArgumentCaptor
				.forClass(String.class);
		Mockito.when(speakerTranslator.fromJson(translatorCaptor.capture()))
				.thenReturn(new Speaker("Guenzel", "EXXETA"));

		Speaker speaker = speakerClient.getSpeaker("Guenzel");

		assertNotNull(speaker);
		assertEquals(new Speaker("Guenzel", "EXXETA"), speaker);

		// wie oft wurde eine methode aufgerufen?
		Mockito.verify(response, Mockito.times(1)).getEntity();
		// mit welchen parametern wurde sie aufgerufen?
		assertEquals("valides JSON", translatorCaptor.getValue());
	}
}