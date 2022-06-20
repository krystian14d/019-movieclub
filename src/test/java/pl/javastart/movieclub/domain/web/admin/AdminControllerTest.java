package pl.javastart.movieclub.domain.web.admin;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class AdminControllerTest {
    
    private AdminController underTest;

    @BeforeEach
    void setUp() {
        underTest = new AdminController();
    }

    @Test
    void itShouldReturnAdminView() {
        //GIVEN
        String adminViewName = "admin/admin";
        //WHEN
        String returnedViewName = underTest.getAdminPanel();
        //THEN
        assertThat(returnedViewName).isEqualTo(adminViewName);
        assertThat(returnedViewName).isNotEmpty();
        assertThat(returnedViewName).isNotBlank();

    }
}