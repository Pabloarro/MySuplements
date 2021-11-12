package Clases;

import java.util.HashMap;

public class Cliente {
	String nom;
	String dni;
	int edad;


	public Cliente() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Cliente(String nom, String dni, int edad) {
		super();
		this.nom = nom;
		this.dni = dni;
		this.edad = edad;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
	@Override
	public String toString() {
		return "Cliente [nom=" + nom + ", dni=" + dni + ", edad=" + edad + "]";
	}
	
	public void guardarDatos() {
		
	}
	
	public void cargarDatos() {
		
	}
}
