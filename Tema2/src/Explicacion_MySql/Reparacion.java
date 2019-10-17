package Explicacion_MySql;

import java.util.Date;

public class Reparacion {

	private int codigo;
	private tipoRep tipo;;
	private Coche matricula;
	private Date fecha;
	
	protected Reparacion() {
		
	}
	
	
	protected int getCodigo() {
		return codigo;
	}
	protected void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	protected tipoRep getTipo() {
		return tipo;
	}
	protected void setTipo(tipoRep tipo) {
		this.tipo = tipo;
	}
	protected Coche getMatricula() {
		return matricula;
	}
	protected void setMatricula(Coche matricula) {
		this.matricula = matricula;
	}
	protected Date getFecha() {
		return fecha;
	}
	protected void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	
	
	
}
