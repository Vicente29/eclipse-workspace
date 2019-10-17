package Explicacion_MySql;

public class Coche {

	private String matricula,marca,modelo;
	private Cliente cliente;
	
	public Coche() {
		
	}
	
	protected String getMatricula() {
		return matricula;
	}
	protected void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	protected String getMarca() {
		return marca;
	}
	protected void setMarca(String marca) {
		this.marca = marca;
	}
	protected String getModelo() {
		return modelo;
	}
	protected void setModelo(String modelo) {
		this.modelo = modelo;
	}
	protected Cliente getCliente() {
		return cliente;
	}
	protected void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	
	
	
	
}
