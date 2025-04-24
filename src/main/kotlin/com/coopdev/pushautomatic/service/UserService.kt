package com.coopdev.pushautomatic.service

import com.coopdev.pushautomatic.dto.UserDTO
import com.coopdev.pushautomatic.model.User
import com.coopdev.pushautomatic.repository.UserRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService(private val repository: UserRepository) {

    fun create(userDTO: UserDTO): User {
        val user = User(name = userDTO.name, email = userDTO.email)
        return repository.save(user)
    }

    fun findAll(): List<User> = repository.findAll()

    fun findById(id: UUID): User = repository.findById(id).orElseThrow {
        NoSuchElementException("Usuário não encontrado")
    }

    fun update(id: UUID, userDTO: UserDTO): User {
        val existing = findById(id)
        val updated = existing.copy(name = userDTO.name, email = userDTO.email)
        return repository.save(updated)
    }

    fun delete(id: UUID) {
        repository.deleteById(id)
    }
}
