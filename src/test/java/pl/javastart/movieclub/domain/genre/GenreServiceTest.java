package pl.javastart.movieclub.domain.genre;

import liquibase.repackaged.org.apache.commons.collections4.IteratorUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import pl.javastart.movieclub.domain.genre.dto.GenreDto;
import pl.javastart.movieclub.domain.movie.Movie;
import pl.javastart.movieclub.domain.movie.MovieRepository;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.Spliterator;
import java.util.stream.Collectors;
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
    @Captor
    private ArgumentCaptor<Set<Movie>> movieSetArgumentCaptor;

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

    @Test
    void itShouldFindGenreById() {
        //given
        Genre genre = new Genre();
        long id = 1L;
        genre.setId(id);
        genre.setName("genre1");
        genre.setDescription("description1");

        given(genreRepository.findById(id)).willReturn(Optional.of(genre));
        //when
        Optional<GenreDto> genreById = underTest.findGenreById(id);
        //then
        assertThat(genreById)
                .isNotEmpty();
        assertThat(genreById.get())
                .isInstanceOf(GenreDto.class);
    }

    @Test
    void itShouldUpdateGenre() {
        //given
        GenreDto genreDto = new GenreDto();
        long id = 1L;
        genreDto.setId(id);
        genreDto.setName("genre1");
        genreDto.setDescription("description1");

        Genre genre = new Genre();

        given(genreRepository.findById(genreDto.getId())).willReturn(Optional.of(genre));
        //when
        underTest.updateGenre(genreDto);

        //then
        ArgumentCaptor<Genre> genreArgumentCaptor = ArgumentCaptor.forClass(Genre.class);
        then(genreRepository).should().save(genreArgumentCaptor.capture());

        Genre genreArgumentCaptorValue = genreArgumentCaptor.getValue();
        assertThat(genreArgumentCaptorValue.getName()).isEqualTo(genreDto.getName());
        assertThat(genreArgumentCaptorValue.getDescription()).isEqualTo(genreDto.getDescription());
    }

    @Test
    void itShouldDeleteGenre() {
        //given
        Movie movie1 = new Movie();
        Movie movie2 = new Movie();
        Movie movie3 = new Movie();
        Genre genre = new Genre();

        Set<Movie> movies = new HashSet<>();
        movies.add(movie1);
        movies.add(movie2);
        movies.add(movie3);
        movies.forEach(m -> m.setGenre(genre));

        long genreId = 1L;

        given(movieRepository.findAllByGenre_Id(genreId)).willReturn(movies);

        //when
        underTest.deleteGenre(genreId);

        //then
        then(movieRepository).should().saveAll(movieSetArgumentCaptor.capture());
        Set<Movie> movieSetArgumentCaptorValue = movieSetArgumentCaptor.getValue();

        assertThat(movieSetArgumentCaptorValue.stream()
                .map(Movie::getGenre)
                .collect(Collectors.toSet())).isNotInstanceOf(Genre.class);
        then(genreRepository).should().deleteById(genreId);
    }
}
