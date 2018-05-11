package ua.gov.uz.util;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendMail{

  private static final String DECODEDPASS = "dGl0dTExMmhlc2w=";

    public static void main(String[] args) throws Exception

    {				
//    			String[] to={"mmmazurkevych@gmail.com"};
//
//                String[] cc={};
//                String[] bcc={};

                //This is for google
//                SendMail.sendMail("maksim.mazurkevych@gmail.com",
//                                "",
//                		            "smtp.gmail.com",
//                		            "465",
//                		            "true",
//                		            "true",
//                		            true,
//                		            "javax.net.ssl.SSLSocketFactory",
//                		            "false",
//                		            to,
//                		            cc,
//                		            bcc,
//                		            "Kiev - Vinnitsa " + new java.util.Date().toString(),
//                		            "Please find the reports attached.\n\n Regards\nQA Automation",
//                		        	System.getProperty("user.dir")+"//target//surefire-reports//html//toHome.jpg",
//                		        	"toHome.jpg");
//
//                SendMail.sendMail(
//    		            "Vinnitsa - Kiev" + new java.util.Date().toString () ,
//    		            "Please find the reports attached.\n\n Regards\nQA Automation",
//    		        	System.getProperty("user.dir")+"//target//surefire-reports//html//fromHome.jpg",
//    		        	"fromHome.jpg");

    }

        public  static boolean sendMail(String subject,
        		String attachmentPath,
        		String attachmentName){
          String userName = "maksim.mazurkevych@gmail.com";
          String passWord= "";
          String host = "smtp.gmail.com";
          String port= "465";
          String starttls="true";
          String auth= "true";
          boolean debug = true;
          String socketFactoryClass = "javax.net.ssl.SSLSocketFactory";
          String fallback = "false";
          String[] to={"mmmazurkevych@gmail.com"};
          String[] cc = {};
          String[] bcc = {};
          String text = "Please find the reports attached.\n\n Regards\nQA Automation";

          Properties props = new Properties();

        props.put("mail.smtp.user", userName);

        props.put("mail.smtp.host", host);

                if(!"".equals(port))

        props.put("mail.smtp.port", port);

                if(!"".equals(starttls))

        props.put("mail.smtp.starttls.enable",starttls);

        props.put("mail.smtp.auth", auth);
       // props.put("mail.smtps.auth", "true");


                if(debug){

                props.put("mail.smtp.debug", "true");

                }else{

                props.put("mail.smtp.debug", "false");         

                }

                if(!"".equals(port))

        props.put("mail.smtp.socketFactory.port", port);

                if(!"".equals(socketFactoryClass))

        props.put("mail.smtp.socketFactory.class",socketFactoryClass);

                if(!"".equals(fallback))

        props.put("mail.smtp.socketFactory.fallback", fallback);

 

        try

        {   
        	Session session = Session.getDefaultInstance(props, null);

            session.setDebug(debug);

            MimeMessage msg = new MimeMessage(session);

            msg.setText(text);

            msg.setSubject(subject);
            //attachment start
            // create the message part 
           
            Multipart multipart = new MimeMultipart();
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            DataSource source = 
              new FileDataSource(attachmentPath);
            messageBodyPart.setDataHandler(
              new DataHandler(source));
            messageBodyPart.setFileName(attachmentName);
            multipart.addBodyPart(messageBodyPart);
            
            // attachment ends

            // Put parts in message
            msg.setContent(multipart);
            msg.setFrom(new InternetAddress("maksim.mazurkevych@gmail.com"));

                        for(int i=0;i<to.length;i++){

            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to[i]));

                        }

                        for(int i=0;i<cc.length;i++){

            msg.addRecipient(Message.RecipientType.CC, new InternetAddress(cc[i]));

                        }

                        for(int i=0;i<bcc.length;i++){

            msg.addRecipient(Message.RecipientType.BCC, new InternetAddress(bcc[i]));

                        }

            msg.saveChanges();

                        Transport transport = session.getTransport("smtp");

                        transport.connect(host, userName, passWord);

                        transport.sendMessage(msg, msg.getAllRecipients());

                        transport.close();

                        return true;

        }

        catch (Exception mex)

        {

            mex.printStackTrace();

                        return false;

        }

        }

 

}