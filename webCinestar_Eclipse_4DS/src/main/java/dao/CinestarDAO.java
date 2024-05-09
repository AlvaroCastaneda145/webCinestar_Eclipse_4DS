package dao;

import db.Db;

public class CinestarDAO {
	Db db = new Db("cinestar");

	public Object getCines() {
		db.Sentencia("call sp_getCines()");
		return db.getRegistros();
	}

	public Object getPeliculas(int id) {
	    db.Sentencia("call sp_getPeliculas(" + id + ")");
	    return db.getRegistros();
	}

}
