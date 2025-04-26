package com.coopdev.pushautomatic.service

import com.google.auth.oauth2.GoogleCredentials
import org.springframework.stereotype.Service
import java.net.HttpURLConnection
import java.net.URL
import com.fasterxml.jackson.databind.ObjectMapper
import java.io.OutputStreamWriter

@Service
class FirebaseService {

    private val objectMapper = ObjectMapper()
    private val firebaseScope = listOf("https://www.googleapis.com/auth/firebase.messaging")

    private val accessToken: String by lazy {
        val credentials = GoogleCredentials
            .fromStream(javaClass.getResourceAsStream("/firebase-service-account.json")) // Coloque esse arquivo em resources
            .createScoped(firebaseScope)
        credentials.refreshIfExpired()
        credentials.accessToken.tokenValue
    }

    fun sendNotification(targetToken: String, title: String, body: String): String {
        val url = URL("https://fcm.googleapis.com/v1/projects/push-automatico-a7ac0/messages:send") // Substitua
        val conn = url.openConnection() as HttpURLConnection
        conn.requestMethod = "POST"
        conn.setRequestProperty("Authorization", "Bearer $accessToken")
        conn.setRequestProperty("Content-Type", "application/json; UTF-8")
        conn.doOutput = true

        val messageBody = mapOf(
            "message" to mapOf(
                "token" to targetToken,
                "notification" to mapOf(
                    "title" to title,
                    "body" to body
                )
            )
        )

        val jsonBody = objectMapper.writeValueAsString(messageBody)

        conn.outputStream.use { os ->
            OutputStreamWriter(os, Charsets.UTF_8).use { it.write(jsonBody) }
        }

        val response = conn.inputStream.bufferedReader().readText()
        return response
    }
}