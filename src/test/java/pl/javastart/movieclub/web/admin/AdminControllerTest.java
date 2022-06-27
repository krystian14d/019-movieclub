package pl.javastart.movieclub.web.admin;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

class AdminControllerTest {

    @Autowired
    private AdminController underTest;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        underTest = new AdminController();
        mockMvc = MockMvcBuilders.standaloneSetup(underTest).build();
    }

    @Test
    void itShouldReturnAdminView() throws Exception {
        //given

        //when

        //then
        mockMvc.perform(get("/admin"))
                .andExpect(view().name("admin/admin"));

    }
}