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
import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Service;
import com.google.api.services.gmail.Gmail;

import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.*;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.Set;

import static com.google.api.services.gmail.GmailScopes.GMAIL_SEND;


@Service
public class MailingService {

    private static final String APPLICATION_NAME = "TEZ app";
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private Gmail service;
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
        service = getGmailService();
        String messageSubject = "Glad to have you onboard!.";
        String body = "<h1>Welcome to TEZ, " +name+ "</h1>"
                   + "<p>Your account has been created successfully.</p>"
                   + "<p>Use the login page to get started.</p>";
        //encode as MIME message
        Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);
        MimeMessage msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress("ibtehajkazmi09@gmail.com"));
        msg.addRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(to));
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

    public void paymentEmail(String to, String subject, String body) {

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

}

