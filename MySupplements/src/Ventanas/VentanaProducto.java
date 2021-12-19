package Ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.GridLayout;
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
		
/*	Scanner scanner = new Scanner(VentanaProducto.class.getResourceAsStream("productosSuplementos.txt"));//txt de productos
		while(scanner.hasNextLine()){
			String linea = scanner.nextLine();
			String[] datos = linea.split(",");
			//cod,precio,nombre,url,proteinas,grasas,hidratos,calorias
			alp.add(new ProductoSuplementos(datos[0], Float.valueOf(datos[1]), datos[2], datos[3], Integer.parseInt(datos[4]), Integer.parseInt(datos[5]), Integer.parseInt(datos[6]),Integer.parseInt(datos[7])));
			
		}
		scanner.close();
		scanner= new Scanner(VentanaProducto.class.getResourceAsStream("productosMerchandise.txt"));
		while(scanner.hasNextLine()) {
			String linea = scanner.nextLine();
			String[] datos = linea.split(",");
			//cod,precio,nombre,url,material
			alp.add(new ProductoMerchandise(datos[0],Float.valueOf(datos[1]), datos[3], datos[4],datos[5]));
		}
		scanner.close();
		
		for(Producto p : alp) {
			String dataRow[] = {p.getCod(),p.getNombre(),String.valueOf(p.getPrecio())};
			modeloTablaProductos.addRow(dataRow);
		}
		*/
		tablaProductos = new JTable(modeloTablaProductos);
		JScrollPane scrollTabla = new JScrollPane(tablaProductos);
		
	
		//panelCentro.setLayout(new GridLayout(0, 2, 0, 0));
		panelCentro.add(scrollTabla);
		/*panelCentro.add(scrollLista);*/
		
		
	}}
