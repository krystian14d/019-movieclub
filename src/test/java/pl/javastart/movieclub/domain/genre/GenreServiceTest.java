package pl.javastart.movieclub.domain.genre;

import liquibase.pro.packaged.G;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import pl.javastart.movieclub.domain.genre.dto.GenreDto;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

class GenreServiceTest {

    @Mock
    private GenreRepository genreRepository;

    private GenreService underTest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        underTest = new GenreService(genreRepository);
    }

    @Test
    void itShouldFindGenreByName() {
        //GIVEN
        Long genreId = 1L;
        String genreName = "genreName";
        String genreDescription = "genreDescription";

        Genre genre = new Genre();
        genre.setId(genreId);
        genre.setName(genreName);
        genre.setDescription(genreDescription);

        given(genreRepository.findByNameIgnoreCase(genreName)).willReturn(Optional.of(genre));
        GenreDto genreDto = GenreDtoMapper.map(genre);

        //WHEN
        Optional<GenreDto> genreOptional = underTest.findGenreByName(genreName);

        //THEN
        assertThat(genreOptional)
                .isPresent()
                .hasValueSatisfying(g -> assertThat(g)
                        .usingRecursiveComparison()
                        .isEqualTo(genreDto));

    }
}