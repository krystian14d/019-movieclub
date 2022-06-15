package pl.javastart.movieclub.domain.movie;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.javastart.movieclub.domain.genre.Genre;
import pl.javastart.movieclub.domain.movie.dto.MovieDto;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

class MovieServiceTest {

    @Mock
    private MovieRepository movieRepository;

    private MovieService underTest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        underTest = new MovieService(movieRepository);
    }

    @Test
    void itShouldFindPromotedMovies() {
        //GIVEN
        long id1 = 1L;
        String title1 = "Forrest Gump";
        int releaseYear1 = 1997;
        boolean promoted1 = true;
        long genre1Id = 1L;

        Genre genre1 = new Genre();
        genre1.setId(genre1Id);
        String genre1Name = "Drama";
        genre1.setName(genre1Name);

        Movie movie1 = new Movie();
        movie1.setId(id1);
        movie1.setTitle(title1);
        movie1.setOriginalTitle(title1);
        movie1.setReleaseYear(releaseYear1);
        movie1.setGenre(genre1);
        movie1.setPromoted(promoted1);

        long id2 = 2L;
        String title2 = "Home Alone";
        int releaseYear2 = 2002;
        boolean promoted2 = true;
        long genre2Id = 2L;

        Genre genre2 = new Genre();
        genre2.setId(genre2Id);
        String genre2Name = "Comedy";
        genre2.setName(genre2Name);

        Movie movie2 = new Movie();
        movie2.setId(id2);
        movie2.setTitle(title2);
        movie2.setOriginalTitle(title2);
        movie2.setReleaseYear(releaseYear2);
        movie2.setGenre(genre2);
        movie2.setPromoted(promoted2);

        given(movieRepository.findAllByPromotedIsTrue()).willReturn(List.of(movie1, movie2));

        //WHEN
        List<MovieDto> allPromotedMovies = underTest.findAllPromotedMovies();

        //THEN
        assertThat(allPromotedMovies).hasSize(2);
        assertThat(allPromotedMovies.get(1)).isInstanceOf(MovieDto.class);
    }

    @Test
    void itShouldNotFindPromotedMovies() {
        //GIVEN
        given(movieRepository.findAllByPromotedIsTrue()).willReturn(Collections.emptyList());
        //WHEN
        List<MovieDto> allPromotedMovies = underTest.findAllPromotedMovies();
        //THEN
        assertThat(allPromotedMovies).isEmpty();
    }
}