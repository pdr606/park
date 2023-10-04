package pdr.parking.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
    @Column(unique = true)
    @NotEmpty
    private String cpf;
    @NotNull(message = "Email obrigátorio")
    private String email;
    @NotEmpty
    private String telephone;
    @NotEmpty
    private String password;
    private Integer balance;
    private boolean admin;
}
