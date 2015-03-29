package com.example.zad2.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.zad2.domain.UserData;

@WebServlet("/user_profile")
public class UserProfileServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html><body>");
		out.println("<a href=\"index.jsp\">Main page</a>");
		
		String username = (String) request.getSession().getAttribute("username");
		
		@SuppressWarnings("unchecked")
		List<UserData> userList = (List<UserData>) request.getServletContext().getAttribute("userList");
		
		if (userList != null) {
			for (UserData user : userList) {
				if (user.getUsername().equals(username)) {
					out.println("<p>User data</p>");
					out.println("<table>");
					out.println("<tr><td>Username:</td><td>" + user.getUsername() + "</td></tr>");
					out.println("<tr><td>Email:</td><td>" + user.getEmail() + "</td></tr>");
					out.println("</table>");
					out.println("</html></body>");
					out.close();
				}
			}
		}
	}
	
}
