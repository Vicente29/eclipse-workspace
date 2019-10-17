package Explicacion_MySql;

public class PiezaReparacion {

	private Pieza pieza;
	private Reparacion reparacion;
	private float precio;
	private int cantidad;
	
	protected PiezaReparacion() {
		
	}

	protected Pieza getPieza() {
		return pieza;
	}

	protected void setPieza(Pieza pieza) {
		this.pieza = pieza;
	}


	protected Reparacion getReparacion() {
		return reparacion;
	}

	protected void setReparacion(Reparacion reparacion) {
		this.reparacion = reparacion;
	}

	protected float getPrecio() {
		return precio;
	}

	protected void setPrecio(float precio) {
		this.precio = precio;
	}

	protected int getCantidad() {
		return cantidad;
	}

	protected void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	
	
	
}
