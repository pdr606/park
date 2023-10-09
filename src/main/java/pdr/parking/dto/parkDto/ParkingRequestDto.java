package pdr.parking.dto.parkDto;

public record ParkingRequestDto(Long userId, Long vehicleId, Integer totalTime) {
}
