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

public class Pedido {
	private int cod;
    private Date fec;
	private Cliente cliente;
	private Producto producto;
	
	private HashMap <String, Double> dineroPorCliente;
	private ArrayList<Pedido> listaPedidos;
    
	private static SimpleDateFormat sdf = new SimpleDateFormat( "dd/MM/yy HH:mm:ss" );

	public Pedido() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Pedido(int cod, Date fec, Cliente cliente, Producto producto, HashMap<String, Double> dineroPorCliente,
			ArrayList<Pedido> listaPedidos) {
		super();
		this.cod = cod;
		this.fec = fec;
		this.cliente = cliente;
		this.producto = producto;
		this.dineroPorCliente = dineroPorCliente;
		this.listaPedidos = listaPedidos;
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

	public HashMap<String, Double> getDineroPorCliente() {
		return dineroPorCliente;
	}

	public void setDineroPorCliente(HashMap<String, Double> dineroPorCliente) {
		this.dineroPorCliente = dineroPorCliente;
	}

	public ArrayList<Pedido> getListaPedidos() {
		return listaPedidos;
	}

	public void setListaPedidos(ArrayList<Pedido> listaPedidos) {
		this.listaPedidos = listaPedidos;
	}

	public static SimpleDateFormat getSdf() {
		return sdf;
	}

	public static void setSdf(SimpleDateFormat sdf) {
		Pedido.sdf = sdf;
	}
	
	
	

	

