package pl.javastart.movieclub.domain.movie;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.javastart.movieclub.domain.genre.Genre;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

class MovieTest {

    @Mock
    private Genre genre;

    private Movie underTest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void itShouldCreateMovie() {
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

        //WHEN
        underTest = new Movie();
        underTest.setId(id);
        underTest.setTitle(title);
        underTest.setOriginalTitle(originalTitle);
        underTest.setShortDescription(shortDesciption);
        underTest.setDescription(description);
        underTest.setYoutubeTrailerId(youtubeTrailerId);
        underTest.setReleaseYear(releaseYear);
        underTest.setGenre(genre);
        underTest.setPromoted(promoted);

        //THEN
        assertThat(underTest.getGenre()).usingRecursiveComparison().isEqualTo(genre);
        assertThat(underTest.getId()).isEqualTo(id);
        assertThat(underTest.getTitle()).isEqualTo(title);
        assertThat(underTest.getOriginalTitle()).isEqualTo(originalTitle);
        assertThat(underTest.getShortDescription()).isEqualTo(shortDesciption);
        assertThat(underTest.getDescription()).isEqualTo(description);
        assertThat(underTest.getYoutubeTrailerId()).isEqualTo(youtubeTrailerId);
        assertThat(underTest.getReleaseYear()).isEqualTo(releaseYear);
        assertThat(underTest.isPromoted()).isEqualTo(promoted);
    }
}