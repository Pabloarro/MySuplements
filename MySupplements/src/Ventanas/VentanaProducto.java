package Ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import Clases.Producto;
import Clases.ProductoMerchandise;
import Clases.ProductoSuplementos;

public class VentanaProducto extends JFrame {

	private JPanel contentPane;
	private JPanel panelNorte;
	private JPanel panelSur;
	private JPanel panelCentro;
	
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
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		panelNorte = new JPanel();
		contentPane.add(panelNorte, BorderLayout.NORTH);
		
		panelSur = new JPanel();
		contentPane.add(panelSur, BorderLayout.SOUTH);
		
		panelCentro = new JPanel();
		contentPane.add(panelCentro, BorderLayout.CENTER);
		/* JLIST */
		
		modeloListaProductos = new DefaultListModel<>();
		listaProductos = new JList<>(modeloListaProductos);
		JScrollPane scrollLista = new JScrollPane(listaProductos);
		
		
		/*listaProductos.setCellRenderer(new DefaultListCellRenderer() {
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
				if(row==1 && col==1)
					return false;
				else if(col==0)
					return false;
				return true;
			}
		};
		
		
		String [] columnas = {"CODIGO","NOMBRE","PRECIO"};
		modeloTablaProductos.setColumnIdentifiers(columnas);
		
		ArrayList<Producto> alp = new ArrayList<>();
		
		BufferedReader br = null;
		try {
			br=new BufferedReader(new FileReader("productos.txt"));
			String linea = br.readLine();
			while(linea!=null) {
				String [] datos= linea.split("	");
				int cod=Integer.parseInt(datos[0]);
				if(cod % 2 == 0) {//SI EL RESTO DE LA DIVISION DEL CODIGO ENTRE DOS ES 0 ES PAR SI NO IMPAR
					Float pr = Float.valueOf(datos[1]);
					String nom = datos[2];
					String url= datos[3];
					String mat = datos[4];
					alp.add(new ProductoMerchandise(cod, pr, nom, url, mat));	
				}else {
					Float pr = Float.valueOf(datos[1]);
					String nom = datos[2];
					String url = datos[3];
					int prot = Integer.parseInt(datos[4]);
					int gra = Integer.parseInt(datos[5]);
					int hi = Integer.parseInt(datos[6]);
					int cal = Integer.parseInt(datos[7]);
					alp.add(new ProductoSuplementos(cod, pr, nom, url, prot, gra, hi, cal));
				}
					}
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				}
		
		//12	59.99	Caseina	/FOTOS/caseina.jpg	50	10	15	360
		//alp.add(new ProductoSuplementos(12,(float) 59.99,"Caseina","/FOTOS/caseina.jpg",50,10,15,360));
		for(Producto p : alp) {
			String dataRow[] = {String.valueOf(p.getCod()),p.getNombre(),String.valueOf(p.getPrecio())};
			modeloTablaProductos.addRow(dataRow);
		}
		
		
		tablaProductos = new JTable(modeloTablaProductos);
		JScrollPane scrollTabla = new JScrollPane(tablaProductos);
		
	
		//panelCentro.setLayout(new GridLayout(0, 2, 0, 0));
		panelCentro.add(scrollTabla);
		/*panelCentro.add(scrollLista);*/
		
	/*	tablaProductos.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});*/
		setVisible(true);
	}}
