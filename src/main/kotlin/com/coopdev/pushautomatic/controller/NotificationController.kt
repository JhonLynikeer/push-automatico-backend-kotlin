package com.coopdev.pushautomatic.controller

import com.coopdev.pushautomatic.service.FirebaseService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/notifications")
class NotificationController(
    private val firebaseService: FirebaseService
) {

    @PostMapping
    fun sendNotification(
        @RequestParam token: String,
        @RequestParam title: String,
        @RequestParam body: String
    ): String {
        return firebaseService.sendNotification(token, title, body)
    }
}