package com.backend.ContactListApi.persistence.repository;

import com.backend.ContactListApi.persistence.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * Repositorio para la entidad {@link Contact}.
 * Extiende {@link JpaRepository} para proporcionar operaciones CRUD predeterminadas.
 */
public interface ContactRepository extends JpaRepository<Contact, Long> {

}
