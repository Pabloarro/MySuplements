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
	public static ArrayList<Administrador> listaAdmins;//lista con los administradores para comprobar al entrar al btnAdmins
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
		
		listaAdmins = new ArrayList<>();
			/*Administrador a = new Administrador("12345678A", "Contrasenia");
			Administrador a1 = new Administrador("12345679B", "Contra");
			listaAdmins.add(a);
			listaAdmins.add(a1);
			VolcarListaAdmins(listaAdmins);*/
		CargarListaAdmins(listaAdmins);
		
		btnAdministrador = new JButton("Administrador");
		btnAdministrador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaProducto v =  new VentanaProducto();
				v.setTitle("ADMINISTRADOR");
				VentanaProducto.ModificarVentanaProductoAdministrador();
				frame.setVisible(false);
				
				//TODO comprobar que el adminmistrador existe en el fichero de administradores
				
			/*String id = JOptionPane.showInputDialog("Introduzca su identificación");
			String con = JOptionPane.showInputDialog("Introduzca su contraseña");
			
			Administrador a = new Administrador(id, con);
			if(ExisteAdmin(a, listaAdmins)==true) {
				new VentanaProducto();
				VentanaProducto.ModificarVentanaProductoAdministrador();
				frame.setVisible(false);
			}else {
				JOptionPane.showMessageDialog(null, "El usuario o la contraseña son incorrectas ", "Administrador erróneo", JOptionPane.ERROR_MESSAGE);
				
			}
			*/
				
				
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
	 * Método que carga la lista de administradores desde un fichero
	 * @param a lista de administradores a la que se van a cargar del fichero
	 */
	public void CargarListaAdmins(ArrayList<Administrador> a) {
	BufferedReader br =null;
	try {
		br= new BufferedReader(new FileReader("Administradores.txt"));
		String linea = br.readLine();
		while(linea!=null) {
			String [] datos= linea.split("\t");
			String dni = datos[0];
			String con = datos[1];
			Administrador ad = new Administrador(dni, con);
			a.add(ad);
			linea = br.readLine();
		}
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}finally {
		if(br!=null) {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	}
	
	/**
	 * Metodo que vuelca a fichero una lista de administradores
	 * @param a lista de administradores a volcar
	 */
	public static void VolcarListaAdmins(ArrayList<Administrador>a) {
		PrintWriter pw = null;
		try {
			pw= new PrintWriter("Administradores.txt");
			for(Administrador ad : a) {
				pw.println(ad.getDni()+"\t"+ad.getContrasenya());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}finally {
			if(pw!=null) {
				pw.flush();
				pw.close();
			}
		}
		
	}

	
	/**
	 * Metodo que comprueba si el administrador introducido existe
	 * @param a Administrador introducido
	 * @param lad lista de todos los administradores
	 * @return devuelve true si existe y  false si no existe
	 */
	public boolean ExisteAdmin(Administrador a,ArrayList<Administrador>lad) {
		for(Administrador ad :lad) {
			if(a.getDni()==ad.getDni() && a.getContrasenya()==ad.getContrasenya()) {
				return true;
				//TODO hay que comprobar que esto funciona para usarlo al darle click al btn admin
			}
		}
		return false;
	}
}

