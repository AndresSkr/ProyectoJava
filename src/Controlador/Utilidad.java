package Controlador;

import Modelo.Persona;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Font;
import java.io.FileOutputStream;

public class Utilidad {

    public void CrearPDFCompra(Persona p ) {

        try {

            Document doc = new Document();
            FileOutputStream fichero = new FileOutputStream("Compra_"+p.getNombre()+".pdf");
            PdfWriter.getInstance(doc, fichero);
            doc.open();
            Paragraph titulo = new Paragraph("prueba titulo",
                    FontFactory.getFont("arial",
                            22,
                            Font.BOLD,
                            BaseColor.BLUE));

            doc.add(titulo);
            doc.add(new Paragraph("Nombre: Juan \n \n"));
            PdfPTable tabla = new PdfPTable(3);
            tabla.addCell("ID");
            tabla.addCell("nombre");
            tabla.addCell("Telefono");
            doc.add(tabla);
            doc.close();
        } catch (Exception e) {
        }
    }

}
