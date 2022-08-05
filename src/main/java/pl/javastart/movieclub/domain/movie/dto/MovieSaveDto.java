package pl.javastart.movieclub.domain.movie.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class MovieSaveDto {

    private String title;
    private String originalTitle;
    private String shortDescription;
    private String description;
    private String youtubeTrailerId;
    private Integer releaseYear;
    private Long genreId;
    private boolean promoted;
    private MultipartFile poster;
}
