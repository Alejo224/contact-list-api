package com.backend.ContactListApi.Repositories;

import com.backend.ContactListApi.Entities.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContactRepository extends JpaRepository<Contact, Long> {

}
