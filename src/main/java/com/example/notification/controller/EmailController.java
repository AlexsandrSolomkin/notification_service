package com.example.notification.controller;

import com.example.notification.service.EmailService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/email")
public class EmailController {

    private final EmailService emailService;

    public EmailController(EmailService emailService) { this.emailService = emailService; }

    @PostMapping
    public void sendEmail(@RequestParam String to, @RequestParam String operation) {
        String text = operation.equals("CREATE") ?
                "Здравствуйте! Ваш аккаунт был успешно создан." :
                "Здравствуйте! Ваш аккаунт был удалён.";
        emailService.sendEmail(to, "Notification", text);
    }
}
