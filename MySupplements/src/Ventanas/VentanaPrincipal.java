package Ventanas;

import java.awt.EventQueue;



import javax.swing.JFrame;
import javax.swing.JPanel;

import Clases.Administrador;
import Clases.Cliente;
import Clases.Pedido;
import Clases.Producto;
import Clases.ProductoMerchandise;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.WindowAdapter;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
	private  JButton btnMiperfil;//Cambiar a Pagina de cliente
	private ArrayList<Administrador> listaAdmins;//lista con los administradores para comprobar al entrar al btnAdmins
	static int NumVentana;//Si el int es 0 se abre la ventanaProducto y si el int es 1 se abre la ventanaPerfil
	
	
	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal window = new VentanaPrincipal();
					Pedido.ObtenerSiguienteCodigodePedido();
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
		
		Administrador a1 = new Administrador("12345678A", "password");
		listaAdmins = new ArrayList<>();
		listaAdmins.add(a1);
		
		btnAdministrador = new JButton("Administrador");
		btnAdministrador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			String id = JOptionPane.showInputDialog("Introduzca su identificación");
			String con = JOptionPane.showInputDialog("Introduzca su contraseña");
			for(Administrador ad : listaAdmins) {
				
				
				//TODO
				
				//POR HACER LA LISTA DE ADMINS QUE ESTARA PREDEFINIDA EN UN FICHERO DE TEXTO
				/*if(ad.getDni()==id && ad.getContrasenya()==con) {
					new VentanaProducto();
					VentanaProducto.ModificarVentanaProductoAdministrador();
				}else {
					JOptionPane.showMessageDialog(null, "Lo sentimos, se ha introducido un administrador erróneo");
				}*/
				
				new VentanaProducto();
				VentanaProducto.ModificarVentanaProductoAdministrador();
			}
				
				frame.setVisible(false);
			}
		});
		
		btnComprar = new JButton("Comprar");
		btnComprar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NumVentana=0;
				if(VentanaPrincipal.clientesesion == null) {
					new VentanaProducto();
					VentanaProducto.ModificarVentanaProductoComprar();
				}else {
					new VentanaProducto();
				}
				Pedido.ObtenerSiguienteCodigodePedido();
				frame.setVisible(false);
			}
		});
		
		btnMiperfil = new JButton("Mi perfil");
		btnMiperfil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			NumVentana=1;
			if(VentanaPrincipal.clientesesion==null) {
				int resp = JOptionPane.showConfirmDialog(null,"¿Es tu primera vez?","Pregunta",JOptionPane.YES_NO_OPTION);
				if(resp == JOptionPane.YES_OPTION) {
					new VentanaInicioDeSesion();
				}else {
					new VentanaInicioSesion2();
				}
				frame.setVisible(false);
				}else {
					new VentanaPerfil();
				}
			
			}});
		
		GroupLayout gl_panel_mid = new GroupLayout(panel_mid);
		gl_panel_mid.setHorizontalGroup(
			gl_panel_mid.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_mid.createSequentialGroup()
					.addGap(19)
					.addComponent(btnAdministrador, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnComprar, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnMiperfil, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(34, Short.MAX_VALUE))
		);
		gl_panel_mid.setVerticalGroup(
			gl_panel_mid.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_mid.createSequentialGroup()
					.addContainerGap(68, Short.MAX_VALUE)
					.addGroup(gl_panel_mid.createParallelGroup(Alignment.LEADING)
						.addComponent(btnMiperfil, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnComprar, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnAdministrador, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE))
					.addGap(62))
		);
		panel_mid.setLayout(gl_panel_mid);

		frame.setVisible(true);
	}
	
	public static void SesionPerfilOProducto() {
		if(VentanaPrincipal.NumVentana==0) {
			new VentanaProducto();
			JOptionPane.showMessageDialog(null,"Presione ALT+Click o el botón 'AÑADIR' para añadir a su carrito el producto");
		}else {
			new VentanaPerfil();
		}
	}
	/**
	 * Método que obtiene el siguiente código de suplemento de fichero
	 */
	public static int ObtenerSiguienteCodigodeProductoSuplemento() {
		int cod= 0;
		ArrayList<Producto>lp = new ArrayList<>();
		VentanaProducto.cargarProductosdeFichero(lp);
		if(lp.get(lp.size()-1).getCod() % 2 ==0) {
			cod=lp.get(lp.size()-1).getCod()+1;
		}else {
			cod = lp.get(lp.size()-1).getCod()+2;
		}	
		return cod;
	}
	
	/**
	 * Método que obtiene el siguiente código de merchandise de fichero
	 */
	public static int ObtenerSiguienteCodigodeProductoMerchandise() {
		int cod= 0;
		ArrayList<Producto>lp = new ArrayList<>();
		VentanaProducto.cargarProductosdeFichero(lp);
		if(lp.get(lp.size()-1).getCod() % 2 ==0) {
			cod=lp.get(lp.size()-1).getCod()+2;
		}else {
			cod = lp.get(lp.size()-1).getCod()+1;
		}	
		return cod;
		
	}


}
