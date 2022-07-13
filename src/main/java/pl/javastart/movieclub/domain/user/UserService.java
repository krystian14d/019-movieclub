package pl.javastart.movieclub.domain.user;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.javastart.movieclub.domain.exception.RoleNotFoundException;
import pl.javastart.movieclub.domain.exception.UserNotFoundException;
import pl.javastart.movieclub.domain.user.dto.UserCredentialsDto;
import pl.javastart.movieclub.domain.user.dto.UserDetailsDto;
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
    public void registerUserWithDefaultRole(UserRegistrationDto userRegistration) throws RoleNotFoundException {
        UserRole defaultRole = userRoleRepository.findByName(DEFAULT_USER_ROLE).orElseThrow(() ->
                new RoleNotFoundException(String.format("Role %s not found", DEFAULT_USER_ROLE)));
        User user = new User();
        user.setEmail(userRegistration.getEmail());
        user.setPassword(passwordEncoder.encode(userRegistration.getPassword()));
        user.getRoles().add(defaultRole);
        userRepository.save(user);
    }

    public UserDetailsDto getUserDetails(Long userId) throws UserNotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new UserNotFoundException(String.format("User with ID %s not found", userId)));
        return UserDetailsDtoMapper.map(user);
    }
}
