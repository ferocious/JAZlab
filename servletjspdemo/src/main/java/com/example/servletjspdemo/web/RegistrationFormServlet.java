package com.example.servletjspdemo.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.servletjspdemo.domain.RegistrationData;

@WebServlet(urlPatterns = "/form")
public class RegistrationFormServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		@SuppressWarnings("unchecked")
		List<RegistrationData> registrationList = (List<RegistrationData>) request
				.getSession().getAttribute("registrationList");
		
		if (registrationList != null && registrationList.size() == 5) {
			out.println("<h3>Sorry, there are no more places available.</h3>");
		} else {
			out.println("<h1>Registration</h1>");
			out.println("<h3></h3>" +
					"<form action='registration'>" +
					"First name: <input type='text' name='firstname' /> <br />" +
					"Name: <input type='text' name='name' /> <br />" +
					"E-mail: <input type='text' name='email' /> <br />");
			
			out.println("<h2>Where did you find the information about the conference?</h2><br />" +
					"<input type='checkbox' name='office' value='bicycle'>It was announced in my office<br />" +
					"<input type='checkbox' name='university' value='tv'>It was announced at the university<br />" +
					"<input type='checkbox' name='facebook' value='beer'>From Facebook<br />" +
					"<input type='checkbox' name='other' value='books'>Other source. Which is...<br />" +
					"<input type='text' name='source' /><br />" +
					"<input type='checkbox' name='hobby' value='books'>From friends<br />" +
					"<h3>What is your workplace?</h3>" +
					"<input type='text' name='workplace' />" +
					"<input type='submit' value=' OK ' />" +
					"</form>" +
					"</body></html>");
		}
		
		out.close();
	}
	
}
