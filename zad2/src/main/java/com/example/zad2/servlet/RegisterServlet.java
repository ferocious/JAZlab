package com.example.zad2.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.zad2.domain.UserData;

@WebServlet("/register_action")
public class RegisterServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	public void init(ServletConfig config) throws ServletException {
		ServletContext ctx = config.getServletContext();

		if (ctx.getAttribute("userList") == null) {
			List<UserData> userList = new ArrayList<UserData>();
			
			UserData user = new UserData();
			user.setUsername("admin");
			user.setPassword("admin");
			user.setEmail("admin");
			
			userList.add(user);
			
			ctx.setAttribute("userList", userList);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html><body>");
		out.println("<a href=\"index.jsp\">Main page</a>");

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String passwordConfirm = request.getParameter("confirm_password");
		String email = request.getParameter("email");
		
		if (username.isEmpty() || password.isEmpty() || passwordConfirm.isEmpty() || email.isEmpty()) {
			out.println("<p>All fields must be filled!</p>");
			out.println("</html></body>");
			out.close();
			return;
		}
		
		if (!password.equals(passwordConfirm)) {
			out.println("<p>Passwords must be the same!</p>");
			out.println("</html></body>");
			out.close();
			return;
		}
		
		@SuppressWarnings("unchecked")
		List<UserData> userList = (List<UserData>) request.getServletContext().getAttribute("userList");
		
		for (UserData user : userList) {
			if (user.getUsername().equals(username)) {
				out.println("<p>User already exists!</p>");
				out.println("</html></body>");
				out.close();
				return;
			}
		}
		
		UserData user = new UserData();
		user.setUsername(username);
		user.setPassword(password);
		user.setEmail(email);
		user.setPremium(false); 
		
		userList.add(user);
		
		out.println("<p>User created successfully!</p>");
		out.println("</html></body>");
		out.close();
	}
}
