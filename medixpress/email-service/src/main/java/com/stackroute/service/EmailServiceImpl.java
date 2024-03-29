package com.stackroute.service;

import com.stackroute.dto.MailResponse;
import com.stackroute.dto.OrderEmail;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;


@Service
public class EmailServiceImpl implements EmailService{
    @Autowired
    private JavaMailSender sender;

    @Autowired
    private Configuration config;

    @Override
    public MailResponse sendEmail(OrderEmail request, Map<String, Object> model) {

        MailResponse response = new MailResponse();
        MimeMessage message = sender.createMimeMessage();
        try {
            // set mediaType
            MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());
            // add attachment
            //	helper.addAttachment("logo.png", new ClassPathResource("logo.png"));
            //ClassPathResource pdf = new ClassPathResource("static/invoice.pdf");

            Template t = config.getTemplate("email-template.ftl");
            String html = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);

            helper.setTo(request.getEmailId());
            helper.setText(html, true);
            helper.setSubject("Order Confirmation Email");
            //helper.setFrom(request.getFrom());
            //helper.addAttachment("invoice.pdf", pdf);
            sender.send(message);

            response.setMessage("mail send to : " + request.getEmailId());
            response.setStatus(Boolean.TRUE);

        } catch (MessagingException | IOException | TemplateException e) {
            response.setMessage("Mail Sending failure : " + e.getMessage());
            response.setStatus(Boolean.FALSE);
        }

        return response;
    }

    }
