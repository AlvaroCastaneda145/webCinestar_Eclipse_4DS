package bean;

public class Cine {
	int id,Salas,idDistrito;
	String RazonSocial,Direccion,Telefonos,Detalle;
	Distrito distrito;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSalas() {
		return Salas;
	}
	public void setSalas(int salas) {
		Salas = salas;
	}
	public int getIdDistrito() {
		return idDistrito;
	}
	public void setIdDistrito(int idDistrito) {
		this.idDistrito = idDistrito;
	}
	public String getRazonSocial() {
		return RazonSocial;
	}
	public void setRazonSocial(String razonSocial) {
		RazonSocial = razonSocial;
	}
	public String getDireccion() {
		return Direccion;
	}
	public void setDireccion(String direccion) {
		Direccion = direccion;
	}
	public String getTelefonos() {
		return Telefonos;
	}
	public void setTelefonos(String telefonos) {
		Telefonos = telefonos;
	}
	public String getDetalle() {
		return Detalle;
	}
	public void setDetalle(String detalle) {
		Detalle = detalle;
	}
	public Distrito getDistrito() {
		return distrito;
	}
	public void setDistrito(Distrito distrito) {
		this.distrito = distrito;
	}
	
	public Cine( String[] aRegistro) {
		id = Integer.parseInt( aRegistro[0] );
		RazonSocial = aRegistro[1];
		Salas = Integer.parseInt( aRegistro[2] );
		idDistrito = Integer.parseInt( aRegistro[3] );
		Direccion = aRegistro[4];
		Telefonos = aRegistro[5];
		
		Detalle = aRegistro[6];
		
		distrito.setId( idDistrito );
		distrito.setDetalle( Detalle );
	}
	
	
	
}
