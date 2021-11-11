package Clases;

public class Administrador {
	private String dni;
	private String nombre;
	private String apellido;
	private String contrasenya;
	
	
	public Administrador(String dni, String nombre, String apellido, String contrasenya) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
		this.contrasenya = contrasenya;
	}
	@Override
	public String toString() {
		return "Administrador [dni=" + dni + ", nombre=" + nombre + ", apellido=" + apellido + ", contrasenya="
				+ contrasenya + "]";
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getContrasenya() {
		return contrasenya;
	}
	public void setContrasenya(String contrasenya) {
		this.contrasenya = contrasenya;
	}
	

}
