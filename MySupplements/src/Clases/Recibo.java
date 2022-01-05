package Clases;


import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFileChooser;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

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
			
			
			FileOutputStream archivo = new FileOutputStream(direc+".pdf");
	
			com.itextpdf.text.Document doc = new com.itextpdf.text.Document();
			PdfWriter.getInstance(doc, archivo);
			doc.open();
			
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
	
	
	
}