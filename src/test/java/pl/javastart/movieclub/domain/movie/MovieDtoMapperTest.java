package pl.javastart.movieclub.domain.movie;

import org.junit.jupiter.api.Test;
import pl.javastart.movieclub.domain.genre.Genre;
import pl.javastart.movieclub.domain.movie.dto.MovieDto;
import pl.javastart.movieclub.domain.rating.Rating;
import pl.javastart.movieclub.domain.user.User;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class MovieDtoMapperTest {

    @Test
    void itShouldMapMovieToDtoObject() {
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
        String genreDescription = "Genre Description";
        genre.setDescription(genreDescription);

        Rating rating1 = new Rating(1L, new User(), new Movie(), 4);
        Rating rating2 = new Rating(2L, new User(), new Movie(), 5);

        Set<Rating> ratings = new HashSet<>();
        ratings.add(rating1);
        ratings.add(rating2);

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
        movie.setRatings(ratings);

        //WHEN
        MovieDto movieDto = MovieDtoMapper.map(movie);

        //THEN
        assertThat(movieDto).usingRecursiveComparison().ignoringFields("genre", "avgRating", "ratingCount").isEqualTo(movie);
        assertThat(movieDto.getGenre()).isEqualTo(movie.getGenre().getName());
        assertThat(movieDto.getAvgRating()).isEqualTo(4.5);
        assertThat(movieDto.getRatingCount()).isEqualTo(2);
    }
}