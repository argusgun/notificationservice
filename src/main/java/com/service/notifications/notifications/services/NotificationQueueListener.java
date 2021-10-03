package com.service.notifications.notifications.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.notifications.notifications.dto.NotificationDto;
import com.service.notifications.notifications.repositories.NotificationRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.stereotype.Component;
@Slf4j
@Component
@RequiredArgsConstructor
public class NotificationQueueListener {
    private final ObjectMapper objectMapper;
    private final NotificationRepo notificationRepo;

    @SqsListener(value = "${orders.queue.name}", deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
    public void processMessage(String message) {
        try {
            log.debug("Received new SQS message: {}", message );
            NotificationDto notificationDto = objectMapper.readValue(message, NotificationDto.class);
            notificationRepo.save(notificationDto.toNotificationEntity());

        } catch (Exception e) {
            throw new RuntimeException("Cannot process message from SQS", e);
        }
    }
}
