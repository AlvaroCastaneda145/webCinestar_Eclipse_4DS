package bean;

public class Distrito {
	int id;
	String Detalle;
	public Distrito(int id, String detalle) {
		super();
		this.id = id;
		Detalle = detalle;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDetalle() {
		return Detalle;
	}
	public void setDetalle(String detalle) {
		Detalle = detalle;
	}
	
	

}
