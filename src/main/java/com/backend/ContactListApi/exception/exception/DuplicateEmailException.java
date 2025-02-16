package com.backend.ContactListApi.exception.exception;

/**
 * Excepción que se lanza cuando se intenta registrar un correo electrónico duplicado.
 * <p>
 * Esta excepción es de tipo {@code RuntimeException}, lo que significa que es una excepción no verificada
 * y puede ser manejada a través de un {@code @ExceptionHandler} en el controlador global de excepciones.
 */

public class DuplicateEmailException extends RuntimeException {
    /**
     * Constructor de la excepción.
     *
     * @param message Mensaje de error que describe el problema.
     */
    public DuplicateEmailException(String message) {
        super(message);
    }
}
