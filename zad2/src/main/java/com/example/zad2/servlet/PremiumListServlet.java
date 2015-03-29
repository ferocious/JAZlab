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

@WebServlet("/premium_list")
public class PremiumListServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html><body>");
		out.println("<a href=\"index.jsp\">Main page</a>");
		
		out.println("<form action=\"premium_action\" method=\"POST\">");
		out.println("<table><tr><td>Username</td><td><input type=\"text\" name=\"username\" /></td>");
		out.println("<td><input type=\"submit\" value=\"Change premium\" /></td></tr>");
		out.println("</table></form>");
		
		@SuppressWarnings("unchecked")
		List<UserData> userList = (List<UserData>) request.getServletContext().getAttribute("userList");
		
		if (userList != null) {
			out.println("<table>");

			for (UserData user : userList) {
				out.println("<tr>");
				out.println("<td><b>Username:</b></td><td>" + user.getUsername() + "</td>");
				out.println("<td><b>Premium:</b></td><td>" + user.isPremium() + "</td>");
				out.println("</tr>");
			}
			
			out.println("</table>");
		}

		out.close();
	}
	
}
