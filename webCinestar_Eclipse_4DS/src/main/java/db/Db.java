package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.table.DefaultTableModel;

public class Db {
	public final static int MySQL = 1;
	public final static int SQLServer = 2;
	int typeBD = 1;
	String _IP="localhost", _PORT="3306", _USER="root", _PASSWORD="1234";
	String _BD="cinestar", _SQL = "";
	Connection cn = null;
	PreparedStatement ps = null;

	public Db(String bd) {
		this._BD = bd;
		getConnection();
	}

	public Db(String ip, String user, String password, String bd) {
		this._IP = ip;
		this._USER = user;
		this._PASSWORD = password;
		this._BD = bd;
		getConnection();
	}

	public Db(String ip, String port, String user, String password, String bd, int typeBD) {
		this._IP = ip;
		this._PORT = port;
		this._USER = user;
		this._PASSWORD = password;
		this._BD = bd;
		this.typeBD = typeBD;
		getConnection();
	}

	public void getConnection() {
		try {
			switch (typeBD) {
			case MySQL:
				Class.forName("com.mysql.jdbc.Driver");
				cn = DriverManager.getConnection(String.format("jdbc:mysql://%s:%s/%s", _IP, _PORT, _BD), _USER,
						_PASSWORD);
				break;
			case SQLServer:
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				cn = DriverManager.getConnection(
						String.format("jdbc:sqlserver://%s:%s;database=%s;integratedSecurity=true;encrypt=false", _IP,
								_PORT, _BD),
						_USER, _PASSWORD);
				break;
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public void Sentencia(String sql) {
		if ( cn == null ) return;

		this._SQL = sql;
		try {
			ps = cn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		} catch (SQLException e) { e.printStackTrace(); }
	}

	public void Parametros(Object... parametros) {

	}

	public int Ejecutar() {
		if ( cn == null || ps == null ) return -1;

		try {
			return ps.executeUpdate();
		} catch (SQLException e) { e.printStackTrace(); }

		return -1;
	}

	public DefaultTableModel getDefaultTableModel() {
		if ( cn == null || ps == null ) return null;

		try {
			ResultSet rs = ps.executeQuery();
			if ( !rs.next() ) return null;

			rs.beforeFirst();
			DefaultTableModel modelo = new DefaultTableModel() {
				private static final long serialVersionUID = 1L;

				@Override
				public boolean isCellEditable(int rowIndex, int columnIndex) { return false; }
			};

			ResultSetMetaData rsmd = rs.getMetaData();
			int columnas = rsmd.getColumnCount();
			for( int i=0; i < columnas; i++ )
				modelo.addColumn( rsmd.getColumnName(i+1) );

			String[] aRegistro = new String[columnas];
			while ( rs.next() ) {
				for(int columna=0; columna < columnas; columna++)
					aRegistro[columna] = rs.getString( columna + 1 ).trim();

				modelo.addRow( aRegistro );
			}

			rs.close();
			return modelo;
		} catch (SQLException e) { e.printStackTrace(); }

		return null;
	}

	public String[] getRegistro() {
		if ( cn == null || ps == null ) return null;

		try {
			ResultSet rs = ps.executeQuery();
			if ( rs.next() ) {
				String[] aRegistro = new String[ rs.getMetaData().getColumnCount() ];
				for ( int i=0; i < aRegistro.length; i++ )
					aRegistro[i] = rs.getString(i+1).trim();

				
				return aRegistro;
			}
		} catch (SQLException e) { e.printStackTrace(); }

		return null;
	}

	public String[][] getRegistros() {
		if ( cn == null || ps == null ) return null;

		try {
			ResultSet rs = ps.executeQuery();
			if ( rs.last() ) {
				int columnas = rs.getMetaData().getColumnCount();
				int filas = rs.getRow();
				String[][] mRegistros = new String[filas][columnas];

				rs.beforeFirst();
				for ( int fila = 0; rs.next(); fila++ )
					for ( int columna = 0; columna < columnas; columna++ )
						mRegistros[fila][columna] = rs.getString(columna + 1);

				rs.close();
				return mRegistros;
			}
		} catch (SQLException e) { e.printStackTrace(); }

		return null;
	}
	
	public String getCampo() {
		if ( cn == null || ps == null ) return null;
		
		try {
			ResultSet rs = ps.executeQuery();
			if ( rs.next() ) rs.getString(0).trim();
		} catch (SQLException e) {e.printStackTrace(); }
		
		return null;
	}
}