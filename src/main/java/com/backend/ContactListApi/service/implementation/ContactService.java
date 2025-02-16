package com.backend.ContactListApi.service.implementation;

import com.backend.ContactListApi.presentation.dto.ContactDTO;
import com.backend.ContactListApi.persistence.entity.Contact;
import com.backend.ContactListApi.exception.exception.ResourceNotFoundException;
import com.backend.ContactListApi.persistence.repository.ContactRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
/**
La clase `ContactService` proporciona los servicios para la gestión de contactos en la aplicación.
Actúa como la capa intermedia entre el controlador y el repositorio, facilitando la lógica de negocio
 *y la manipulación de datos.

Esta clase implementa las operaciones CRUD (Crear, Leer, Actualizar, Eliminar) para la entidad `Contact`
utilizando `ContactRepository`. Además, emplea `ModelMapper` para transformar los objetos de transferencia
 de datos (DTOs) en entidades y viceversa.
 */
@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    private final ModelMapper mapper = new ModelMapper();

    public List<Contact> findAll(){
        //obtener la lista de usuarios de forma ascendente
        return contactRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }
    public Optional<Contact> findById(Long id){
        return contactRepository.findById(id);

    }

    public Contact create(ContactDTO contactDTO){
        // Transferir valores de la entidad al DTO
        Contact contact = mapper.map(contactDTO, Contact.class);
        //Asignarle la hor de registro al contacto
        contact.setCreatedAt(LocalDateTime.now());
        return contactRepository.save(contact);
    }

    public Contact update(Long id, ContactDTO contactDTO){
        Contact contactFormOb = contactRepository.findById(id).orElseThrow(ResourceNotFoundException::new);

        // Transferir valores de la entidad al DTO
        mapper.map(contactDTO, contactFormOb);
        return contactRepository.save(contactFormOb);
    }

    public void deleteById(Long id) {
        Optional<Contact> contact = contactRepository.findById(id);

        if (contact.isEmpty()){
            throw new ResourceNotFoundException();
        }
        contactRepository.deleteById(id);
    }

}
