package Clases;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;

public class Pedido {
	String cod;
	Date fecha_salida;
	Date fecha_llegada;
	
	private ArrayList<Producto> productos;

	public Pedido() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Pedido(String cod, Date fecha_salida, Date fecha_llegada, ArrayList<Producto> productos) {
		super();
		this.cod = cod;
		this.fecha_salida = fecha_salida;
		this.fecha_llegada = fecha_llegada;
		this.productos = productos;
	}

	public String getCod() {
		return cod;
	}

	public void setCod(String cod) {
		this.cod = cod;
	}

	public Date getFecha_salida() {
		return fecha_salida;
	}

	public void setFecha_salida(Date fecha_salida) {
		this.fecha_salida = fecha_salida;
	}

	public Date getFecha_llegada() {
		return fecha_llegada;
	}

	public void setFecha_llegada(Date fecha_llegada) {
		this.fecha_llegada = fecha_llegada;
	}

	public ArrayList<Producto> getProductos() {
		return productos;
	}

	public void setProductos(ArrayList<Producto> productos) {
		this.productos = productos;
	}

	@Override
	public String toString() {
		return "Pedido [cod=" + cod + ", fecha_salida=" + fecha_salida + ", fecha_llegada=" + fecha_llegada
				+ ", productos=" + productos + "]";
	}
	
	

}
