package com.service.notifications.notifications.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.notifications.notifications.dto.NotificationDto;
import com.service.notifications.notifications.entities.NotificationEntity;
import com.service.notifications.notifications.models.NotificationStatus;
import com.service.notifications.notifications.models.NotificationType;
import com.service.notifications.notifications.repositories.NotificationRepo;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
@Sql(value = {"/create-notification-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/create-notification-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class NotificationRestControllerTest {
    @Autowired
    private NotificationRestController notificationRestController;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getOne() throws Exception {
        this.mockMvc.perform(get("/notifications/1"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.id").value("1"));
    }

    @Test
    void create() throws Exception{
        this.mockMvc.perform(post("/notifications")
                .content(objectMapper.writeValueAsString(getTestNotificationDtoForCreate().toNotificationEntity()))
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value("2"));

    }

    @Test
    void update() throws Exception{
        NotificationEntity notificationEntity=getTestNotificationEntityForUpdate();
        LocalDateTime time=LocalDateTime.of(2021,9,9,15,1,1);
        notificationEntity.setModified(time);
        this.mockMvc.perform(put("/notifications/1")
                .content(objectMapper.writeValueAsString(notificationEntity))
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.modified").value(List.of(2021,9,9,15,1,1)));
    }

    @Test
    void deleteTest() throws Exception{
        this.mockMvc.perform(delete("/notifications/{id}",getTestNotificationEntityForUpdate().getId()))
                    .andExpect(status().isAccepted())
                .andExpect(content().json(objectMapper.writeValueAsString(getTestNotificationEntityForUpdate())));
    }

    @Test
    void list() throws Exception {
        this.mockMvc.perform(get("/notifications"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.id").value("1"));
    }

    private NotificationDto getTestNotificationDtoForCreate(){
        return new NotificationDto();
    }
    private NotificationEntity getTestNotificationEntityForUpdate(){
        NotificationEntity notificationEntity = new NotificationEntity();
        notificationEntity.setId(1L);
        notificationEntity.setStatus(NotificationStatus.NEW);
        notificationEntity.setType(NotificationType.INFO);
        notificationEntity.setCreated(LocalDateTime.of(2021,8,8,15,0,0,0));
        notificationEntity.setCreatedBy("argusgun");
        notificationEntity.setDescription("ok");

        return notificationEntity;
    }
}