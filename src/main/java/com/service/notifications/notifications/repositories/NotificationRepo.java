package com.service.notifications.notifications.repositories;

import com.service.notifications.notifications.entities.NotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepo extends JpaRepository<NotificationEntity,Long> {
}
