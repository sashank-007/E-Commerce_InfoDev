package com.infodev.ecommerceproject.Exception;

import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
//    @ResponseBody
//    public ExceptionResponse handleValidationError(MethodArgumentNotValidException ex){
//        BindingResult bindingResult = ex.getBindingResult();
//        FieldError fieldError = bindingResult.getFieldError();
//        String message = null;
//        if (fieldError != null) {
//            message = fieldError.getDefaultMessage();
//        }
//        return new ExceptionResponse("VALIDATION_ERROR", message);
//    }

//    @ExceptionHandler(Exception.class)
//    public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) throws Exception {
//        ExceptionResponse response = new ExceptionResponse(ex.getMessage(), request.getDescription(false));
//        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//    }


}
