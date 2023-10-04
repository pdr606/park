package pdr.parking.exceptions.handler;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pdr.parking.exceptions.UserDuplicateException;
import reactor.core.publisher.Mono;

import java.util.*;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(UserDuplicateException.class)
    public Mono<Map<String, List<String>>> handleUserDuplicated(Exception ex){
        List<String> err = Collections.singletonList(ex.getMessage());
        return  Mono.just(getErrorsMap(err));
    }

    private Map<String, List<String>> getErrorsMap(List<String> errors) {
        Map<String, List<String>> errorsMap = new HashMap<>();
        errorsMap.put("errors", errors);
        return errorsMap;
    }
}
