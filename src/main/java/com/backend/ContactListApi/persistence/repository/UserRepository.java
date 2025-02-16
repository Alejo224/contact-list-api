package com.backend.ContactListApi.persistence.repository;

import com.backend.ContactListApi.persistence.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
/**
 * Repositorio para la entidad {@link User}.
 * Proporciona métodos para la gestión de usuarios en la base de datos.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Verifica si existe un usuario con el nombre de usuario dado.
     *
     * @param username Nombre de usuario a verificar.
     * @return true si el usuario existe, false en caso contrario.
     */
    boolean existsByUsername(String username);

    /**
     * Verifica si existe un usuario con el correo electrónico dado.
     *
     * @param email Correo electrónico a verificar.
     * @return true si el correo ya está registrado, false en caso contrario.
     */
    boolean existsByEmail(String email);

    /**
     * Busca un usuario por su nombre de usuario.
     *
     * @param username Nombre de usuario a buscar.
     * @return Un {@link Optional} que contiene el usuario si se encuentra, o vacío si no existe.
     */
    Optional<User> findByUsername(String username);

    /**
     * Busca un usuario por su correo electrónico.
     *
     * @param email Correo electrónico a buscar.
     * @return Un {@link Optional} que contiene el usuario si se encuentra, o vacío si no existe.
     */
    Optional<User> findByEmail(String email);
}
