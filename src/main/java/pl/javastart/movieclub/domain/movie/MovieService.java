package pl.javastart.movieclub.domain.movie;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.javastart.movieclub.domain.genre.Genre;
import pl.javastart.movieclub.domain.genre.GenreRepository;
import pl.javastart.movieclub.domain.movie.dto.MovieDto;
import pl.javastart.movieclub.domain.movie.dto.MovieEditDto;
import pl.javastart.movieclub.domain.movie.dto.MovieSaveDto;
import pl.javastart.movieclub.storage.FileStorageService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class MovieService {

    private final MovieRepository movieRepository;
    private final GenreRepository genreRepository;
    private final FileStorageService fileStorageService;


    public List<MovieDto> findAllPromotedMovies() {
        return movieRepository.findAllByPromotedIsTrue().stream()
                .map(MovieDtoMapper::map)
                .toList();
    }

    public Optional<MovieDto> findMovieById(Long id) {
        return movieRepository.findById(id).map(MovieDtoMapper::map);
    }

    public List<MovieDto> findMoviesByGenreName(String genre) {
        return movieRepository.findAllByGenre_NameIgnoreCase(genre)
                .stream()
                .map(MovieDtoMapper::map)
                .toList();
    }

    public void addMovie(MovieSaveDto movieToSave) {
        Movie movie = new Movie();
        movie.setTitle(movieToSave.getTitle());
        movie.setOriginalTitle(movieToSave.getOriginalTitle());
        movie.setPromoted(movieToSave.isPromoted());
        movie.setReleaseYear(movieToSave.getReleaseYear());
        movie.setShortDescription(movieToSave.getShortDescription());
        movie.setDescription(movieToSave.getDescription());
        movie.setYoutubeTrailerId(movieToSave.getYoutubeTrailerId());
        Genre genre = genreRepository.findByNameIgnoreCase(movieToSave.getGenre()).orElseThrow();
        movie.setGenre(genre);
        if (movieToSave.getPoster() != null) {
            String savedFileName = fileStorageService.saveImage(movieToSave.getPoster());
            movie.setPoster(savedFileName);
        }
        movieRepository.save(movie);
    }

    public List<MovieDto> findTopMovies(int size) {
        Pageable page = Pageable.ofSize(size);
        return movieRepository.findTopByRating(page).stream()
                .map(MovieDtoMapper::map)
                .toList();
    }

    @Transactional
    public void updateMovie(Long movieId, MovieEditDto movieToEdit) {
        Movie movie = movieRepository.findById(movieId).orElseThrow();
        movie.setTitle(movieToEdit.getTitle());
        movie.setOriginalTitle(movieToEdit.getOriginalTitle());
        movie.setPromoted(movieToEdit.isPromoted());
        movie.setReleaseYear(movieToEdit.getReleaseYear());
        movie.setShortDescription(movieToEdit.getShortDescription());
        movie.setDescription(movieToEdit.getDescription());
        movie.setYoutubeTrailerId(movieToEdit.getYoutubeTrailerId());
        Genre genre = genreRepository.findByNameIgnoreCase(movieToEdit.getGenre()).orElseThrow();
        movie.setGenre(genre);
        if (movieToEdit.getPoster() != null) {
            String savedFileName = fileStorageService.saveImage(movieToEdit.getPoster());
            movie.setPoster(savedFileName);
        }
        movieRepository.save(movie);
    }
}

