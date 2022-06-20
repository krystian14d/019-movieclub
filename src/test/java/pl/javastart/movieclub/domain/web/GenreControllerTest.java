package pl.javastart.movieclub.domain.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.web.server.ResponseStatusException;
import pl.javastart.movieclub.domain.genre.Genre;
import pl.javastart.movieclub.domain.genre.GenreService;
import pl.javastart.movieclub.domain.genre.dto.GenreDto;
import pl.javastart.movieclub.domain.movie.MovieService;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

class GenreControllerTest {

    private GenreController underTest;

    @Mock
    private GenreService genreService;
    @Mock
    private MovieService movieService;
    @Mock
    private Model model;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        underTest = new GenreController(genreService, movieService);
    }

    @Test
    void itShouldThrowExceptionWhenGenreNotFound() {
        //GIVEN
        String genreName = "someGenre";

        //WHEN
        //THEN
        assertThatThrownBy(() -> underTest.getGenre(genreName, model)).isInstanceOf(ResponseStatusException.class);
    }

    @Test
    void itShouldReturnMovieListing() {
        //GIVEN
        Long genreId = 1L;
        String viewName = "movie-listing";
        String genreName = "Drama";
        String genreDescription = "Drama genre description";

        GenreDto genreDto = new GenreDto(genreId, genreName, genreDescription);
        given(genreService.findGenreByName(genreName))
                .willReturn(Optional.of(genreDto));

        //WHEN
        String returnedValue = underTest.getGenre(genreName, model);

        //THEN
        assertThat(returnedValue).isNotEmpty();
        assertThat(returnedValue).isNotBlank();
        assertThat(returnedValue).isEqualTo(viewName);
    }


}