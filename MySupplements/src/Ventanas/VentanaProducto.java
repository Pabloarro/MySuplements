package Ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashMap;

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
		
		
		String [] columnas = {"DNI","NOMBRE","EDAD"};
		modeloTablaProductos.setColumnIdentifiers(columnas);
		
		
		
		
		}
		
		
		
		
		
	}
