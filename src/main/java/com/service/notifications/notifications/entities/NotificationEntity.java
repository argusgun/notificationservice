package com.service.notifications.notifications.entities;

import com.service.notifications.notifications.dto.NotificationDto;
import com.service.notifications.notifications.enums.NotificationStatus;
import com.service.notifications.notifications.enums.NotificationType;
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

    public NotificationDto toDto(){
        NotificationDto notificationDto = new NotificationDto();
        notificationDto.setCreated(getCreated());
        notificationDto.setDescription(getDescription());
        notificationDto.setModified(getModified());
        notificationDto.setCreatedBy(getCreatedBy());
        notificationDto.setModifiedBy(getModifiedBy());
        notificationDto.setStatus(getStatus());
        notificationDto.setType(getType());
        return notificationDto;
    }
}
