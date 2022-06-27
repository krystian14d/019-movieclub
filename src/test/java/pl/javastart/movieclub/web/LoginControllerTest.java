package pl.javastart.movieclub.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

class LoginControllerTest {

    @Autowired
    private LoginController underTest;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        underTest = new LoginController();
        mockMvc = MockMvcBuilders.standaloneSetup(underTest).build();
    }

    @Test
    void itShouldReturnLoginFormViewName() throws Exception {
        //given

        //when

        //then
        mockMvc.perform(get("/login"))
                .andExpect(MockMvcResultMatchers.view().name("login-form"));

    }
}