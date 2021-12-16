package Ventanas;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFrame;

import Clases.Producto;

public class VentanaProducto {

	private JFrame frame;
	public static ArrayList<Producto> ListadeTodosLosProductos;//POR HACER

	
	public VentanaProducto() {
		initialize();
	}

	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}



}
