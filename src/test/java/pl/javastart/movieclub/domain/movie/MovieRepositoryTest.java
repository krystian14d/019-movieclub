package pl.javastart.movieclub.domain.movie;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
        int pageNo = 0;
        int pageSize = 5;
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        //WHEN

        Page<Movie> moviesPaged = underTest.findAllByPromotedIsTrue(pageable);


        //THEN
        assertThat(moviesPaged.getSize()).isEqualTo(pageSize);
        assertThat(moviesPaged.getContent()).isNotEmpty();
    }

    @Test
    void itShouldFindAllMoviesByGenreName() {
        //given
        String genreName = "Drama";
        int pageNo = 0;
        int pageSize = 5;
        Pageable pageable = PageRequest.of(pageNo, pageSize);

        //when
        Page<Movie> moviesPaged = underTest.findAllByGenre_NameIgnoreCase(genreName, pageable);

        //then
        assertThat(moviesPaged.getSize()).isEqualTo(pageSize);
        assertThat(moviesPaged.getContent()).isNotEmpty();
        List<Genre> genreList = moviesPaged.getContent().stream()
                .map(movie -> movie.getGenre())
                .distinct()
                .toList();
        assertThat(genreList).hasSize(1);
    }
}