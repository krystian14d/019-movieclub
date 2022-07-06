package pl.javastart.movieclub.domain.movie;

import lombok.Getter;
import lombok.Setter;
import pl.javastart.movieclub.domain.comment.Comment;
import pl.javastart.movieclub.domain.genre.Genre;
import pl.javastart.movieclub.domain.rating.Rating;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@Entity
@Table(name = "movie")
public class Movie {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "original_title")
    private String originalTitle;
    @Column(name = "short_description")
    private String shortDescription;
    @Column(name = "description")
    private String description;
    @Column(name = "youtube_trailer_id")
    private String youtubeTrailerId;
    @Column(name = "release_year")
    private Integer releaseYear;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(
            name = "genre_id",
            referencedColumnName = "id"
    )
    private Genre genre;

    @OneToMany(mappedBy = "movie",
            orphanRemoval = true,
            cascade = CascadeType.REMOVE)
    private Set<Rating> ratings = new HashSet<>();

    @Column(name = "promoted")
    private boolean promoted;

    @Column(name = "poster")
    private String poster;

    @OneToMany(mappedBy = "movie",
            cascade = CascadeType.REMOVE)
    private Set<Comment> movieComments = new HashSet<>();
}
