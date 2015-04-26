<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Demo application</title>
    </head>
    
    <body>
        <h2>Dodaj adres</h2>
        
        <a href="index.jsp">Main page</a>
        
        <div>
        	<form action="dodaj_adres_action" method="POST">
        		<table>
        			<tr>
        				<td>Typ adresu</td>
        				<td>
        				 	<select name = "typ_adresu">
							  <option value="Zameldowania">Zameldowania</option>
							  <option value="Korespondencyjny">Korespondencyjny</option>
							  <option value="Pracy">Pracy</option>
							</select> 
        				</td>
        			</tr>
        			<tr>
        				<td>Województwo</td>
        				<td>
        				 	<select name = "wojewodztwo">
							  <option value="Pomorskie">Pomorskie</option>
							  <option value="Mazowieckie">Mazowieckie</option>
							  <option value="Warminsko-Mazurskie">Warmińsko-Mazurskie</option>
							  <option value="Kujawsko-Pomorskie">Kujawsko-Pomorskie</option>
  							  <option value="Lubuskie">Lubuskie</option>
  							  <option value="Lubelskie">Lubelskie</option>
  							  <option value="Dolnoslaskie">Dolnośląskie</option>
  							  <option value="Lodzkie">Łódzkie</option>
  							  <option value="Malopolskie">Małopolskie</option>
  							  <option value="Opolskie">Opolskie</option>
  							  <option value="Podkarpackie">Podkarpackie</option>
  							  <option value="Podlaskie">Podlaskie</option>
  							  <option value="Slaskie">Śląskie</option>
  							  <option value="Swietokrzyskie">Świętokrzyskie</option>
  							  <option value="Wielkopolskie">Wielkopolskie</option>
  							  <option value="Zacjodniopomorskie">Zachodniopomorskie</option>
  											  							  
							</select> 
        				</td>
        			</tr>
        			<tr>
        				<td>Miasto</td><td><input type="text" name="miasto" /></td>
        			</tr>
        			<tr>
        				<td>Kod pocztowy</td><td><input type="text" name="kod_pocztowy" /></td>
        			</tr>
        			<tr>
        				<td>Ulica</td><td><input type="text" name="ulica" /></td>
        			</tr>
        			<tr>
        				<td>Nr domu/mieszkania</td><td><input type="text" name="nr_domu" /></td>
        			</tr>
        			<tr>
        				<td></td><td><input type="submit" value="Dodaj!" /></td>
        			</tr>
        		</table>
        	</form>
        </div>
    </body>
</html>
