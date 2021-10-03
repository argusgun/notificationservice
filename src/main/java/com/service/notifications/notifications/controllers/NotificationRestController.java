package com.service.notifications.notifications.controllers;

import com.service.notifications.notifications.entities.NotificationEntity;
import com.service.notifications.notifications.repositories.NotificationRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/notifications")
@RequiredArgsConstructor
public class NotificationRestController {
    private final NotificationRepo notificationRepo;

    @GetMapping
    public ResponseEntity<List<NotificationEntity>> list() {
        return new ResponseEntity<>(notificationRepo.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotificationEntity> getOne(@PathVariable("id") NotificationEntity notificationEntity) {
        return new ResponseEntity<>(notificationEntity, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<NotificationEntity> create(@RequestBody NotificationEntity notificationEntity) {
        return new ResponseEntity<>(notificationRepo.save(notificationEntity), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<NotificationEntity> update(
            @PathVariable("id") NotificationEntity notificationEntityFromDb,
            @RequestBody NotificationEntity notificationEntity
    ) {
        BeanUtils.copyProperties(notificationEntity, notificationEntityFromDb, "id");

        return new ResponseEntity<>(notificationRepo.save(notificationEntityFromDb), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<NotificationEntity> delete(@PathVariable("id") NotificationEntity notificationEntity) {
        notificationRepo.delete(notificationEntity);
        return new ResponseEntity<>(notificationEntity, HttpStatus.OK);
    }
}
