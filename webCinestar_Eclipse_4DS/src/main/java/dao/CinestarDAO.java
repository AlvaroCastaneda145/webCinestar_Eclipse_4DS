package dao;

import db.Db;

public class CinestarDAO {
	Db db = new Db("cinestar");

	public Object getCines() {
		db.Sentencia("call sp_getCines()");
		return db.getRegistros();
	}
	
	public Object getCine(String id) {
		db.Sentencia("call sp_getCine()");
		return db.getRegistro();	}
	
	public Object getPeliculas(String id) {                                  	 
		id = id.equals("cartelera") ? "1" : "2";								
	    db.Sentencia( String.format("call sp_getPeliculas(%s)", id) );      	
	    return db.getRegistros();												
	}
	
	public Object getPelicula(String id) {
	    db.Sentencia( String.format("call sp_getPelicula(%s)", id) );
	    return db.getRegistro();
	}

}
