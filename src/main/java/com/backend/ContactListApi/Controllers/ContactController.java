package com.backend.ContactListApi.Controllers;


import com.backend.ContactListApi.Dtos.ContactDTO;
import com.backend.ContactListApi.Entities.Contact;
import com.backend.ContactListApi.Services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

@CrossOrigin
@RequestMapping("/api/contacts")
@RestController
public class ContactController {
    
    @Autowired
    private ContactService contactService;

    @CrossOrigin
    @GetMapping
    private ResponseEntity<List<Contact>> getAllContact(){
        //mostar todos los contactos registrados de forma asendente
        return ResponseEntity.ok(contactService.findAll(Sort.by(Sort.Direction.ASC, "id")));
//            Sort sort = Sort.by(Sort.Direction.fromString(direction.toUpperCase()), sortBy);
//        return ResponseEntity.ok(contactService.findAll(sort));
    }

    @CrossOrigin
    @GetMapping("/{id}")
    public ResponseEntity<Contact> getContactById(@PathVariable Long id){
//        return movie.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        return contactService.findById(id).map(ResponseEntity::ok).
                orElseGet(() -> ResponseEntity.notFound().build());
        
    }

    @CrossOrigin
    @PostMapping
    public ResponseEntity<Contact> createContact(@Validated @RequestBody ContactDTO contactDTO){

        // Crear el contacto y obtener el recurso creado
        Contact createdContact = contactService.create(contactDTO);

        // Crear el contacto y obtener el recurso creado
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest() // Obtiene la URL base de la solicitud actual
                .path("/{id}") // Añade el ID del recurso creado a la URL
                .buildAndExpand(createdContact.getId())
                .toUri();

        // Devolver una respuesta con el código 201 y la ubicación del recurso
        return ResponseEntity.created(location).body(createdContact);
    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContact(@PathVariable Long id){
        if (!contactService.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        contactService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @CrossOrigin
    @PutMapping("/{id}")
    public ResponseEntity<Contact> updateContact(@PathVariable Long id,@Validated @RequestBody ContactDTO contactDTO){

        return ResponseEntity.ok(contactService.update(id, contactDTO));
    }
}
