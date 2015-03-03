package com.example.servletjspdemo.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.servletjspdemo.domain.RegistrationData;

/**
 * Servlet implementation class RegistrationListServlet
 */
@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String firstName = request.getParameter("firstname");
		String name = request.getParameter("name");
		String email = request.getParameter("email");

		RegistrationData data = new RegistrationData();
		data.setFirstName(firstName);
		data.setName(name);
		data.setEmail(email);

		@SuppressWarnings("unchecked")
		List<RegistrationData> registrationList = (List<RegistrationData>) request
				.getSession().getAttribute("registrationList");
		
		if (registrationList == null) {
			registrationList = new ArrayList<RegistrationData>();
			registrationList.add(data);
			request.getSession().setAttribute("registrationList", registrationList );
			out.println("<h3>Thank you, you have registered :)</h3>");
		}
		else if (registrationList != null) {
			if (registrationList.size() == 5) {
				out.println("<h3>Sorry, there are no more places available.</h3>");
				
				return;
			}
			
			for (int i = 0; i < registrationList.size(); i++) {
				if (firstName.equals(registrationList.get(i).getFirstName())) {
					if (name.equals(registrationList.get(i).getName())) {
						if (email.equals(registrationList.get(i).getEmail())) {
							
							out.println("<h3>Sorry, you have already registered :(</h3>");
							return;
						}
					}
				}
					
			}
			registrationList.add(data);
			out.println("<h3>Thank you, you have registered :)</h3>");
		}
		

	}
}
