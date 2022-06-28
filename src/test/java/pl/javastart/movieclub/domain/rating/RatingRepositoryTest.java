package pl.javastart.movieclub.domain.rating;

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
class RatingRepositoryTest {

    @Autowired
    private RatingRepository underTest;

    @Test
    void itShouldFindRatingByEmailAndMovieId() {
        //given
        String userEmail = "admin@example.com";
        Long movieId = 1L;
        //when
        Optional<Rating> ratingOptional = underTest.findByUser_EmailAndMovie_Id(userEmail, movieId);
        //then
        assertThat(ratingOptional).isNotEmpty();
        assertThat(ratingOptional.get().getUser().getEmail()).isEqualTo(userEmail);
        assertThat(ratingOptional.get().getMovie().getId()).isEqualTo(movieId);
    }
}