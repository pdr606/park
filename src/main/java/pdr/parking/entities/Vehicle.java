package pdr.parking.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import pdr.parking.entities.enums.VehicleType;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString(exclude = {"user", "park"})
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String plate;
    @Column(nullable = false)
    private boolean foreignPlate;
    @Column(nullable = false)
    private String brand;
    @Column(nullable = false)
    private String model;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private VehicleType vehicleType;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(mappedBy = "vehicle")
    @JsonIgnore
    private Park park;

    public Vehicle(String plate, boolean foreignPlate, String brand,
                   String model, VehicleType vehicleType, User user){
        this.plate = plate;
        this.foreignPlate = foreignPlate;
        this.brand = brand;
        this.model = model;
        this.vehicleType = vehicleType;
        this.user = user;

    }

}
