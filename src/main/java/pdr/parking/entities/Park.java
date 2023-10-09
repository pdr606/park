package pdr.parking.entities;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Park {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private LocalDateTime createdAt;
    @Column(nullable = false)
    private LocalDateTime expirationAt;
    @Column(nullable = false)
    private Integer totalTime;
    @Column(nullable = false)
    private boolean current;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "vehicle_id", referencedColumnName = "id")
    private Vehicle vehicle;

    public Park(Integer totalTime, User user, Vehicle vehicle){
        this.totalTime = totalTime;
        this.createdAt = LocalDateTime.now(ZoneId.of("America/Sao_Paulo"));
        this.expirationAt = LocalDateTime.now(ZoneId.of("America/Sao_Paulo")).plusMinutes(totalTime);
        this.user = user;
        this.vehicle = vehicle;
    }
}
