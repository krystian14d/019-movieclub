package pl.javastart.movieclub.domain.genre.dto;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class GenreDtoTest {

    private GenreDto underTest;

    @Test
    void itShouldCreateGenreDto() {
        //GIVEN
        Long id = 1L;
        String name = "Drama";
        String description = "Some description";

        //WHEN
        underTest = new GenreDto(
                id,
                name,
                description
        );

        //THEN
        assertThat(underTest.getId()).isEqualTo(id);
        assertThat(underTest.getName()).isEqualTo(name);
        assertThat(underTest.getDescription()).isEqualTo(description);
    }
}