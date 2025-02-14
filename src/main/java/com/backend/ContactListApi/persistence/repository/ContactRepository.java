package com.backend.ContactListApi.persistence.repository;

import com.backend.ContactListApi.persistence.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Long> {

}
