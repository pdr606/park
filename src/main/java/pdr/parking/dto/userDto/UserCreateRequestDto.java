package pdr.parking.dto.userDto;


import pdr.parking.entities.enums.Role;

public record UserCreateRequestDto(String firstName,
                                   String lastName,
                                   String cpf,
                                   String email,
                                   String telephone,
                                   String password,
                                   Role role
)
{}
