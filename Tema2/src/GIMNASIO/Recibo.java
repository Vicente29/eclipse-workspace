package GIMNASIO;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Recibo {

	private Cliente cliente_id;
	private Date fecha_emision;
	private Date fecha_pago;
	private float cuantia;
	private boolean pagado;
	
	public Recibo() {
		
	}

	public Recibo(Cliente cliente_id, Date fecha_emision, Date fecha_pago, float cuantia, boolean pagado) {
		
		this.cliente_id = cliente_id;
		this.fecha_emision = fecha_emision;
		this.fecha_pago = fecha_pago;
		this.cuantia = cuantia;
		this.pagado = pagado;
	}

	protected Cliente getCliente_id() {
		return cliente_id;
	}

	protected void setCliente_id(Cliente cliente_id) {
		this.cliente_id = cliente_id;
	}

	protected Date getFecha_emision() {
		return fecha_emision;
	}

	protected void setFecha_emision(Date fecha_emision) {
		this.fecha_emision = fecha_emision;
	}

	protected Date getFecha_pago() {
		return fecha_pago;
	}

	protected void setFecha_pago(Date fecha_pago) {
		this.fecha_pago = fecha_pago;
	}

	protected float getCuantia() {
		return cuantia;
	}

	protected void setCuantia(float cuantia) {
		this.cuantia = cuantia;
	}

	protected boolean isPagado() {
		return pagado;
	}

	protected void setPagado(boolean pagado) {
		this.pagado = pagado;
	}

	
	protected void mostrar_Recibo() {
		
		SimpleDateFormat formatoFecha =new SimpleDateFormat("dd/MM/yyyy");
		System.out.println("Cliente id: "+cliente_id.getDni()+
				"\tFecha emision: "+formatoFecha.format(fecha_emision)+
				"\tFecha de pago: "+formatoFecha.format(fecha_pago)+
				"\tCuantia: "+cuantia+
				"\tPagado: "+pagado);
		
	}
	
	
}
