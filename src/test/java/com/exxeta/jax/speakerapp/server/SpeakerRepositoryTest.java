package com.exxeta.jax.speakerapp.server;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SpeakerRepositoryTest {

	@InjectMocks
	private SpeakerRepository repository;

	@Spy
	private EntityManager entityManager = Persistence
			.createEntityManagerFactory("unittest").createEntityManager();

	@Test
	public void getAllSpeakers() {
		entityManager.getTransaction().begin();
		entityManager.persist(new Speaker("Adam", "none"));
		entityManager.getTransaction().commit();

		List<Speaker> allSpeakers = repository.getAllSpeakers();

		assertNotNull(allSpeakers);
		assertEquals(1, allSpeakers.size());
	}
}