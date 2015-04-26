package com.example.zad2.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.zad2.domain.AdresData;
import com.example.zad2.domain.UserData;

@WebServlet("/adresy")
public class AdresyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html><body>");
		out.println("<a href=\"index.jsp\">Main page</a>");
		
		String username = (String) request.getSession().getAttribute("username");
		
		@SuppressWarnings("unchecked")
		List<UserData> userList = (List<UserData>) request.getServletContext().getAttribute("userList");

		for (UserData user : userList) {
			if (username.equals(user.getUsername())){
				if (request.getParameter("usun") != null) {
					for (AdresData adres : user.getAdresy()) {
						if (adres.getId() == Integer.parseInt(request.getParameter("usun"))) {
							user.getAdresy().remove(adres);
							break;
						}
					}
				}
				
				if (request.getParameter("edytuj") != null) {
					for (AdresData adres : user.getAdresy()) {
						if (adres.getId() == Integer.parseInt(request.getParameter("edytuj"))) {
							user.getAdresy().remove(adres);
							break;
						}
					}
				}
				
				out.println("<table border = 1 ><tr><td>Typ Adresu</td><td>Województwo</td><td>Miasto</td><td>Kod POcztowy</td><td>Ulica</td><td>Nr Domu / Mieszkania</td><td>Usun</td><td>Edytuj</td></tr>");
				
				for (AdresData adres : user.getAdresy()){
					out.println("<tr>");
					out.println("<td>" + adres.getTypAdresu() + "</td>");
					out.println("<td>" + adres.getWojewodztwo() + "</td>");
					out.println("<td>" + adres.getMiasto() + "</td>");
					out.println("<td>" + adres.getKodPocztowy() + "</td>");
					out.println("<td>" + adres.getUlica() + "</td>");
					out.println("<td>" + adres.getNrDomuMieszkania() + "</td>");
					out.println("<td><a href=\"adresy?usun=" + adres.getId() + "\">Usun</a></td>");
					out.println("<td><a href=\"adres_form?id=" + adres.getId() + "\">Edytuj</a></td>");
					out.println("</tr>");
				}
				
			out.println("</table>");
			
			out.println("<a href = \"dodaj_adres.jsp\">Dodaj nowy adres</a>");
			
			}
		}
	}

}
