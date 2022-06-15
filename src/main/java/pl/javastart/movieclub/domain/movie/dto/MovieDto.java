package pl.javastart.movieclub.domain.movie.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MovieDto {

    private Long id;
    private String title;
    private String originalTitle;
    private Integer releaseYear;
    private String genre;
    private boolean promoted;

}
