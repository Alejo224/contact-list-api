package com.backend.ContactListApi.Controllers;


import com.backend.ContactListApi.Entities.Contact;
import com.backend.ContactListApi.Services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
    private ResponseEntity<List<Contact>> getAllContact(@RequestParam(defaultValue = "id") String sortBy,
                                                        @RequestParam(defaultValue = "ASC") String direction){
        //mostar todos los contactos registrados de forma asendente
//        return ResponseEntity.ok(contactService.findAll(Sort.by(Sort.Direction.ASC, "id")));
            Sort sort = Sort.by(Sort.Direction.fromString(direction.toUpperCase()), sortBy);
        return ResponseEntity.ok(contactService.findAll(sort));
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
    public ResponseEntity<Contact> createContact(@RequestBody Contact contact){
        //condición de una posible respuesta de error que le avisara al frontend
        try {
            //Asignarle la hor de registro al contacto
            contact.setCreatedAt(LocalDateTime.now());
            Contact saveContact = contactService.save(contact);
            return ResponseEntity.created(new URI("/api/contacts/create/"+saveContact.getId())).body(saveContact);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

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
    public ResponseEntity<Contact> updateContact(@PathVariable Long id, @RequestBody Contact contact){
        //Busca el contacto y lanza un excepción si no existe
        Contact existingContact = contactService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contact not found"));

        //actualiza los compos necesarios
        existingContact.setName(contact.getName());
        existingContact.setEmail(contact.getEmail());

        //gardar los datos actualizado a la bse de datos
        Contact updateContact = contactService.save(existingContact);

        // retorna la respuesta con el contacto ctualizado
        return ResponseEntity.ok(updateContact);
    }
}
