/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
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


/**
 *
 * @author Azer Lahmer
 */
public class pdf {
    private Connection con;
        private Statement ste;
        public pdf(){
            
        }
         public void add(String file) throws FileNotFoundException, SQLException, DocumentException{
          try {
            con = MyDB.getInstance().getConnection();
                ste=con.createStatement();
                Document my_pdf_report = new Document();
                PdfWriter.getInstance(my_pdf_report, new FileOutputStream(file));
                  ResultSet rs=ste.executeQuery("select * from salle");
                my_pdf_report.open();  
                PdfPTable my_report_table = new PdfPTable(4);
                  PdfPCell table_cell;
                                
                              
                                table_cell=new PdfPCell(new Phrase("id salle"));
                                my_report_table.addCell(table_cell);
                               
                                table_cell=new PdfPCell(new Phrase("nom salle"));
                                my_report_table.addCell(table_cell);
                               
                                
                                 table_cell=new PdfPCell(new Phrase("description"));
                                my_report_table.addCell(table_cell);
                                
                                
                                table_cell=new PdfPCell(new Phrase("categorie id"));
                                my_report_table.addCell(table_cell);
                                
                                 
                                while (rs.next()) {  
                                            
                                String id=rs.getString("idsalle");
                                table_cell=new PdfPCell(new Phrase(id));
                                my_report_table.addCell(table_cell);
                                
                                String nomS=rs.getString("nomsalle");
                                table_cell=new PdfPCell(new Phrase(nomS));
                                my_report_table.addCell(table_cell);
                                

                                String description=rs.getString("description");
                                table_cell=new PdfPCell(new Phrase(description));
                                my_report_table.addCell(table_cell);
                                
                                
                                String idcat=rs.getString("idcategorie");
                                table_cell=new PdfPCell(new Phrase(idcat));
                                my_report_table.addCell(table_cell);
                                
                                
                                
                                

                }
                my_pdf_report.add(my_report_table);                       
                my_pdf_report.close();
                
               /* Close all DB related objects */
                 rs.close();
                ste.close();
                con.close();

          } catch(Exception ex){
              System.out.println("errrrr");
          }
         }
         
         
         
         
         
         

    
}
