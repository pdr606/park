package pdr.parking.dto;

public record ParkingRequestDto(Long userId, Long vehicleId, Integer totalTime) {
}
