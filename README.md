Notification Service

Микросервис для отправки уведомлений на email.
Получает сообщения из Kafka от user-service и отправляет письма.

Требования:
- Java 17+
- Maven
- Kafka (локальный брокер на localhost:9092)
- SMTP сервер (например Gmail, Яндекс)

Настройка Kafka:
- Kafka должен быть запущен на localhost:9092.
- Топик "user-events" используется для получения сообщений о пользователях.

Настройка почты:
В application.properties настройте:

spring.mail.host=smtp.example.com
spring.mail.port=587
spring.mail.username=your_email@example.com
spring.mail.password=your_email_password
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true

> Для Gmail потребуется создать App Password.

Запуск приложения:
1. Перейдите в директорию notification-service.
2. Выполните команду:

mvn spring-boot:run

3. Приложение будет доступно на http://localhost:8081 (порт можно изменить при необходимости).

API:

Отправить письмо напрямую:
POST /email?to=ivan@example.com&operation=CREATE

- operation — CREATE или DELETE.
- Этот API дублирует функционал Kafka и позволяет тестировать email напрямую.

Проверка работы:
1. Создайте или удалите пользователя в user-service.
2. Notification-service получит сообщение из Kafka и отправит письмо.
3. Для отладки можно смотреть логи Notification-service.
