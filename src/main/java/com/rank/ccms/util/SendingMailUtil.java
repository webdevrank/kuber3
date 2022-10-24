package com.rank.ccms.util;

import java.util.Date;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import org.apache.log4j.Logger;

public class SendingMailUtil {

    private static final Logger logger = Logger.getLogger(SendingMailUtil.class);

    public static final String SCHEDULE_CALL = " You Have a Schedule Call";
    public static final String MULTI_WAY_CALL = " You Have a Multiway Call";
    public static final String NEW_CUSTOMER = " New Account details";
    public static final String LOAN_CUSTOMER = " Loan Account Details";
    public static final String LOAN_APPROVE = " Loan Approval Greetings";
    public static final String EXCEL_REPORT_SUBJECT = "Account Summary Report";
    public static final String TELE_THX = "\n\n" + "\nThanking you,\n" + "\nRank Team.";
    public static final String TELE_THX_HTML = "<br>Thanking you," + "<br>Digital Customer Experience Team<br>Tata Communications Ltd.";

    public synchronized static boolean sendEMail(String mailTo, String sbMessageBody, String subject) throws Exception {
        boolean flag = false;
        try {
            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", Constants.smtp_host);
            props.put("mail.smtp.port", "587");

            Session session = Session.getInstance(props,
                    new javax.mail.Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(Constants.from_address, Constants.password);
                }
            });

            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(Constants.from_address));

            InternetAddress[] toAddresses = {new InternetAddress(mailTo)};
            msg.setRecipients(Message.RecipientType.TO, toAddresses);
            msg.setSubject(subject);
            msg.setText(sbMessageBody);
            msg.setContent(sbMessageBody, "text/html; charset=utf-8");

            Transport.send(msg);
            logger.info("Sent message successfully....");
            flag = true;
        } catch (Exception e) {
            logger.info("Error ...." + e);
            throw new Exception();
        }
        return flag;
    }

    public synchronized static boolean sendMail(String mailTo, StringBuffer sbMessageBody, String subject) throws Exception {
        boolean flag = false;
        try {

            logger.info("<==============================================================IN MAIL==================================================================>");

            logger.info("mailTo========================>>" + mailTo);

            // Sender's email ID needs to be mentioned
            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", Constants.smtp_host);
            props.put("mail.smtp.port", "587");

            logger.info("mailFROM========================>>" + Constants.from_address);
            // Get the Session object.
            Session session = Session.getInstance(props,
                    new javax.mail.Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(Constants.from_address, Constants.password);
                }
            });
            logger.info("mailHost========================>>" + Constants.smtp_host);
            // Create a default MimeMessage object.
            Message message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(Constants.from_address));

            // Set To: header field of the header.
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mailTo));

            // Set Subject: header field
            message.setSubject(subject);
            message.setText(sbMessageBody.toString());
            logger.info("mailPWD========================>>" + Constants.password);
            //   message.setContent(message, "text/html");

            // Send message
            Transport.send(message);

            logger.info("Sent message successfully....");
            flag = true;
        } catch (Exception e) {
            throw new Exception();
        }
        return flag;
    }

    public synchronized static boolean sendMailWithAttachment(String mailTo, StringBuffer sbMessageBody, String subject, String filePath, String fileAttachmentName) throws Exception {
        boolean flag = false;
        try {

            logger.info("<==============================================================IN MAIL==================================================================>");

            logger.info("mailTo========================>>" + mailTo);

            // Sender's email ID needs to be mentioned
            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", Constants.smtp_host);
            props.put("mail.smtp.port", "587");

            // Get the Session object.
            Session session = Session.getInstance(props,
                    new javax.mail.Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(Constants.from_address, Constants.password);
                }
            });

            // Create a default MimeMessage object.
            Message message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(Constants.from_address));

            // Set To: header field of the header.
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mailTo));

            // Set Subject: header field
            message.setSubject(subject);

            //3) create MimeBodyPart object and set your message text        
            BodyPart messageBodyPart1 = new MimeBodyPart();
            messageBodyPart1.setText(sbMessageBody.toString());//.concat("\nxmlBlob Requested : "+body.getXmlBlob())

            //4) create new MimeBodyPart object and set DataHandler object to this object        
            MimeBodyPart messageBodyPart2 = new MimeBodyPart();
            DataSource source = new FileDataSource(filePath);
            messageBodyPart2.setDataHandler(new DataHandler(source));
            messageBodyPart2.setFileName(fileAttachmentName);

            //5) create Multipart object and add MimeBodyPart objects to this object        
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart1);
            multipart.addBodyPart(messageBodyPart2);

            //6) set the multiplart object to the message object    
            message.setContent(multipart);

            Transport.send(message);

            logger.info("Sent message successfully....");
            flag = true;
        } catch (Exception e) {
            throw new Exception();
        }
        return flag;
    }

    public synchronized static boolean sendMailWithAttatchment(String mailTo, StringBuffer sbMessageBody, String subject, byte[] byteArray) throws Exception {
        boolean flag = false;
        try {

            logger.info("<==============================================================IN MAIL==================================================================>");

            logger.info("mailTo========================>>" + mailTo);

            // Sender's email ID needs to be mentioned
            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", Constants.smtp_host);
            props.put("mail.smtp.port", "587");

            // Get the Session object.
            Session session = Session.getInstance(props,
                    new javax.mail.Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(Constants.from_address, Constants.password);
                }
            });

            // Create a default MimeMessage object.
            Message message = new MimeMessage(session);
            Multipart multipart = new MimeMultipart();
            // Set From: header field of the header.
            message.setFrom(new InternetAddress(Constants.from_address));

            // Set To: header field of the header.
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mailTo));

            // Set Subject: header field
            message.setSubject(subject);
            message.setSentDate(new Date());

            // creates message part
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent(sbMessageBody.toString(), "text/html; charset=utf-8");

            // creates attachment body part
            MimeBodyPart attachBodyPart = new MimeBodyPart();
            ByteArrayDataSource fds = new ByteArrayDataSource(byteArray, "application/vnd.ms-excel");
            attachBodyPart.setDataHandler(new DataHandler(fds));
            attachBodyPart.setFileName("Account Details.xlsx");

            // sets the multi-part as e-mail's content
            multipart.addBodyPart(messageBodyPart);
            multipart.addBodyPart(attachBodyPart);
            message.setContent(multipart);

            // sends the e-mail
            Transport.send(message);

            logger.info("Sent message successfully....");
            flag = true;
        } catch (Exception e) {
            throw new Exception();
        }
        return flag;
    }
}
