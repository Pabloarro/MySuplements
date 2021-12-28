package Clases;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JFileChooser;
import javax.swing.text.Document;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import BaseDatos.BaseDatos;
import Ventanas.VentanaProducto;

public class Recibo {

	

	public static void main(String[] args) {
		new Recibo();
	}
	
	
	
	/**
	 * Método que genera un pdf del pedido realizado
	 * @param c Cliene que ha realizado el pedido
	 * @param p Pedido realizado.
	 */
	public static void generarpdf(Cliente c,Pedido p) throws DocumentException, SQLException, MalformedURLException, IOException {
		/*JFileChooser guardar = new JFileChooser();
		guardar.showSaveDialog(null);
		guardar.setFileSelectionMode(JFileChooser.SAVE_DIALOG);
		guardar.setDialogTitle("Recibo"+p.getCod()+".pdf");
		*/
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
		Date d = new Date(p.getFec());
		
		FileOutputStream archivo = new FileOutputStream("Recibo"+p.getCod()+".pdf");
		
		com.itextpdf.text.Document doc = new com.itextpdf.text.Document();
		PdfWriter.getInstance(doc, archivo);
		
		//Image logo = Image.getInstance("/LOGO/logo_large.png");
		
		doc.open();
		
		//doc.add(logo);
		
		Paragraph parrafo = new Paragraph("Pedido realizado el: "+ sdf.format(d) +"\t Por: "+c.getNom());
		parrafo.setAlignment(1);
		doc.add(parrafo);
		
		doc.add(new Paragraph("Pedido con código : "+p.getCod()));
		doc.add(new Paragraph("Con productos:"));
		float tot = 0;
		for(Producto pr : p.getListaproductos()) {
			tot+=pr.getPrecio();
			if(pr instanceof ProductoSuplementos) {
				doc.add(new Paragraph(""+(ProductoSuplementos)pr));
			}else {
				doc.add(new Paragraph(""+(ProductoMerchandise)pr));
			}
		}
		
		
		doc.add(new Paragraph("Total sin descuentos : " +tot+"€"));
		
		doc.add(new Paragraph("Puntos utilizados : "+VentanaProducto.puntosGastados));
		tot=tot-(VentanaProducto.puntosGastados/5);
		doc.add(new Paragraph("Precio final : "+tot+"€"));
		
		float punt = tot/5;
		doc.add(new Paragraph("Puntos acumulados para la siguiente compra : "+ punt));
		doc.add(new Paragraph("Puntos Totales : "+c.getPuntos()));	
}
	
}