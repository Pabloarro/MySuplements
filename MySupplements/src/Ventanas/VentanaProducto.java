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

	private JPanel contentPane,panelNorte,panelSur,panelCentro,panelCentroDerecha;
	private JLabel lblInfo,lblFiltro;
	private JComboBox<String> comboFiltro;
	private JButton btnAtras,btnVerPedido,btnAdd,btnRealizarPedido;
	
	private JTable tablaProductos;
	private DefaultTableModel modeloTablaProductos;
	
	private JList<Producto> listaProductos;
	private DefaultListModel<Producto> modeloListaProductos;

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
		comboFiltro.addItem("");
		comboFiltro.addItem("Precio mayor a menor");
		comboFiltro.addItem("Precio menor a mayor");
		comboFiltro.addItem("Suplementos");
		comboFiltro.addItem("Merchandise");
		comboFiltro.addItem("Orden de A-Z");
		

		lblInfo= new JLabel();
		lblInfo.setText("Productos en carrito: "+0);
		
		lblFiltro = new JLabel();
		lblFiltro.setText("Filtrar Productos por:");		

		btnAdd = new JButton("AÑADIR A CARRITO");
		btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//POR HACER
				
			}
		});
		
		btnVerPedido = new JButton("Ver Carrito");
		btnVerPedido.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				lblInfo.setVisible(false);
				btnAtras.setVisible(true);
				btnRealizarPedido.setVisible(true);
				btnVerPedido.setEnabled(false);
				btnAdd.setEnabled(false);
				modeloListaProductos = new DefaultListModel<>();
				listaProductos = new JList<>(modeloListaProductos);
				JScrollPane scrollLista = new JScrollPane(listaProductos);
				panelCentro.setLayout(new GridLayout(0,2));
				panelCentro.add(panelCentroDerecha);
				panelCentroDerecha.add(scrollLista);
				
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
				panelCentro.remove(panelCentroDerecha);
				btnAtras.setVisible(false);
				btnRealizarPedido.setVisible(false);
				btnVerPedido.setEnabled(true);
				btnAdd.setEnabled(true);
				lblInfo.setVisible(true);
			}
		});
		
		panelNorte.setLayout(new GridLayout(1,3));
		panelNorte.add(lblInfo);
		panelNorte.add(lblFiltro);
		panelNorte.add(comboFiltro);
		
		panelSur = new JPanel();

		contentPane.add(panelSur, BorderLayout.SOUTH);
		
		panelSur.add(btnAtras);
		panelSur.add(btnVerPedido);
		panelSur.add(btnAdd);
		panelSur.add(btnRealizarPedido);
		panelCentro = new JPanel();
		contentPane.add(panelCentro, BorderLayout.CENTER);
		
		panelCentroDerecha = new JPanel();
		panelCentroDerecha.setLayout(new GridLayout(2,0));
		
		/* JLIST 
		
		modeloListaProductos = new DefaultListModel<>();
		listaProductos = new JList<>(modeloListaProductos);
		JScrollPane scrollLista = new JScrollPane(listaProductos);
		
		
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
		
		
		String [] columnas = {"CODIGO","NOMBRE","PRECIO"};
		modeloTablaProductos.setColumnIdentifiers(columnas);
		
		ArrayList<Producto> alp = new ArrayList<>();
	
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
		//2	20	Sudadera con gorro	/FOTOS/sudaderaGorro.jpg	algodón
		alp.add(new ProductoMerchandise(2,(float) 20,"Sudadera con goro","/FOTOS/sudaderaGorro.jpg", "algodón"));
		for(Producto p : alp) {
			//Icon i = new ImageIcon(""+p.getImagen());
			Object dataRow[] = {""+String.valueOf(p.getCod()),""+p.getNombre(),""+String.valueOf(p.getPrecio())};
			modeloTablaProductos.addRow(dataRow);
		}
		

		
		tablaProductos = new JTable(modeloTablaProductos);
		JScrollPane scrollTabla = new JScrollPane(tablaProductos);


	
		
		panelCentro.add(scrollTabla);
	

		setVisible(true);
		JOptionPane.showMessageDialog(null,"Presione ALT+Click o el botón 'AÑADIR' para añadir a su carrito el producto");
	}
	

}
	
