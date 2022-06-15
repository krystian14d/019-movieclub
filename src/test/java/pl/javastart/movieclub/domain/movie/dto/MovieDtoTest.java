package pl.javastart.movieclub.domain.movie.dto;

import org.junit.jupiter.api.Test;
import pl.javastart.movieclub.domain.genre.Genre;
import pl.javastart.movieclub.domain.movie.Movie;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MovieDtoTest {

    private MovieDto underTest;
    @Test
    void itShouldCreateMovieDto() {
        //GIVEN
        long id = 1L;
        String title = "Forrest Gump";
        int releaseYear = 1997;
        boolean promoted = false;
        String genreName = "Drama";

        //WHEN
        underTest = new MovieDto(
                id,
                title,
                title,
                releaseYear,
                genreName,
                promoted
        );

        //THEN
        assertThat(underTest.getId()).isEqualTo(id);
        assertThat(underTest.getTitle()).isEqualTo(title);
        assertThat(underTest.getOriginalTitle()).isEqualTo(title);
        assertThat(underTest.getReleaseYear()).isEqualTo(releaseYear);
        assertThat(underTest.isPromoted()).isEqualTo(promoted);
        assertThat(underTest.getGenre()).isEqualTo(genreName);
    }
}