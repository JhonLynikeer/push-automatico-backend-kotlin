package com.coopdev.pushautomatic.model

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "users") // <-- muda aqui
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null,

    val name: String,
    val email: String
)
