package pl.javastart.movieclub.domain.movie;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import pl.javastart.movieclub.domain.genre.Genre;
import pl.javastart.movieclub.domain.genre.GenreRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@DataJpaTest(
        properties = {
                "spring.jpa.properties.javax.persistence.validation.mode=none"
        }
)
class MovieRepositoryTest {

    @Autowired
    private MovieRepository underTest;

    @Mock
    private GenreRepository genreRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void itShouldFindPromotedMovies() {
        //GIVEN

        //WHEN
        List<Movie> promotedMovies = underTest.findAllByPromotedIsTrue();
        System.out.println(promotedMovies.size());

        //THEN
        assertThat(promotedMovies).isNotEmpty();
    }

    @Test
    void itShouldFindAllMoviesByGenreName() {
        //given
        String genreName = "Drama";
        //when
        List<Movie> foundMovies = underTest.findAllByGenre_NameIgnoreCase(genreName);
        System.out.println(foundMovies.size());
        //then
        assertThat(foundMovies).isNotEmpty();
        List<Genre> genreList = foundMovies.stream()
                .map(movie -> movie.getGenre())
                .distinct()
                .toList();
        assertThat(genreList).hasSize(1);

    }
}