package model;

public class FacturaProducto {
	//Atributos de clase
	private int idFactura;
	private int idProducto;
	private int cantidad;
	
	//Constructores
	public FacturaProducto() {
		super();
	}

	public FacturaProducto(int idFactura, int idProducto, int cantidad) {
		super();
		this.idFactura = idFactura;
		this.idProducto = idProducto;
		this.cantidad = cantidad;
	}

	//Getters y setters
	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public int getIdFactura() {
		return idFactura;
	}

	public int getIdProducto() {
		return idProducto;
	}

	//toString para la impresión
	@Override
	public String toString() {
		return "FacturaProducto [idFactura=" + idFactura + ", idProducto=" + idProducto + ", cantidad=" + cantidad
				+ "]";
	}
}
