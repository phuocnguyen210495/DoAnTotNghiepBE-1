package com.example.demo.controller;

import com.example.demo.model.Notification;
import com.example.demo.service.notification.INotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/notifications")
public class NotificationController {
    @Autowired
    private INotificationService notificationService;


    @GetMapping
    public ResponseEntity<Iterable<Notification>> getAllNotification() {
        return new ResponseEntity<>(notificationService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Notification> createNewNotification(@RequestBody Notification notification) {
        return new ResponseEntity<>(notificationService.save(notification), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Notification> getNotification(@PathVariable Long id) {
        Optional<Notification> notification = notificationService.findById(id);
        return notification.map(notification1 -> new ResponseEntity<>(notification1, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping
    public ResponseEntity<Notification> updateNotification(@RequestBody Notification notification) {
        Optional<Notification> notificationOptional = notificationService.findById(notification.getId());
        return notificationOptional.map(notification1 -> {
            notification1.setId(notification1.getId());
            notification1.setContent(notification.getContent());
            notification1.setStatus(notification.getStatus());
            return new ResponseEntity<>(notificationService.save(notification1), HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping
    public ResponseEntity<Notification> deleteNotification(@RequestBody Notification notification) {
        Optional<Notification> notificationOptional = notificationService.findById(notification.getId());
        return notificationOptional.map(notification1 -> {
            notificationService.remove(notification.getId());
            return new ResponseEntity<>(notification1, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
