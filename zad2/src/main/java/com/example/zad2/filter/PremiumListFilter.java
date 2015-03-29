package com.example.zad2.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@WebFilter("/premium_list")
public class PremiumListFilter implements Filter {

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		HttpSession session = ((HttpServletRequest) request).getSession();
		String username = (String) session.getAttribute("username");
		
		if (username == null) {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println("<html><body>");
			out.println("<a href=\"index.jsp\">Main page</a>");
			out.println("<p>You need to login to see this page!</p>");
			out.println("</html></body>");
			out.close();
			return;
		} else if (username.equals("admin")) {
			chain.doFilter(request, response);
			return;
		}
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html><body>");
		out.println("<a href=\"index.jsp\">Main page</a>");
		out.println("<p>This page is accessible only to administrators!</p>");
		out.println("</html></body>");
		out.close();
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

}
