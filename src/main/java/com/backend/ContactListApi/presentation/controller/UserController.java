package com.backend.ContactListApi.presentation.controller;

import com.backend.ContactListApi.presentation.dto.UserRequestDTO;
import com.backend.ContactListApi.presentation.dto.UserResponseDTO;
import com.backend.ContactListApi.persistence.entity.User;
import com.backend.ContactListApi.service.implementation.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * Controlador REST para la gestión de usuarios.
 * Proporciona endpoints para operaciones CRUD sobre la entidad {@link User}.
 */
@CrossOrigin
@RequestMapping("api/users")
@RestController
public class UserController {

    //inyectar servicio
    @Autowired
    private UserService userService;

    //Obtenr todos los usuarios
    @CrossOrigin
    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    //buscar usuario registrado
    @CrossOrigin
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id){

        return ResponseEntity.ok(userService.getUserById(id));
    }

    //metodo create
    @CrossOrigin
    @PostMapping
    public ResponseEntity<User> createUser(@Validated @RequestBody UserRequestDTO userRequestDTO){
        User createUser = userService.create(userRequestDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(createUser);
    }

    //metodo update
    @CrossOrigin
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @Validated @RequestBody UserRequestDTO userRequestDTO){
        User updateUser = userService.updateUser(id, userRequestDTO);

        return ResponseEntity.ok( updateUser);
    }

    //metodo delete
    @CrossOrigin
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
