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

@WebServlet("/dodaj_adres_action")
public class DodajAdresServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html><body>");
		out.println("<a href=\"index.jsp\">Main page</a>");

		String typAdresu = request.getParameter("typ_adresu");
		String wojewodztwo = request.getParameter("wojewodztwo");
		String miasto = request.getParameter("miasto");
		String kodPocztowy = request.getParameter("kod_pocztowy");
		String ulica = request.getParameter("ulica");
		String nrDomuMieszkania = request.getParameter("nr_domu");
	
		String username = (String) request.getSession().getAttribute("username");
		
		@SuppressWarnings("unchecked")
		List<UserData> userList = (List<UserData>) request.getServletContext().getAttribute("userList");
		
		for (UserData user : userList) {
			if (user.getUsername().equals(username)) {
				AdresData adres = null;
				if (request.getParameter("id") != null) {
					for (AdresData adresData : user.getAdresy()) {
						if (adresData.getId() == Integer.parseInt(request.getParameter("id"))) {
							adres = adresData;
						}
					}
				} else {
					adres = new AdresData();
					adres.setId(user.getAdresy().size());

					user.getAdresy().add(adres);
				}
				
				adres.setTypAdresu(typAdresu);
				adres.setWojewodztwo(wojewodztwo);
				adres.setUlica(ulica);
				adres.setMiasto(miasto);
				adres.setKodPocztowy(kodPocztowy);
				adres.setNrDomuMieszkania(nrDomuMieszkania);
			}
		}
		
	
		
		out.println("<p>Address created successfully!</p>");
		out.println("</html></body>");
		out.close();
	}
}
