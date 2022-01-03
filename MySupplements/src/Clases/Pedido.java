package Clases;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.TreeMap;

import BaseDatos.BaseDatos;
import Ventanas.VentanaPrincipal;

public class Pedido {
	static int cod;	
	private int codpe;//TODO		/*Hay que hacer que se obtenga de la bd cada vez que se inicie */
    private long fec;
	private Cliente cliente;
	private ArrayList<Producto> Listaproductos;//En la tabla de la bd se introducirán en cada fila un producto del pedido.
												//Y luego en la clase pedido recogeremos la lista de productos que se han seleccionado

    	
	private  SimpleDateFormat sdf = new SimpleDateFormat( "dd/MM/yy HH:mm:ss" );


//Cuando se crea un pedido nuevo
	public Pedido(long fec, Cliente cliente, ArrayList<Producto> alproductos) {
		super();
		cod++;
		this.codpe=cod;
		this.fec = fec;
		this.cliente = cliente;
		this.Listaproductos = alproductos;

	}
	//Cuando se carga en pedido ya realizado de la base de datos
	public Pedido(int co,long fec, Cliente cliente, ArrayList<Producto> alproductos) {
		super();
		this.codpe = co;
		this.fec = fec;
		this.cliente = cliente;
		this.Listaproductos = alproductos;

	}
	//este es el codigo para crear un nuevo pedido
	public static int getCod() {
		return cod;
	}

	public static void setCod(int cod) {
		Pedido.cod = cod;
	}

	
	public int getCodpe() {
		return codpe;
	}
	public void setCodpe(int codpe) {
		this.codpe = codpe;
	}
	public long getFec() {
		return fec;
	}

	public void setFec(long fec) {
		this.fec = fec;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}



	public ArrayList<Producto> getListaproductos() {
		return Listaproductos;
	}

	public void setListaproductos(ArrayList<Producto> listaproductos) {
		Listaproductos = listaproductos;
	}

	public SimpleDateFormat getSdf() {
		return sdf;
	}

	public void setSdf(SimpleDateFormat sdf) {
		this.sdf = sdf;
	}
	
	
	
	
	@Override
	public String toString() {
		return "Pedido "+codpe+"\t [fec=" + fec + ", cliente=" + cliente.getDni() + ", Listaproductos=" + Listaproductos.size() + "]";
	}

	/**
	 * Método que guarda el siguiente código de pedido en un fichero, ya que en el constructor se suma 1 cada vez,
	 * el codigo que guardamos es uno menos que el siguiente.
	 */
	public static void GuardarSiguienteCodigodePedido() {
		PrintWriter pw = null;
		try {
			pw = new PrintWriter("Codigopedido.txt");
			pw.println(getCod());
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(pw!=null) {
				pw.flush();
				pw.close();
			}
		}
		
		
	}

	
	/**
	 * Método que obtiene el siguiente código del pedido de fichero
	 */
	public static  void ObtenerSiguienteCodigodePedido() {
		int cod = 0;
		BufferedReader br = null;
		try {
			 br = new BufferedReader(new FileReader("Codigopedido.txt"));
			String linea = br.readLine();
			 cod = Integer.parseInt(linea);
			 setCod(cod);	
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(br!=null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
}

	
	
	

	

