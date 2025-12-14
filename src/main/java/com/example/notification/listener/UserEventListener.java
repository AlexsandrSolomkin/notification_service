package com.example.notification.listener;

import com.example.notification.model.UserEvent;
import com.example.notification.service.EmailService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class UserEventListener {

    private final EmailService emailService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public UserEventListener(EmailService emailService) { this.emailService = emailService; }

    @KafkaListener(topics = "user-events", groupId = "notification-group")
    public void listen(String message) throws Exception {
        UserEvent event = objectMapper.readValue(message, UserEvent.class);
        String text = event.operation.equals("CREATE") ?
                "Здравствуйте! Ваш аккаунт был успешно создан." :
                "Здравствуйте! Ваш аккаунт был удалён.";
        emailService.sendEmail(event.email, "Notification", text);
    }
}
