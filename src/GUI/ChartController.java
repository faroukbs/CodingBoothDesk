/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.GrayColor;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.sun.scenario.effect.ImageData;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.imageio.ImageIO;
import entities.Commande;
import util.MyDBPi;

/**
 * FXML Controller class
 *
 * @author X
 */
public class ChartController implements Initializable {

    @FXML
    private AnchorPane chartNode;
    @FXML
    private HBox chartHBox;
    public static int numeroPDF = 0;
    Document doc = new Document();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        double total = 0;
//        DecimalFormat df2 = new DecimalFormat(".##");
//        Stage stage = new Stage();
//        Scene scene = new Scene(new Group());
//        stage.setTitle("Imported Fruits");
//        stage.setWidth(500);
//        stage.setHeight(500);
//
////        ObservableList<PieChart.Data> pieChartData
////                = FXCollections.observableArrayList(
////                        new PieChart.Data("Grapefruit", 13),
////                        new PieChart.Data("Oranges", 25),
////                        new PieChart.Data("Plums", 10),
////                        new PieChart.Data("Pears", 22),
////                        new PieChart.Data("Apples", 30));
//        final PieChart chart = new PieChart(buildDataBonPlan());
//        final Label caption = new Label("");
//        caption.setTextFill(Color.DARKORANGE);
//        caption.setStyle("-fx-font: 24 arial;");
//
//        for (final PieChart.Data data : chart.getData()) {
//            total = total + data.getPieValue();
//        }
//        final double totalFinal = total;
//
//        for (final PieChart.Data data : chart.getData()) {
//            data.setName(((data.getName()+" "+df2.format((data.getPieValue() / totalFinal) * 100))) + "%");
//            data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED,
//                    new EventHandler<MouseEvent>() {
//                @Override
//                public void handle(MouseEvent e) {
//                    
//                    caption.setTranslateX(e.getSceneX());
//                    caption.setTranslateY(e.getSceneY());
//                    caption.setText(String.valueOf(df2.format((data.getPieValue() / totalFinal) * 100)) + "%");
//                    if (!((Group) scene.getRoot()).getChildren().contains(caption)) {
//                        ((Group) scene.getRoot()).getChildren().add(caption);
//                    }
//                }
//            });
//        }
//
//        System.out.println(total);
//
//        chart.setTitle("Imported Fruits");
//        ((Group) scene.getRoot()).getChildren().add(chart);
//        stage.setScene(scene);
//        stage.show();
        // TODO
        detailleBonPlan();
    }

    public ObservableList buildDataBonPlan() {
        return null;
////     public  ObservableList<PieChart.Data> buildData() {
//        List<PieChart.Data> myList = new ArrayList<PieChart.Data>();
//        ResultSet rs = null;
//        PieChart.Data d;
//        ObservableList observableList = null;
//
//        try {
//// String requete1 = "SELECT * from Reclamation"; //MAJUSCULE NON OBLIGATOIRE 
//// String requete = "SELECT ref_bonplan,COUNT(id) as nbr FROM `Reclamation` group by ref_bonplan";
//// String requete = "SELECT r.ref_bonplan,COUNT(r.id) as nbr FROM Reclamation r,bon_plan b where r.ref_bonplan=b.id group by r.ref_bonplan";
//            String requete = "SELECT bon_plan.libelle,COUNT(Reclamation.id) as nbr FROM Reclamation left join bon_plan on Reclamation.ref_bonplan=bon_plan.id group by Reclamation.ref_bonplan";
//
//            Statement pst = MyConnection.getInstance().getCnx().prepareStatement(requete); // import java.sql.Statement
//            rs = pst.executeQuery(requete);
//            while (rs.next()) {
//
//                if (rs.getObject(1) == null) {
//                    System.out.println(rs.getString(1));
//                    d = new PieChart.Data("Autre ", rs.getInt(2));
//                } else {
//                    d = new PieChart.Data(rs.getString(1), rs.getInt(2));
//                }
////                System.out.println(rs.getString(1));
////                System.out.println(rs.getInt(2));
//                myList.add(d);
//
//            }
//            observableList = FXCollections.observableArrayList(myList);
//
//            return observableList;
//
//        } catch (Exception e) {
//
//            System.out.println("Error on DB connection BuildDataBonPlan");
//            System.out.println(e.getStackTrace());
//            System.out.println(e.getMessage());
//
//        }
//        return observableList;
    }
    
    public ObservableList buildDataCompte() {
//     public  ObservableList<PieChart.Data> buildData() {
        List<PieChart.Data> myList = new ArrayList<PieChart.Data>();
        ResultSet rs = null;
        PieChart.Data d;
        ObservableList observableList = null;

        try {
// String requete1 = "SELECT * from Reclamation"; //MAJUSCULE NON OBLIGATOIRE 
// String requete = "SELECT ref_bonplan,COUNT(id) as nbr FROM `Reclamation` group by ref_bonplan";
// String requete = "SELECT r.ref_bonplan,COUNT(r.id) as nbr FROM Reclamation r,bon_plan b where r.ref_bonplan=b.id group by r.ref_bonplan";
            String requete = "SELECT idcommande, mode_paiement  FROM commande";

            Statement pst = MyDBPi.getInstance().getConnection().prepareStatement(requete); // import java.sql.Statement
            rs = pst.executeQuery(requete);
            while (rs.next()) {

                if (rs.getObject(1) == null) {
                    System.out.println(rs.getString(1));
                    d = new PieChart.Data("Autre ", rs.getInt(2));
                } else {
                    d = new PieChart.Data(rs.getString(1), rs.getInt(2));
                }
//                System.out.println(rs.getString(1));
//                System.out.println(rs.getInt(2));
                myList.add(d);

            }
            observableList = FXCollections.observableArrayList(myList);

            return observableList;

        } catch (Exception e) {

            System.out.println("Error on DB connection BuildDataBonPlan");
            System.out.println(e.getStackTrace());
            System.out.println(e.getMessage());

        }
        return observableList;
    }
    
    
     public XYChart.Series buildDataLineChart() {
         XYChart.Series series = new XYChart.Series();
        series.setName("Nombre de Commande par mode de paiement");

        ResultSet rs = null;
        XYChart.Series d;
        try {
            String requete = "SELECT idcommande, mode_paiement  FROM commande";

            Statement pst = MyDBPi.getInstance().getConnection().prepareStatement(requete); // import java.sql.Statement
            rs = pst.executeQuery(requete);
            while (rs.next()) 
            {
                    series.getData().add(new XYChart.Data(rs.getString(1), rs.getInt(2)));
            }
            
            return series;

        } catch (Exception e) {

            System.out.println("Error on DB connection BuildDataLineChart");
            System.out.println(e.getStackTrace());
            System.out.println(e.getMessage());

        }
        return series;
    }

     
     //***********************************************************************************************************************************************************
     //***********************************************************************************************************************************************************
     
     
    @FXML
    private void detailleBonPlan() {
//        double total = 0;
//        DecimalFormat df2 = new DecimalFormat(".##");
//        Stage stage = new Stage();
//        Scene scene = new Scene(new Group());
//        stage.setTitle("Reclamations sur les bons plan");
//        stage.setWidth(600);
//        stage.setHeight(600);
//
//        final PieChart chart = new PieChart(buildDataBonPlan());
//        final Label caption = new Label("");
//        caption.setTextFill(Color.DARKORANGE);
//        caption.setStyle("-fx-font: 24 arial;");
//
//        for (final PieChart.Data data : chart.getData()) {
//            total = total + data.getPieValue();
//        }
//        final double totalFinal = total;
//
//        for (final PieChart.Data data : chart.getData()) {
//            data.setName(((data.getName() + " " + df2.format((data.getPieValue() / totalFinal) * 100))) + "%");
//            data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED,
//                    new EventHandler<MouseEvent>() {
//                @Override
//                public void handle(MouseEvent e) {
//
//                    caption.setTranslateX(e.getSceneX());
//                    caption.setTranslateY(e.getSceneY());
//                    caption.setText(String.valueOf(df2.format((data.getPieValue() / totalFinal) * 100)) + "%");
//                    if (!((Group) scene.getRoot()).getChildren().contains(caption)) {
//                        ((Group) scene.getRoot()).getChildren().add(caption);
//                    }
//                }
//            });
//        }
//
//        chart.setTitle("Reclamations sur les bons plan");
//        ((Group) scene.getRoot()).getChildren().add(chart);
//        stage.setScene(scene);
//        chartNode.getChildren().clear();
//        chartNode.getChildren().setAll(chart);

    }

