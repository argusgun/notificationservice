package com.service.notifications.notifications.entities;

import com.service.notifications.notifications.models.NotificationStatus;
import com.service.notifications.notifications.models.NotificationType;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@Entity
public class NotificationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private NotificationStatus status;
    private NotificationType type;
    private LocalDateTime created;
    private LocalDateTime modified;
    private String createdBy;
    private String modifiedBy;
    private String description;
}
