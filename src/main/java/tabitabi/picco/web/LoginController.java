package tabitabi.picco.web;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import tabitabi.picco.browserid.BrowserIdResponse;
import tabitabi.picco.browserid.BrowserIdResponse.Status;
import tabitabi.picco.browserid.Verifier;
import tabitabi.picco.model.Account;
import tabitabi.picco.persistence.repository.AccountsRepository;

@Controller
public class LoginController {

	public static final String AUDIENCE = "localhost";//TODO fix this
	@Autowired
	private AccountsRepository accountsRepo;
	@Autowired
	private Verifier verifier;

	@RequestMapping(value = { "/login" }, method = RequestMethod.POST, 
			produces = "text/plain")
	@ResponseBody
	public String login(@RequestParam(required = true) final String assertion,
			final HttpServletRequest request) {

		BrowserIdResponse loginRepsonse = verifier.verify(assertion, AUDIENCE);
		Status status = loginRepsonse.getStatus();

		if (status == Status.OK) {
			renewSession(request);
			String email = loginRepsonse.getEmail();
			Account account = accountsRepo.findByEmail(email);
			
			if(account == null){
				/* This account doesn't exist, let's create it */
				account = new Account();
				account.setEmail(loginRepsonse.getEmail());
				account = accountsRepo.save(account);//TODO correct?
			}
			
			request.setAttribute("user", account.getEmail());
			
			return "OK";//detach account

		} else {
			// TODO execute navigator.id.logout() ????

			//https://developer.mozilla.org/en/Persona/Quick_Setup
			// Note that if the identity assertion can't be verified, you should
			// call navigator.id.logout(): this has the effect of telling
			// Persona that noone is currently logged in. If you don't do this,
			// then Persona may immediately call onlogin again with the same
			// assertion, and this can lead to an endless loop of failed logins.

		}
		return "error";
	}

	private void renewSession(final HttpServletRequest request) {
		//Use spring security???
		
		HttpSession oldSession = request.getSession(false);
		Map<String,Object> sessionMap = new HashMap<>();
		if(oldSession != null){
			Enumeration<String> attributeNames = oldSession.getAttributeNames();
			while(attributeNames.hasMoreElements()){
				String attributeName = attributeNames.nextElement();
				sessionMap.put(attributeName, oldSession.getAttribute(attributeName));
			}
			
			oldSession.invalidate();
		}
				
		HttpSession newSession = request.getSession(true);

		for(String key : sessionMap.keySet()){
			newSession.setAttribute(key, sessionMap.get(key));
		}		
		
		//Can't read from old and put into new at the same time?
	}

}
