package pl.javastart.movieclub.domain.movie;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MovieTest {

    @Autowired
    private Movie underTest;

    @Test
    void itShouldCreateMovie() {
        //given
        underTest = new Movie();
        Long id = 1L;
        String name = "Forrest Gump";

        //when
        underTest.setId(id);
        underTest.setName(name);

        //then
        assertThat(underTest.getId()).isEqualTo(id);
        assertThat(underTest.getName()).isEqualTo(name);
    }
}