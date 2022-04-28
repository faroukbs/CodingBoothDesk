/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import com.itextpdf.text.Rectangle;
import com.sun.scenario.effect.ImageData;
//import javafx.scene.image.Image;
import com.itextpdf.text.Image;
import java.awt.Color;
/**
 *
 * @author Azer Lahmer
 */
public class pdfc {

    private Connection con;
    private Statement ste;

    public pdfc() {

    }

    public void add(String file) throws FileNotFoundException, SQLException, DocumentException {
        try {
            con = MyDB.getInstance().getCnx();
            ste = con.createStatement();
            Document my_pdf_report = new Document();
            PdfWriter.getInstance(my_pdf_report, new FileOutputStream(file));
            ResultSet rs = ste.executeQuery("select * from salle");
            my_pdf_report.open();
            
            
//            Image logo = Image.getInstance("C:\\Users\\ahmed\\Desktop\\ahmedjebridesc\\src\\image\\LOGO1.png");
//            logo.setAlignment(Element.ALIGN_LEFT);
//            //logo.setHeight(Element.ALIGN_LEFT);
//            
//            my_pdf_report.add(logo);


            Paragraph paragraph1 = new Paragraph("Table Cours\n");
            paragraph1.setAlignment(Element.ALIGN_CENTER);
            my_pdf_report.add(paragraph1);

            Paragraph paragraph2 = new Paragraph("\n");
            paragraph2.setAlignment(Element.ALIGN_CENTER);
            my_pdf_report.add(paragraph2);

            PdfPTable my_report_table = new PdfPTable(4);
            PdfPCell table_cell;

            table_cell = new PdfPCell(new Phrase("id salle"));
            my_report_table.addCell(table_cell);

            table_cell = new PdfPCell(new Phrase("nom salle"));
            my_report_table.addCell(table_cell);

            table_cell = new PdfPCell(new Phrase("description"));
            my_report_table.addCell(table_cell);

            table_cell = new PdfPCell(new Phrase("categorie id"));
            my_report_table.addCell(table_cell);

            while (rs.next()) {

                String id = rs.getString("idsalle");
                table_cell = new PdfPCell(new Phrase(id));
                my_report_table.addCell(table_cell);

                String nomS = rs.getString("nomsalle");
                table_cell = new PdfPCell(new Phrase(nomS));
                my_report_table.addCell(table_cell);

                String description = rs.getString("description");
                table_cell = new PdfPCell(new Phrase(description));
                my_report_table.addCell(table_cell);

                String idcat = rs.getString("idcategorie");
                table_cell = new PdfPCell(new Phrase(idcat));
                my_report_table.addCell(table_cell);

            }
            my_pdf_report.add(my_report_table);
            
            Paragraph paragraph3 = new Paragraph("\n");
            paragraph3.setAlignment(Element.ALIGN_CENTER);
            my_pdf_report.add(paragraph3);

            Paragraph paragraph4 = new Paragraph("QrCode du site web symphony\n");
            paragraph4.setAlignment(Element.ALIGN_CENTER);
            my_pdf_report.add(paragraph4);

            Paragraph paragraph5 = new Paragraph("\n");
            paragraph5.setAlignment(Element.ALIGN_CENTER);
            my_pdf_report.add(paragraph5);

            
            
            Image image = Image.getInstance("C:\\Users\\ahmed\\Desktop\\ahmedjebridesc\\QrCode.png");
            image.setAlignment(Element.ALIGN_CENTER);
            my_pdf_report.add(image);
            
            my_pdf_report.close();

            /* Close all DB related objects */
            rs.close();
            ste.close();
            con.close();

        } catch (Exception ex) {
            System.out.println("errrrr");
        }
    }

}
