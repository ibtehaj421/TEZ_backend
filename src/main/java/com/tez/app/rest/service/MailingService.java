package com.tez.app.rest.service;


import com.resend.Resend;
import com.resend.core.exception.ResendException;
import com.resend.services.emails.model.CreateEmailOptions;
import com.resend.services.emails.model.CreateEmailOptions;
import com.resend.services.emails.model.Email;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class MailingService {

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


}

