package com.example.notificationservice.kafka;

import com.example.notificationservice.dto.UserEventDto;
import com.example.notificationservice.service.EmailService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class UserEventConsumer {

    private final EmailService emailService;

    public UserEventConsumer(EmailService emailService) {
        this.emailService = emailService;
    }

    @KafkaListener(topics = "user-events", groupId = "notification-group")
    public void listen(UserEventDto event) {
        String subject = "Account Notification";
        String text = switch (event.getOperation()) {
            case "CREATE" -> "Здравствуйте! Ваш аккаунт был успешно создан.";
            case "DELETE" -> "Здравствуйте! Ваш аккаунт был удалён.";
            default -> "Операция с аккаунтом выполнена.";
        };
        emailService.sendEmail(event.getEmail(), subject, text);
    }
}
