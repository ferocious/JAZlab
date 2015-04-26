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

@WebServlet("/adres_form")
public class AdresFormServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String username = (String) request.getSession().getAttribute("username");
		
		@SuppressWarnings("unchecked")
		List<UserData> userList = (List<UserData>) request.getServletContext().getAttribute("userList");

		AdresData adres = null;
		for (UserData user : userList) {
			if (username.equals(user.getUsername())){
				if (request.getParameter("id") != null) {
					for (AdresData adresData : user.getAdresy()) {
						if (adresData.getId() == Integer.parseInt(request.getParameter("id"))) {
							adres = adresData;
							break;
						}
					}
				}
			}
		}

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html><body>");
		out.println("<a href=\"index.jsp\">Main page</a>");

		out.println("<form action=\"dodaj_adres_action\" method=\"POST\">");
		
		if (request.getParameter("id") != null) {
			out.println("<input type=\"hidden\" name=\"id\" value=\"" + request.getParameter("id") + "\">");
		}
		
		out.println("<table>");
		
		out.println("<tr><td>Typ adresu</td>");
		out.println("<td><select name =\"typ_adresu\">");
		out.println("<option value=\"Zameldowania\">Zameldowania</option>");
		out.println("<option value=\"Korespondencyjny\">Korespondencyjny</option>");
		out.println("<option value=\"Pracy\">Pracy</option>");
		out.println("</select></td></tr>");
		
		out.println("<tr><td>Województwo</td>");
		out.println("<td><select name =\"wojewodztwo\">");
		out.println("<option value=\"Pomorskie\">Pomorskie</option>");
		out.println("<option value=\"Mazowieckie\">Mazowieckie</option>");
		out.println("<option value=\"Warminsko-Mazurskie\">Warmiñsko-Mazurskie</option>");
		out.println("<option value=\"Kujawsko-Pomorskie\">Kujawsko-Pomorskie</option>");
		out.println("<option value=\"Lubuskie\">Lubuskie</option>");
		out.println("<option value=\"Lubelskie\">Lubelskie</option>");
		out.println("<option value=\"Dolnoslaskie\">Dolnoœl¹skie</option>");
		out.println("<option value=\"Lodzkie\">£ódzkie</option>");
		out.println("<option value=\"Malopolskie\">Ma³opolskie</option>");
		out.println("<option value=\"Opolskie\">Opolskie</option>");
		out.println("<option value=\"Podkarpackie\">Podkarpackie</option>");
		out.println("<option value=\"Podlaskie\">Podlaskie</option>");
		out.println("<option value=\"Slaskie\">Œl¹skie</option>");
		out.println("<option value=\"Swietokrzyskie\">Œwiêtokrzyskie</option>");
		out.println("<option value=\"Wielkopolskie\">Wielkopolskie</option>");
		out.println("<option value=\"Zacjodniopomorskie\">Zachodniopomorskie</option>");
		out.println("</select></td></tr>");

		out.println("<tr><td>Miasto</td><td><input type=\"text\" name=\"miasto\" value=\"" + adres.getMiasto() + "\"/></td></tr>");
		out.println("<tr><td>Kod pocztowy</td><td><input type=\"text\" name=\"kod_pocztowy\" value=\"" + adres.getKodPocztowy() + "\" /></td></tr>");
		out.println("<tr><td>Ulica</td><td><input type=\"text\" name=\"ulica\" value=\"" + adres.getUlica() + "\"/></td></tr>");
		out.println("<tr><td>Nr domu/mieszkania</td><td><input type=\"text\" name=\"nr_domu\" value=\"" + adres.getNrDomuMieszkania() + "\"/></td></tr>");
		out.println("<tr><td></td><td><input type=\"submit\" value=\"Dodaj!\" /></td></tr>");
		
		out.println("</table>");
		out.println("</form>");

		out.println("</html></body>");
		out.close();
	}
}
