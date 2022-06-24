package pl.javastart.movieclub.domain.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest(
        properties = {
                "spring.jpa.properties.javax.persistence.validation.mode=none"
        }
)
class UserRepositoryTest {

    @Autowired
    private UserRepository underTest;

    private UserRoleRepository userRoleRepository;

    @Test
    void itShouldFindUserByUsername() {
        //given
        String email = "user@example.com";

        //when
        Optional<User> optionalUser = underTest.findByEmail(email);

        //then
        assertThat(optionalUser).isPresent();
        assertThat(optionalUser.get().getEmail()).isEqualTo(email);
        assertThat(optionalUser.get().getPassword()).isNotEmpty();
        assertThat(optionalUser.get().getRoles()).isNotEmpty();
        assertThat(optionalUser.get().getId()).isNotNull();
    }
}