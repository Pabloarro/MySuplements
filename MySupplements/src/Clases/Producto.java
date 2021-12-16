package Clases;

import java.util.ArrayList;

public class Producto{
	
	private String cod;
	private float precio;
	private String nombre;
	//private ArrayList<Pedido> pedidosdeproducto;

	
	public Producto(String codigo_Producto, float precio, String nom,ArrayList<Pedido>pedidosdeproductos) {
		super();
		this.cod = codigo_Producto;
		this.precio = precio;
		this.nombre = nom;
	//	this.pedidosdeproducto = pedidosdeproductos;
	}
	public Producto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public String getCod() {
		return cod;
	}
	public void setCod(String cod) {
		this.cod = cod;
	}
	public float getPrecio() {
		return precio;
	}
	public void setPrecio(float precio) {
		this.precio = precio;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/*public ArrayList<Pedido> getPedidosdeproducto() {
		return pedidosdeproducto;
	}
	public void setPedidosdeproducto(ArrayList<Pedido> pedidosdeproducto) {
		this.pedidosdeproducto = pedidosdeproducto;
	}*/
	@Override
	public String toString() {
		return "Producto [nom=" + nombre + ", precio=" + precio + "]";
	}
	
}

