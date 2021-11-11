package Ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaInicioDeSesion extends JFrame {

	private JPanel contentPane;
	private JFrame ventanaActual, ventanaAnterior;
	/**
	 * Launch the application.
	 */
	
	

	/**
	 * Create the frame.
	 */
	
	public VentanaInicioDeSesion(JFrame Vanterior) {
		setTitle("Inicio de sesion");
		ventanaAnterior = Vanterior;
		ventanaActual= this;
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.WEST);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.NORTH);
		
		JButton btnAtras = new JButton("Atras");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventanaActual.dispose();
				ventanaAnterior.setVisible(true);
			}
		});
		panel_2.add(btnAtras);
		
		JButton btnAdelante = new JButton("Adelante");
		panel_2.add(btnAdelante);
	}

}
