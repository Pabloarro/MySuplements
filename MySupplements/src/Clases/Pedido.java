package Clases;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.TreeMap;

public class Pedido {
	private int cod;
    private Date fec;
	private Cliente cliente;
	private Producto producto;

    
	private static SimpleDateFormat sdf = new SimpleDateFormat( "dd/MM/yy HH:mm:ss" );

	public Pedido() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Pedido(int cod, Date fec, Cliente cliente, Producto producto) {
		super();
		this.cod = cod;
		this.fec = fec;
		this.cliente = cliente;
		this.producto = producto;

	}

	public int getCod() {
		return cod;
	}

	public void setCod(int cod) {
		this.cod = cod;
	}

	public Date getFec() {
		return fec;
	}

	public void setFec(Date fec) {
		this.fec = fec;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}


	public static SimpleDateFormat getSdf() {
		return sdf;
	}

	public static void setSdf(SimpleDateFormat sdf) {
		Pedido.sdf = sdf;
	}
}
	
	
	

	

