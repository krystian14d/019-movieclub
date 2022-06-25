package pl.javastart.movieclub.domain.movie;

import lombok.Getter;
import lombok.Setter;
import pl.javastart.movieclub.domain.genre.Genre;
import pl.javastart.movieclub.domain.rating.Rating;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String originalTitle;
    private String shortDescription;
    private String description;
    private String youtubeTrailerId;
    private Integer releaseYear;
    @ManyToOne
    @JoinColumn(
            name = "genre_id",
            referencedColumnName = "id"
    )
    private Genre genre;
    @OneToMany(mappedBy = "movie")
    private Set<Rating> ratings = new HashSet<>();
    private boolean promoted;
    private String poster;
}
