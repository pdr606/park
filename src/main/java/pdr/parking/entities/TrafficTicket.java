package pdr.parking.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.ZoneId;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class TrafficTicket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer value;
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    public TrafficTicket(User user, Vehicle vehicle){
        this.value = 60;
        this.createdAt = LocalDateTime.now(ZoneId.of("America/Sao_Paulo"));
        this.user = user;
        this.vehicle = vehicle;
    }
}
