package com.backend.ContactListApi.exception.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
/**
 * Excepción que se lanza cuando un recurso solicitado no se encuentra en el sistema.
 * <p>
 * Se devuelve un código de estado HTTP 404 (NOT FOUND) para indicar que el recurso no existe.
 */
//ESTATUS DE LA CLASE
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{
}

