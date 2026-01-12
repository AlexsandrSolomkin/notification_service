package com.example.notificationservice.controller;

import com.example.notificationservice.dto.UserEventDto;
import com.example.notificationservice.service.EmailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final EmailService emailService;

    public NotificationController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping
    public Mono<ResponseEntity<String>> sendEmail(@RequestBody UserEventDto dto) {
        String subject = "Account Notification";
        String text = switch (dto.getOperation()) {
            case "CREATE" -> "Здравствуйте! Ваш аккаунт был успешно создан.";
            case "DELETE" -> "Здравствуйте! Ваш аккаунт был удалён.";
            default -> "Операция с аккаунтом выполнена.";
        };
        emailService.sendEmail(dto.getEmail(), subject, text);
        return Mono.just(ResponseEntity.ok("Email отправлен"));
    }
}
