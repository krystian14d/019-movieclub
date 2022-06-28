package pl.javastart.movieclub.web.admin;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.javastart.movieclub.domain.genre.GenreService;
import pl.javastart.movieclub.domain.genre.dto.GenreDto;
import pl.javastart.movieclub.domain.movie.MovieService;
import pl.javastart.movieclub.domain.movie.dto.MovieSaveDto;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.instanceOf;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

class MovieManagementControllerTest {

    @Mock
    private MovieService movieService;
    @Mock
    private GenreService genreService;

    @Autowired
    private MovieManagementController underTest;
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        underTest = new MovieManagementController(movieService, genreService);
        mockMvc = MockMvcBuilders.standaloneSetup(underTest).build();
    }

    @Test
    void itShouldAddAttributesAndReturnViewName() throws Exception {
        //given
        GenreDto genre1 = new GenreDto();
        GenreDto genre2 = new GenreDto();
        List<GenreDto> allGenres = List.of(genre1, genre2);

        given(genreService.findAllGenres()).willReturn(allGenres);
        //when
        //then
        mockMvc.perform(get("/admin/dodaj-film"))
                .andExpect(model().attribute("genres", allGenres))
                .andExpect(model().attribute("genres", hasSize(allGenres.size())))
                .andExpect(model().attribute("movie", instanceOf(MovieSaveDto.class)))
                .andExpect(view().name("admin/movie-form"));
    }

    @Test
    void itShouldAddFlashAttributeAndReturnRedirect() throws Exception {
        //given
        MovieSaveDto movie = new MovieSaveDto();
        String title = "Forest Gump";
        movie.setTitle(title);

        ArgumentCaptor<MovieSaveDto> movieSaveDtoArgumentCaptor = ArgumentCaptor.forClass(MovieSaveDto.class);

        //when

        //then
        mockMvc.perform(post("/admin/dodaj-film")
                        .param("title", movie.getTitle()))
                .andExpect(flash().attribute(AdminController.NOTIFICATION_ATTRIBUTE,
                        "Film %s został zapisany.".formatted(movie.getTitle())))
                .andExpect(view().name("redirect:/admin"));

        then(movieService).should().addMovie(movieSaveDtoArgumentCaptor.capture());
        MovieSaveDto movieSaveDtoArgumentCaptorValue = movieSaveDtoArgumentCaptor.getValue();

        assertThat(movieSaveDtoArgumentCaptorValue).usingRecursiveComparison().isEqualTo(movie);


    }
}