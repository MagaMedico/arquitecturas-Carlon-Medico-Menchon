package model;

public class Producto {
	//Atributos de la clase
	private int idProducto;
	private String nombre;
	private float valor;
	
	//Constructores
	public Producto() {
		super();
	}
	
	public Producto(int idProducto, String nombre, float valor) {
		super();
		this.idProducto = idProducto;
		this.nombre = nombre;
		this.valor = valor;
	}

	//Getters y setters
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}

	public int getIdProducto() {
		return idProducto;
	}

	//toString para la impresión
	@Override
	public String toString() {
		return "Producto [idProducto=" + idProducto + ", nombre=" + nombre + ", valor=" + valor + "]";
	}
}
