package pl.javastart.movieclub.domain.movie;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.javastart.movieclub.domain.genre.Genre;
import pl.javastart.movieclub.domain.movie.dto.MovieDto;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class MovieServiceTest {

    @Mock
    private MovieRepository movieRepository;

    @Mock
    private MovieDtoMapper movieDtoMapper;

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
        String originalTitle1 = "Original title of Forrest Gump";
        String shortDesciption1 = "Short description about movie Forrest Gump.";
        String description1 = "Long description about movie Forrest Gump.";
        String youtubeTrailerId1 = "linkToYouTube";
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
        movie1.setOriginalTitle(originalTitle1);
        movie1.setShortDescription(shortDesciption1);
        movie1.setDescription(description1);
        movie1.setYoutubeTrailerId(youtubeTrailerId1);
        movie1.setReleaseYear(releaseYear1);
        movie1.setGenre(genre1);
        movie1.setPromoted(promoted1);

        long id2 = 2L;
        String title2 = "Home Alone";
        String originalTitle2 = "Original title of Home Alone";
        String shortDesciption2 = "Short description about movie Home Alone.";
        String description2 = "Long description of movie Home ALone.";
        String youtubeTrailerId2 = "linkToYouTube2";
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
        movie2.setOriginalTitle(originalTitle2);
        movie2.setShortDescription(shortDesciption2);
        movie2.setDescription(description2);
        movie2.setYoutubeTrailerId(youtubeTrailerId2);
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

    @Test
    void itShouldFindMovieById() {
        //GIVEN
        long id = 1L;
        String title = "Forrest Gump";
        String originalTitle = "Original title of Forrest Gump";
        String shortDesciption = "Short description about movie Forrest Gump.";
        String description = "Long description about movie Forrest Gump.";
        String youtubeTrailerId = "linkToYouTube";
        int releaseYear = 1997;
        boolean promoted = false;
        long genreId = 1L;

        Genre genre = new Genre();
        genre.setId(genreId);
        String genreName = "Drama";
        genre.setName(genreName);

        Movie movie = new Movie();
        movie.setId(id);
        movie.setTitle(title);
        movie.setOriginalTitle(originalTitle);
        movie.setShortDescription(shortDesciption);
        movie.setDescription(description);
        movie.setYoutubeTrailerId(youtubeTrailerId);
        movie.setReleaseYear(releaseYear);
        movie.setGenre(genre);
        movie.setPromoted(promoted);

        MovieDto movieDto1 = MovieDtoMapper.map(movie);

        //WHEN
        given(movieRepository.findById(id)).willReturn(Optional.of(movie));

        //TODO: dlaczego nie można zrobić tak:
//        given(movieRepository.findById(id).map(MovieDtoMapper::map)).willReturn(Optional.of(movieDto1));

        Optional<MovieDto> optionalFoundMovie = underTest.findMovieById(1L);

        //THEN
        assertThat(optionalFoundMovie)
                .isPresent();

        assertThat(optionalFoundMovie.get())
                .usingRecursiveComparison()
                .isEqualTo(movieDto1);
    }
}