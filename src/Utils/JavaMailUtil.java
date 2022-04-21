/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import Services.ServiceUtilisateur;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author benha
 */
public class JavaMailUtil {
    
    public static void sendMail(String recepient , int id) throws Exception {
        Properties properties= new Properties();
        
        
//        mail.smtp.auth
properties.put("mail.smtp.auth", "true");
//        mail.smtp.starttls.enable
 properties.put("mail.smtp.starttls.enable", "true");
//        mail.smtp.host=smtp.gmail.com
properties.put("mail.smtp.host", "smtp.gmail.com");
//        mail.smtp.port =587
 properties.put("mail.smtp.port", "587");
  //Your gmail address
        String myAccountEmail = "ghhassendhif@gmail.com";
        //Your gmail password
        String password = "AZERTY123456";
 //Create a session with account credentials
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password);
            }
        });
        //Prepare email message
        Message message = prepareMessage(session, myAccountEmail, recepient ,id);

        //Send mail
       Transport.send(message);
        System.out.println("Message sent successfully");
    }

    private static Message prepareMessage(Session session, String myAccountEmail, String recepient ,int id) {
try {      ServiceUtilisateur o= new ServiceUtilisateur();
           String code=o.envoyerCode(id);
           o.updateCode(code, id);
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("Réinitialiser le mot de passe ");
            String htmlCode = "<p> Bonjour, </p> <br/> <p> " +
"<p>Nous avons reçu une demande de réinitialisation de votre mot de passe .</p> <br>" +
"<p>Entrez le code de réinitialisation du mot de passe suivant :</p>"+"<br/>"+"<h2>"+code+"</h2>";
            message.setContent(htmlCode, "text/html");
            return message;
        } catch (Exception ex) {
            Logger.getLogger(JavaMailUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
