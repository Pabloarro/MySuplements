package Ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import BaseDatos.BaseDatos;
import Clases.Cliente;

import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

public class VentanaInicioDeSesion extends JFrame {

	
	private JPanel contentPane;
	private JPanel panelSur;
	private JPanel panelCentro;
	private JLabel lblNombre;
	private JTextField textNombre;
	private JLabel lblContrasenia;
	private JPasswordField textContrasenia;
	private JLabel lbldni;
	private JTextField textdni;
	private JLabel lblFNac;
	private JButton btnRegistro;
	private JDateChooser calendario;
	private SimpleDateFormat sdf;
	private static Logger logger = Logger.getLogger( "VentanaInicioDeSesion" );
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaInicioDeSesion frame = new VentanaInicioDeSesion();
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
	public VentanaInicioDeSesion() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaPrincipal.class.getResource("/LOGO/logo_small_icon_only_inverted.png")));
		BaseDatos.initBD("Basedatos.db");
		BaseDatos.crearTablas();
		BaseDatos.closeBD();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		panelSur = new JPanel();
		contentPane.add(panelSur, BorderLayout.SOUTH);
		
		btnRegistro = new JButton("REGISTRO");
		panelSur.add(btnRegistro);
		
		panelCentro = new JPanel();
		contentPane.add(panelCentro, BorderLayout.CENTER);
		panelCentro.setLayout(new GridLayout(0, 2, 0, 0));
		
		lblNombre = new JLabel("Introduce tu nombre:");
		panelCentro.add(lblNombre);
		
		textNombre = new JTextField();
		panelCentro.add(textNombre);
		textNombre.setColumns(10);
		
		lblContrasenia = new JLabel("Introduce la contraseña:");
		panelCentro.add(lblContrasenia);
		
		textContrasenia = new JPasswordField();
		panelCentro.add(textContrasenia);
		textContrasenia.setColumns(10);
		
		lbldni = new JLabel("Introduce el dni:");
		panelCentro.add(lbldni);
		
		textdni = new JTextField();
		panelCentro.add(textdni);
		textNombre.setColumns(10);
		
		lblFNac = new JLabel("Introduce tu fecha de nacimiento:");
		panelCentro.add(lblFNac);
		
		calendario = new JDateChooser();
		panelCentro.add(calendario);
		
		sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date fechaActual = new Date(System.currentTimeMillis());
		calendario.getJCalendar().setMaxSelectableDate(fechaActual);
		/*EVENTOS*/
		btnRegistro.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String nom = textNombre.getText();
				String con = textContrasenia.getText();
				String dni = textdni.getText();
				Date fecha = calendario.getDate();
				if(nom.equals("") || con.equals("") || dni.equals("") || fecha==null) {
					JOptionPane.showMessageDialog(null, "Tienes que rellenar todos los campos");
				}else {
					long f = fecha.getTime();
					String puntos="0";
					BaseDatos.initBD("BaseDatos.db");
					BaseDatos.crearTablas();
						BaseDatos.insertarCliente(nom, con,dni,f);
						try {
							VentanaPrincipal.clientesesion = new Cliente(nom, con, dni, f);
							BaseDatos.ObtenerPuntosCliente(VentanaPrincipal.clientesesion);
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						JOptionPane.showMessageDialog(null, "Registro realizado con éxito");
						logger.log(Level.INFO, "Registro realizado ");
						VentanaPrincipal.SesionPerfilOProducto();
						setVisible(false);
					}
					BaseDatos.closeBD();
				}
			
		});
		
		//TODO Hilo que compruebe que el dni tiene un formato establecido y
		//TODO hasta que no lo tenga no desbloquee el boton de registrarse
		setVisible(true);
	}

}