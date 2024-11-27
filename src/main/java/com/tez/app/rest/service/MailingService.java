package com.tez.app.rest.service;


import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.googleapis.json.GoogleJsonError;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;


import com.google.api.services.gmail.model.Message;
import com.tez.app.rest.Model.BusTicket;
import com.tez.app.rest.Model.Payment;
import com.tez.app.rest.Repo.BusTicketRepo;
import com.tez.app.rest.Repo.OrganizationRepo;
import com.tez.app.rest.Repo.SeatRepo;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.google.api.services.gmail.Gmail;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.*;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Properties;
import java.util.Set;

import static com.google.api.services.gmail.GmailScopes.GMAIL_SEND;


@Service
public class MailingService {

    private static final String APPLICATION_NAME = "TEZ app";
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private final OrganizationRepo organizationRepo;
    private final BusTicketRepo busTicketRepo;
    private Gmail service;

    @Autowired
    SeatRepo seatRepo;
    public MailingService(OrganizationRepo organizationRepo, BusTicketRepo busTicketRepo) {
        this.organizationRepo = organizationRepo;
        this.busTicketRepo = busTicketRepo;
    }

    //    public MailingService() throws IOException, GeneralSecurityException {
//        //Gmail service = getGmailService();
//        NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
//        service = new Gmail.Builder(HTTP_TRANSPORT,JSON_FACTORY,getCredentials(HTTP_TRANSPORT))
//                .setApplicationName(APPLICATION_NAME).build();
//    }
//    Resend mail = new Resend("re_TB9RVd3K_MMu6idyPJTaDh9hFvomaAexK");
//    public void sendMail(String to,String name) throws ResendException {
//            String subject = "Glad to have you onboard!";
//            String body = "<h1>Welcome to TEZ, "+ name + "</h1>"
//                    + "<p>Your account has been created successfully.</p>"
//                    + "<p>Use the login page to get started.</p>";
//
//        CreateEmailOptions email = CreateEmailOptions.builder()
//                        .from("no-reply@tez.free.nf")
//                                .to(to)
//                                        .subject(subject)
//                                                .html(body)
//                                                        .build();
//        mail.emails().send(email);
//    }
    public void sendMail(String to,String name) throws Exception {
        //service = getGmailService();
        String messageSubject = "Glad to have you onboard!.";
        String body = "<!DOCTYPE html>\n"
                + "<html lang=\"en\">\n"
                + "<head>\n"
                + "    <meta charset=\"UTF-8\">\n"
                + "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                + "    <title>Welcome to TEZ</title>\n"
                + "    <style>\n"
                + "        body {\n"
                + "            font-family: Arial, sans-serif;\n"
                + "            background-color: #f9f9f9;\n"
                + "            margin: 0;\n"
                + "            padding: 0;\n"
                + "            display: flex;\n"
                + "            justify-content: center;\n"
                + "            align-items: center;\n"
                + "            height: 100vh;\n"
                + "        }\n"
                + "        .email-container {\n"
                + "            background-color: #ffffff;\n"
                + "            border-radius: 8px;\n"
                + "            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);\n"
                + "            width: 500px;\n"
                + "            padding: 20px;\n"
                + "            text-align: center;\n"
                + "        }\n"
                + "        h1 {\n"
                + "            color: #4CAF50;\n"
                + "            font-size: 28px;\n"
                + "            margin-bottom: 20px;\n"
                + "        }\n"
                + "        p {\n"
                + "            font-size: 16px;\n"
                + "            color: #555;\n"
                + "            line-height: 1.6;\n"
                + "        }\n"
                + "        .btn {\n"
                + "            display: inline-block;\n"
                + "            background-color: #4CAF50;\n"
                + "            color: white;\n"
                + "            padding: 10px 20px;\n"
                + "            text-decoration: none;\n"
                + "            border-radius: 5px;\n"
                + "            font-size: 16px;\n"
                + "            transition: background-color 0.3s;\n"
                + "            margin-top: 20px;\n"
                + "        }\n"
                + "        .btn:hover {\n"
                + "            background-color: #45a049;\n"
                + "        }\n"
                + "    </style>\n"
                + "</head>\n"
                + "<body>\n"
                + "    <div class=\"email-container\">\n"
                + "        <h1>Welcome to TEZ, " + name + "!</h1>\n"
                + "        <p>Your account has been created successfully.</p>\n"
                + "        <p>We are excited to have you on board! To get started, please visit the login page and access your account.</p>\n"
                + "    </div>\n"
                + "</body>\n"
                + "</html>";
        //encode as MIME message
        generateMailAPI(messageSubject,body,to);
    }

