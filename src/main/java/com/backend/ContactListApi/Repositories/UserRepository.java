package com.backend.ContactListApi.Repositories;

import com.backend.ContactListApi.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByUsername(String username);    // Verifica si existe el usuario
    boolean existsByEmail(String email);        //Verifica si esxite el email

    Optional<User> findByUsername(String username);     //encontrar el usuario por el username
    Optional<User> findByEmail(String email);   //encontrar el usuario por el username

}
