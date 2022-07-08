package pl.javastart.movieclub.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.javastart.movieclub.domain.user.UserService;
import pl.javastart.movieclub.domain.user.dto.UserRegistrationDto;

import static org.hamcrest.Matchers.instanceOf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

class RegistrationControllerTest {

    @Mock
    private UserService userService;

    @Autowired
    private RegistrationController underTest;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        underTest = new RegistrationController(userService);
        mockMvc = MockMvcBuilders.standaloneSetup(underTest).build();
    }

    @Test
    void itShouldReturnRegistrationFormViewNameAndAddUserRegistrationObjectAttribute() throws Exception {
        //given
        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/register"))
                .andExpect(view().name("registration-form"))
                .andExpect(model().attribute("user", instanceOf(UserRegistrationDto.class)));
    }

    @Test
    void itShouldRegisterNewUserAndRedirect() throws Exception {
        //given
        //when
        //then
        mockMvc.perform(post("/register"))
                .andExpect(view().name("redirect:/"));
    }
}