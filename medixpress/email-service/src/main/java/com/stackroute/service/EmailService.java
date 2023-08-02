package com.stackroute.service;

import com.stackroute.dto.MailResponse;
import com.stackroute.dto.OrderEmail;

import java.util.Map;

public interface EmailService {
    public MailResponse sendEmail(OrderEmail request, Map<String, Object> model);
}
