package com.exxeta.jax.speakerapp.client;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.exxeta.jax.speakerapp.common.SpeakerTranslator;
import com.exxeta.jax.speakerapp.server.Speaker;

/**
 * 1. Implementierung des SpeakerClient-Test.<br>
 * Das ist kein Unit-Test, da der Test nur lauffaehig ist, wenn der Server
 * gestartet und der Speaker in der DB hinterlegt ist. Zudem ist der Test
 * Abhaengig von der Implemntierung des {@link SpeakerTranslator}.
 */
public class SpeakerClient1Test {

	@Test
	public void getSpeaker() {
		SpeakerClient1 speakerClient = new SpeakerClient1();
		Speaker speaker = speakerClient.getSpeaker("Andreas");

		assertNotNull(speaker);
		assertEquals(new Speaker("Andreas", "EXXETA"), speaker);
	}
}