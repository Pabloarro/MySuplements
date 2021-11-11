package Ventanas;
 

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;



import javax.swing.ImageIcon;
import javax.swing.JButton;



public class VentanaMenu extends JFrame {

	private JPanel contentPane;
	private JFrame ventanaActual;
	private JButton btnInicioDeSesion;
	private final JLabel lblLogolarge = new JLabel("logo_large");
	String nom;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaMenu frame = new VentanaMenu();
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
	public VentanaMenu() {
		ventanaActual = this;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel_Izquierda = new JPanel();
		contentPane.add(panel_Izquierda, BorderLayout.WEST);
		
		JPanel panel_Centro = new JPanel();
		contentPane.add(panel_Centro, BorderLayout.CENTER);
		panel_Centro.add(lblLogolarge);
		lblLogolarge.setIcon(new ImageIcon("C:\\Users\\Pablo Arroyuelos\\git\\MySuplements\\logo_large.png"));
		
		JPanel panel_Centro1 = new JPanel();
		contentPane.add(panel_Centro1, BorderLayout.CENTER);
		
		JPanel panel_Norte = new JPanel();
		contentPane.add(panel_Norte, BorderLayout.NORTH);
		
		btnInicioDeSesion.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new VentanaInicioDeSesion(ventanaActual);
				ventanaActual.setVisible(false);
				
			}
		});
		
		
		
	}

}
