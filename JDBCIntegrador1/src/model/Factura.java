package model;

public class Factura {
	//Atributos de la clase
	private int idFactura;
	private int idCliente;
	
	//Constructores
	public Factura() {
		super();
	}
	
	public Factura(int idFactura, int idCliente) {
		super();
		this.idFactura = idFactura;
		this.idCliente = idCliente;
	}

	//Getters y setters
	public int getIdFactura() {
		return idFactura;
	}

	public int getIdCliente() {
		return idCliente;
	}

	//toString para la impresión
	@Override
	public String toString() {
		return "Factura [idFactura=" + idFactura + ", idCliente=" + idCliente + "]";
	}
}
