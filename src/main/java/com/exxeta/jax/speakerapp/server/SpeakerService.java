package com.exxeta.jax.speakerapp.server;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class SpeakerService {

	@Inject
	private SpeakerRepository repository;

	public Speaker createSpeaker(String name, String company) {
		Speaker speaker = new Speaker(name, company);
		repository.addSpeaker(speaker);
		return speaker;
	}

	public Collection<Speaker> listSpeakers() {
		return repository.getAllSpeakers();
	}

	public Speaker findSpeaker(String name) {
		return repository.findSpeakers(name);
	}
}