package com.exxeta.jax.speakerapp.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.core.Response;

/**
 * Beispiel fuer Legacy-Code, der auf static Methoden aufsetzt und (z.B. durch
 * einen JNDI Lookup) nur auf einem AppServer lauffehig ist.
 */
public class HttpUtils {

	public static Response request(String pathInSpeakerApp) {
		HttpURLConnection con = null;
		try {
			con = (HttpURLConnection) new URL(lookupServerUrl()
					+ pathInSpeakerApp).openConnection();
			con.setRequestMethod("GET");
			con.setDoOutput(true);
			con.connect();

			if (con.getResponseCode() == 200) {
				BufferedReader rd = new BufferedReader(new InputStreamReader(
						con.getInputStream()));
				StringBuilder sb = new StringBuilder();
				String line = null;
				while ((line = rd.readLine()) != null) {
					sb.append(line + '\n');
				}

				return Response.ok(sb.toString()).build();
			}

			return Response.status(con.getResponseCode()).build();
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			if (con != null) {
				con.disconnect();
			}
		}
	}

	private static String lookupServerUrl() {
		InitialContext initialContext = null;
		try {
			initialContext = new InitialContext();
			return (String) initialContext.lookup("java:global/env/jax/url");
		} catch (Exception e) {
			// default-Wert als fallback
			return "http://localhost:8080/jax/speaker/";
		} finally {
			if (initialContext != null) {
				try {
					initialContext.close();
				} catch (NamingException e) {
					// nothing to do
				}
			}
		}
	}
}