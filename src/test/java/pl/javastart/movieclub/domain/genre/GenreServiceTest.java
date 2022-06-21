package pl.javastart.movieclub.domain.genre;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import pl.javastart.movieclub.domain.genre.dto.GenreDto;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

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

    @Test
    void itShouldSaveGenre() {
        //GIVEN
        String genreName = "genreName";
        String genreDescription = "genreDescription";

        GenreDto genreToSave = new GenreDto();
        genreToSave.setName(genreName);
        genreToSave.setDescription(genreDescription);

        //WHEN
        underTest.addGenre(genreToSave);

        //THEN
        ArgumentCaptor<Genre> genreArgumentCaptor = ArgumentCaptor.forClass(Genre.class);
        then(genreRepository).should().save(genreArgumentCaptor.capture());

        Genre genreArgumentCaptorValue = genreArgumentCaptor.getValue();

        assertThat(genreArgumentCaptorValue)
                .usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(genreToSave);
    }

    //TODO: test findAllGenres() method
}
