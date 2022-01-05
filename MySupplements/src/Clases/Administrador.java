package Clases;

import java.io.Serializable;

public class Administrador implements Serializable{
	

	private static final long serialVersionUID = 1L;
	private String dni;
	private String contrasenya;
	
	
	public Administrador(String dni, String contrasenya) {
		super();
		this.dni = dni;
		this.contrasenya = contrasenya;
	}
	
	
	@Override
	public String toString() {
		return "Administrador [dni=" + dni + ", contrasenya=" + contrasenya + "]";
	}


	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}

	
	public String getContrasenya() {
		return contrasenya;
	}
	public void setContrasenya(String contrasenya) {
		this.contrasenya = contrasenya;
	}
	

}
