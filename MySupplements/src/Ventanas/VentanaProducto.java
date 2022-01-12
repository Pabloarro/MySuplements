package Ventanas;

import java.awt.BorderLayout;

import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.ScrollPane;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import javax.swing.table.DefaultTableModel;

import com.itextpdf.text.DocumentException;

import BaseDatos.BaseDatos;
import Clases.Administrador;
import Clases.Cliente;
import Clases.Pedido;
import Clases.Producto;
import Clases.ProductoMerchandise;
import Clases.ProductoSuplementos;
import Clases.Recibo;

public class VentanaProducto extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane, panelNorte, panelSur, panelCentroDerecha, panelCentroDerechaAbajo,
			panelCentroDerechaAbajo1;
	private static JPanel panelCentro;
	private JLabel lblLogo, lblSumaDinero;
	private static JLabel lblInfo, lblFiltro;
	private static JComboBox<String> comboFiltro;
	private static JButton btnAtras;
	private static JButton btnVerPedido;
	private static JButton btnAdd;
	private JButton btnRealizarPedido, btnSalir;
	private JButton btnEditarPedido;
	private JButton btnBorrarPedido;
	private JButton btnAddDescuento;
	private static JButton btnAddProductoNuevo, btnBorrarProducto, btnAniadirAdmin,btnZipRecibos;
	private static JButton btnInicioSesion;
	private static JButton btnRepetirPedido, btnDescargarRecibo, btnVerInfoPedido, btnRetroceder;
	private static int opc = 0;

	private static JTextArea textArea;

	private static JTable tablaInformacion;
	private static DefaultTableModel modeloTablaInformacion;

	private ArrayList<Producto> listaProductosPedido, alp;// lista de productos en el carrito,lista de productos,lista
															// de pedidos de clientesesión.

	private int cant;
	public static float puntosAnteriores, puntosGastados = 0;

	private JList<Producto> listaProductosPedidos;
	private DefaultListModel<Producto> modeloListaProductosPedidos;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaProducto frame = new VentanaProducto();
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
	public VentanaProducto() {
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(VentanaPrincipal.class.getResource("/LOGO/logo_small_icon_only_inverted.png")));
		setTitle("PRODUCTOS");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 900, 650);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		panelNorte = new JPanel();
		contentPane.add(panelNorte, BorderLayout.NORTH);

		comboFiltro = new JComboBox<>();
		comboFiltro.addItem("Todos los productos");
		comboFiltro.addItem("Precio mayor a menor");
		comboFiltro.addItem("Precio menor a mayor");
		comboFiltro.addItem("Suplementos");
		comboFiltro.addItem("Merchandise");
		comboFiltro.addItem("Orden de A-Z");

		comboFiltro.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (comboFiltro.getSelectedItem() == "Precio mayor a menor") {
					OrdenarListaMayoraMenor(alp, alp.size());
					vaciarTabla();
					modificarTablafiltro();
					agregarAtablaFiltro(alp);
				} else if (comboFiltro.getSelectedItem() == "Precio menor a mayor") {
					OrdenarListaMenoraMayor(alp);
					vaciarTabla();
					modificarTablafiltro();
					agregarAtablaFiltro(alp);
				} else if (comboFiltro.getSelectedItem() == "Orden de A-Z") {
					OrdenarListaAlfabetica(alp);
					vaciarTabla();
					modificarTablafiltro();
					agregarAtablaFiltro(alp);
				} else if (comboFiltro.getSelectedItem() == "Suplementos") {
					vaciarTabla();
					modificarTablafiltro();
					agregarAtablaFiltro(OrdenarListaSuplementos(alp));

				} else if (comboFiltro.getSelectedItem() == "Merchandise") {
					vaciarTabla();
					modificarTablafiltro();
					agregarAtablaFiltro(OrdenarListaMerchandise(alp));
				} else {
					ordenarListaCodigoAscendente(alp, alp.size());
					vaciarTabla();
					estructuratabla();
					agregarProductosAtabla(alp);
				}

			}
		});

		modeloListaProductosPedidos = new DefaultListModel<>();
		listaProductosPedidos = new JList<>(modeloListaProductosPedidos);
		JScrollPane scrollLista = new JScrollPane(listaProductosPedidos);

		lblInfo = new JLabel();
		cant = 0;
		lblInfo.setText("Productos en carrito: " + cant);

		puntosAnteriores = 0;
		puntosGastados = 0;

		lblFiltro = new JLabel();
		lblFiltro.setText("Filtrar Productos por:");

		lblSumaDinero = new JLabel();
		lblSumaDinero.setText("TOTAL: " + 0 + "€");

		lblLogo = new JLabel();

		btnRepetirPedido = new JButton("REPETIR PEDIDO");
		btnRepetirPedido.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int pos = tablaInformacion.getSelectedRow();
				ArrayList<Pedido> pedidos = obtenerlistapedidosCliente();
				listaProductosPedido = pedidos.get(pos).getListaproductos();
				int resp = JOptionPane.showConfirmDialog(null, "¿Quieres usar tus puntos?", "Pregunta",
						JOptionPane.YES_NO_OPTION);
				if (resp == JOptionPane.YES_OPTION) {
					opc = 1;
					btnAddDescuento.doClick();

				} else {
					btnRealizarPedido.doClick();
				}
				setVisible(false);
				VentanaProducto v = new VentanaProducto();
				v.setTitle("PEDIDOS");
				v.ModificarVentanaProductoConPedidos();
			}
		});
		
		btnZipRecibos = new JButton("DESCARGAR ZIP");
		btnZipRecibos.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ArrayList<Pedido> pedidoscliente = obtenerlistapedidosCliente();
				try {
					Recibo.ZipRecibosUsuario(VentanaPrincipal.clientesesion, pedidoscliente);
				} catch (IOException | DocumentException e1) {
					e1.printStackTrace();
				}
				
			}
		});

		btnRetroceder = new JButton("ATRAS");
		btnRetroceder.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				panelCentro.setLayout(new GridLayout(1, 1));
				panelCentro.remove(1);
				btnRetroceder.setVisible(false);
				btnVerInfoPedido.setVisible(true);
				btnRepetirPedido.setVisible(false);
				btnDescargarRecibo.setVisible(false);

			}
		});
		btnVerInfoPedido = new JButton("VER PEDIDO");
		btnVerInfoPedido.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				btnRepetirPedido.setVisible(true);
				btnDescargarRecibo.setVisible(true);
				btnRetroceder.setVisible(true);

				textArea = new JTextArea();
				JScrollPane scroll = new JScrollPane(textArea);
				scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
				scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

				int pos = tablaInformacion.getSelectedRow();
				ArrayList<Pedido> pedidos = obtenerlistapedidosCliente();
				Pedido p = pedidos.get(pos);
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
				Date d = new Date(p.getFec());
				textArea.append("Pedido con código: " + p.getCodpe() + "\t" + "Realizado el:" + sdf.format(d) + "\n");
				textArea.append("\n");
				float tot = 0;
				for (Producto pr : p.getListaproductos()) {
					tot += pr.getPrecio();
					textArea.append("" + pr + "\n");
					textArea.append("\n");

				}
				DecimalFormat df = new DecimalFormat();
				df.setMaximumFractionDigits(2);
				textArea.append("\n");

				textArea.append("Puntos Usados: " + p.getPuntosUsados() + "\n");
				textArea.append("\n");

				textArea.append("Total sin descuento: " + df.format(tot) + "€" + "\n");
				textArea.append("\n");

				textArea.append("Total con descuento: " + df.format(tot - (p.getPuntosUsados() / 5)) + "€" + "\n");

				textArea.setEditable(false);

				panelCentro.setLayout(new GridLayout(0, 2));

				panelCentro.add(scroll);

				btnVerInfoPedido.setVisible(false);

			}
		});

		btnDescargarRecibo = new JButton("DESCARGAR PDF");
		btnDescargarRecibo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int pos = tablaInformacion.getSelectedRow();
				ArrayList<Pedido> listapedidos = obtenerlistapedidosCliente();
				try {
					Recibo.generarpdf(VentanaPrincipal.clientesesion, listapedidos.get(pos));
				} catch (DocumentException | SQLException | IOException e1) {
					JOptionPane.showMessageDialog(null, "LO SENTIMOS HA OCURRIDO UN ERROR", "ERROR",
							JOptionPane.ERROR_MESSAGE);
				} catch (AddressException e1) {
					JOptionPane.showMessageDialog(null, "LO SENTIMOS HA OCURRIDO UN ERROR CON LA DIRECCIÓN", "ERROR",
							JOptionPane.ERROR_MESSAGE);
				} catch (MessagingException e1) {
					JOptionPane.showMessageDialog(null, "LO SENTIMOS HA OCURRIDO UN ERROR CON EL MENSAJE", "ERROR",
							JOptionPane.ERROR_MESSAGE);
				}
				BaseDatos.closeBD();

			}
		});

		btnEditarPedido = new JButton("ELIMINAR PRODUCTO");
		btnEditarPedido.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int pos = listaProductosPedidos.getSelectedIndex();
				modeloListaProductosPedidos.remove(pos);
				listaProductosPedido.remove(pos);
				cant--;
				lblInfo.setText("Productos en carrito: " + cant);
				if (!btnAddDescuento.isEnabled()) {
					VentanaPrincipal.clientesesion.setPuntos(puntosAnteriores);
					BaseDatos.initBD("Basedatos.db");
					try {
						BaseDatos.modificarClientePuntos(VentanaPrincipal.clientesesion);
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, "LO SENTIMOS HA OCURRIDO UN ERROR CON LA BASE DA DATOS",
								"ERROR", JOptionPane.ERROR_MESSAGE);
					}
					BaseDatos.closeBD();
				}
				if (listaProductosPedido != null) {
					DecimalFormat df = new DecimalFormat();
					df.setMaximumFractionDigits(2);
					lblSumaDinero.setText("TOTAL: " + df.format(obtenerDineroTotal(listaProductosPedido)) + " €");
					btnAddDescuento.setEnabled(true);
				}

			}
		});

		btnInicioSesion = new JButton("INICIAR SESIÓN");
		btnInicioSesion.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int resp = JOptionPane.showConfirmDialog(null, "¿Es tu primera vez?", "Pregunta",
						JOptionPane.YES_NO_OPTION);
				if (resp == JOptionPane.YES_OPTION) {
					new VentanaInicioDeSesion();
				} else {
					new VentanaInicioSesion2();
				}
				setVisible(false);

			}
		});

		btnBorrarPedido = new JButton("BORRAR PEDIDO");
		btnBorrarPedido.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				vaciarLista();
				listaProductosPedido.clear();
				JOptionPane.showMessageDialog(null, "Pedido eliminado correctamente", "Eliminar Pedido",
						JOptionPane.INFORMATION_MESSAGE);
				cant = 0;
				lblInfo.setText("Productos en carrito: " + cant);
				if (!btnAddDescuento.isEnabled()) {
					VentanaPrincipal.clientesesion.setPuntos(puntosAnteriores);
					BaseDatos.initBD("Basedatos.db");
					try {
						BaseDatos.modificarClientePuntos(VentanaPrincipal.clientesesion);
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, "LO SENTIMOS HA OCURRIDO UN ERROR CON LA BASE DE DATOS",
								"ERROR", JOptionPane.ERROR_MESSAGE);
					}
					BaseDatos.closeBD();
					btnAddDescuento.setEnabled(true);
				}

			}
		});

		btnAddProductoNuevo = new JButton("AÑADIR PRODUCTO");
		btnAddProductoNuevo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String[] opciones = new String[] { "Merchandise", "Suplemento" };
				int resp = JOptionPane.showOptionDialog(null, "Selecciona el tipo de producto a añadir",
						"Añadir producto", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, opciones,
						opciones[0]);
				if (resp == 0) {
					String nombre = JOptionPane.showInputDialog("Introduce el nombre ");
					float precio = Float.parseFloat(JOptionPane.showInputDialog("Introduce el precio "));

					// String foto = "/FOTOS/" + JOptionPane.showInputDialog("Introduce el nombre de
					// la imagen + .jpg,.gif,.png ");
					String foto = null;
					JFileChooser jfc = new JFileChooser();
					int Resul = jfc.showSaveDialog(null);
					if (Resul == JFileChooser.APPROVE_OPTION) {
						foto = jfc.getSelectedFile().getPath();

					}
					String material = JOptionPane.showInputDialog("Introduce el material ");
					ProductoMerchandise p = new ProductoMerchandise(
							VentanaPrincipal.ObtenerSiguienteCodigodeProductoMerchandise(), precio, nombre, foto,
							material);
					alp.add(p);
					ActualizarFicheroProductos(alp);
					vaciarTabla();
					agregarProductosAtabla(alp);
				} else {
					String nombre = JOptionPane.showInputDialog("Introduce el nombre ");
					float precio = Float.parseFloat(JOptionPane.showInputDialog("Introduce el precio "));
					// String foto = "/FOTOS/" + JOptionPane.showInputDialog("Introduce el nombre de
					// la imagen + .jpg,.gif,.png ");

					String foto = null;
					JFileChooser jfc = new JFileChooser();
					int Resul = jfc.showSaveDialog(null);
					if (Resul == JFileChooser.APPROVE_OPTION) {
						foto = jfc.getSelectedFile().getPath();

					}
					int prot = Integer.parseInt(JOptionPane.showInputDialog("Introduce los gr de proteinas "));
					int grasas = Integer.parseInt(JOptionPane.showInputDialog("Introduce los gr de grasas "));
					int hidratos = Integer.parseInt(JOptionPane.showInputDialog("Introduce los gr de hidratos "));
					int calorias = Integer.parseInt(JOptionPane.showInputDialog("Introduce las caloirias "));
					ProductoSuplementos ps = new ProductoSuplementos(
							VentanaPrincipal.ObtenerSiguienteCodigodeProductoSuplemento(), precio, nombre, foto, prot,
							grasas, hidratos, calorias);
					alp.add(ps);
					ActualizarFicheroProductos(alp);
					vaciarTabla();
					agregarProductosAtabla(alp);
				}
			}
		});

		btnBorrarProducto = new JButton("BORRAR PRODUCTO");
		btnBorrarProducto.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int pos = tablaInformacion.getSelectedRow();
				Producto P = alp.remove(pos);
				ActualizarFicheroProductos(alp);
				vaciarTabla();
				agregarProductosAtabla(alp);
				JOptionPane.showMessageDialog(null, "Producto eliminado correctamente: \n" + P, "Producto eliminado",
						JOptionPane.INFORMATION_MESSAGE);

			}
		});

		btnAniadirAdmin = new JButton("AÑADIR ADMIN");
		btnAniadirAdmin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new VentanaInicioDeSesion();
				VentanaInicioDeSesion.modificarRegistroAdmin();

			}
		});
		btnSalir = new JButton("SALIR DE PAGINA");
		btnSalir.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				Pedido.GuardarSiguienteCodigodePedido();
				new VentanaPrincipal();

			}
		});

		btnAdd = new JButton("AÑADIR A CARRITO");
		btnAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int pos = tablaInformacion.getSelectedRow();
				listaProductosPedido.add(alp.get(pos));
				cant++;
				lblInfo.setText("Productos en carrito: " + cant);
				btnVerPedido.setEnabled(true);

			}
		});

		btnVerPedido = new JButton("VER CARRITO");
		btnVerPedido.setEnabled(false);
		btnVerPedido.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				vaciarLista();
				agregarALista(listaProductosPedido);
				btnAdd.setVisible(false);
				lblInfo.setVisible(false);
				btnAtras.setVisible(true);
				btnRealizarPedido.setVisible(true);
				btnVerPedido.setEnabled(false);
				btnAdd.setEnabled(false);
				btnAtras.setText("Salir del carrito");
				panelCentro.setLayout(new GridLayout(0, 2));
				panelCentroDerecha.setLayout(new GridLayout(2, 0));
				panelCentro.add(panelCentroDerecha);
				panelCentroDerecha.add(scrollLista);
				panelCentroDerechaAbajo.add(btnEditarPedido);
				if (listaProductosPedido == null) {
					panelCentroDerechaAbajo1.add(lblSumaDinero);
				} else {
					DecimalFormat df = new DecimalFormat();
					df.setMaximumFractionDigits(2);
					lblSumaDinero.setText("TOTAL: " + df.format(obtenerDineroTotal(listaProductosPedido)) + " €");
					panelCentroDerechaAbajo1.add(lblSumaDinero);
				}
				panelCentroDerechaAbajo.add(panelCentroDerechaAbajo1);
				panelCentroDerechaAbajo.add(btnBorrarPedido);
				panelCentroDerechaAbajo.add(btnAddDescuento);
				panelCentroDerecha.add(panelCentroDerechaAbajo);
				comboFiltro.setEnabled(false);

			}
		});

		btnRealizarPedido = new JButton("REALIZAR PEDIDO");
		btnRealizarPedido.setVisible(false);
		btnRealizarPedido.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Pedido p = new Pedido(System.currentTimeMillis(), VentanaPrincipal.clientesesion, listaProductosPedido,
						puntosGastados);
				VentanaPrincipal.clientesesion
						.setPuntos(VentanaPrincipal.clientesesion.getPuntos() + calcularPuntosPedido(p));
				BaseDatos.initBD("Basedatos.db");
				try {
					BaseDatos.insertarPedido(p);
					BaseDatos.modificarClientePuntos(VentanaPrincipal.clientesesion);
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "LO SENTIMOS HA OCURRIDO UN ERROR CON LA BASE DE DATOS",
							"ERROR", JOptionPane.ERROR_MESSAGE);
				}
				BaseDatos.closeBD();

				int resp = JOptionPane.showConfirmDialog(null, "¿Quieres descargar un recibo?", "Pregunta",
						JOptionPane.YES_NO_OPTION);
				if (resp == JOptionPane.YES_OPTION) {
					try {
						Recibo.generarpdf(VentanaPrincipal.clientesesion, p);
						Logger log = Logger.getLogger("");
						log.log(Level.INFO, "Pdf generado correctamente");
					} catch (DocumentException | SQLException | IOException e1) {
						JOptionPane.showMessageDialog(null, "LO SENTIMOS HA OCURRIDO UN ERROR", "ERROR",
								JOptionPane.ERROR_MESSAGE);
					} catch (AddressException e1) {
						JOptionPane.showMessageDialog(null, "LO SENTIMOS HA OCURRIDO UN ERROR CON LA DIRECCIÓN",
								"ERROR", JOptionPane.ERROR_MESSAGE);
					} catch (MessagingException e1) {
						JOptionPane.showMessageDialog(null, "LO SENTIMOS HA OCURRIDO UN ERROR CON EL ENVÍO DEL MENSAJE",
								"ERROR", JOptionPane.ERROR_MESSAGE);
					}
				}

				listaProductosPedido.clear();
				if (opc == 0) {
					modeloListaProductosPedidos.clear();
					lblSumaDinero.setText("TOTAL: " + 0 + "€");
					lblInfo.setText("Productos en carrito: " + 0);
					btnAddDescuento.setEnabled(true);
				}
				btnAtras.doClick();
			}
		});
		btnAddDescuento = new JButton("AÑADIR PUNTOS");
		btnAddDescuento.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (VentanaPrincipal.clientesesion.getPuntos() != 0) {
					puntosAnteriores = VentanaPrincipal.clientesesion.getPuntos();
					float puntos = VentanaPrincipal.clientesesion.getPuntos();
					puntosGastados = Float
							.parseFloat(JOptionPane.showInputDialog("Cuantos puntos quieres gastar de: " + puntos));
					if (puntosGastados <= puntos) {
						float tot = 0;
						for (Producto p : listaProductosPedido) {
							tot += p.getPrecio();
						}
						DecimalFormat df = new DecimalFormat();
						df.setMaximumFractionDigits(2);
						tot = tot - (puntosGastados / 5);
						puntos -= puntosGastados;
						VentanaPrincipal.clientesesion.setPuntos(puntos);
						BaseDatos.initBD("Basedatos.db");
						try {
							BaseDatos.modificarClientePuntos(VentanaPrincipal.clientesesion);
						} catch (SQLException e1) {
							JOptionPane.showMessageDialog(null, "Error al acceder a la base de datos", "Error",
									JOptionPane.ERROR_MESSAGE);
						}
						BaseDatos.closeBD();
						if (opc == 0) {
							lblSumaDinero.setText("TOTAL: " + df.format(tot) + "€");
							btnAddDescuento.setEnabled(false);
						} else {
							btnRealizarPedido.doClick();
						}
					} else {
						JOptionPane.showMessageDialog(null, "No tienes esos puntos", "Puntos incorrectos",
								JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Lo sentimos pero no dispone de puntos", "Sin puntos",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		btnAtras = new JButton("QUITAR CARRITO");
		btnAtras.setVisible(false);

		btnAtras.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				panelCentro.setLayout(new GridLayout(1, 1));
				panelCentroDerecha.remove(scrollLista);
				panelCentro.remove(panelCentroDerecha);
				btnAtras.setVisible(false);
				btnRealizarPedido.setVisible(false);
				btnAdd.setEnabled(true);
				lblInfo.setVisible(true);
				panelCentroDerecha.remove(lblLogo);
				if (VentanaPrincipal.clientesesion != null) {
					btnAdd.setVisible(true);
				}
				comboFiltro.setEnabled(true);
				if (listaProductosPedidos.getModel().getSize() != 0) {
					btnVerPedido.setEnabled(true);
				}
			}
		});
		btnVerPedido.setVisible(true);
		btnAdd.setVisible(true);

		panelNorte.setLayout(new GridLayout(1, 3));
		panelNorte.add(btnAniadirAdmin);
		btnAniadirAdmin.setVisible(false);
		panelNorte.add(lblInfo);
		panelNorte.add(lblFiltro);
		panelNorte.add(comboFiltro);
		panelNorte.add(btnZipRecibos);
		btnZipRecibos.setVisible(false);
		comboFiltro.setEnabled(true);

		panelSur = new JPanel();

		contentPane.add(panelSur, BorderLayout.SOUTH);

		panelSur.add(btnSalir);
		panelSur.add(btnAtras);
		panelSur.add(btnVerPedido);
		panelSur.add(btnAdd);
		panelSur.add(btnRealizarPedido);
		panelSur.add(btnAddProductoNuevo);
		panelSur.add(btnInicioSesion);
		panelSur.add(btnBorrarProducto);
		panelSur.add(btnRepetirPedido);
		panelSur.add(btnDescargarRecibo);
		panelSur.add(btnVerInfoPedido);
		panelSur.add(btnRetroceder);

		btnRetroceder.setVisible(false);
		btnVerInfoPedido.setVisible(false);
		btnRepetirPedido.setVisible(false);
		btnDescargarRecibo.setVisible(false);
		btnBorrarProducto.setVisible(false);
		btnInicioSesion.setVisible(false);
		btnAddProductoNuevo.setVisible(false);
		panelCentro = new JPanel();
		contentPane.add(panelCentro, BorderLayout.CENTER);

		panelCentroDerecha = new JPanel();
		panelCentroDerecha.setLayout(new GridLayout(2, 0));

		panelCentroDerechaAbajo = new JPanel();
		panelCentroDerechaAbajo.setLayout(new GridLayout(2, 2));

		panelCentroDerechaAbajo1 = new JPanel();

		listaProductosPedido = new ArrayList<>();

		/* JTABLE */

		modeloTablaInformacion = new DefaultTableModel() {
			public boolean isCellEditable(int row, int col) {
				return false;
			}
		};

		estructuratabla();

		alp = new ArrayList<>();
		cargarProductosdeFichero(alp);

		ordenarListaCodigoAscendente(alp, alp.size());
		agregarProductosAtabla(alp);

		tablaInformacion = new JTable(modeloTablaInformacion);
		JScrollPane scrollTabla = new JScrollPane(tablaInformacion);

		tablaInformacion.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if (btnAdd.isVisible() || btnInicioSesion.isVisible()) {
					if (e.getClickCount() >= 2) {
						int pos = tablaInformacion.getSelectedRow();
						String dir = alp.get(pos).getImagen();

						BufferedImage img = null;

						try {
							img = ImageIO.read(new File(dir));
						} catch (IOException e1) {
							JOptionPane.showMessageDialog(null, "LO SENTIMOS HA OCURRIDO UN ERROR", "ERROR",
									JOptionPane.ERROR_MESSAGE);
						}

						lblLogo.setIcon(new ImageIcon(img));
						panelCentroDerecha = new JPanel();
						panelCentroDerecha.add(lblLogo);

						panelCentro.setLayout(new GridLayout(1, 2));
						panelCentro.add(panelCentroDerecha);

						btnAtras.setVisible(true);
						btnAtras.setText("Salir de imagen");
					}
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {
				if (btnAdd.isVisible()) {
					panelCentro.setLayout(new GridLayout(1, 1));
					panelCentro.add(scrollTabla);
				}
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				if (e.isAltDown()) {
					btnAdd.doClick();
				}
			}
		});

		panelCentro.setLayout(new GridLayout(1, 1));
		panelCentro.add(scrollTabla);

		setVisible(true);
	}

	/**
	 * Método que añade a la tabla los Productos
	 * 
	 * @param a lista de los Productos a cargar
	 */

	public void agregarProductosAtabla(ArrayList<Producto> a) {
		for (Producto p : a) {
			Object dataRow[] = { "" + String.valueOf(p.getCod()), "" + p.getNombre(),
					"" + String.valueOf(p.getPrecio()) };
			modeloTablaInformacion.addRow(dataRow);
		}

	}

	/**
	 * Método que recibe la lista de pedidos de un cliente y lo añade a la tabla
	 * 
	 * @param a
	 */
	public static void agregarPedidosAtabla(ArrayList<Pedido> a) {
		for (Pedido p : a) {
			float tot = 0;
			DecimalFormat df = new DecimalFormat();
			df.setMaximumFractionDigits(2);
			for (Producto pr : p.getListaproductos()) {
				tot += pr.getPrecio();
			}
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
			Date d = new Date(p.getFec());
			Object dataRow[] = { "" + String.valueOf(p.getCod()), "" + String.valueOf(sdf.format(d)),
					"" + String.valueOf(df.format(tot)) };
			modeloTablaInformacion.addRow(dataRow);
		}
	}

	/**
	 * Metodo que añade a la Jlist la lista de productos
	 * 
	 * @param a lista de productos
	 */
	public void agregarALista(ArrayList<Producto> a) {
		for (Producto p : a) {
			modeloListaProductosPedidos.addElement(p);
		}

	}

	/**
	 * Metodo que vacia la Jlist
	 */
	public void vaciarLista() {
		modeloListaProductosPedidos.clear();
	}

	/**
	 * Metodo que modifica las columnas de la tabla
	 * 
	 * @param a lista de productos
	 */
	public void agregarAtablaFiltro(ArrayList<Producto> a) {
		for (Producto p : a) {
			// Icon i = new ImageIcon(""+p.getImagen());
			Object dataRow[] = { "" + p.getNombre(), "" + String.valueOf(p.getPrecio()) };
			modeloTablaInformacion.addRow(dataRow);
		}

	}

	/*
	 * Modifica las columnas ded la tabla
	 */
	public void modificarTablafiltro() {
		String[] columnas = { "NOMBRE", "PRECIO" };
		modeloTablaInformacion.setColumnIdentifiers(columnas);
	}

	/**
	 * crea las columnas de la tabla
	 */
	public void estructuratabla() {
		String[] columnas = { "CODIGO", "NOMBRE", "PRECIO" };
		modeloTablaInformacion.setColumnIdentifiers(columnas);
	}

	/**
	 * Metodo que vacía la tabla
	 */
	public static void vaciarTabla() {
		while (modeloTablaInformacion.getRowCount() > 0) {
			modeloTablaInformacion.removeRow(0);
		}
	}

	/**
	 * Método que ordena una lista recursivamente de mayor a menor.
	 * 
	 * @param a Lista a ordenar
	 * @param n size de la lista
	 * @return Devuelve la lista ordenada de Precio más alto a bajo
	 */
	public void OrdenarListaMayoraMenor(ArrayList<Producto> a, int n) {
		if (n == 1) {
			return;
		} else {
			for (int i = 0; i < n - 1; i++) {
				if (a.get(i).getPrecio() < a.get(i + 1).getPrecio()) {
					Producto p = a.remove(i + 1);
					a.add(i, p);
				}
			}
			OrdenarListaMayoraMenor(a, n - 1);
		}
		return;
	}

	/**
	 * Metodo que ordena la lista de menor a mayor recursivamente por el precio del
	 * producto
	 * 
	 * @param a lista a ordenar
	 */
	public void OrdenarListaMenoraMayor(ArrayList<Producto> a) {
		OrdenarListaMayoraMenor(a, a.size());
		Collections.reverse(a);
	}

	/**
	 * Metodo que recorre una lista obteniendo los productos que son suplementos
	 * 
	 * @param a lista de productos
	 */
	public ArrayList<Producto> OrdenarListaSuplementos(ArrayList<Producto> a) {
		ArrayList<Producto> al = new ArrayList<>();
		for (Producto p : a) {
			if (p instanceof ProductoSuplementos) {
				al.add(p);
			}
		}
		return al;
	}

	/**
	 * MEtodo que recorre una lista obteniendo los productos que son merchandise
	 * 
	 * @param a lista de productos
	 * @param t tamaño de la lista
	 */
	public ArrayList<Producto> OrdenarListaMerchandise(ArrayList<Producto> a) {
		ArrayList<Producto> al = new ArrayList<>();
		for (Producto p : a) {
			if (p instanceof ProductoMerchandise) {
				al.add(p);
			}
		}
		return al;
	}

	/**
	 * Metodo que ordena alfabeticamente una lista de productos
	 * 
	 * @param a lista a ordenar
	 * @return La lista ordenada alfabeticamente
	 */
	public void OrdenarListaAlfabetica(ArrayList<Producto> a) {
		Collections.sort(a, new Comparator<Producto>() {

			@Override
			public int compare(Producto o1, Producto o2) {
				return o1.getNombre().compareTo(o2.getNombre());
			}
		});
	}

	/**
	 * Metodo que ordena la lista Ascendentemente por codigo
	 * 
	 * @param a lista a ordenar
	 * @param t tamaño de la lista
	 */
	public void ordenarListaCodigoAscendente(ArrayList<Producto> a, int t) {
		if (t == 1) {
			return;
		} else {
			for (int i = 0; i < t - 1; i++) {
				if (a.get(i).getCod() > a.get(i + 1).getCod()) {
					Producto p = a.remove(i + 1);
					a.add(i, p);
				}
			}
			ordenarListaCodigoAscendente(a, t - 1);
		}
		return;
	}

	/**
	 * Metodo que obtiene el total del dinero
	 * 
	 * @param a lista de productos
	 * @return total dinero a pagar
	 */

	public float obtenerDineroTotal(ArrayList<Producto> a) {
		float dinero = 0;
		for (Producto p : a) {
			dinero += p.getPrecio();
		}
		return dinero;
	}

	/**
	 * Metodo que modifica la ventana Producto para el administrador
	 */
	public static void ModificarVentanaProductoAdministrador() {
		btnVerPedido.setVisible(false);
		btnAdd.setVisible(false);
		btnAddProductoNuevo.setVisible(true);
		btnBorrarProducto.setVisible(true);
		lblInfo.setVisible(false);
		btnAniadirAdmin.setVisible(true);

	}

	/**
	 * Metodo que modifica la ventana Producto para que al darle al comprar solo se
	 * puedam ver los productos si no se ha iniciado la sesión
	 */
	public static void ModificarVentanaProductoComprar() {
		btnVerPedido.setVisible(false);
		btnAdd.setVisible(false);
		btnInicioSesion.setVisible(true);

	}

	/**
	 * Metodo que modifica la VentanaProducto para que quite la tabla de productos y
	 * haya una tabla de Pedidos del cliente
	 */
	public static void ModificarVentanaProductoConPedidos() {
		vaciarTabla();
		btnVerPedido.setVisible(false);
		btnAdd.setVisible(false);
		btnInicioSesion.setVisible(false);
		comboFiltro.setVisible(false);
		AniadirPedidosClienteATabla();
		lblInfo.setVisible(false);
		lblFiltro.setVisible(false);
		btnDescargarRecibo.setVisible(false);
		btnAdd.setVisible(false);
		btnVerInfoPedido.setVisible(true);
		btnZipRecibos.setVisible(true);

	}

	/**
	 * Método que modifica la tabla para insetar el historial de pedidos del cliente
	 */
	public static void modificartablaPedidos() {
		String[] columnas = { "CODIGO", "FECHA", "TOTAL" };
		modeloTablaInformacion.setColumnIdentifiers(columnas);
	}

	/**
	 * Metodo que añade los pedidos del cliente que está en la sesión a la tabla.
	 */
	public static void AniadirPedidosClienteATabla() {

		modificartablaPedidos();
		ArrayList<Pedido> pcliente = new ArrayList<>();
		BaseDatos.initBD("Basedatos.db");
		BaseDatos.obtenerPedidosdeCliente(VentanaPrincipal.clientesesion, pcliente);
		BaseDatos.closeBD();
		for (Pedido p : pcliente) {
			float tot = 0;
			for (Producto pr : p.getListaproductos()) {
				tot += pr.getPrecio();
			}
			DecimalFormat df = new DecimalFormat();
			df.setMaximumFractionDigits(2);
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
			Date d = new Date(p.getFec());
			Object dataRow[] = { "" + String.valueOf(p.getCodpe()), "" + sdf.format(d), "" + df.format(tot-(p.getPuntosUsados()/5)) + "€" };
			modeloTablaInformacion.addRow(dataRow);
		}

	}

	/**
	 * Método que carga los productos del fichero a una Arraylist
	 * 
	 * @param a lista donde se van a cargar los productos
	 */
	public static void cargarProductosdeFichero(ArrayList<Producto> a) {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader("productos.txt"));
			String linea = br.readLine();
			while (linea != null) {
				String[] datos = linea.split("\t");
				int cod = Integer.parseInt(datos[0]);
				if (cod % 2 == 0) {
					Float pr = Float.valueOf(datos[1]);
					String nom = datos[2];
					String url = datos[3];
					String mat = datos[4];
					a.add(new ProductoMerchandise(cod, pr, nom, url, mat));
				} else {
					Float pr = Float.valueOf(datos[1]);
					String nom = datos[2];
					String url = datos[3];
					int prot = Integer.parseInt(datos[4]);
					int grasas = Integer.parseInt(datos[5]);
					int hid = Integer.parseInt(datos[6]);
					int cal = Integer.parseInt(datos[7]);
					a.add(new ProductoSuplementos(cod, pr, nom, url, prot, grasas, hid, cal));

				}
				linea = br.readLine();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Método que actualiza el fichero de productos añadiendolos nuevos productos
	 * que se hayan creado
	 * 
	 * @param a lista de productos que se va a guardar en el fichero
	 */
	public void ActualizarFicheroProductos(ArrayList<Producto> a) {
		PrintWriter pw = null;

		try {
			pw = new PrintWriter("productos.txt");
			for (Producto p : a) {
				if (p instanceof ProductoSuplementos) {
					ProductoSuplementos ps = (ProductoSuplementos) p;
					pw.println(ps.getCod() + "\t" + ps.getPrecio() + "\t" + ps.getNombre() + "\t" + ps.getImagen()
							+ "\t" + ps.getProteinas() + "\t" + ps.getGrasas() + "\t" + ps.getHidratos() + "\t"
							+ ps.getCalorias());
				} else {
					ProductoMerchandise pm = (ProductoMerchandise) p;
					pw.println(pm.getCod() + "\t" + pm.getPrecio() + "\t" + pm.getNombre() + "\t" + pm.getImagen()
							+ "\t" + pm.getMaterial());
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (pw != null) {
				pw.flush();
				pw.close();
			}
		}

	}

	/**
	 * Método que calcula los puntos del pedido realizado,por cada euro obtienen 1
	 * punto
	 * 
	 * @param p Pedido realizado
	 * @return Puntos obtenidos por el pedido realizado
	 */
	public float calcularPuntosPedido(Pedido p) {
		float punt = 0;
		for (Producto producto : p.getListaproductos()) {
			punt += producto.getPrecio();
		}
		return punt;
	}

	public ArrayList<Pedido> obtenerlistapedidosCliente() {
		ArrayList<Pedido> listapedidos = new ArrayList<>();
		BaseDatos.initBD("Basedatos.db");
		BaseDatos.obtenerPedidosdeCliente(VentanaPrincipal.clientesesion, listapedidos);
		return listapedidos;

	}
}
