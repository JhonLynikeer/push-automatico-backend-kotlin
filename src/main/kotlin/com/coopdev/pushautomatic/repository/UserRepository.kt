package com.coopdev.pushautomatic.repository

import com.coopdev.pushautomatic.model.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserRepository : JpaRepository<User, UUID>