    public void paymentEmail(String email,String name, Payment in) throws Exception {
        //service = getGmailService();
        String messageSubject = "Payment Processed.";
        String body = "<!DOCTYPE html>\n"
                + "<html lang=\"en\">\n"
                + "<head>\n"
                + "    <meta charset=\"UTF-8\">\n"
                + "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                + "    <title>Payment Confirmation</title>\n"
                + "    <style>\n"
                + "        body {\n"
                + "            font-family: Arial, sans-serif;\n"
                + "            background-color: #f4f4f9;\n"
                + "            margin: 0;\n"
                + "            padding: 0;\n"
                + "            display: flex;\n"
                + "            justify-content: center;\n"
                + "            align-items: center;\n"
                + "            height: 100vh;\n"
                + "        }\n"
                + "        .email-container {\n"
                + "            background-color: #ffffff;\n"
                + "            border-radius: 8px;\n"
                + "            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);\n"
                + "            width: 500px;\n"
                + "            padding: 20px;\n"
                + "            text-align: center;\n"
                + "        }\n"
                + "        h1 {\n"
                + "            color: #4CAF50;\n"
                + "            font-size: 24px;\n"
                + "            margin-bottom: 20px;\n"
                + "        }\n"
                + "        p {\n"
                + "            font-size: 16px;\n"
                + "            color: #555;\n"
                + "            line-height: 1.6;\n"
                + "        }\n"
                + "        .payment-details {\n"
                + "            margin-top: 20px;\n"
                + "            font-size: 16px;\n"
                + "            text-align: left;\n"
                + "            padding: 10px;\n"
                + "            background-color: #f9f9f9;\n"
                + "            border-radius: 5px;\n"
                + "            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);\n"
                + "        }\n"
                + "        .payment-details p {\n"
                + "            margin: 8px 0;\n"
                + "        }\n"
                + "        .footer {\n"
                + "            font-size: 14px;\n"
                + "            color: #888;\n"
                + "            margin-top: 30px;\n"
                + "        }\n"
                + "    </style>\n"
                + "</head>\n"
                + "<body>\n"
                + "    <div class=\"email-container\">\n"
                + "        <h1>Payment Confirmation</h1>\n"
                + "        <p>Thank you for your payment. Your ticket status is now paid. Below are the payment details:</p>\n"
                + "        <div class=\"payment-details\">\n"
                + "            <p><strong>Payment ID: </strong> " + in.getId() + "</p>\n"
                + "            <p><strong>Paid To: </strong> " + organizationRepo.fetchName(in.getOrgID()) + "</p>\n"
                + "            <p><strong>Amount Paid: </strong> " + in.getAmount() + " Rs </p>\n"
                + "            <p><strong>Date of Payment: </strong> " + in.getIssued() + "</p>\n"
                + "        </div>\n"
                + "        <p>If you have any questions, feel free to contact our support team.</p>\n"
                + "        <div class='footer'>"
                + "            &copy; 2024 TEZ Bus Services. All rights reserved."
                + "        </div>"
                + "    </div>\n"
                + "</body>\n"
                + "</html>";
        generateMailAPI(messageSubject,body,email);

    }
    public static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException {

        //load client secret key values from json file.
        InputStream in = MailingService.class.getResourceAsStream("/clientSecret.json");
        if(in == null) {
            throw new FileNotFoundException("Resource not found: " + "/...json");
        }
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY,new InputStreamReader(in));

