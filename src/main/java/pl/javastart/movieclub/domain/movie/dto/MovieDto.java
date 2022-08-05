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
    private String shortDescription;
    private String description;
    private String youtubeTrailerId;
    private Integer releaseYear;
    private Long genreId;
    private String genre;
    private boolean promoted;
    private String poster;
    private double avgRating;
    private int ratingCount;

}
