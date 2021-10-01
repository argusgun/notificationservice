package com.service.notifications.notifications.dto;

import com.service.notifications.notifications.entities.NotificationEntity;
import com.service.notifications.notifications.enums.NotificationStatus;
import com.service.notifications.notifications.enums.NotificationType;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NotificationDto {
        private NotificationStatus status;
        private NotificationType type;
        private LocalDateTime created;
        private LocalDateTime modified;
        private String createdBy;
        private String modifiedBy;
        private String description;

        public NotificationEntity toNotificationEntity(){
                NotificationEntity notificationEntity =new NotificationEntity();
                notificationEntity.setCreated(getCreated());
                notificationEntity.setDescription(getDescription());
                notificationEntity.setModified(getModified());
                notificationEntity.setCreatedBy(getCreatedBy());
                notificationEntity.setModifiedBy(getModifiedBy());
                notificationEntity.setStatus(getStatus());
                notificationEntity.setType(getType());
                return notificationEntity;
        }

}