        //user auth request to the Gmail api.
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT,JSON_FACTORY,clientSecrets, Set.of(GMAIL_SEND))
                .setDataStoreFactory(new FileDataStoreFactory(Paths.get("tokens").toFile()))
                .setAccessType("offline").build();
        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        return new AuthorizationCodeInstalledApp(flow,receiver).authorize("user");

    }

    public static Gmail getGmailService() throws Exception {
        //Gmail service = getGmailService();
        NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        return new Gmail.Builder(HTTP_TRANSPORT,JSON_FACTORY,getCredentials(HTTP_TRANSPORT))
                .setApplicationName(APPLICATION_NAME).build();
    }

    public void sendPassGenMail(String email, String userName, long passid, LocalDate created) throws Exception {
        //service = getGmailService();
        String messageSubject = "Your Bus Pass.";
        String body = "<!DOCTYPE html>\n"
                + "<html lang=\"en\">\n"
                + "<head>\n"
                + "    <meta charset=\"UTF-8\">\n"
                + "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                + "    <title>Bus Pass Generated</title>\n"
                + "    <style>\n"
                + "        body {\n"
                + "            font-family: Arial, sans-serif;\n"
                + "            background-color: #f0f4f8;\n"
                + "            display: flex;\n"
                + "            justify-content: center;\n"
                + "            align-items: center;\n"
                + "            height: 100vh;\n"
                + "            margin: 0;\n"
                + "        }\n"
                + "        .bus-pass-card {\n"
                + "            background-color: #ffffff;\n"
                + "            border-radius: 8px;\n"
                + "            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);\n"
                + "            width: 350px;\n"
                + "            padding: 20px;\n"
                + "            text-align: center;\n"
                + "        }\n"
                + "        .bus-pass-card h1 {\n"
                + "            color: #4CAF50;\n"
                + "            font-size: 28px;\n"
                + "            margin-bottom: 20px;\n"
                + "        }\n"
                + "        .bus-pass-card p {\n"
                + "            font-size: 16px;\n"
                + "            color: #555;\n"
                + "            margin-bottom: 15px;\n"
                + "        }\n"
                + "        .bus-pass-card .info {\n"
                + "            background-color: #f1f1f1;\n"
                + "            padding: 10px;\n"
                + "            border-radius: 5px;\n"
                + "            margin-bottom: 15px;\n"
                + "        }\n"
                + "        .bus-pass-card .info span {\n"
                + "            font-weight: bold;\n"
                + "        }\n"
                + "        .bus-pass-card .btn {\n"
                + "            display: inline-block;\n"
                + "            background-color: #4CAF50;\n"
                + "            color: white;\n"
                + "            padding: 10px 20px;\n"
                + "            text-decoration: none;\n"
                + "            border-radius: 5px;\n"
                + "            font-size: 16px;\n"
                + "            transition: background-color 0.3s;\n"
                + "        }\n"
                + "        .bus-pass-card .btn:hover {\n"
                + "            background-color: #45a049;\n"
                + "        }\n"
                + "    </style>\n"
                + "</head>\n"
                + "<body>\n"
                + "    <div class=\"bus-pass-card\">\n"
                + "        <h1>Bus Pass Generated</h1>\n"
                + "        <p>Your bus pass has been successfully created! Here are the details:</p>\n"
                + "        <div class=\"info\">\n"
                + "            <p><span>Pass Number: </span>"+passid+"</p>\n"
                + "            <p><span>Passenger Name: </span>" +userName+"</p>\n"
                + "            <p><span>Generated On: </span>"+created+"</p>\n"
                + "        </div>\n"
                + "        <p>Thank you for choosing our service. Have a safe and comfortable journey!</p>\n"
                + "    </div>\n"
                + "</body>\n"
                + "</html>";
        generateMailAPI(messageSubject,body,email);

    }

    public void sendGeneratedMessage(){

    }

    public void sendReservationMail(String email, String userName, BusTicket ticket) throws Exception {
        //service = getGmailService();
        String subject = "Bus Ticket";
        String body = "<!DOCTYPE html>"
                + "<html>"
                + "<head>"
                + "    <style>"
                + "        body { font-family: Arial, sans-serif; background-color: #f2f2f2; margin: 0; padding: 0; }"
                + "        .container { max-width: 600px; margin: 20px auto; background-color: #ffffff; padding: 20px; border-radius: 8px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); }"
                + "        .header { background-color: #007BFF; padding: 10px 0; text-align: center; color: white; border-radius: 8px 8px 0 0; }"
                + "        .content { padding: 20px; text-align: center; }"
                + "        .details { text-align: left; margin: 20px 0; }"
                + "        .details p { margin: 10px 0; font-size: 16px; }"
                + "        .footer { text-align: center; font-size: 14px; color: #888888; margin-top: 20px; }"
                + "        .status { font-weight: bold; color: #28a745; }"
                + "    </style>"
                + "</head>"
                + "<body>"
                + "    <div class='container'>"
                + "        <div class='header'>"
                + "            <h1>Your Bus Ticket Reservation</h1>"
                + "        </div>"
                + "        <div class='content'>"
                + "            <p>Dear "+userName+"</p>"
                + "            <p>Your bus ticket has been successfully <span class='status'>reserved</span>.</p>"
                + "            <div class='details'>"
                + "                <p><strong>Ticket ID: </strong> " + ticket.getId() + "</p>"
                + "                <p><strong>Seat # : </strong> " + seatRepo.getSeatNumber(ticket.getSeatID()) + "</p>"
                + "                <p><strong>Status: </strong>"+ticket.getStatus()+"</p>"
                + "            </div>"
                + "            <p>Proceed to payment options to change the status of your reservation.</p>"
                + "        </div>"
                + "        <div class='footer'>"
                + "            &copy; 2024 TEZ Bus Services. All rights reserved."
                + "        </div>"
                + "    </div>"
                + "</body>"
                + "</html>";
        generateMailAPI(subject,body,email);

    }

    public void sendAdminMail(String name,String email, String password) throws Exception {
        String messageSubject = "Glad to have you onboard!.";
        String body = "<!DOCTYPE html>\n"
                + "<html lang=\"en\">\n"
                + "<head>\n"
                + "    <meta charset=\"UTF-8\">\n"
                + "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                + "    <title>Welcome to TEZ</title>\n"
                + "    <style>\n"
                + "        body {\n"
                + "            font-family: Arial, sans-serif;\n"
                + "            background-color: #f9f9f9;\n"
                + "            margin: 0;\n"
                + "            padding: 0;\n"
                + "            display: flex;\n"
                + "            justify-content: center;\n"
                + "            align-items: center;\n"
                + "            height: 100vh;\n"
                + "        }\n"
                + "        .email-container {\n"
                + "            background-color: #ffffff;\n"
                + "            border-radius: 8px;\n"
                + "            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);\n"
                + "            width: 500px;\n"
                + "            padding: 20px;\n"
                + "            text-align: center;\n"
                + "        }\n"
                + "        h1 {\n"
                + "            color: #4CAF50;\n"
                + "            font-size: 28px;\n"
                + "            margin-bottom: 20px;\n"
                + "        }\n"
                + "        p {\n"
                + "            font-size: 16px;\n"
                + "            color: #555;\n"
                + "            line-height: 1.6;\n"
                + "        }\n"
                + "        .btn {\n"
                + "            display: inline-block;\n"
                + "            background-color: #4CAF50;\n"
                + "            color: white;\n"
                + "            padding: 10px 20px;\n"
                + "            text-decoration: none;\n"
                + "            border-radius: 5px;\n"
                + "            font-size: 16px;\n"
                + "            transition: background-color 0.3s;\n"
                + "            margin-top: 20px;\n"
                + "        }\n"
                + "        .btn:hover {\n"
                + "            background-color: #45a049;\n"
                + "        }\n"
                + "    </style>\n"
                + "</head>\n"
                + "<body>\n"
                + "    <div class=\"email-container\">\n"
                + "        <h1>Welcome to TEZ, " + name + "!</h1>\n"
                + "        <p>Your account has been created successfully.</p>\n"
                + "        <p>We are excited to have you on board! To get started, please visit the login page and access your account.</p>\n"
                + "        <p><strong>Your account details:</strong></p>\n"
                + "        <p><strong>Email:</strong> " + email + "</p>\n"
                + "        <p><strong>Password:</strong> " + password + "</p>\n"
                + "    </div>\n"
                + "</body>\n"
                + "</html>";
            generateMailAPI(messageSubject,body,email);
    }

    public void generateMailAPI(String messageSubject,String body,String email) throws Exception {
        service = getGmailService();
        Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);
        MimeMessage msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress("ibtehajkazmi09@gmail.com"));
        msg.addRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(email));
        msg.setSubject(messageSubject);
        msg.setContent(body, "text/html");

        //encode the mime message to a gmail message for the email.
        ByteArrayOutputStream buff = new ByteArrayOutputStream();
        msg.writeTo(buff);
        byte[] bytes = buff.toByteArray();
        String encoded = Base64.encodeBase64URLSafeString(bytes);
        Message message1 = new Message();
        message1.setRaw(encoded);

        try {
            //create the send message
            message1 = service.users().messages().send("me",message1).execute();
            System.out.println(message1.toPrettyString());
        } catch (GoogleJsonResponseException e){
            GoogleJsonError error = e.getDetails();
            if(error.getCode() == 403){
                System.err.println("Unable to send message: " + error.getDetails());
            } else {
                throw e;
            }
        }
    }
}

