package pdr.parking.dto.userDto;


public record UserCreateRequestDto(String firstName,
                                   String lastName,
                                   String cpf,
                                   String email,
                                   String telephone,
                                   String password
)
{}
