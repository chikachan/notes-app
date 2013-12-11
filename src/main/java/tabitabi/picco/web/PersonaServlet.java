package tabitabi.picco.web;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import tabitabi.picco.NotesAppException;
import tabitabi.picco.browserid.BrowserIdResponse;
import tabitabi.picco.browserid.BrowserIdResponse.Status;
import tabitabi.picco.browserid.Verifier;
import tabitabi.picco.model.Account;
import tabitabi.picco.persistence.repository.AccountsRepository;

@WebServlet(value = { "/login", "/logout" })
public class PersonaServlet extends HttpServlet {
	
	private static final long serialVersionUID = -6146110902487181662L;

	private static final String ASSERTION_PARAMETER = "assertion";
	@Autowired
	private AccountsRepository accountsRepo;
	

	@Override
	protected void doGet(final HttpServletRequest request,
			final HttpServletResponse response) throws ServletException,
			IOException {
		
		System.out.print("get");
	}

	@Override
	protected void doPost(final HttpServletRequest request,
			final HttpServletResponse response) throws ServletException,
			IOException {

		String assertion = request.getParameter(ASSERTION_PARAMETER);
		String audience = "";
		
		if (assertion != null) {
			audience = request.getRequestURL().toString();//TODO is this safe??
			try {
				URL url = new URL(audience);
				audience = url.getHost();
			} catch (MalformedURLException exc) {
				throw new NotesAppException("Login erro invalid audince URL: ", exc); //TODO log?
			}
		}
		Verifier verifier = new Verifier();
		BrowserIdResponse browserIdResponse = verifier.verify(assertion, audience);
		
		if(Status.OK == browserIdResponse.getStatus()){
			String email = browserIdResponse.getEmail();
			Account account = accountsRepo.findByEmail(email);
			
			
		} else{
			//Login error
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		

	}
	
	
	void login(final BrowserIdResponse browserIdResponse){
		if(BrowserIdResponse.Status.OK == browserIdResponse.getStatus()){
			//look in db if the user exists, if it is in the DB load its info,
			//if it is not in DB insert one
			//copy old session
			//invalidate session
			//create a new session and put old attributes
		}
		
	}

}
