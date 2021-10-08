package com.service.notifications.notifications.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.notifications.notifications.dto.NotificationDto;
import com.service.notifications.notifications.models.NotificationStatus;
import com.service.notifications.notifications.models.NotificationType;
import com.service.notifications.notifications.repositories.NotificationRepo;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource("/application-test.properties")
class NotificationQueueListenerTest {
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private NotificationRepo notificationRepo;
    @Autowired
    private NotificationQueueListener notificationQueueListener;

    @Test
    void processMessage() throws JsonProcessingException {
        NotificationDto result = objectMapper.readValue(getMessageForTest(),NotificationDto.class);

        assertEquals(result, getNotificationDtoForTest());
        notificationQueueListener.processMessage(getMessageForTest());
        Mockito.verify(notificationRepo,Mockito.times(1)).save(Mockito.any());
    }

    private NotificationDto getNotificationDtoForTest(){
        NotificationDto notificationDto = new NotificationDto();
        notificationDto.setStatus(NotificationStatus.NEW);
        notificationDto.setType(NotificationType.INFO);
        notificationDto.setCreated(LocalDateTime.of(2021,9,9,15,0,0));
        notificationDto.setDescription("ok");
        notificationDto.setCreatedBy("user");
        return notificationDto;
    }

    private String getMessageForTest(){
        return "{\"status\":0,\"type\":0,\"created\":\"2021-09-09T15:00:00\",\"modified\":null,\"createdBy\":\"user\",\"modifiedBy\":null,\"description\":\"ok\"}";
    }

}