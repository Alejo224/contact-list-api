package com.backend.ContactListApi.exception.exception;

/**
 * Excepción que se lanza cuando se intenta registrar un nombre de usuario que ya existe en el sistema.
 * <p>
 * Se utiliza para evitar la duplicación de nombres de usuario en la base de datos.
 */

public class DuplicateUsernameException extends RuntimeException {
    /**
     * Constructor de la excepción.
     *
     * @param message Mensaje de error que describe el problema.
     */
    public DuplicateUsernameException(String message) {
        super(message);
    }
}
