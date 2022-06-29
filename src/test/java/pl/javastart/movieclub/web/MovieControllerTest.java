package pl.javastart.movieclub.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.javastart.movieclub.domain.movie.MovieService;
import pl.javastart.movieclub.domain.movie.dto.MovieDto;
import pl.javastart.movieclub.domain.rating.RatingService;

import java.util.Optional;

import static org.hamcrest.Matchers.instanceOf;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class MovieControllerTest {

    @Mock
    private MovieService movieService;

    @Mock
    private RatingService ratingService;

    @Autowired
    private MovieController underTest;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        underTest = new MovieController(movieService, ratingService);
        mockMvc = MockMvcBuilders.standaloneSetup(underTest).build();
    }

    @Test
    void itShouldReturnMovieViewNameAndAddMovieAsAttribute() throws Exception {
        //given
        long id = 1L;
        String title = "Forrest Gump";
        String originalTitle = "Original title of Forrest Gump";
        String shortDesciption = "Short description about movie Forrest Gump.";
        String description = "Long description about movie Forrest Gump.";
        String youtubeTrailerId = "linkToYouTube";
        int releaseYear = 1997;
        boolean promoted = false;
        String genreName = "Drama";
        String poster = "poster.png";
        double avgRating = 4.5;
        int ratingCount = 123;

        MovieDto movie = new MovieDto(
                id,
                title,
                originalTitle,
                shortDesciption,
                description,
                youtubeTrailerId,
                releaseYear,
                genreName,
                promoted,
                poster,
                avgRating,
                ratingCount
        );

        given(movieService.findMovieById(id)).willReturn(Optional.of(movie));
        //when
        //then

        mockMvc.perform(MockMvcRequestBuilders.get("/film/1"))
                .andExpect(MockMvcResultMatchers.view().name("movie"))
                .andExpect(model().attribute("movie", movie))
                .andExpect(model().attribute("movie", instanceOf(MovieDto.class)));
    }

    @Test
    void itShouldThrowAnExceptionWhenMovieNotFound() throws Exception {
        //given
        long id = 1L;
        given(movieService.findMovieById(id)).willReturn(Optional.empty());
        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(status().isNotFound());
    }
}