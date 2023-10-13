package pdr.parking.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TraffIcGuardNotFoundException extends RuntimeException {
    public TraffIcGuardNotFoundException(){
        super("TrafficGuard not found");
    }
}
