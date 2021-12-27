package Clases;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import Ventanas.VentanaPrincipal;

public class Listaproductos {
	private static ArrayList<Producto> listaProductos;

	public static ArrayList<Producto> getListaProductos() {
		return listaProductos;
	}


	public static void main(String[] args) {
		Listaproductos lp = new Listaproductos();
		new VentanaPrincipal();
	}
	
	public Listaproductos() {
		listaProductos = new ArrayList<>();
		cargarProductosdeFichero(listaProductos);
		
	}
	
	/**
	 * Método que carga los productos del fichero a una Arraylist
	 * @param a lista donde se van a cargar los productos
	 */
	public void cargarProductosdeFichero(ArrayList<Producto> a) {
		BufferedReader br = null;
		try {
			br=new BufferedReader(new FileReader("productos.txt"));
			String linea = br.readLine();
			while(linea!=null) {
				String [] datos= linea.split("\t");
				int cod=Integer.parseInt(datos[0]);
				if(cod % 2 == 0) {
					Float pr = Float.valueOf(datos[1]);
					String nom = datos[2];
					String url= datos[3];
					String mat = datos[4];
					a.add(new ProductoMerchandise(cod, pr, nom, url, mat));
				}else {
					Float pr = Float.valueOf(datos[1]);
					String nom = datos[2];
					String url= datos[3];
					int prot = Integer.parseInt(datos[4]);
					int grasas= Integer.parseInt(datos[5]);
					int hid= Integer.parseInt(datos[6]);
					int cal = Integer.parseInt(datos[7]);
					a.add(new ProductoSuplementos(cod, pr, nom, url, prot, grasas, hid, cal));
					
				}
					}
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
				
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
	
	/**
	 * Método que actualiza el fichero de productos añadiendolos nuevos productos que se hayan creado
	 * @param a lista de productos que se va a guardar en el fichero
	 */
	public void ActualizarFicheroProductos(ArrayList<Producto> a) {
		PrintWriter pw = null;
		
		try {
			pw = new PrintWriter("productos.txt");
			for(Producto p  : a) {
				if(p instanceof ProductoSuplementos) {
					ProductoSuplementos ps = (ProductoSuplementos) p;
					pw.println(ps.getCod()+"\t"+ps.getPrecio()+"\t"+ps.getNombre()+"\t"+ps.getImagen()+"\t"+ps.getProteinas()+"\t"+ps.getGrasas()+"\t"+ps.getHidratos()+"\t"+ps.getCalorias());
				}else {
					ProductoMerchandise pm = (ProductoMerchandise) p;
					pw.println(pm.getCod()+"\t"+pm.getPrecio()+"\t"+pm.getNombre()+"\t"+pm.getImagen()+"\t"+pm.getMaterial());
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}finally {
			if(pw!=null) {
				pw.flush();
				pw.close();
			}
		}
		
	}
	
}
