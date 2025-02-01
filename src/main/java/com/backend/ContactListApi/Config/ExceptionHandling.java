package com.backend.ContactListApi.Config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class ExceptionHandling {

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
}