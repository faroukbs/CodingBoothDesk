/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

/**
 *
 * @author Admin
 */
import entities.Commande;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
 
public class Mailling {
private String username = "aicha.ouali@esprit.tn";
private String password = "aicha1997";
    private static int id;
    public static int getIdd(int id) {
        id= id;
        return id;
    }

Commande E = new Commande();
public void envoyer(Commande r) {
    CommandeService Es = new CommandeService();
             Commande E = Es.recuperer(id);

// Etape 1 : Création de la session
Properties props = new Properties();
props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
props.put("mail.smtp.auth", "true");
props.put("mail.smtp.starttls.enable","true");
props.put("mail.smtp.host","smtp.gmail.com");
props.put("mail.smtp.port","25");

Session session = Session.getInstance(props,
new javax.mail.Authenticator() {
protected PasswordAuthentication getPasswordAuthentication() {
return new PasswordAuthentication(username, password);
}
});
try {
// Etape 2 : Création de l'objet Message
Message message = new MimeMessage(session);
message.setFrom(new InternetAddress("aicha.ouali@esprit.tn"));
message.setRecipients(Message.RecipientType.TO,
InternetAddress.parse("aicha.ouali@esprit.tn"));
message.setSubject("GO GYM");

            DateTimeFormatter Date = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
            LocalDateTime now = LocalDateTime.now();
            System.out.println(Date.format(now));
            String htmlCode ="<h1>Confirmation Commande</h1> <br/><h2>Bonjour Mr/Mme  "+r.getNom_client()+" "+r.getPrenom_client()+"<br/>Votre Commande a été bien éditée le :"+Date.format(now)+" dont le montant est:  " +r.getMontant()+" TND<br/> Merci de choisir notre services </h2>";
            message.setContent(htmlCode,"text/html");
            


// Etape 3 : Envoyer le message
Transport.send(message);
System.out.println("Message_envoye");
} catch (MessagingException e) {
throw new RuntimeException(e);
} 
}
}

