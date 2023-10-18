package pdr.parking.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pdr.parking.entities.enums.Role;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString(exclude = {"vehicles", "parks", "trafficTickets"})
public class User extends AbstractUser implements UserDetails {

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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.role == Role.ADMIN) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"));
        if(this.role == Role.USER) return List.of(new SimpleGrantedAuthority("ROLE_USER"));
        if(this.role == Role.TRAFFIC_GUARD) return List.of(new SimpleGrantedAuthority("ROLE_TRAFFIC_GUARD"));
        return null;
    }

    @Override
    public String getUsername() {
        return getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
