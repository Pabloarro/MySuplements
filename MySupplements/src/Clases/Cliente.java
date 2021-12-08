package Clases;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Cliente {
	private String nom;
	private String dni;
	private String con;
	private Date fechanac;
	

	public Cliente() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Cliente(String nom,String con, String dni, Date fechanac) {
		super();
		this.con = con;
		this.nom = nom;
		this.dni = dni;
		this.fechanac = fechanac;
	}
	
	public String getCon() {
		return con;
	}
	public void setCon(String con) {
		this.con = con;
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

	
	public Date getFechanac() {
		return fechanac;
	}
	public void setFechanac(Date fechanac) {
		this.fechanac = fechanac;
	}
	
	private static SimpleDateFormat sdf = new SimpleDateFormat( "dd/MM/yy" );
	
	@Override
	public String toString() {
		return "Cliente [nom=" + nom + ", con=" + dni +", dni=" + dni + ", Fecha de nacimiento=" + sdf.format(fechanac) + "]";
	}
	

}
