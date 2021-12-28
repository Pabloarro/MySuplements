package Ventanas;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import BaseDatos.BaseDatos;

public class VentanaPerfil extends JFrame{
	private JPanel panelArriba, panelCentro,panelAbajo,panel1;
	private JButton btnAtras,btnModificar,btnGuardar,btnHistorial;
	private JLabel lblTitulo,lblNom,lblCon,lblDni,lblFechanac;
	private JTextField txtNom,txtCon,txtDni,txtFechanac;
	private JDateChooser calendario;
	private SimpleDateFormat sdf;
	
	
	public VentanaPerfil() {
	//PROPIEDADES DE LA VENTANA
	setDefaultCloseOperation(EXIT_ON_CLOSE);
	setBounds(200, 200, 400, 400);
	
	//creacion de los paneles
	panelArriba= new JPanel();
	panelCentro = new JPanel(new GridLayout(2,4));
	panelAbajo = new JPanel(new GridLayout(1,3));
	panel1= new JPanel();
	
	//creación de los componentes
	btnAtras = new JButton("ATRAS");
	btnModificar = new JButton("MODIFICAR");
	btnGuardar = new JButton("GUARDAR");
	btnGuardar.setEnabled(false);
	
	
	lblNom= new JLabel("   Nombre:");
	lblCon= new JLabel("   Contraseña:");
	lblDni= new JLabel("   Dni:");
	lblFechanac= new JLabel("   Fecha de nacimiento:");
	
	txtNom= new JTextField();
	txtCon = new JPasswordField();
	txtDni = new JTextField();
	calendario = new JDateChooser();
	
	setTitle("MI PERFIL");
	//componentes a paneles
	
	panelCentro.add(lblNom);
	panelCentro.add(txtNom);
	panelCentro.add(lblCon);
	panelCentro.add(txtCon);
	panelCentro.add(lblDni);
	panelCentro.add(txtDni);
	panelCentro.add(lblFechanac);
	panelCentro.add(calendario);
	
	panelAbajo.add(btnAtras);
	panelAbajo.add(btnModificar);
	panelAbajo.add(btnGuardar);
	
	txtNom.setEditable(false);
	txtCon.setEditable(false);
	txtDni.setEditable(false);
	calendario.setEnabled(false);
	
	
	sdf = new SimpleDateFormat("yyyy-MM-dd");
	Date fechaActual = new Date(System.currentTimeMillis());
	calendario.getJCalendar().setMaxSelectableDate(fechaActual);
	
	
	
	//paneles a ventana
	getContentPane().add(panelArriba,BorderLayout.NORTH);
	getContentPane().add(panelCentro, BorderLayout.CENTER);
	getContentPane().add(panelAbajo, BorderLayout.SOUTH);
	//evetos
	btnAtras.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			setVisible(false);
			new VentanaPrincipal();
		}
	});
	btnModificar.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			txtNom.setEditable(true);
			txtCon.setEditable(true);
			calendario.setEnabled(true);
			btnGuardar.setEnabled(true);
			btnModificar.setEnabled(false);
			txtNom.setText(VentanaPrincipal.clientesesion.getNom());
			txtCon.setText(VentanaPrincipal.clientesesion.getCon());
			txtDni.setText(String.valueOf(VentanaPrincipal.clientesesion.getDni()));

			Date fechanac = new Date(VentanaPrincipal.clientesesion.getFechanac());
			calendario.setDate(fechanac);//TODO AÑADIR UN CALENDARIO CON SDF
		}
	});
	
	btnHistorial = new JButton("Ver mis pedidos");
	panelArriba.add(btnHistorial);
	btnHistorial.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			new VentanaProducto();
			VentanaProducto.ModificarVentanaProductoConPedidos();
				//TODO
			
		}
	});
	
	
	
	
	
	
	btnGuardar.addActionListener(new ActionListener() {
		
		@Override	
		public void actionPerformed(ActionEvent e) {
			String nombre = txtNom.getText();
			String con = txtCon.getText();
			long fechanac = calendario.getDate().getTime();
			
			/*GUARDAR LOS VALORES DEL USUARIO*/
			txtNom.setText("");
			txtCon.setText("");
			txtDni.setText("");
			calendario.setDate(null);
			btnGuardar.setEnabled(false);
			btnModificar.setEnabled(true);
			txtNom.setEditable(false);
			txtCon.setEditable(false);
			calendario.setEnabled(false);
			VentanaPrincipal.clientesesion.setNom(nombre);
			VentanaPrincipal.clientesesion.setCon(con);;
			VentanaPrincipal.clientesesion.setFechanac(fechanac);
			BaseDatos.initBD("Basedatos.db");
			try {
				BaseDatos.ObtenerPuntosCliente(VentanaPrincipal.clientesesion);
				BaseDatos.eliminarCliente(VentanaPrincipal.clientesesion.getDni());
				BaseDatos.insertarCliente(nombre, con, VentanaPrincipal.clientesesion.getDni(), fechanac);
				BaseDatos.modificarClientePuntos(VentanaPrincipal.clientesesion);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			BaseDatos.closeBD();
		}
	});
	
	
	
	
	
	setVisible(true);
}
	
	public static void main(String[] args) {
		VentanaPerfil Vperfil = new VentanaPerfil();
	}
	
	
	

}

