package pl.javastart.movieclub.domain.movie;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import pl.javastart.movieclub.domain.genre.Genre;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest(
        properties = {
                "spring.jpa.properties.javax.persistence.validation.mode=none"
        }
)
class MovieRepositoryTest {

    @Autowired
    private MovieRepository underTest;

    @Test
    void itShouldSaveMovie() {
        //GIVEN
        long id = 1L;
        String title = "Forrest Gump";
        int releaseYear = 1997;
        boolean promoted = false;
        long genreId = 1L;

        Genre genre = new Genre();
        genre.setId(genreId);
        String genreName = "Drama";
        genre.setName(genreName);

        Movie movie = new Movie();
        movie.setId(id);
        movie.setTitle(title);
        movie.setOriginalTitle(title);
        movie.setReleaseYear(releaseYear);
        movie.setGenre(genre);
        movie.setPromoted(promoted);

        //WHEN
        underTest.save(movie);

        //THEN
        Optional<Movie> movieOptional = underTest.findById(id);
        assertThat(movieOptional)
                .isPresent()
                .hasValueSatisfying(m ->
                        assertThat(m).usingRecursiveComparison().isEqualTo(movie));
    }

    @Test
    void itShouldFindPromotedMovies() {
        //GIVEN
        long id = 1L;
        String title = "Forrest Gump";
        int releaseYear = 1997;
        boolean promoted = true;
        long genreId = 1L;

        Genre genre = new Genre();
        genre.setId(genreId);
        String genreName = "Drama";
        genre.setName(genreName);

        Movie movie = new Movie();
        movie.setId(id);
        movie.setTitle(title);
        movie.setOriginalTitle(title);
        movie.setReleaseYear(releaseYear);
        movie.setGenre(genre);
        movie.setPromoted(promoted);

        //WHEN
        List<Movie> promotedMovies = underTest.findAllByPromotedIsTrue();
        System.out.println(promotedMovies.size());

        //THEN
        assertThat(promotedMovies).isNotEmpty();
    }
}