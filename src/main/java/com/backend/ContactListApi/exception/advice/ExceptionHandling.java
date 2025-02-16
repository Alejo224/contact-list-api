package com.backend.ContactListApi.exception.advice;

import com.backend.ContactListApi.exception.exception.DuplicateEmailException;
import com.backend.ContactListApi.exception.exception.DuplicateUsernameException;
import com.backend.ContactListApi.exception.exception.InvalidPasswordException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Manejador global de excepciones para la API REST.
 * <p>
 * Captura excepciones comunes y personalizadas, proporcionando respuestas estructuradas y significativas.
 */
@RestControllerAdvice
public class ExceptionHandling {
    /**
     * Maneja excepciones de validación cuando un argumento de un método no es válido.
     * <p>
     * Genera una respuesta en formato `ProblemDetail`, proporcionando detalles de los errores encontrados.
     *
     * @param exception Excepción capturada de tipo `MethodArgumentNotValidException`.
     * @return Un objeto `ProblemDetail` con los errores de validación.
     */

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleValidation(MethodArgumentNotValidException exception) {

        // Crear un objeto ProblemDetail para estructurar la respuesta de error
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setTitle("Error de validación");

        // Crear una lista para almacenar los errores de validación
        List<Map<String, String>> errors = new ArrayList<>();

        // Obtener la lista de errores de la excepción
        List<FieldError> fieldErrors = exception.getFieldErrors();
        for (FieldError fe : fieldErrors) {
            // Crear un mapa para almacenar detalles de cada error
            Map<String, String> errorDetail = new HashMap<>();
            errorDetail.put("campo", fe.getField()); // Nombre del campo que falló
            errorDetail.put("mensaje", fe.getDefaultMessage()); // Mensaje de error asociado
            errors.add(errorDetail); // Agregar el error a la lista
        }

        // Agregar la lista de errores a la respuesta
        problemDetail.setProperty("errores", errors);
        return problemDetail;
    }
    /**
     * Maneja la excepción `DuplicateUsernameException` y `DuplicateEmailException`, cuando un nombre de usuario ya está registrado.
     *
     * @param ex Excepción capturada.
     * @return Un `ResponseEntity` con código 400 (BAD REQUEST) y el mensaje de error.
     */

    // Manejo de excepciones personalizadas
    @ExceptionHandler(DuplicateUsernameException.class)
    public ResponseEntity<String> handleDuplicateUsernameException(DuplicateUsernameException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
    @ExceptionHandler(DuplicateEmailException.class)
    public ResponseEntity<String> handleDuplicateEmailException(DuplicateEmailException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    /**
     * Maneja la excepción `InvalidPasswordException`, cuando la contraseña no cumple con los requisitos de seguridad.
     *
     * @param ex Excepción capturada.
     * @return Un `ResponseEntity` con código 400 (BAD REQUEST) y el mensaje de error.
     */
    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<String> handleInvalidPasswordException(InvalidPasswordException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
//    @ExceptionHandler(ResourceNotFoundException.class)
//    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException ex) {
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
//    }
}