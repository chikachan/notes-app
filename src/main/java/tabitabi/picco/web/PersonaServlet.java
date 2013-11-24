package tabitabi.picco.web;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pt.webdetails.browserid.BrowserIdResponse;
import pt.webdetails.browserid.BrowserIdResponse.Status;
import pt.webdetails.browserid.Verifier;
import tabitabi.picco.NotesAppException;

@WebServlet(value = { "/login", "/logout" })
public class PersonaServlet extends HttpServlet {

	private static final String ASSERTION_PARAMETER = "assertion";

	private static final long serialVersionUID = -6146110902487181661L;

	@Override
	protected void doGet(final HttpServletRequest request,
			final HttpServletResponse response) throws ServletException,
			IOException {

		System.out.println("Here get");
	}

	@Override
	protected void doPost(final HttpServletRequest request,
			final HttpServletResponse response) throws ServletException,
			IOException {

		System.out.println("Here post");

		String assertion = request.getParameter(ASSERTION_PARAMETER);
		System.out.println("browserIdAssertion:" + assertion);
		String audience = null;
		
		if (assertion != null) {
			audience = request.getRequestURL().toString();//TODO is this safe??
			try {
				URL url = new URL(audience);
				audience = url.getHost();
			} catch (MalformedURLException exc) {
				throw new NotesAppException(exc);
			}
		}
		Verifier verifier = new Verifier();
		BrowserIdResponse browserIdResponse = verifier.verify(assertion, audience);
		
		if(Status.OK == browserIdResponse.getStatus()){
			request.getSession();
			
		} else{
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		
		

	}

}