//    private void setNode(Node node) {
//        btnGestionAffichage.getChildren().clear();
//        btnGestionAffichage.getChildren().add((Node) node);
//        FadeTransition ft = new FadeTransition(Duration.seconds(1));//dure de la translation
//        ft.setNode(node);
//        ft.setFromValue(0.10);//dispartion 
//        ft.setToValue(1);
//        ft.setCycleCount(1);
//        ft.setAutoReverse(true);
//        ft.play();
//    }
    @FXML
    private void globalChart(ActionEvent event) {

        List<PieChart.Data> myList = new ArrayList<PieChart.Data>();
        ResultSet rs = null;
        PieChart.Data d;
        ObservableList observableList = null;

        try {
// String requete1 = "SELECT * from Reclamation"; //MAJUSCULE NON OBLIGATOIRE 
// String requete = "SELECT ref_bonplan,COUNT(id) as nbr FROM `Reclamation` group by ref_bonplan";
// String requete = "SELECT r.ref_bonplan,COUNT(r.id) as nbr FROM Reclamation r,bon_plan b where r.ref_bonplan=b.id group by r.ref_bonplan";
            String requete = "SELECT idcommande, mode_paiement  FROM commande";

            Statement pst = MyDBPi.getInstance().getConnection().prepareStatement(requete); // import java.sql.Statement
            rs = pst.executeQuery(requete);
            while (rs.next()) {

                if (rs.getObject(1) == null) {
                    System.out.println(rs.getString(1));
                    d = new PieChart.Data("Autre ", rs.getInt(2));
                } else {
                    d = new PieChart.Data(rs.getString(1), rs.getInt(2));
                }
//                System.out.println(rs.getString(1));
//                System.out.println(rs.getInt(2));
                myList.add(d);

            }
            observableList = FXCollections.observableArrayList(myList);

        } catch (Exception e) {

            System.out.println("Error on DB connection BuildDataBonPlan");
            System.out.println(e.getStackTrace());
            System.out.println(e.getMessage());

        }

    }

    @FXML
    private void detailleCompte(ActionEvent event) {
//                 double total = 0;
//        DecimalFormat df2 = new DecimalFormat(".##");
//        Stage stage = new Stage();
//        Scene scene = new Scene(new Group());
//        stage.setTitle("Reclamations sur les comptes");
//        stage.setWidth(600);
//        stage.setHeight(600);
//        
//        System.out.println(buildDataCompte());
//        final PieChart chart = new PieChart(buildDataCompte());
//        final Label caption = new Label("");
//        caption.setTextFill(Color.DARKORANGE);
//        caption.setStyle("-fx-font: 24 arial;");
//
//        for (final PieChart.Data data : chart.getData()) {
//            total = total + data.getPieValue();
//        }
//        final double totalFinalCompte = total;
//
//        for (final PieChart.Data data : chart.getData()) {
//            data.setName(((data.getName() + " " + df2.format((data.getPieValue() / totalFinalCompte) * 100))) + "%");
//            data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED,
//                    new EventHandler<MouseEvent>() {
//                @Override
//                public void handle(MouseEvent e) {
//
//                    caption.setTranslateX(e.getSceneX());
//                    caption.setTranslateY(e.getSceneY());
//                    caption.setText(String.valueOf(df2.format((data.getPieValue() / totalFinalCompte) * 100)) + "%");
//                    if (!((Group) scene.getRoot()).getChildren().contains(caption)) {
//                        ((Group) scene.getRoot()).getChildren().add(caption);
//                    }
//                }
//            });
//        }
//
//        chart.setTitle("Reclamations sur les comptes");
//        ((Group) scene.getRoot()).getChildren().add(chart);
//        stage.setScene(scene);
//        chartNode.getChildren().clear();
//        chartNode.getChildren().setAll(chart);
    }

    @FXML
    private void lineChart(ActionEvent event) {
        
        
//        ObservableList<XYChart.Series<Date, Number>> series = FXCollections.observableArrayList();
// 
//ObservableList<XYChart.Data<Date, Number>> series1Data = FXCollections.observableArrayList();
//series1Data.add(new XYChart.Data<Date, Number>(new GregorianCalendar(2012, 11, 15).getTime(), 2));
//series1Data.add(new XYChart.Data<Date, Number>(new GregorianCalendar(2014, 5, 3).getTime(), 4));
// 
//ObservableList<XYChart.Data<Date, Number>> series2Data = FXCollections.observableArrayList();
//series2Data.add(new XYChart.Data<Date, Number>(new GregorianCalendar(2014, 0, 13).getTime(), 8));
//series2Data.add(new XYChart.Data<Date, Number>(new GregorianCalendar(2014, 7, 27).getTime(), 4));
// 
//series.add(new XYChart.Series<>("Series1", series1Data));
//series.add(new XYChart.Series<>("Series2", series2Data));
// 
//NumberAxis numberAxis = new NumberAxis();
//DateAxis dateAxis = new DateAxis();
//LineChart<Date, Number> lineChart = new LineChart<>(dateAxis, numberAxis, series);

      double total = 0;
        DecimalFormat df2 = new DecimalFormat(".##");
        Stage stage = new Stage();
        Scene scene = new Scene(new Group());
        stage.setTitle("Nombre de commande par mode de paiement");
        stage.setWidth(600);
        stage.setHeight(600);


        //defining the axes
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("mode de paiement");
        //creating the chart
        final LineChart<String,Number> lineChart = 
                new LineChart<String,Number>(xAxis,yAxis);
                
//        lineChart.setTitle("Nombre de reclamations par jour");
        //defining a series
//        XYChart.Series series = new XYChart.Series();
//        series.setName("My portfolio");
        //populating the series with data
//        series.getData().add(new XYChart.Data("10", 23));
//        series.getData().add(new XYChart.Data("15", 14));
//        series.getData().add(new XYChart.Data(3, 15));
//        series.getData().add(new XYChart.Data(4, 24));
//        series.getData().add(new XYChart.Data(5, 34));
//        series.getData().add(new XYChart.Data(6, 36));
//        series.getData().add(new XYChart.Data(7, 22));
//        series.getData().add(new XYChart.Data(8, 45));
//        series.getData().add(new XYChart.Data(9, 43));
//        series.getData().add(new XYChart.Data(10, 17));
//        series.getData().add(new XYChart.Data(11, 29));
//        series.getData().add(new XYChart.Data(12, 25));
        
        lineChart.getData().add(buildDataLineChart());
       ((Group) scene.getRoot()).getChildren().add(lineChart);
        stage.setScene(scene);
        chartNode.getChildren().clear();
        chartNode.getChildren().setAll(lineChart);
        
    }

    @FXML
    private void convertirPDF(ActionEvent event) throws FileNotFoundException, DocumentException, BadElementException, IOException {
        numeroPDF = numeroPDF + 1;
        String nom = "Graph statistique " + numeroPDF + ".pdf";
        try {
            SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat Heure = new SimpleDateFormat("hh:mm:ss");
            //Font f = new Font(FontFamily.HELVETICA, 13, Font.NORMAL, GrayColor.GRAYWHITE);

            WritableImage wimg = chartNode.snapshot(new SnapshotParameters(), null);
            File file = new File("ChartSnapshot.png");
            ImageIO.write(SwingFXUtils.fromFXImage(wimg, null), "png", file);

            PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream(System.getProperty("user.home") + "\\Desktop\\" + nom));
            doc.open();
//            Image img = Image.getInstance("C:\\xampp\\htdocs\\TunisiaBonPlan2-Integration\\web\\uploads\\images\\Sanstitre.png");
            Image img = Image.getInstance("ChartSnapshot.png");
            doc.add(img);

//                doc.add(new Paragraph("                                                           "
//                        + "                                                                                           "
//                        + "    " + date.format(new java.util.Date()), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
//                        taille12NORMALRed));
//
//                doc.add(new Paragraph("                                                           "
//                        + "                                                                                                 "
//                        + "  " + Heure.format(new java.util.Date()), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
//                        taille12NORMALRed));
//                doc.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------------------"));
//                doc.add(new Paragraph("                                    Bon de demande N°" + numeroCommande + " Ajouter un cadeaux dans le stock                                                         "));
//                doc.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------------------"));
//                doc.add(new Paragraph("Bon Plan",taille12NORMA));
//                
//
//         
//         
//  /*********************************Tableaux**********************************************/
//              
//                PdfPTable table = new PdfPTable(2);
//                table.setWidthPercentage(60);
//                table.setSpacingBefore(11f);
//          
//                Font f = new Font(Font.FontFamily.HELVETICA, 13, Font.NORMAL, GrayColor.GRAYWHITE);
//     
//                PdfPCell c1 = new PdfPCell(new Paragraph("Libelle", taille15B));
//                 c1.setHorizontalAlignment(Element.ALIGN_CENTER);
//                  PdfPCell c2 = new PdfPCell(new Paragraph("Quantité", taille15B));
//                  c2.setHorizontalAlignment(Element.ALIGN_CENTER);
//                PdfPCell c3 = new PdfPCell(new Paragraph(boncadeaux.getCadeaux().getLibelle(), taille12NORMA));
//              c3.setHorizontalAlignment(Element.ALIGN_CENTER);
//                PdfPCell c4 = new PdfPCell(new Paragraph(TXQuantite.getText(), taille12NORMA));
//          c4.setHorizontalAlignment(Element.ALIGN_CENTER);
//    
//                table.addCell(c1);
//                table.addCell(c3);
//                table.addCell(c2);
//                table.addCell(c4);
// 
//  /*********************************Tableaux Admin**********************************************/
//     
//                 User loggedUser = pidev.bonplan.Controller.LoginController.getInstance().getLoggedUser();
//                 
//        
//  
// User UserConneter=myServices.chercherUtilisateurByid(loggedUser.getId());            
//  
//  
//                PdfPTable tableAdmin = new PdfPTable(2);
//                tableAdmin.setWidthPercentage(60);
//                tableAdmin.setSpacingBefore(11f);
//               
//        
// 
//                PdfPCell cAdmin1 = new PdfPCell(new Paragraph("Admin"));
//                 cAdmin1.setHorizontalAlignment(Element.ALIGN_CENTER);
//                  PdfPCell cAdmin2 = new PdfPCell(new Paragraph(UserConneter.getUsername()));
//                  cAdmin2.setHorizontalAlignment(Element.ALIGN_CENTER);
//           
//    
//                tableAdmin.addCell(cAdmin1);
//                tableAdmin.addCell(cAdmin2);
// 
//  
//   /*********************************Tableaux User**********************************************/
//                
//   
//   
//                          PdfPTable tableUser= new PdfPTable(2);
//                tableUser.setWidthPercentage(60);
//                tableUser.setSpacingBefore(11f);
//       
//               
// 
//                PdfPCell cUser1 = new PdfPCell(new Paragraph("Partenaire"));
//                 cUser1.setHorizontalAlignment(Element.ALIGN_CENTER);
//            
//                  PdfPCell cUser2 = new PdfPCell(new Paragraph(comboBoxSelectionnerPartenaire.getSelectionModel().getSelectedItem()));
//                  cUser2.setHorizontalAlignment(Element.ALIGN_CENTER);
//           
//    
//                tableUser.addCell(cUser1);
//                tableUser.addCell(cUser2);
//                
//                
//                  
//                doc.add(table);
//                doc.add(tableAdmin);
//              doc.add(tableUser);
            doc.close();
            Desktop.getDesktop().open(new File(System.getProperty("user.home") + "\\Desktop\\" + nom));
            writer.close();

        } catch (Exception e) {

            System.out.println("Error PDF");
            System.out.println(e.getStackTrace());
            System.out.println(e.getMessage());

        }

    }
}
