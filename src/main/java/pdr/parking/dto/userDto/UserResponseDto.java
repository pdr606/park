package pdr.parking.dto.userDto;

import pdr.parking.entities.Park;
import pdr.parking.entities.TrafficTicket;
import pdr.parking.entities.Vehicle;
import pdr.parking.entities.enums.Role;

import java.util.List;

public record UserResponseDto(
        String firstName,
        String lastName,
        String cpf,
        String telephone,
        String email,
        Long id,
        Integer balance,
        Role role,
        List<Vehicle> vehicles,
        List<Park> parks,
        List<TrafficTicket> trafficTickets
) {
}
