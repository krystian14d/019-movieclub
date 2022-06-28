package pl.javastart.movieclub.domain.rating;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import pl.javastart.movieclub.domain.movie.Movie;
import pl.javastart.movieclub.domain.movie.MovieRepository;
import pl.javastart.movieclub.domain.user.User;
import pl.javastart.movieclub.domain.user.UserRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

class RatingServiceTest {

    @Mock
    private RatingRepository ratingRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private MovieRepository movieRepository;

    @Autowired
    private RatingService underTest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        underTest = new RatingService(ratingRepository, userRepository, movieRepository);
    }

    @Test
    void itShouldAddOrUpdateRating() {
        //given
        String userEmail = "email@example.com";
        User user = new User();
        user.setEmail(userEmail);

        Long movieId = 1L;
        Movie movie = new Movie();
        movie.setId(movieId);

        int userRating = 4;

        Rating rating = new Rating();
        given(ratingRepository.findByUser_EmailAndMovie_Id(userEmail, movieId)).willReturn(Optional.of(rating));

        given(userRepository.findByEmail(userEmail)).willReturn(Optional.of(user));
        given(movieRepository.findById(movieId)).willReturn(Optional.of(movie));

        ArgumentCaptor<Rating> ratingArgumentCaptor = ArgumentCaptor.forClass(Rating.class);
        //when
        underTest.addOrUpdateRating(userEmail, movieId, userRating);

        //then
        then(ratingRepository).should().save(ratingArgumentCaptor.capture());
        Rating ratingArgumentCaptorValue = ratingArgumentCaptor.getValue();

        assertThat(ratingArgumentCaptorValue).isNotNull();
        assertThat(ratingArgumentCaptorValue.getRating()).isEqualTo(userRating);
        assertThat(ratingArgumentCaptorValue.getUser().getEmail()).isEqualTo(userEmail);
        assertThat(ratingArgumentCaptorValue.getMovie().getId()).isEqualTo(movieId);
    }

    @Test
    void itShouldGetUserRatingForMovie() {
        //given
        String userEmail = "email@example.com";
        User user = new User();
        user.setEmail(userEmail);

        Long movieId = 1L;
        Movie movie = new Movie();
        movie.setId(movieId);

        int userRating = 4;

        Rating rating = new Rating();
        rating.setRating(userRating);
        rating.setUser(user);
        rating.setMovie(movie);
        rating.setId(1L);
        given(ratingRepository.findByUser_EmailAndMovie_Id(userEmail, movieId)).willReturn(Optional.of(rating));

        //when
        //then
        Optional<Integer> userRatingForMovie = underTest.getUserRatingForMovie(userEmail, movieId);

        assertThat(userRatingForMovie).isNotEmpty();
        assertThat(userRatingForMovie.get()).isEqualTo(userRating);

    }
}