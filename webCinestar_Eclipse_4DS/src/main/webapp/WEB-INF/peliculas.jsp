<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

	
<br /> <h1>Cartelera</h1><br />
<%   String[][] peliculas = (String[][]) session.getAttribute("peliculas");
	 for (String[] pelicula : peliculas) {
%>	
<div class="contenido-pelicula">
	<div class="datos-pelicula">
		<h2><%= pelicula[1] %></h2>
		<br />
		<p><%= pelicula[2] %></p>
		<br />
		<div class="boton-pelicula">
			<a
				href="./peliculas?id=<%= pelicula[0]%>">
				<img src="img/varios/btn-mas-info.jpg" width="120" height="30"
				alt="Ver info" />
			</a>
		</div>
		<div class="boton-pelicula">
			<a href="https://www.youtube.com/v/<%= pelicula[3] %>" target=_blank
				onclick="return hs.htmlExpand(this, { objectType: 'iframe' } )">
				<img src="img/varios/btn-trailer.jpg" width="120" height="30"
				alt="Ver trailer" />
			</a>
		</div>
	</div>
	<img src="img/pelicula/<%= pelicula[0] %>.jpg" width="160" height="226" /><br /> <br />
</div>
<% 	 } %>