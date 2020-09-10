package com.github.viktorgozhiy.authenticator.support;

import com.github.viktorgozhiy.authenticator.service.iface.ErrorResponseFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

public class MailSender {

    Logger logger = LoggerFactory.getLogger(MailSender.class);

    @Autowired
    private ErrorResponseFactory errorResponseFactory;

    public static final String MAIL_USERNAME = "mail.username";
    public static final String MAIL_PASSWORD = "mail.password";

    private final Session session;
    private final String from;

    public MailSender(final Properties properties) {
        from = properties.getProperty(MAIL_USERNAME);
        session = create(properties);
    }

    private Session create(final Properties properties) {
        return Session.getInstance(properties, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(properties.getProperty(MAIL_USERNAME), properties.getProperty(MAIL_PASSWORD));
            }
        });
    }

    public void sendMessage(final String email, final String subject, final String body) throws RestException {
        try {
            final MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(MimeMessage.RecipientType.TO, InternetAddress.parse(email));
            message.setSubject(subject);
            message.setSentDate(new Date());
            message.setContent(body, "text/html; charset=utf-8");
            message.saveChanges();
            Transport.send(message);
        } catch (MessagingException ex) {
            logger.error("Email sent failed!", ex);
            throw new RestException(errorResponseFactory.getErrorResponse(MESSAGES.ERR_INTERNAL_SERVER_ERROR, ApiStatus.UNABLE_TO_SENT_EMAIL_TO_USER), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
