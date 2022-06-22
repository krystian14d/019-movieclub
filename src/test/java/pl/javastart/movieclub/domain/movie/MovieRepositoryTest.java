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

//    @Test
//    void itShouldSaveMovie() {
//        //GIVEN
//        long id = 100L;
//        String title = "Forrest Gump";
//        String originalTitle = "Original title of Forrest Gump";
//        String shortDesciption = "Short description about movie Forrest Gump.";
//        String description = "Long description about movie Forrest Gump.";
//        String youtubeTrailerId = "linkToYouTube";
//        int releaseYear = 1997;
//        boolean promoted = false;
//
//        long genreId = 100L;
//        Genre genre = new Genre();
//        genre.setId(genreId);
//        String genreName = "Genre name";
//        genre.setName(genreName);
//        String genreDescription = "Genre description";
//        genre.setDescription(genreDescription);
//
//        Movie movie = new Movie();
//        movie.setId(id);
//        movie.setTitle(title);
//        movie.setOriginalTitle(originalTitle);
//        movie.setShortDescription(shortDesciption);
//        movie.setDescription(description);
//        movie.setYoutubeTrailerId(youtubeTrailerId);
//        movie.setReleaseYear(releaseYear);
//        movie.setGenre(genre);
//        movie.setPromoted(promoted);
//
//        given(genreRepository.findById(genreId)).willReturn(Optional.of(genre));
//
//        //WHEN
//        //TODO: check how to test .save() method: org.springframework.orm.jpa.JpaObjectRetrievalFailureException: Unable to find pl.javastart.movieclub.domain.genre.Genre with id 100; nested exception is javax.persistence.EntityNotFoundException: Unable to find pl.javastart.movieclub.domain.genre.Genre with id 100
//        underTest.save(movie);
//
//        //THEN
//        Optional<Movie> movieOptional = underTest.findById(id);
//        assertThat(movieOptional)
//                .isPresent()
//                .hasValueSatisfying(m ->
//                        assertThat(m).usingRecursiveComparison().isEqualTo(movie));
//    }

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