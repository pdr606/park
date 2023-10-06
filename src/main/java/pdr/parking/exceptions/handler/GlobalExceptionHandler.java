package pdr.parking.exceptions.handler;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pdr.parking.exceptions.UserDuplicateException;
import pdr.parking.exceptions.UserNotFoundException;

import java.util.*;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(UserDuplicateException.class)
    public ResponseEntity<Map<String, List<String>>> handleUserDuplicated(Exception ex){
        List<String> err = Collections.singletonList(ex.getMessage());
        return  ResponseEntity.ok(getErrorsMap(err));
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String, List<String>>> handleUserNotFound(Exception ex){
        List<String> err = Collections.singletonList(ex.getMessage());
        return  ResponseEntity.ok(getErrorsMap(err));
    }

    private Map<String, List<String>> getErrorsMap(List<String> errors) {
        Map<String, List<String>> errorsMap = new HashMap<>();
        errorsMap.put("errors", errors);
        return errorsMap;
    }
}
