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

@WebServlet("/login_action")
public class LoginServlet extends HttpServlet {

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
		
		if (username.isEmpty() || password.isEmpty()) {
			out.println("<p>All fields must be filled!</p>");
			out.println("</html></body>");
			out.close();
			return;
		}
		
		@SuppressWarnings("unchecked")
		List<UserData> userList = (List<UserData>) request.getServletContext().getAttribute("userList");
		
		if (userList != null) {
			for (UserData user : userList) {
				if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
					request.getSession().setAttribute("username", username);
					
					out.println("<p>Authentication was successful!</p>");
					out.println("</html></body>");
					out.close();
					
					return;
				}
			}
		}
		
		Integer failedLogins = (Integer) request.getSession().getAttribute("failedLogins");
		if (failedLogins == null) {
			failedLogins = 1;
			request.getSession().setAttribute("failedLogins", failedLogins);
		} else {
			request.getSession().setAttribute("failedLogins", failedLogins + 1);
		}
		
		out.println("<p>Username or password is incorrect!</p>");
		out.println("</html></body>");
		out.close();
	}
	
}
