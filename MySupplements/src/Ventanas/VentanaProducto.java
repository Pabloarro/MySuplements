package Ventanas;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;


import BaseDatos.BaseDatos;
import Clases.Producto;
import Clases.ProductoMerchandise;
import Clases.ProductoSuplementos;

public class VentanaProducto extends JFrame {

	private JPanel contentPane,panelNorte,panelSur,panelCentro,panelCentroDerecha,panelCentroDerechaAbajo,panelCentroDerechaAbajo1;
	private JLabel lblInfo,lblFiltro,lblLogo,lblSumaDinero;
	private JComboBox<String> comboFiltro;
	private JButton btnAtras;
	private static JButton btnVerPedido;
	private static JButton btnAdd;
	private JButton btnRealizarPedido,btnSalir;
	private JButton btnEditarPedido;
	private JButton btnBorrarPedido;
	private JButton btnAddDescuento;
	private static JButton btnCrearDescuento;
	private static JButton btnAddProductoNuevo;

	
	private JTable tablaProductos;
	private DefaultTableModel modeloTablaProductos;
	
	private ArrayList<Producto>listaPedido,alp;//lista de productos 
	
	private int cant;
	
	private JList<Producto> listaProductosPedidos;
	private DefaultListModel<Producto> modeloListaProductosPedidos;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaProducto frame = new VentanaProducto();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaProducto() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaPrincipal.class.getResource("/LOGO/logo_small_icon_only_inverted.png")));
		setTitle("PRODUCTOS");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		panelNorte = new JPanel();
		contentPane.add(panelNorte, BorderLayout.NORTH);
				
		comboFiltro = new JComboBox<>();
		comboFiltro.addItem("Todos los productos");
		comboFiltro.addItem("Precio mayor a menor");
		comboFiltro.addItem("Precio menor a mayor");
		comboFiltro.addItem("Suplementos");
		comboFiltro.addItem("Merchandise");
		comboFiltro.addItem("Orden de A-Z");
		
		comboFiltro.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(comboFiltro.getSelectedItem()=="Precio mayor a menor") {
					OrdenarListaMayoraMenor(alp, alp.size());
					vaciarTabla();
					modificarTablafiltro();
					agregarAtablaFiltro(alp);
				}else if (comboFiltro.getSelectedItem()=="Precio menor a mayor") {
					OrdenarListaMenoraMayor(alp);
					vaciarTabla();
					modificarTablafiltro();
					agregarAtablaFiltro(alp);
				}else if(comboFiltro.getSelectedItem()=="Orden de A-Z"){
					OrdenarListaAlfabetica(alp);
					vaciarTabla();
					modificarTablafiltro();
					agregarAtablaFiltro(alp);
				}else if(comboFiltro.getSelectedItem()=="Suplementos") {
					vaciarTabla();
					modificarTablafiltro();
					agregarAtablaFiltro(OrdenarListaSuplementos(alp));
					
				}else if(comboFiltro.getSelectedItem()=="Merchandise") {
					vaciarTabla();
					modificarTablafiltro();
					agregarAtablaFiltro(OrdenarListaMerchandise(alp));
				}else {
					ordenarListaCodigoAscendente(alp, alp.size());
					vaciarTabla();
					estructuratabla();
					agregarAtabla(alp);
				}
				
			}
		});
		
		modeloListaProductosPedidos = new DefaultListModel<>();
		listaProductosPedidos = new JList<>(modeloListaProductosPedidos);
		JScrollPane scrollLista = new JScrollPane(listaProductosPedidos);
		
		lblInfo= new JLabel();
		 cant = 0;
		lblInfo.setText("Productos en carrito: "+cant);
		
		lblFiltro = new JLabel();
		lblFiltro.setText("Filtrar Productos por:");
		
		lblSumaDinero = new JLabel();
		lblSumaDinero.setText("TOTAL: " + 0+ "€");
		 
		lblLogo = new JLabel();
		
		btnEditarPedido = new JButton("Editar Pedido");
		btnEditarPedido.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// POR HACER
				
			}
		});
		
		btnAddDescuento = new JButton("Añadir descuento");
		btnAddDescuento.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// POR HACER
				
			}
		});
		btnBorrarPedido = new JButton("Borrar Pedido");
		btnBorrarPedido.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// POR HACER
				
			}
		});
		
		btnCrearDescuento = new JButton("Crear Descuento");
		btnCrearDescuento.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// POR HACER
				
			}
		});
		
		btnAddProductoNuevo = new JButton("Añadir Producto");
		btnAddProductoNuevo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//POR HACER
				
			}
		});
		btnSalir = new JButton("Salir de la página");
		btnSalir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				
			}
		});
		btnAdd = new JButton("Añadir a carrito");
		btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			
				int pos = tablaProductos.getSelectedRow();
				listaPedido.add(alp.get(pos));
				//AÑADIR A LISTA Y LUEGO EN EL BOTON VER PEDIDO VISUALIZARLO
				cant++;
				lblInfo.setText("Productos en carrito: "+cant);
				btnVerPedido.setEnabled(true);
				
			}});
		
		btnVerPedido = new JButton("Ver Carrito");
		btnVerPedido.setEnabled(false);
		btnVerPedido.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				btnAdd.setVisible(false);
				lblInfo.setVisible(false);
				btnAtras.setVisible(true);
				btnRealizarPedido.setVisible(true);
				btnVerPedido.setEnabled(false);
				btnAdd.setEnabled(false);
				btnAtras.setText("Salir del carrito");
				panelCentro.setLayout(new GridLayout(0,2));
				panelCentroDerecha.setLayout(new GridLayout(2,0));
				panelCentro.add(panelCentroDerecha);
				panelCentroDerecha.add(scrollLista);
				panelCentroDerechaAbajo.add(btnEditarPedido);
				if(listaPedido == null) {
					panelCentroDerechaAbajo1.add(lblSumaDinero);
				}else {
					lblSumaDinero.setText("TOTAL: "+obtenerDineroTotal(listaPedido)+" €");
					panelCentroDerechaAbajo1.add(lblSumaDinero);
				}
				panelCentroDerechaAbajo.add(panelCentroDerechaAbajo1);
				panelCentroDerechaAbajo.add(btnBorrarPedido);
				panelCentroDerechaAbajo.add(btnAddDescuento);
				panelCentroDerecha.add(panelCentroDerechaAbajo);
				
			}
		});
		
		btnRealizarPedido = new JButton("Realizar Pedido");
		btnRealizarPedido.setVisible(false);
		
		btnAtras = new JButton("Salir del Carrito");
		btnAtras.setVisible(false);
		
		btnAtras.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				panelCentro.setLayout(new GridLayout(1,1));
				panelCentroDerecha.remove(scrollLista);
				panelCentro.remove(panelCentroDerecha);
				btnAtras.setVisible(false);
				btnRealizarPedido.setVisible(false);
				btnVerPedido.setEnabled(true);
				btnAdd.setEnabled(true);
				lblInfo.setVisible(true);
				panelCentroDerecha.remove(lblLogo);
				btnAdd.setVisible(true);
			}
		});
		btnVerPedido.setVisible(true);
		btnAdd.setVisible(true);
		btnCrearDescuento.setVisible(false);
		
		panelNorte.setLayout(new GridLayout(1,3));
		panelNorte.add(lblInfo);
		panelNorte.add(lblFiltro);
		panelNorte.add(comboFiltro);
		
		panelSur = new JPanel();

		contentPane.add(panelSur, BorderLayout.SOUTH);
		
		panelSur.add(btnSalir);
		panelSur.add(btnAtras);
		panelSur.add(btnVerPedido);
		panelSur.add(btnAdd);
		panelSur.add(btnRealizarPedido);
		panelSur.add(btnCrearDescuento);
		panelSur.add(btnAddProductoNuevo);
		btnCrearDescuento.setVisible(false);
		btnAddProductoNuevo.setVisible(false);
		panelCentro = new JPanel();
		contentPane.add(panelCentro, BorderLayout.CENTER);
		
		panelCentroDerecha = new JPanel();
		panelCentroDerecha.setLayout(new GridLayout(2,0));
		
		panelCentroDerechaAbajo = new JPanel();
		panelCentroDerechaAbajo.setLayout(new GridLayout(2,2));
		
		panelCentroDerechaAbajo1 = new JPanel();

		listaPedido = new ArrayList<>();
		
		
		/* JLIST 
		
		modeloListaProductosPedidos = new DefaultListModel<>();
		listaProductosPedidos = new JList<>(modeloListaProductosPedidos);
		JScrollPane scrollLista = new JScrollPane(listaProductosPedidos);
		*/
		/*
		listaProductos.setCellRenderer(new DefaultListCellRenderer() {
			public Component getListCellRendererComponent(JList<?> list,Object value,int index,boolean isSelected,boolean cellHasFocus) {
				Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
				Producto p = (Producto) value;
				if(p.getPrecio()<100) {
					c.setForeground(Color.RED);
				}else {
					c.setForeground(Color.BLACK);
				}
				return c;
			}
		});*/
		
		/*JTABLE*/
		
		modeloTablaProductos = new DefaultTableModel() {
			public boolean isCellEditable(int row, int col) {
				return false;
			}
		};
		
		estructuratabla();
		
		alp = new ArrayList<>();
	
	/*	BufferedReader br = null;
		try {
			br=new BufferedReader(new FileReader("productos.txt"));
			String linea = br.readLine();
			while(linea!=null) {
				String [] datos= linea.split("	");
				int cod=Integer.parseInt(datos[0]);
					Float pr = Float.valueOf(datos[1]);
					String nom = datos[2];
					String url= datos[3];
					String mat = datos[4];
					alp.add(new ProductoMerchandise(cod, pr, nom, url, mat));	
				
					}
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				}
		*/
	
		//1	59.99	Caseina	/FOTOS/caseina.jpg	50	10	15	360
		alp.add(new ProductoSuplementos(1,(float) 59.99,"Caseina","/FOTOS/caseina.jpg",50,10,15,360));
		alp.add(new ProductoSuplementos(3,(float) 49.99,"Proteina en polvo","/FOTOS/proteina.jpg",46,8,18,346));

		//2	20	Sudadera con gorro	/FOTOS/sudaderaGorro.jpg	algodón
		alp.add(new ProductoMerchandise(2,(float) 20,"Sudadera con goro","/FOTOS/sudaderaGorro.jpg", "algodón"));
		
		ordenarListaCodigoAscendente(alp, alp.size());
		agregarAtabla(alp);
		
		tablaProductos = new JTable(modeloTablaProductos);
		JScrollPane scrollTabla = new JScrollPane(tablaProductos);

		tablaProductos.addMouseListener(new MouseAdapter() {
				
			@Override
			public void mouseClicked(MouseEvent e) {
				if(btnAdd.isVisible())
					if(e.getClickCount()>=2) {
					int pos = tablaProductos.getSelectedRow();
					String dir = alp.get(pos).getImagen();
					lblLogo.setIcon(new ImageIcon(VentanaProducto.class.getResource(dir)));
					
					panelCentro.setLayout(new GridLayout(1,2));
					panelCentroDerecha.setLayout(new GridLayout(1,0));
					panelCentro.add(panelCentroDerecha);
					panelCentroDerecha.remove(scrollTabla);
					panelCentroDerecha.remove(btnEditarPedido);
					panelCentroDerecha.add(lblLogo);
					btnAtras.setVisible(true);
					btnAtras.setText("Salir de imagen");
			}}
			@Override
			public void mousePressed(MouseEvent e) {
				panelCentro.setLayout(new GridLayout(1,1));
				panelCentro.add(scrollTabla);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				if(e.isAltDown()) {
					btnAdd.doClick();
				}				
			}
		});

	
		
		panelCentro.setLayout(new GridLayout(1,1));
		panelCentro.add(scrollTabla);
	

		setVisible(true);
		JOptionPane.showMessageDialog(null,"Presione ALT+Click o el botón 'AÑADIR' para añadir a su carrito el producto");
	}
	
	/**
	 * Método que añade a la tabla los Productos
	 * @param a lista de los Productos a cargar
	 */
	
	public void agregarAtabla(ArrayList<Producto> a) {
		for(Producto p : a) {
			//Icon i = new ImageIcon(""+p.getImagen());
			Object dataRow[] = {""+String.valueOf(p.getCod()),""+p.getNombre(),""+String.valueOf(p.getPrecio())};
			modeloTablaProductos.addRow(dataRow);
		}
		
	}
	/**
	 * Metodo que modifica las columnas de la tabla 
	 * @param a lista de productos
	 */
	public void agregarAtablaFiltro(ArrayList<Producto> a) {
		for(Producto p : a) {
			//Icon i = new ImageIcon(""+p.getImagen());
			Object dataRow[] = {""+p.getNombre(),""+String.valueOf(p.getPrecio())};
			modeloTablaProductos.addRow(dataRow);
		}
		
	}
	 
	/*
	 * Modifica las columnas ded la tabla
	 */
	public void modificarTablafiltro() {
		String [] columnas = {"NOMBRE","PRECIO"};
		modeloTablaProductos.setColumnIdentifiers(columnas);
	}
	
	/**
	 * crea las columnas de la tabla
	 */
	public void estructuratabla() {
		String [] columnas = {"CODIGO","NOMBRE","PRECIO"};
		modeloTablaProductos.setColumnIdentifiers(columnas);
	}
	
	/**
	 * Metodo que vacía la tabla
	 */
	public void vaciarTabla() {
		while(modeloTablaProductos.getRowCount()>0) {
			modeloTablaProductos.removeRow(0);
		}
	}
	
	
	/**
	 * Método que ordena una lista recursivamente de mayor a menor.
	 * @param a Lista a ordenar
	 * @param n size de la lista
	 * @return Devuelve la lista ordenada de Precio más alto a bajo
	 */
	public void OrdenarListaMayoraMenor(ArrayList<Producto> a,int n) {
		if(n==1) {
			return;
		}else {
			for(int i=0;i<n-1;i++) {
				if(a.get(i).getPrecio()<a.get(i+1).getPrecio()) {
					Producto p = a.remove(i+1);
					a.add(i, p);
				}
			}
			OrdenarListaMayoraMenor(a, n-1);
		}
		return;
	}
	/**
	 * Metodo que ordena la lista de menor a mayor recursivamente por el precio del producto
	 * @param a lista a ordenar
	 */
	public void OrdenarListaMenoraMayor(ArrayList<Producto> a) {
		OrdenarListaMayoraMenor(a, a.size());
		Collections.reverse(a);
	}
	
	/**
	 * Metodo que recorre una lista  obteniendo los productos que son suplementos
	 * @param a lista de productos
	 */
	public ArrayList<Producto> OrdenarListaSuplementos(ArrayList<Producto> a) {
		ArrayList<Producto>al = new ArrayList<>();
		for(Producto p : a) {
			if(p instanceof ProductoSuplementos){
				al.add(p);
			}
		}
		return al;
	}
	
	/**
	 * MEtodo que recorre una lista  obteniendo los productos que son merchandise
	 * @param a lista de productos
	 * @param t tamaño de la lista
	 */
	public ArrayList<Producto> OrdenarListaMerchandise(ArrayList<Producto> a) {
		ArrayList<Producto>al = new ArrayList<>();
		for(Producto p : a) {
			if(p instanceof ProductoMerchandise){
				al.add(p);
			}
		}
		return al;
	}
	/**
	 * Metodo que ordena alfabeticamente una lista de productos
	 * @param a lista a ordenar
	 * @return La lista ordenada alfabeticamente
	 */
	public void OrdenarListaAlfabetica(ArrayList<Producto> a){
		Collections.sort(a,new Comparator<Producto>() {

			@Override
			public int compare(Producto o1, Producto o2) {
				return o1.getNombre().compareTo(o2.getNombre());
			}
		} );
		}
	

	/**
	 * Metodo que ordena la lista Ascendentemente por codigo
	 * @param a lista a ordenar
	 * @param t tamaño de la lista
	 */
	public void ordenarListaCodigoAscendente(ArrayList<Producto>a,int t) {
			if(t==1) {
				return;
			}else {
				for(int i=0;i<t-1;i++) {
					if(a.get(i).getCod()>a.get(i+1).getCod()) {
						Producto p = a.remove(i+1);
						a.add(i, p);
					}
				}
				ordenarListaCodigoAscendente(a, t-1);
			}
			return;
	}
	/**
	 * Metodo que obtiene el total del dinero
	 * @param a lista de productos
	 * @return total dinero a pagar
	 */
	
	public float obtenerDineroTotal(ArrayList<Producto> a) {
	float dinero = 0;
	for(Producto p : a) {
		dinero += p.getPrecio();
	}
	return dinero;
	}
	
	public static void ModificarVentanaProductoAdministrador() {
		btnVerPedido.setVisible(false);
		btnAdd.setVisible(false);
		btnCrearDescuento.setVisible(true);
		btnAddProductoNuevo.setVisible(true);
		
	}
	
	public static void ModificarVentanaProductoComprar() {
		btnVerPedido.setVisible(false);
		btnAdd.setVisible(false);
		
	}
}
	
