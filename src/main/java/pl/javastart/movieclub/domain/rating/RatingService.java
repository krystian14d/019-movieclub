package pl.javastart.movieclub.domain.rating;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.javastart.movieclub.domain.movie.Movie;
import pl.javastart.movieclub.domain.movie.MovieRepository;
import pl.javastart.movieclub.domain.user.User;
import pl.javastart.movieclub.domain.user.UserRepository;

import java.util.Optional;

@AllArgsConstructor
@Service
public class RatingService {

    private final RatingRepository ratingRepository;
    private final UserRepository userRepository;
    private final MovieRepository movieRepository;

    public void addOrUpdateRating(String userEmail, Long movieId, int rating) {
        Rating ratingToSaveOrUpdate = ratingRepository.findByUser_EmailAndMovie_Id(userEmail, movieId)
                .orElseGet(Rating::new);
        User user = userRepository.findByEmail(userEmail).orElseThrow();
        Movie movie = movieRepository.findById(movieId).orElseThrow();

        ratingToSaveOrUpdate.setUser(user);
        ratingToSaveOrUpdate.setMovie(movie);
        ratingToSaveOrUpdate.setRating(rating);

        ratingRepository.save(ratingToSaveOrUpdate);
    }

    public Optional<Integer> getUserRatingForMovie(String userEmail, Long movieId){
        return ratingRepository.findByUser_EmailAndMovie_Id(userEmail, movieId)
                .map(Rating::getRating);
    }
}

