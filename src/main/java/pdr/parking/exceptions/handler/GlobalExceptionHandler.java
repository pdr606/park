package pdr.parking.exceptions.handler;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pdr.parking.entities.User;
import pdr.parking.exceptions.*;

import java.time.Instant;
import java.util.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(UserDuplicateException.class)
    public StandardError userDuplicateException(UserDuplicateException e, HttpServletRequest request){
        String error = "User duplicate";
        HttpStatus status = HttpStatus.CONFLICT;
        return new StandardError(
                Instant.now(),
                status.value(),
                error,
                e.getMessage(),
                request.getRequestURI()
        );
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({UserNotFoundException.class, VehicleNotFoundException.class, TraffIcGuardNotFoundException.class})
    public StandardError UserNotFoundException(UserNotFoundException e, HttpServletRequest request){
        String error = "Request not found";
        HttpStatus status = HttpStatus.NOT_FOUND;
        return new StandardError(
                Instant.now(),
                status.value(),
                error,
                e.getMessage(),
                request.getRequestURI()
        );
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(InternalServerErrorException.class)
    public StandardError InternalServerErrorException(InternalServerErrorException e, HttpServletRequest request){
        String error = e.getMessage();
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        return new StandardError(
                Instant.now(),
                status.value(),
                error,
                "Internal Server Error",
                request.getRequestURI()
        );
    }
}
