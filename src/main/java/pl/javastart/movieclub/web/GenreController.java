package pl.javastart.movieclub.web;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;
import pl.javastart.movieclub.domain.genre.GenreService;
import pl.javastart.movieclub.domain.genre.dto.GenreDto;
import pl.javastart.movieclub.domain.movie.MovieService;
import pl.javastart.movieclub.domain.movie.dto.MovieDto;

import java.util.List;

@AllArgsConstructor
@Controller
public class GenreController {

    private final GenreService genreService;
    private final MovieService movieService;

    @GetMapping("/genre/{genreId}")
    public String getGenre(
            @PathVariable long genreId,
            @RequestParam(name = "pageNo", required = false, defaultValue = "1") int pageNo,
            @RequestParam(name = "pageSize", required = false, defaultValue = "5") int pageSize,
            Model model){

        GenreDto genre = genreService.findGenreById(genreId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        Page<MovieDto> moviesPaged = movieService.findByGenreId(genreId, pageNo, pageSize);
        List<MovieDto> moviesByGenre = moviesPaged.getContent();

        model.addAttribute("heading", genre.getName());
        model.addAttribute("description", genre.getDescription());
        model.addAttribute("movies", moviesByGenre);
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", moviesPaged.getTotalPages());
        model.addAttribute("totalItems", moviesPaged.getTotalElements());

        return "movie-listing";
    }

    @GetMapping("/movie-genres")
    public String getGenreList(Model model){
        List<GenreDto> genres = genreService.findAllGenres();
        model.addAttribute("genres", genres);
        return "genre-listing";
    }
}
