package pl.javastart.movieclub.domain.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import pl.javastart.movieclub.domain.movie.MovieService;

import static org.assertj.core.api.Assertions.assertThat;

class HomeControllerTest {

    private HomeController underTest;

    @Mock
    private MovieService movieService;

    @Mock
    private Model model;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        underTest = new HomeController(movieService);
    }

    @Test
    void itShouldReturnMovieListing() {
        //GIVEN
        String viewName = "movie-listing";

        //WHEN
        String returnedValue = underTest.home(model);

        //THEN
        assertThat(returnedValue).isNotEmpty();
        assertThat(returnedValue).isNotBlank();
        assertThat(returnedValue).isEqualTo(viewName);
    }
}