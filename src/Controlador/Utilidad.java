package Controlador;

import Modelo.Persona;
import Modelo.Producto;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Font;
import java.io.FileOutputStream;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Utilidad {

    public void CrearPDFCompra(Persona p, ArrayList<Producto> listaDeProductos, int factura) {

        try {
            Document doc = new Document();
            FileOutputStream fichero = new FileOutputStream("Factura" + p.getNombre() + "_" + factura + ".pdf");
            PdfWriter.getInstance(doc, fichero);
            doc.open();
            Paragraph titulo = new Paragraph("FACTURACION",
                    FontFactory.getFont("arial",
                            22,
                            Font.BOLD,
                            BaseColor.BLUE));

            doc.add(titulo);
            doc.add(new Paragraph("Nombre: " + p.getNombre() + " \n \n"));
            PdfPTable tabla = new PdfPTable(4);
            tabla.addCell("ID");
            tabla.addCell("Nombre");
            tabla.addCell("Cantidad");
            tabla.addCell("Precio");
            float total = 0f;
            for (Producto listado : listaDeProductos) {
                tabla.addCell(String.valueOf(listado.getId()));
                tabla.addCell(listado.getNombre());
                tabla.addCell(String.valueOf(listado.getCantidad()));
                tabla.addCell(String.valueOf(listado.getPrecio()));
                total += listado.getCantidad() * listado.getPrecio();
            }
            Paragraph totalNumerico = new Paragraph(String.valueOf(total)+"$",
                    FontFactory.getFont("arial",
                            14,
                            Font.BOLD,
                            BaseColor.RED));
            tabla.addCell("");
            tabla.addCell("");
            tabla.addCell("TOTAL");
            tabla.addCell(totalNumerico);

            doc.add(tabla);
            doc.close();
        } catch (Exception e) {
        }
    }
    
    public boolean SoloNumeros(String cadena){
        try {
            double numeros = Double.parseDouble(cadena);
            return true;
        } catch (Exception a) {
            return false;
        }
        
    }

}
