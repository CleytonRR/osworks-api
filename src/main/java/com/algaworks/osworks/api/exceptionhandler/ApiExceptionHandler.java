package com.algaworks.osworks.api.exceptionhandler;

import com.algaworks.osworks.domain.exceptions.DomainException;
import com.algaworks.osworks.domain.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(DomainException.class)
    public ResponseEntity<?> handleDomainException(DomainException ex, WebRequest webRequest) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        ExceptionCustom exceptionCustom = new ExceptionCustom();
        exceptionCustom.setStatus(httpStatus.value());
        exceptionCustom.setTitulo(ex.getMessage());
        exceptionCustom.setLocalDateTime(OffsetDateTime.now());

        return handleExceptionInternal(ex, exceptionCustom, new HttpHeaders(), httpStatus, webRequest);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> handleEntityNotFound(DomainException ex, WebRequest webRequest) {
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        ExceptionCustom exceptionCustom = new ExceptionCustom();
        exceptionCustom.setStatus(httpStatus.value());
        exceptionCustom.setTitulo(ex.getMessage());
        exceptionCustom.setLocalDateTime(OffsetDateTime.now());

        return handleExceptionInternal(ex, exceptionCustom, new HttpHeaders(), httpStatus, webRequest);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ArrayList<ExceptionCustom.Campo> campos = new ArrayList<ExceptionCustom.Campo>();

        for(ObjectError error : ex.getBindingResult().getAllErrors()) {
            String field = ((FieldError) error).getField();
            String message = messageSource.getMessage(error, LocaleContextHolder.getLocale());

            campos.add(new ExceptionCustom.Campo(field, message));
        }
        ExceptionCustom exceptionCustom = new ExceptionCustom();
        exceptionCustom.setStatus(status.value());
        exceptionCustom.setTitulo("Um ou mais campos estão inválidos. " +
                "Faça o preenchimento correto e tente novamente.");
        exceptionCustom.setLocalDateTime(OffsetDateTime.now());
        exceptionCustom.setCampos(campos);
        return super.handleExceptionInternal(ex, exceptionCustom, headers, status, request);
    }
}
