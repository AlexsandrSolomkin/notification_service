package com.example.notification.model;

public class UserEvent {
    public String email;
    public String operation;

    public UserEvent() {}
    public UserEvent(String email, String operation) { this.email = email; this.operation = operation; }
}
