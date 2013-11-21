package tabitabi.picco.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value={"/login","/logout"})
public class PersonaServlet extends HttpServlet {

	private static final long serialVersionUID = -6146110902487181660L;

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
		
		System.out.println("Here");
    }
	
}
