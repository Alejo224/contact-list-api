package com.backend.ContactListApi.Controllers;

import com.backend.ContactListApi.Dtos.UserRequestDTO;
import com.backend.ContactListApi.Dtos.UserResponseDTO;
import com.backend.ContactListApi.Entities.User;
import com.backend.ContactListApi.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
//        Optional<User> user = userService.findById(id);
//        if(user.isEmpty()){
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok(userService.getById(id));

//        return userService.findById(id).map(ResponseEntity::ok)
//                .orElseGet(() -> ResponseEntity.notFound().build());

    }

    //metodo create
    @CrossOrigin
    @PostMapping
    public ResponseEntity<User> createUser(@Validated @RequestBody UserRequestDTO userRequestDTO){

//        try {
//            User createUser = userService.create(userDTO);
//            return ResponseEntity.status(HttpStatus.CREATED).body(createUser);
//        } catch (DuplicateUsernameException ex) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
//        }
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
//        Optional<User> findUser = userService.findById(id);
//
//        if (findUser.isEmpty()) return ResponseEntity.notFound().build();
//
//        userService.deleteById(id);
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
