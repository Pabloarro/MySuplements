package Clases;

public class Producto {
	String codigo_Producto;
	float precio;
	String nom;
	public Producto(String codigo_Producto, float precio, String nom) {
		super();
		this.codigo_Producto = codigo_Producto;
		this.precio = precio;
		this.nom = nom;
	}
	public Producto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getCodigo_Producto() {
		return codigo_Producto;
	}
	public void setCodigo_Producto(String codigo_Producto) {
		this.codigo_Producto = codigo_Producto;
	}
	public float getPrecio() {
		return precio;
	}
	public void setPrecio(float precio) {
		this.precio = precio;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	@Override
	public String toString() {
		return "Producto [nom=" + nom + ", precio=" + precio + "]";
	}
	
}

