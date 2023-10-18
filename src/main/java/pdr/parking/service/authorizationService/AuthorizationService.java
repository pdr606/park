package pdr.parking.service.authorizationService;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pdr.parking.service.userService.UserGateway;

@Service
@AllArgsConstructor
public class AuthorizationService implements UserDetailsService {

    final UserGateway userGateway;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userGateway.findByEmail(username);
    }
}
