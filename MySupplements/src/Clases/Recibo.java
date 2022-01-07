package Clases;


import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.URLName;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.sun.mail.smtp.SMTPTransport;

import Ventanas.VentanaProducto;

public class Recibo {

	

	public static void main(String[] args) {
		new Recibo();
	}
	
	
	/**
	 * Método que genera un pdf del pedido realizado,pidiendo la ruta donde se va a guardar el documento
	 * @param c Cliene que ha realizado el pedido
	 * @param p Pedido realizado.
	 */
	public static void generarpdf(Cliente c,Pedido p) throws DocumentException, SQLException, MalformedURLException, IOException, AddressException, MessagingException {
	
		 String[] opciones = new String[] {"Descargar", "Por correo"};
		  int resp = JOptionPane.showOptionDialog(null, "Selecciona la forma en la que quieres recibir el recibo", "Opciones recibo", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, opciones, opciones[0]);
 
		if(resp==0) {
			 JFileChooser jfc = new JFileChooser();
	        int Resul = jfc.showSaveDialog(null);
	        if (Resul==JFileChooser.APPROVE_OPTION){
	            String direc = jfc.getSelectedFile().getPath();
	        
	        
				
				
				FileOutputStream archivo = new FileOutputStream(direc+".pdf");
		
				Document doc = new Document();
				PdfWriter.getInstance(doc, archivo);
				crearDocumento(c, p, doc);
	        }
	        
		}else {
			  	Document doc = new Document();
			  	String nombre="Pedido"+p.getCodpe()+".pdf";
			  	String comp="C:/Users/ALAR/Desktop/RecibosDescargados/"+nombre;
	            FileOutputStream archivo = new FileOutputStream(comp);
	           
	            PdfWriter.getInstance(doc,archivo);
	            crearDocumento(c, p, doc);
	            enviarcorreo(comp, nombre);
	            
	         
		}
	
	}	
	
	/**
	 * Método que envía el mensaje de correo
	 * @param comp dirección del archivo adjunto
	 * @param nom nombre del archivo adjunto
	 */
	public static void enviarcorreo(String comp,String nom) throws AddressException, MessagingException {
		String correo="";//EL CORREO ESTÁ  QUITADO PARA QUE NO SE VEA EN EL GITHUB
		String contra="";//LA CONTRASEÑA DEL CORREO ESTÁ QUITADA PARA QUE NO SE VEA EN EL GITHUB
		
		
		String correoDestinatario=JOptionPane.showInputDialog(null,"Introduzca su correo completo por favor");
		
		Properties p = new Properties();
			p.put("mail.smtp.host", "smtp.gmail.com");
			p.setProperty("mail.smtp.starttls.enable", "true");
			p.put("mail.smtp.ssl.trust", "smtp.gmail.com");
			p.setProperty("mail.smtp.port", "587");
			
			p.setProperty("mail.smtp.user", correo);
			p.setProperty("mail.smtp.auth", "true");
			
		Session s = Session.getDefaultInstance(p);
		BodyPart texto = new MimeBodyPart();
			texto.setText("Aqui le adjuntamos su recibo. Gracias por elegirnos.");
		
		BodyPart adjunto = new MimeBodyPart();
			adjunto.setDataHandler(new DataHandler(new FileDataSource(comp)));
			adjunto.setFileName(nom);
			
		MimeMultipart m = new MimeMultipart();
			m.addBodyPart(texto);
			m.addBodyPart(adjunto);
		
			
		
		MimeMessage mensaje = new MimeMessage(s);
			mensaje.setFrom(new InternetAddress(correo));
			mensaje.addRecipient(Message.RecipientType.TO, new InternetAddress(correoDestinatario));
			mensaje.setSubject("Recibo del pedido realizado");
			
			mensaje.setContent(m);
			
			
			
			
		Transport t = s.getTransport("smtp");
		t.connect(correo, contra);
		t.sendMessage(mensaje, mensaje.getAllRecipients());
		
		
		
		t.close();
		
		JOptionPane.showMessageDialog(null, "mensaje enviado correctamente","MENSAJE ENVIADO",JOptionPane.INFORMATION_MESSAGE);
		
		
	}
	
	
	/**
	 * Método que crea el documento
	 * @param c Cliente
	 * @param p Pedido
	 * @param doc documento
	 */
	public static void crearDocumento(Cliente c,Pedido p,Document doc) throws DocumentException {

		//Abrir el documento
		doc.open();
		

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
		Date d = new Date(p.getFec());
		//TODO Tratar de añadir una imagen
		
		Paragraph parrafo = new Paragraph("Pedido realizado el: "+ sdf.format(d) +"\t Por: "+c.getNom());
		parrafo.setAlignment(1);
		doc.add(parrafo);
		
		doc.add(new Paragraph(" "));
		doc.add(new Paragraph("Pedido con código : "+p.getCod()));
		doc.add(new Paragraph(" "));
		doc.add(new Paragraph("Con productos:"));
		doc.add(new Paragraph(" "));
		
		
		float tot=0;
		//Columnas nombre de producto ,precio
		
		float[] anchuraColumnas= {5f,2f};
		
		PdfPTable tabla = new PdfPTable(anchuraColumnas);
		tabla.setWidthPercentage(85f);
		
		
		tabla.addCell("Nombre");
		tabla.addCell("Precio");
		
		
		
		for(Producto pr : p.getListaproductos()) {
			tot+=pr.getPrecio();
		tabla.addCell(""+pr.getNombre());
		tabla.addCell(""+pr.getPrecio()+"€");
		}
		
		
		DecimalFormat df = new DecimalFormat();
		df.setMaximumFractionDigits(2);
		
		
		PdfPCell celda1 = new PdfPCell(new Phrase(" "));
		PdfPCell celda2 = new PdfPCell(new Phrase(" "));
		
		celda1.setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
		celda2.setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
		
		tabla.addCell(celda1);
		tabla.addCell(celda2);
		tabla.addCell(celda1);
		tabla.addCell(celda2);
		
		
		tabla.addCell("Total sin descuentos");
		tabla.addCell(""+df.format(tot)+"€");
		
		tabla.addCell(celda1);
		tabla.addCell(celda2);
		
		tabla.addCell("Puntos utilizados");
		tabla.addCell(""+p.getPuntosUsados());
		
		tabla.addCell("Valor de puntos utilizados");
	
		tabla.addCell(""+df.format(p.getPuntosUsados()/5)+"€");
		
		tabla.addCell(celda1);
		tabla.addCell(celda2);
		
		tabla.addCell("Precio final ");
		float totFinal=tot-(p.getPuntosUsados()/5);
		tabla.addCell(""+df.format(totFinal)+"€");
		
		
		tabla.addCell(celda1);
		tabla.addCell(celda2);
		tabla.addCell(celda1);
		tabla.addCell(celda2);
		
		
		tabla.addCell("Puntos obtenidos por la compra"); 
		tabla.addCell(""+df.format(tot));
		
		tabla.addCell("Puntos acumulados");
		tabla.addCell(""+df.format(c.getPuntos()));
		

		Paragraph p1 = new Paragraph();
		p1.add(tabla);
		doc.add(p1);
		
		
		doc.close();
		
	}
}