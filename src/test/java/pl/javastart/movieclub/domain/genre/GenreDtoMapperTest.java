package pl.javastart.movieclub.domain.genre;

import org.junit.jupiter.api.Test;
import pl.javastart.movieclub.domain.genre.dto.GenreDto;

import static org.assertj.core.api.Assertions.assertThat;

class GenreDtoMapperTest {

    @Test
    void itShouldMapGenreToDtoObject() {
        //GIVEN
        long genreId = 1L;
        String genreName = "Drama";
        String genreDescription = "Genre Description";

        Genre genre = new Genre();
        genre.setId(genreId);
        genre.setName(genreName);
        genre.setDescription(genreDescription);

        //WHEN
        GenreDto genreDto = GenreDtoMapper.map(genre);

        //THEN
        assertThat(genreDto).usingRecursiveComparison().isEqualTo(genre);
    }
}