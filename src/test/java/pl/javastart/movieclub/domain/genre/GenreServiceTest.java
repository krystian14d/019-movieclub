package pl.javastart.movieclub.domain.genre;

import liquibase.repackaged.org.apache.commons.collections4.IteratorUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.javastart.movieclub.domain.genre.dto.GenreDto;
import pl.javastart.movieclub.domain.movie.MovieRepository;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Spliterator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static java.util.Arrays.asList;
import static java.util.Arrays.spliterator;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.when;

class GenreServiceTest {

    @Mock
    private GenreRepository genreRepository;
    @Mock
    private MovieRepository movieRepository;

    private GenreService underTest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        underTest =
                new GenreService(genreRepository, movieRepository);
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

    @Test
    void itShouldFindAllGenres() {
        //given
        Genre genre1 = new Genre();
        genre1.setId(1L);
        genre1.setName("genre1");
        genre1.setDescription("description1");

        Genre genre2 = new Genre();
        genre1.setId(2L);
        genre1.setName("genre2");
        genre1.setDescription("description2");

        HashSet<Genre> genres = new HashSet<>();
        genres.add(genre1);
        genres.add(genre2);

        given(genreRepository.findAll()).willReturn(genres);
        //when
        List<GenreDto> allGenres = underTest.findAllGenres();
        //then
        assertThat(allGenres).hasSize(2);
    }
}
