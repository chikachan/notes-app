package pt.webdetails.browserid;

import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.springframework.util.StringUtils;

import tabitabi.picco.NotesAppException;
import tabitabi.picco.web.WebClientDevWrapper;

/**
 * Code taken from https://github.com/mozilla/browserid-cookbook/blob/master/java/spring/src/pt/webdetails/browserid/BrowserIdVerifier.java
 * */

public class Verifier {

	
	public static final String DEFAULT_URL = "https://verifier.login.persona.org/verify";
	private String url;

	
	public Verifier() {
		this.url = DEFAULT_URL;
	}
	
	public BrowserIdResponse verify(String assertion, String audience) {
	    
	    if(!StringUtils.hasLength(assertion)) throw new IllegalArgumentException("assertion is mandatory");
	    if(!StringUtils.hasLength(audience)) throw new IllegalArgumentException("audience is mandatory");
	    
	    HttpClient client = new DefaultHttpClient();
	    boolean dev = true;
	    if(dev){
	    	client = WebClientDevWrapper.wrapClient(client);
	    }
	    	    
		JSONObject body = new JSONObject();
		try {
			// TODO: check certificate?
			HttpPost post = new HttpPost(url);
			post.addHeader("Content-Type", "application/json");
			body.put("assertion", assertion);
			body.put("audience", audience);			 
			post.setEntity(new StringEntity( body.toString()));

			HttpResponse response = client.execute(post);
			int statusCode = response.getStatusLine().getStatusCode();

			if (statusCode == 200) {
				HttpEntity entity = response.getEntity();

				if (entity != null) {
					return new BrowserIdResponse(EntityUtils.toString(entity));
				}
			}

			throw new HttpException("failed" + statusCode);

		} catch (Exception exc) {
			throw new NotesAppException(exc);
		} finally {
			client.getConnectionManager().shutdown();
		}

	}
	
}
