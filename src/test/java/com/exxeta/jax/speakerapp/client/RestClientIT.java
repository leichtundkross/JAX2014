package com.exxeta.jax.speakerapp.client;

import static org.junit.Assert.assertEquals;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.exxeta.jax.speakerapp.common.HttpUtils;
import com.exxeta.jax.speakerapp.server.SpeakerService;

/**
 * Arquillian-Test fuer den {@link RestClient} bzw. indirekt die
 * {@link HttpUtils}.
 */
@RunWith(Arquillian.class)
public class RestClientIT {

	@Inject
	private SpeakerService speakerService;

	@Test
	public void get() {
		speakerService.createSpeaker("Simon", "EXXETA");

		RestClient restClient = new RestClient();
		Response response = restClient.get("Simon");

		assertEquals(200, response.getStatus());
		String json = (String) response.getEntity();
		assertEquals("{\"name\":\"Simon\",\"company\":\"EXXETA\"}", json.trim());
	}

	@Deployment
	public static WebArchive createDeployment() {
		return ShrinkWrap.create(WebArchive.class, "jax.war") //
				.addPackages(true, "com.exxeta.jax.speakerapp") //
				.addAsResource("META-INF/persistence.xml") //
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
	}
}
