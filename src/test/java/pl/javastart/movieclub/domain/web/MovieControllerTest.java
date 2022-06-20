package pl.javastart.movieclub.domain.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.web.server.ResponseStatusException;
import pl.javastart.movieclub.domain.genre.Genre;
import pl.javastart.movieclub.domain.movie.Movie;
import pl.javastart.movieclub.domain.movie.MovieDtoMapper;
import pl.javastart.movieclub.domain.movie.MovieService;
import pl.javastart.movieclub.domain.movie.dto.MovieDto;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;

class MovieControllerTest {

    private MovieController underTest;

    @Mock
    private MovieService movieService;

    @Mock
    private Model model;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        underTest = new MovieController(movieService);
    }

    @Test
    void itShouldReturnMovieView() {
        //GIVEN
        long id = 1L;
        String title = "Forrest Gump";
        String originalTitle = "Original title of Forrest Gump";
        String shortDesciption = "Short description about movie Forrest Gump.";
        String description = "Long description about movie Forrest Gump.";
        String youtubeTrailerId = "linkToYouTube";
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
        movie.setOriginalTitle(originalTitle);
        movie.setShortDescription(shortDesciption);
        movie.setDescription(description);
        movie.setYoutubeTrailerId(youtubeTrailerId);
        movie.setReleaseYear(releaseYear);
        movie.setGenre(genre);
        movie.setPromoted(promoted);

        MovieDto movieDto = MovieDtoMapper.map(movie);
        given(movieService.findMovieById(id)).willReturn(Optional.of(movieDto));

        String movieViewName = "movie";

        //WHEN
        String movieView = underTest.getMovie(id, model);

        //THEN
        assertThat(movieView).isNotEmpty();
        assertThat(movieView).isNotBlank();
        assertThat(movieView).isEqualTo(movieViewName);
    }

    @Test
    void itShouldThrowExceptionWhenMovieNotFound() {
        //GIVEN
        long id = 1000L;
        given(movieService.findMovieById(id)).willReturn(null);

        //WHEN
        //THEN
        assertThatThrownBy(() -> underTest.getMovie(1L, model))
                .isInstanceOf(ResponseStatusException.class);
    }
}