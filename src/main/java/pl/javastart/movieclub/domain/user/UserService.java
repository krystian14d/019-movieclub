package pl.javastart.movieclub.domain.user;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.javastart.movieclub.domain.user.dto.UserCredentialsDto;
import pl.javastart.movieclub.domain.user.dto.UserRegistrationDto;

import javax.transaction.Transactional;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UserService {

    private static final String DEFAULT_USER_ROLE = "USER";

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;

    public Optional<UserCredentialsDto> findCredentialsByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(UserCredentialsDtoMapper::map);
    }

    @Transactional
    public void registerUserWithDefaultRole(UserRegistrationDto userRegistration) {
        UserRole defaultRole = userRoleRepository.findByName(DEFAULT_USER_ROLE).orElseThrow();
        User user = new User();
        user.setEmail(userRegistration.getEmail());
        user.setPassword(passwordEncoder.encode(userRegistration.getPassword()));
        user.getRoles().add(defaultRole);
        userRepository.save(user);
    }
}
