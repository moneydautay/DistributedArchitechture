package net.mail.ws.service;

import javax.jws.WebService;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by greenlucky on 12/28/16.
 */
@WebService(endpointInterface = "net.mail.ws.service.WSMail",
        serviceName = "MyOwnMailAgent")
public class WSMailImpl {

    public void sendMail(String smtpHost, int smtpPost,
                         String from, String to,
                         String username, String password, boolean isSSL){
        Properties props = System.getProperties();

        if(isSSL){
            props.put("mail.transport.protocol", "smtps");
            props.put("mail.smtps.auth", "true");
        }else{
            props.put("mail.transport.protocol", "smtp");
            props.put("mail.smpts.auth", "false");
        }

        props.put("mail.smtp.host", smtpHost);

        Session session = Session.getDefaultInstance(props);
        session.setDebug(true);

        try{
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("Hello");
            message.setText("The first time build the gmail web service");
            System.out.println(message.getContent());
            Transport tr;
            tr = session.getTransport();
            tr.connect(smtpHost, smtpPost, username, password);
            message.saveChanges();

            tr.sendMessage(message, message.getAllRecipients());
            tr.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
