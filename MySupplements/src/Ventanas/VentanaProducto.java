package Ventanas;

import javax.swing.JFrame;

public class VentanaProducto {

	private JFrame frame;

	
	public VentanaProducto() {
		initialize();
	}

	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}



}
