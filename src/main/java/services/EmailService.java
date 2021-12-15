package services;

import model.Customer;
import model.Email;
import utility.Consts;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

public class

EmailService implements EmailServiceI {

    public void sendRegistrationEmail(Email email, Customer customer) {

        String to = customer.getEmail(); // to address. It can be any like gmail, hotmail etc.
        final String from = email.getMail(); // from address. As this is using Gmail SMTP.
        final String password = email.getPassword(); // password for from mail address.

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "465");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.socketFactory.port", "465");
        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject("Ecommerce Site. Activate your Account");

            String url = "\"omoka.ml/register\"";

            String msg =
                    "<h3>Please click on this link to activate your account!" +
                            "Link is only valid for 10 minutes" +
                            "<a href=" + url + " class=\"primary-link\"> Click Here!</a></h3>";

            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setContent(msg, "text/html");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mimeBodyPart);
            message.setContent(multipart);

            Transport.send(message);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}

