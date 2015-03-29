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

@WebServlet("/premium_action")
public class PremiumFormServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html><body>");
		out.println("<a href=\"index.jsp\">Main page</a>");
		
		String username = (String) request.getSession().getAttribute("username");
		
		if (username == null) {
			out.println("<p>You need to login to see this page!</p>");
			out.println("</html></body>");
			out.close();
			return;
		}
		
		if (!username.equals("admin")) {
			out.println("<p>You must be administrator to see this page!</p>");
			out.println("</html></body>");
			out.close();
			return;
		}
		
		@SuppressWarnings("unchecked")
		List<UserData> userList = (List<UserData>) request.getServletContext().getAttribute("userList");
		
		if (userList != null) {
			for (UserData user : userList) {
				if (user.getUsername().equals(request.getParameter("username"))) {
					user.setPremium(!user.isPremium());
					
					out.println("<p>Premium changed successfully!</p>");
					out.println("</html></body>");
					out.close();
					return;
				}
			}
		}

		out.println("<p>User not found!</p>");
		out.println("</html></body>");
		out.close();
	}
	
}
