package com.coopdev.pushautomatic.controller

import com.coopdev.pushautomatic.dto.UserDTO
import com.coopdev.pushautomatic.model.User
import com.coopdev.pushautomatic.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/users")
class UserController(private val service: UserService) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody dto: UserDTO): User = service.create(dto)

    @GetMapping
    fun getAll(): List<User> = service.findAll()

    @GetMapping("/{id}")
    fun getById(@PathVariable id: UUID): User = service.findById(id)

    @PutMapping("/{id}")
    fun update(@PathVariable id: UUID, @RequestBody dto: UserDTO): User =
        service.update(id, dto)

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: UUID) = service.delete(id)
}
