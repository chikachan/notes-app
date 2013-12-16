package tabitabi.picco.browserid;
//package pt.webdetails.browserid;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Code taken from https://github.com/mozilla/browserid-cookbook/blob/master/java/spring/src/pt/webdetails/browserid/BrowserIdVerifier.java
 * */

public class Verifier {

	
	public static final String DEFAULT_URL = "https://verifier.login.persona.org/verify";
	//private String url;

	
	public Verifier() {
		//this.url = DEFAULT_URL;
	}
	
	public BrowserIDResponse verify(final String assertion, final String audience) {
	    	   
	    if(assertion == null || !(assertion.length() > 0)) {
	    	 throw new IllegalArgumentException("assertion is mandatory");
	    }
	    if(audience == null || !(audience.length() > 0)) {
	    	 throw new IllegalArgumentException("audience is mandatory");
	    }
	    
	    return executeVerifyRequest(assertion,audience);
	   
	}
	
	private BrowserIDResponse executeVerifyRequest(final String assertion,
			final String audience) {

		try {

			URL url = new URL(DEFAULT_URL);
			String response = "";
			HttpsURLConnection connection = (HttpsURLConnection) url
					.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/json");
			JSONObject body = new JSONObject();
			body.put("assertion", assertion);
			body.put("audience", audience);
			connection.setDoOutput(true);

			
			DataOutputStream wr = new DataOutputStream(
					connection.getOutputStream());
			wr.writeBytes(body.toString());
			wr.flush();
			InputStream rd = connection.getInputStream();
			Scanner scanner = new Scanner(rd);
			
			response = scanner.useDelimiter("\\A").hasNext() ? scanner.next()
					: "";
			//TODOclose these resources ala java 7
			
			int responseCode = connection.getResponseCode();
			System.out.print(responseCode);// TODO log

			return new BrowserIDResponse(response);

		} catch (IOException | JSONException exc) {
			throw new BrowserIDException(exc);
		}
	}
	
}
