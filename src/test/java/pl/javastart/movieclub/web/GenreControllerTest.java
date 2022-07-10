package pl.javastart.movieclub.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.javastart.movieclub.domain.genre.GenreService;
import pl.javastart.movieclub.domain.genre.dto.GenreDto;
import pl.javastart.movieclub.domain.movie.Movie;
import pl.javastart.movieclub.domain.movie.MovieService;
import pl.javastart.movieclub.domain.movie.dto.MovieDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

class GenreControllerTest {

    @Mock
    private GenreService genreService;
    @Mock
    private MovieService movieService;

    @Autowired
    private GenreController underTest;
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        underTest = new GenreController(genreService, movieService);
        mockMvc = MockMvcBuilders.standaloneSetup(underTest).build();
    }

    //TODO: update test method

    @Test
    void itShouldAddGenreAndMovieToAttributesAndReturnViewName() throws Exception {
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
        String genreDescription = "Drama genre description";

        GenreDto genre = new GenreDto(1L, genreName, genreDescription);

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

        List<MovieDto> moviesByGenre = List.of(movie);

        given(genreService.findGenreByName(genreName)).willReturn(Optional.of(genre));
//        given(movieService.findMoviesByGenreName(genreName)).willReturn(moviesByGenre);
        int pageNo = 1;
        int pageSize = 5;

        Pageable pageable = PageRequest.of(pageNo, pageSize);
        List<MovieDto> movieList = List.of(movie);

        PageImpl<MovieDto> moviesPageImpl = new PageImpl<>(movieList, pageable, 2);

        Mockito.when(movieService.findPagedMoviesByGenreName(Mockito.anyString(),Mockito.anyInt(), Mockito.any())).thenReturn(moviesPageImpl);

        //when
        //then

        mockMvc.perform(get("/genre/Drama"))
                .andExpect(model().attribute("heading", genre.getName()))
                .andExpect(model().attribute("description", genreDescription))
                .andExpect(model().attribute("movies", moviesByGenre))
                .andExpect(view().name("movie-listing"));
    }

    @Test
    void itShouldThrowExceptionAndSentNotFoundResponse() throws Exception {
        //given
        String genreName = "Drama";
        given(genreService.findGenreByName(genreName)).willReturn(Optional.empty());
        //when
        //then
        mockMvc.perform(get("/genre/Drama"))
                .andExpect(status().isNotFound());

    }

    @Test
    void itShouldAddAllGenresAndReturnViewName() throws Exception {
        //given
        String genreName = "Drama";
        String genreDescription = "Drama genre description";
        GenreDto genre1 = new GenreDto(1L, genreName, genreDescription);
        String genreName2 = "Comedy";
        String genreDescription2 = "Comedy genre description";
        GenreDto genre2 = new GenreDto(1L, genreName2, genreDescription2);

        List<GenreDto> genres = new ArrayList<>();
        genres.add(genre1);
        genres.add(genre2);

        given(genreService.findAllGenres()).willReturn(genres);

        //when
        //then
        mockMvc.perform(get("/movie-genres"))
                .andExpect(model().attribute("genres", genres))
                .andExpect(view().name("genre-listing"));

    }
}