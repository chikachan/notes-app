package tabitabi.picco.browserid;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Code inspired by from
 * https://github.com/mozilla/browserid-cookbook/blob/master/java/spring/src/pt/webdetails/browserid/BrowserIdVerifier.java
 * 
 */

public class Verifier {

	public static final String DEFAULT_URL = "https://verifier.login.persona.org/verify";
	private static final Logger log = LoggerFactory.getLogger(Verifier.class);
	private final String url;


	/**
	 * Creates a {@code Verifier} object with the default URL {@link Verifier#DEFAULT_URL}.
	 */
	public Verifier() {
		this.url = DEFAULT_URL;
	}
	
	public Verifier(final String url){
		this.url = url;
	}
	
	public String getURL() {
		return new String(url);
	}

	/***
	 * @param assertion
	 * @param audience
	 * @return the result of the verification
	 * 
	 * @throws <code>BrowserIDResponse</code> if there is a failure in the
	 *         verification process
	 */
	public BrowserIDResponse verify(final String assertion,
			final String audience) {
		log.debug("assertion: {} {}audience: {} ", assertion,
				System.lineSeparator(), audience);
		if (assertion == null || !(assertion.length() > 0)) {
			throw new IllegalArgumentException("assertion is mandatory");
		}
		if (audience == null || !(audience.length() > 0)) {
			throw new IllegalArgumentException("audience is mandatory");
		}

		try {
			/* Prepare connection */
			JSONObject body = new JSONObject();
			body.put("assertion", assertion);
			body.put("audience", audience);
			log.debug("Verifying using: {}", this.url);
			URL verifierURL = new URL(this.url);
			String response = "";
			HttpsURLConnection connection = (HttpsURLConnection) verifierURL
					.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setDoOutput(true);

			/* Write to the connection */
			try (DataOutputStream wr = new DataOutputStream(
					connection.getOutputStream())) {
				wr.writeBytes(body.toString());
				wr.flush();
			} catch (IOException wrExc) {
				throw wrExc;
			}

			/* Read from the connection */
			try (Scanner scanner = new Scanner(connection.getInputStream())) {
				response = scanner.useDelimiter("\\A").hasNext() ? scanner
						.next() : "";
			} catch (IOException rdExc) {
				throw rdExc;
			}

			log.debug("Response from verifier: [{}] {} {}",
					connection.getResponseCode(), System.lineSeparator(),
					response);

			return new BrowserIDResponse(response);

		} catch (IOException | JSONException exc) {
			throw new BrowserIDException(exc);
		}
	}

}
