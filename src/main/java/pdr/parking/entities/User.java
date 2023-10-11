package pdr.parking.entities;

import jakarta.persistence.*;
import lombok.*;
import pdr.parking.entities.enums.Role;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString(exclude = {"vehicles", "parks", "trafficTickets"})
public class User extends AbstractUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer balance;

    @Column(nullable = false)
    private Role role;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Vehicle> vehicles = new ArrayList<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Park> parks = new ArrayList<>();


    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<TrafficTicket> trafficTickets = new ArrayList<>();

    public User(String firstName, String lastName, String cpf,
                String email, String telephone, String password, Role role){
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setTelephone(telephone);
        this.setCpf(cpf);
        this.setEmail(email);
        this.setPassword(password);
        this.setRole(role);
    }

}
