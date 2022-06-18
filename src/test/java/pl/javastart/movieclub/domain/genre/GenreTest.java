package pl.javastart.movieclub.domain.genre;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GenreTest {

    private Genre underTest;

    @Test
    void itShouldCreateGenre() {
        //GIVEN
        Long id = 1L;
        String name = "Comedy";
        String description = "Comedy description";
        underTest = new Genre();
        //WHEN
        underTest.setId(id);
        underTest.setName(name);
        underTest.setDescription(description);
        //THEN
        assertThat(underTest.getId()).isEqualTo(id);
        assertThat(underTest.getName()).isEqualTo(name);
        assertThat(underTest.getDescription()).isEqualTo(description);
    }
}