package pl.javastart.movieclub.web.admin;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.javastart.movieclub.domain.genre.GenreService;
import pl.javastart.movieclub.domain.genre.dto.GenreDto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

class GenreManagementControllerTest {

    @Mock
    private GenreService genreService;

    @Autowired
    private GenreManagementController underTest;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        underTest = new GenreManagementController(genreService);
        mockMvc = MockMvcBuilders.standaloneSetup(underTest).build();
    }

    @Test
    void itShouldAddGenreAttributeAndReturnViewName() throws Exception {
        //given

        //when

        //then
        mockMvc.perform(get("/admin/add-genre"))
                .andExpect(model().attribute("genre", instanceOf(GenreDto.class)))
                .andExpect(view().name("admin/genre-form"));
    }

    @Test
    void itShouldAddFlashAttributeAndReturnRedirect() throws Exception {
        //given
        GenreDto genre = new GenreDto(1L, "Drama", "Drama description");
        ArgumentCaptor<GenreDto> genreDtoArgumentCaptor = ArgumentCaptor.forClass(GenreDto.class);

        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.post("/admin/add-genre")
                        .param("id", genre.getId().toString())
                        .param("name", genre.getName())
                        .param("description", genre.getDescription()))
                .andExpect(flash().attribute(AdminController.NOTIFICATION_ATTRIBUTE,
                        "Gatunek %s został zapisany".formatted(genre.getName())))
                .andExpect(view().name("redirect:/admin"));

        then(genreService).should().addGenre(genreDtoArgumentCaptor.capture());
        GenreDto genreDtoArgumentCaptorValue = genreDtoArgumentCaptor.getValue();

        assertThat(genreDtoArgumentCaptorValue).usingRecursiveComparison().isEqualTo(genre);
    }
}