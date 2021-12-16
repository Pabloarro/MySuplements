package Ventanas;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JPanel;

import Clases.Cliente;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;




public class VentanaPrincipal {

	public static Cliente clientesesion;
	private JFrame frame;
	private JPanel panelLogo;
	private JLabel lblLOGO;
	private JPanel panel_sur;
	private JButton btnSalir;
	private JPanel panel_mid;
	private JButton btnAdministrador;
	private JButton btnComprar;
	private JButton btnInicioSesion;
	
	
	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal window = new VentanaPrincipal();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaPrincipal() {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaPrincipal.class.getResource("/LOGO/logo_small_icon_only_inverted.png")));
		frame.setBounds(100, 100, 558, 380);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panelLogo = new JPanel();
		frame.getContentPane().add(panelLogo, BorderLayout.NORTH);
		
		lblLOGO = new JLabel("");
		lblLOGO.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/LOGO/logo_small.png")));
		panelLogo.add(lblLOGO);
		
		panel_sur = new JPanel();
		frame.getContentPane().add(panel_sur, BorderLayout.SOUTH);
		
		btnSalir = new JButton("SALIR");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		panel_sur.add(btnSalir);
		
		panel_mid = new JPanel();
		frame.getContentPane().add(panel_mid, BorderLayout.CENTER);
		
		btnAdministrador = new JButton("Administrador");
		btnAdministrador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaAdministrador va = new VentanaAdministrador();
				
				
			}
		});
		
		btnComprar = new JButton("Comprar");
		btnComprar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaProducto vp = new VentanaProducto();
				
			}
		});
		
		btnInicioSesion = new JButton("Inicio Sesion");
		btnInicioSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			int resp = JOptionPane.showConfirmDialog(null,"¿Es tu primera vez?","Pregunta",JOptionPane.YES_NO_OPTION);
			if(resp == JOptionPane.YES_OPTION) {
				new VentanaInicioDeSesion();
			}else
				new VentanaInicioSesion2();
			}
			//setvisible(false)
		});
		GroupLayout gl_panel_mid = new GroupLayout(panel_mid);
		gl_panel_mid.setHorizontalGroup(
			gl_panel_mid.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_mid.createSequentialGroup()
					.addGap(19)
					.addComponent(btnAdministrador, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnComprar, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnInicioSesion, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(34, Short.MAX_VALUE))
		);
		gl_panel_mid.setVerticalGroup(
			gl_panel_mid.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_mid.createSequentialGroup()
					.addContainerGap(68, Short.MAX_VALUE)
					.addGroup(gl_panel_mid.createParallelGroup(Alignment.LEADING)
						.addComponent(btnInicioSesion, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnComprar, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnAdministrador, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE))
					.addGap(62))
		);
		panel_mid.setLayout(gl_panel_mid);

		
	}
}
