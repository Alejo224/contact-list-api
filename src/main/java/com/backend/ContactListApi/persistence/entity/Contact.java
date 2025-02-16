package com.backend.ContactListApi.persistence.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
/**
 * Entidad que representa un contacto en la base de datos.
 * <p>
 * La tabla asociada en la base de datos se llama "Contact" y almacena la información
 * de cada contacto registrado en la aplicación.
 */
@Entity
@Table(name = "Contact")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private LocalDateTime createdAt;

    public Contact(){}

    public Contact(String name, String email, LocalDateTime createdAt) {
        this.name = name;
        this.email = email;
        this.createdAt = createdAt;
    }

    //... getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
