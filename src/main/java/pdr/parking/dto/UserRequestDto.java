package pdr.parking.dto;

import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotBlank;


public record UserRequestDto(String firstName,
                             String lastName,
                             String cpf,
                             String email,
                             String telephone,
                             String password
)
{}
