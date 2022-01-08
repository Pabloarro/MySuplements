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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Properties;
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
	public static ArrayList<Administrador> listaAdmins;//lista con los administradores para comprobar al entrar al btnAdmins
	static int NumVentana;//Si el int es 0 se abre la ventanaProducto y si el int es 1 se abre la ventanaPerfil
	
	
	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				Properties p = new Properties();
				p.setProperty("Idioma", "Castellano");
				p.setProperty("Email", "mysupplements.info@gmail.com");
				try {
					p.store(new FileWriter("Propiedades.properties"), "Propiedades");
				} catch (IOException e) {
					e.printStackTrace();
				}		
				
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
		frame.setLocationRelativeTo(null);
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
		
		listaAdmins = new ArrayList<>();
		CargarListaAdmins();
		/**
		 * Lista de administradores:
		 * 12345678A  password
		 * 12345677F  password
		 */
		btnAdministrador = new JButton("Administrador");
		btnAdministrador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaInicioSesion2 v2= new VentanaInicioSesion2();
				v2.ModificarSesionAdmin();
				v2.setTitle("INICIAR SESIÓN");
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
				}else {
					new VentanaPerfil();
				}
			frame.setVisible(false);
			
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
	
	/**
	 * Metodo que hace que al iniciar sesión ya sea desde la ventana de productos o desde el botón mi perfil
	 * se abra depende de por donde hayas iniciado sesión una ventana u otra
	 */
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
	

	/**
	 * Metodo que vuelca a fichero una lista de administradores
	 * @param a lista de administradores a volcar
	 */
	public static void VolcarListaAdmins(ArrayList<Administrador> a) {
		ObjectOutputStream oos= null;
		try {
			oos= new ObjectOutputStream(new FileOutputStream("Administradores.dat"));
			oos.writeObject(a);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "LO SENTIMOS HA OCURRIDO UN ERROR", "ERROR", JOptionPane.ERROR_MESSAGE);		}finally {
			if (oos!=null) {
				try {
					oos.close();
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, "LO SENTIMOS HA OCURRIDO UN ERROR", "ERROR", JOptionPane.ERROR_MESSAGE);				}
				try {
					oos.flush();
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, "LO SENTIMOS HA OCURRIDO UN ERROR", "ERROR", JOptionPane.ERROR_MESSAGE);				}
			}
		}		
	}
	
	/**
	 * Método que carga la lista de administradores desde un fichero
	 * @param a lista de administradores a la que se van a cargar del fichero
	 */
	public  void CargarListaAdmins() {
		ObjectInputStream ois = null;
		try {
			ois= new ObjectInputStream(new FileInputStream("Administradores.dat"));
			listaAdmins=(ArrayList<Administrador>) ois.readObject();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "LO SENTIMOS HA OCURRIDO UN ERROR", "ERROR", JOptionPane.ERROR_MESSAGE);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "LO SENTIMOS HA OCURRIDO UN ERROR", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		
	}

}

