package pl.javastart.movieclub.domain.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.javastart.movieclub.domain.exception.RoleNotFoundException;
import pl.javastart.movieclub.domain.user.dto.UserCredentialsDto;
import pl.javastart.movieclub.domain.user.dto.UserRegistrationDto;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private UserRoleRepository userRoleRepository;
    @Mock
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService underTest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        underTest = new UserService(userRepository, userRoleRepository, passwordEncoder);
    }

    @Test
    void itShouldFindCredentialsByEmail() {
        //given
        Long id = 1L;
        String email = "jan@example.com";
        String password = "password";

        UserRole userRole = new UserRole();
        userRole.setId(id);
        String userRoleName = "USER";
        userRole.setName(userRoleName);
        userRole.setDescription("User role description");

        Set<UserRole> role = Collections.singleton(userRole);

        User user = new User();
        user.setId(id);
        user.setEmail(email);
        user.setPassword(password);
        user.setRoles(role);

        given(userRepository.findByEmail(email)).willReturn(Optional.of(user));

        //when
        Optional<UserCredentialsDto> userCredentialsOptional = underTest.findCredentialsByEmail(email);

        //then
        assertThat(userCredentialsOptional).isNotEmpty();
        assertThat(userCredentialsOptional.get())
                .isInstanceOf(UserCredentialsDto.class);
        assertThat(userCredentialsOptional.get().getEmail()).isEqualTo(email);
        assertThat(userCredentialsOptional.get().getPassword()).isEqualTo(password);
        assertThat(userCredentialsOptional.get().getRoles()).contains(userRoleName);
    }

    @Test
    void itShouldRegisterUserWithDefaultRole() throws RoleNotFoundException {
        //given
        String email = "jan@example.com";
        String password = "password";

        UserRegistrationDto userRegistrationDto = new UserRegistrationDto();
        userRegistrationDto.setEmail(email);
        userRegistrationDto.setPassword(password);

        Long id = 1L;
        UserRole defaultRole = new UserRole();
        defaultRole.setId(id);
        String userRoleName = "USER";
        defaultRole.setName(userRoleName);
        defaultRole.setDescription("User role description");

        String DEFAULT_USER_ROLE = "USER";

        given(userRoleRepository.findByName(DEFAULT_USER_ROLE)).willReturn(Optional.of(defaultRole));
        given(passwordEncoder.encode(userRegistrationDto.getPassword())).willReturn(password);
        //when
        underTest.registerUserWithDefaultRole(userRegistrationDto);

        //then
        ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor.forClass(User.class);
        then(userRepository).should().save(userArgumentCaptor.capture());

        User userArgumentCaptorValue = userArgumentCaptor.getValue();

        assertThat(userArgumentCaptorValue).isNotNull();
        assertThat(userArgumentCaptorValue.getEmail()).isEqualTo(email);
        assertThat(userArgumentCaptorValue.getPassword()).isEqualTo(password);
        assertThat(userArgumentCaptorValue.getRoles()).contains(defaultRole);
    }
}