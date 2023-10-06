package pdr.parking.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "{firstName.not.blank}")
    @Column(nullable = false)
    private String firstName;
    @NotBlank(message = "{lastName.not.blank}")
    @Column(nullable = false)
    private String lastName;
    @NotBlank(message = "{cpf.not.blank}")
    @Column(unique = true, nullable = false)
    private String cpf;
    @NotBlank(message = "{email.not.blank}")
    @Column(nullable = false)
    private String email;
    @NotBlank(message = "{telephone.not.blank}")
    @Column(nullable = false)
    private String telephone;
    @NotBlank(message = "{password.not.blank}")
    @Column(nullable = false)
    private String password;
    private Integer balance;
    private boolean admin;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Vehicle> vehicles = new ArrayList<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Park> parks = new ArrayList<>();

}
