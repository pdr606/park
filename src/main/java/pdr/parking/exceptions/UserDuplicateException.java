package pdr.parking.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class UserDuplicateException extends RuntimeException{
    public UserDuplicateException(String cpf){
        super("Usuário com o CPF " + cpf + " já cadastrado");

    }
}