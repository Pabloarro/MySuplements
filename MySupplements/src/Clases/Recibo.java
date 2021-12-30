package Clases;

import java.awt.Toolkit;
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

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import Ventanas.VentanaPrincipal;
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

		JFileChooser jfc = new JFileChooser();
        int Resul = jfc.showSaveDialog(null);
        if (Resul==JFileChooser.APPROVE_OPTION){
            String direc = jfc.getSelectedFile().getPath();
        
        
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
		Date d = new Date(p.getFec());
		
		//FileOutputStream archivo = new FileOutputStream("Recibo"+p.getCod()+".pdf");
		FileOutputStream archivo = new FileOutputStream(direc+".pdf");

		com.itextpdf.text.Document doc = new com.itextpdf.text.Document();
		PdfWriter.getInstance(doc, archivo);
		doc.open();
		
		//TODO Tratar de añadir una imagen
		
		Paragraph parrafo = new Paragraph("Pedido realizado el: "+ sdf.format(d) +"\t Por: "+c.getNom());
		parrafo.setAlignment(1);
		doc.add(parrafo);
		
		doc.add(new Paragraph(""));
		doc.add(new Paragraph("Pedido con código : "+p.getCod()));
		doc.add(new Paragraph("Con productos:"));
		
		float tot=0;
		
		//TODO añadir una tabla para mostrar los productos pedidos.
		
		for(Producto pr : p.getListaproductos()) {
			tot+=pr.getPrecio();
			if(pr instanceof ProductoSuplementos) {
				ProductoSuplementos ps = (ProductoSuplementos)pr;
				doc.add(new Paragraph(""+ps));
			}else {
				ProductoMerchandise pm = (ProductoMerchandise)pr;
				doc.add(new Paragraph(""+pm));
			}
		}
		
		
		doc.add(new Paragraph("Total sin descuentos : " +tot+"€"));
		
		doc.add(new Paragraph("Puntos utilizados : "+VentanaProducto.puntosGastados));
		
		float totFinal=tot-(VentanaProducto.puntosGastados/5);
		
		doc.add(new Paragraph("Precio final : "+totFinal+"€"));
		
		doc.add(new Paragraph("Puntos acumulados para la siguiente compra : "+ tot));
		
		doc.add(new Paragraph("Puntos Totales Actuales : "+c.getPuntos()));	
		
		doc.close();
}
	}
}