package Clases;

public class Producto implements Comparable<Producto>{
	
	private int cod;
	private float precio;
	private String nombre;
	private String imagen;

	
	public Producto(int codigo_Producto, float precio, String nom,String urlimagen) {
		super();
		this.cod = codigo_Producto;
		this.precio = precio;
		this.nombre = nom;
		this.imagen = urlimagen;
	}
	public Producto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public int getCod() {
		return cod;
	}
	public void setCod(int cod) {
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
	
	
	public String getImagen() {
		return imagen;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	@Override
	public int compareTo(Producto p) {
		
		return p.getNombre().compareTo(this.getNombre());
	}
	

	
}

