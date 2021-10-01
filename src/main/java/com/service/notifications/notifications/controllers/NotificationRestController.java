package com.service.notifications.notifications.controllers;

import com.service.notifications.notifications.entities.NotificationEntity;
import com.service.notifications.notifications.repositories.NotificationRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/notifications")
public class NotificationRestController {
    private final NotificationRepo notificationRepo;

    public NotificationRestController(NotificationRepo notificationRepo) {
        this.notificationRepo = notificationRepo;
    }

    @GetMapping
    public List<NotificationEntity> list() {
        return notificationRepo.findAll();
    }

    @GetMapping("{id}")
    public NotificationEntity getOne(@PathVariable("id") NotificationEntity notificationEntity) {
        return notificationEntity;
    }

    @PostMapping
    public NotificationEntity create(@RequestBody NotificationEntity notificationEntity) {
        notificationEntity.setCreated(LocalDateTime.now());
        return notificationRepo.save(notificationEntity);
    }

    @PutMapping("{id}")
    public NotificationEntity update(
            @PathVariable("id") NotificationEntity notificationEntityFromDb,
            @RequestBody NotificationEntity notificationEntity
    ) {
        BeanUtils.copyProperties(notificationEntity, notificationEntityFromDb, "id");

        return notificationRepo.save(notificationEntityFromDb);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") NotificationEntity notificationEntity) {
        notificationRepo.delete(notificationEntity);
    }
}
