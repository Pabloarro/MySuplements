package Clases;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;

public class Pedido {
	int cod;
	Date fecha_salida;
	Date fecha_llegada;
	
	private HashMap <String, Double> dineroPorCliente;
	private LinkedList<Pedido> pedidos;
	

	
    
    
	public Pedido() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public Pedido(int cod, Date fecha_salida, Date fecha_llegada, HashMap<String, Double> dineroPorCliente,
			LinkedList<Pedido> pedidos) {
		super();
		this.cod = cod;
		this.fecha_salida = fecha_salida;
		this.fecha_llegada = fecha_llegada;
		this.dineroPorCliente = dineroPorCliente;
		this.pedidos = pedidos;
	}
	
	
	


	@Override
	public String toString() {
		return "Pedido [cod=" + cod + ", fecha_salida=" + fecha_salida + ", fecha_llegada=" + fecha_llegada
				+ ", dineroPorCliente=" + dineroPorCliente + ", pedidos=" + pedidos + "]";
	}


	public int getCod() {
		return cod;
	}


	public void setCod(int cod) {
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


	public HashMap<String, Double> getDineroPorCliente() {
		return dineroPorCliente;
	}


	public void setDineroPorCliente(HashMap<String, Double> dineroPorCliente) {
		this.dineroPorCliente = dineroPorCliente;
	}


	public LinkedList<Pedido> getPedidos() {
		return pedidos;
	}


	public void setPedidos(LinkedList<Pedido> pedidos) {
		this.pedidos = pedidos;
	}
	//LEER FICHERO BINARIO Y CARGAR LISTA PEDIDOS
	public void cargarDatos() {
		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream(newFileInputStream("Pedidos.dat"));
			pedidos = (LinkedList<Pedido>)ois.readObject();
		}catch(FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(ois!=null) {
				try {
					ois.close();
				}catch(IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	private InputStream newFileInputStream(String string) {
		// TODO Auto-generated method stub
		return null;
	}


	//GUARDAR EL DINERO GASTADO POR CLIENTE EN UN FICHERO DE TEXTO
	public void guardarDatos() {
		PrintWriter pw = null;
		try {
			pw = new PrintWriter("Clientes.txt");
			for(String cliente: dineroPorCliente.keySet())
			{
				Double dinero = dineroPorCliente.get(cliente);
				pw.println(cliente + " " + dinero);
			}
		}catch(FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(pw!=null) {
				pw.flush();
				pw.close();
			}
		}
	}
	//METODO PARA MOSTRAR PEDIDO
	public void mostrarPedidos() {
		for(Pedido p:pedidos) {
			System.out.println(p);
		}
	}
	//METODO PARA AÑADIR PEDIDO
	public void anyadirPedido(Pedido p) {
		pedidos.add(p);
	}
}
