package pdr.parking.entities.enums;

public enum VehicleType {
    CAR(2),
    MOTORCYCLE(1),
    TRUCK(3);

    private final Integer pricePerHour;

    VehicleType(Integer pricePerHour) {
        this.pricePerHour = pricePerHour;
    }

    public Integer getPricePerHour() {
        return pricePerHour;
    }
}
