package com.exxeta.jax.speakerapp.client;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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
 * 2. Implementierung des SpeakerClient-Test.<br>
 * Die Abhaengigkeit zum {@link SpeakerTranslator} wurde dank Mockito-Injection
 * aufgeloest.
 */
@RunWith(MockitoJUnitRunner.class)
public class SpeakerClient2Test {

	@InjectMocks
	private SpeakerClient2 speakerClient;

	@Mock
	private SpeakerTranslator speakerTranslator;

	@Test
	public void getSpeaker() {
		// Verhalten des translators simulieren/mocken
		Mockito.when(speakerTranslator.fromJson(Matchers.anyString()))
				.thenReturn(new Speaker("Andreas", "EXXETA"));

		Speaker speaker = speakerClient.getSpeaker("Andreas");

		assertNotNull(speaker);
		assertEquals(new Speaker("Andreas", "EXXETA"), speaker);
	}
}