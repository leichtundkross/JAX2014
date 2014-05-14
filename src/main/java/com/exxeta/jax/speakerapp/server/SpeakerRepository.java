package com.exxeta.jax.speakerapp.server;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

public class SpeakerRepository {

	@PersistenceContext(name = "h2")
	private EntityManager entityManager;

	public void addSpeaker(Speaker speaker) {
		entityManager.persist(speaker);
	}

	public Speaker findSpeakers(String name) {
		TypedQuery<Speaker> query = entityManager.createNamedQuery(
				Speaker.QUERY_SPEAKER_BY_NAME, Speaker.class);
		query.setParameter("name", name);
		return query.getSingleResult();
	}

	public List<Speaker> getAllSpeakers() {
		return entityManager.createNamedQuery(Speaker.QUERY_ALL_SPEAKERS,
				Speaker.class).getResultList();
	}
}
