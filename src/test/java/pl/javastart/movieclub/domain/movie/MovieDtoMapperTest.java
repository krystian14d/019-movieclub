package pl.javastart.movieclub.domain.movie;

import org.junit.jupiter.api.Test;
import pl.javastart.movieclub.domain.genre.Genre;
import pl.javastart.movieclub.domain.movie.dto.MovieDto;

import static org.assertj.core.api.Assertions.assertThat;

class MovieDtoMapperTest {

    @Test
    void itShouldMapMovieToDtoObject() {
        //GIVEN
        long id = 1L;
        String title = "Forrest Gump";
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
        movie.setOriginalTitle(title);
        movie.setReleaseYear(releaseYear);
        movie.setGenre(genre);
        movie.setPromoted(promoted);

        //WHEN
        MovieDto movieDto = MovieDtoMapper.map(movie);

        //THEN
        assertThat(movieDto).usingRecursiveComparison().ignoringFields("genre").isEqualTo(movie);
        assertThat(movieDto.getGenre()).isEqualTo(movie.getGenre().getName());
    }
}