package com.tez.app.rest.service;


import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.GmailScopes;
import com.resend.Resend;
import com.resend.core.exception.ResendException;
import com.resend.services.emails.model.CreateEmailOptions;
import com.resend.services.emails.model.CreateEmailOptions;
import com.resend.services.emails.model.Email;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.util.Collections;
import java.util.Map;

@Service
public class MailingService {

    private static final String APPLICATION_NAME = "TEZ app";
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

    Resend mail = new Resend("re_TB9RVd3K_MMu6idyPJTaDh9hFvomaAexK");
    public void sendMail(String to,String name) throws ResendException {
            String subject = "Glad to have you onboard!";
            String body = "<h1>Welcome to TEZ, "+ name + "</h1>"
                    + "<p>Your account has been created successfully.</p>"
                    + "<p>Use the login page to get started.</p>";

        CreateEmailOptions email = CreateEmailOptions.builder()
                        .from("no-reply@tez.free.nf")
                                .to(to)
                                        .subject(subject)
                                                .html(body)
                                                        .build();
        mail.emails().send(email);
    }

    public void paymentEmail(String to, String subject, String body) {

    }

//    public static Gmail getGmailService() throws Exception {
//        GoogleCredential credential = GoogleCredential
//                .fromStream(new FileInputStream("the-tez-app.json"))
//                .createScoped(Collections.singleton(GmailScopes.GMAIL_SEND));
//
//        return new Gmail.Builder(
//                GoogleNetHttpTransport.newTrustedTransport(),
//                JSON_FACTORY,
//                new Ht
//        )
//    }

}

