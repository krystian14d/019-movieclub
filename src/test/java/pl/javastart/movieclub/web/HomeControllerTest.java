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

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

class HomeControllerTest {

    @Mock
    private MovieService movieService;

    @Autowired
    private HomeController underTest;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        underTest = new HomeController(movieService);
        mockMvc = MockMvcBuilders.standaloneSetup(underTest).build();
    }

//TODO: update test

//    @Test
//    void itShouldReturnMovieListingAndLoadPromotedMovies() throws Exception {
//        //given
//        long id = 1L;
//        String title = "Forrest Gump";
//        String originalTitle = "Original title of Forrest Gump";
//        String shortDesciption = "Short description about movie Forrest Gump.";
//        String description = "Long description about movie Forrest Gump.";
//        String youtubeTrailerId = "linkToYouTube";
//        int releaseYear = 1997;
//        boolean promoted = false;
//        String genreName = "Drama";
//        String poster = "poster.png";
//        double avgRating = 4.5;
//        int ratingCount = 123;
//
//        MovieDto movieDto1 = new MovieDto(
//                id,
//                title,
//                originalTitle,
//                shortDesciption,
//                description,
//                youtubeTrailerId,
//                releaseYear,
//                genreName,
//                promoted,
//                poster,
//                avgRating,
//                ratingCount
//        );
//
//        long id2 = 2L;
//
//        MovieDto movieDto2 = new MovieDto(
//                id2,
//                title,
//                originalTitle,
//                shortDesciption,
//                description,
//                youtubeTrailerId,
//                releaseYear,
//                genreName,
//                promoted,
//                poster,
//                avgRating,
//                ratingCount
//        );
//
//        List<MovieDto> promotedMovies = new ArrayList<>();
//        promotedMovies.add(movieDto1);
//        promotedMovies.add(movieDto2);
//
//        given(movieService.findAllPromotedMovies()).willReturn(promotedMovies);
//        //when
//        //then
//        mockMvc.perform(MockMvcRequestBuilders.get("/"))
//                .andExpect(MockMvcResultMatchers.view().name("movie-listing"))
//                .andExpect(model().attribute("movies", hasSize(2)))
//                .andExpect(model().attribute("heading", "Promowane filmy"))
//                .andExpect(model().attribute("description", "Filmy polecane przez nasz zespół"));
//    }
}