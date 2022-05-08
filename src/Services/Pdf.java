/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;
import Entities.Eventl;
import Utils.MailAPI;
import Utils.MyDB;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
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
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.mail.MessagingException;


/**
 *
 * @author Azer Lahmer
 */
public class Pdf {
    private Connection con;
        private Statement ste;
        public Pdf(){
            
        }
         public void add(String file) throws FileNotFoundException, SQLException, DocumentException{
          try {
            con = MyDB.getInstance().getConnection();
                ste=con.createStatement();
                Document my_pdf_report = new Document();
                PdfWriter.getInstance(my_pdf_report, new FileOutputStream(file));
                  ResultSet rs=ste.executeQuery("select * from eventl");
                my_pdf_report.open();  
                PdfPTable my_report_table = new PdfPTable(6);
                  PdfPCell table_cell;
                                
                              
                                table_cell=new PdfPCell(new Phrase("titre"));
                                my_report_table.addCell(table_cell);
                               
                                table_cell=new PdfPCell(new Phrase("datedebut"));
                                my_report_table.addCell(table_cell);
                                table_cell=new PdfPCell(new Phrase("datefin"));
                                my_report_table.addCell(table_cell);
                                table_cell=new PdfPCell(new Phrase("ville"));
                                my_report_table.addCell(table_cell);
                                 table_cell=new PdfPCell(new Phrase("description"));
                                my_report_table.addCell(table_cell);
                       
                                
                                
                                while (rs.next()) {  
                                            
                                String titre=rs.getString("titre");
                                table_cell=new PdfPCell(new Phrase(titre));
                                my_report_table.addCell(table_cell);
                                
                                
                                Date datedebut= rs.getDate("datedebut");
                                //String dateDebut=rs.getString("dateFin");
                                String dd= String.valueOf(datedebut);
                                table_cell=new PdfPCell(new Phrase(dd));
                                my_report_table.addCell(table_cell);
                                
                                Date datefin=rs.getDate("datefin");
                                String dd2= String.valueOf(datefin);
                                table_cell=new PdfPCell(new Phrase(dd2));
                                my_report_table.addCell(table_cell);
                                
                                
                                String ville=rs.getString("ville");
                                table_cell=new PdfPCell(new Phrase(ville));
                                my_report_table.addCell(table_cell);
                                
                                
                                String description=rs.getString("description");
                                table_cell=new PdfPCell(new Phrase(description));
                                my_report_table.addCell(table_cell);
                                
                                
                           
                               

                }
                my_pdf_report.add(my_report_table);                       
                my_pdf_report.close();
                
               /* Close all DB related objects */
                 rs.close();
                ste.close();
                con.close();

          } catch(Exception ex){
              System.out.println("ex");
          }
         }
         
         
         
         
     public static void createAndSendList(String mail) {
       ServiceEventl p = new ServiceEventl();
        try {
            Rectangle pageSize = new Rectangle(350, 720);
            Document document = new Document(pageSize);
            String filepath = "events.pdf";
            PdfWriter instance = PdfWriter.getInstance(document, new FileOutputStream(filepath));

            document.open();
            Image image1 = Image.getInstance("src/img/LOGO1.png");
            double percent = 0.5;
            image1.scaleAbsolute(150, 130);
            document.add(image1);
            document.add(new Paragraph("Voici la liste de tous les evenements\n"));
           
            List<Eventl> events = p.recuperer();
            for (Eventl ligne : events) {
                document.add(new Paragraph(
                        "\n\n* Titre: " + ligne.getTitre() + "\n* ville: " + ligne.getVille()+ "\n* Description: " + ligne.getDescription()+ "\n* datedebut: " + ligne.getDatedebut()+ "\n* Datefin: " + ligne.getDatefin()+ "\n*"));

            }

            document.close();
            MailAPI.sendMailWithFile(mail, "Evenements Go Gym", new File(filepath));
            //auto open for testing
            //  File myFile = new File("/path/to/file.Pdf");
            //Desktop.getDesktop().open(myFile);
        } catch (DocumentException | IOException | MessagingException e) {
            System.err.println(e.getMessage());
        }
    }
         

    
}
