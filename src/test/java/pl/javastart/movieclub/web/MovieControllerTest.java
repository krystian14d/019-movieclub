package pl.javastart.movieclub.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.javastart.movieclub.domain.comment.Comment;
import pl.javastart.movieclub.domain.comment.CommentService;
import pl.javastart.movieclub.domain.movie.MovieService;
import pl.javastart.movieclub.domain.movie.dto.MovieDto;
import pl.javastart.movieclub.domain.rating.RatingService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.instanceOf;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class MovieControllerTest {

    @Mock
    private MovieService movieService;

    @Mock
    private RatingService ratingService;

    @Mock
    private CommentService commentService;

    @Autowired
    private MovieController underTest;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        underTest = new MovieController(movieService, ratingService, commentService);
        mockMvc = MockMvcBuilders.standaloneSetup(underTest).build();
    }

    @Test
    void itShouldReturnMovieViewNameAndAddMovieAsAttribute() throws Exception {
        //given
        long id = 1L;
        String title = "Forrest Gump";
        String originalTitle = "Original title of Forrest Gump";
        String shortDesciption = "Short description about movie Forrest Gump.";
        String description = "Long description about movie Forrest Gump.";
        String youtubeTrailerId = "linkToYouTube";
        int releaseYear = 1997;
        boolean promoted = false;
        String genreName = "Drama";
        String poster = "poster.png";
        double avgRating = 4.5;
        int ratingCount = 123;

        MovieDto movie = new MovieDto(
                id,
                title,
                originalTitle,
                shortDesciption,
                description,
                youtubeTrailerId,
                releaseYear,
                genreName,
                promoted,
                poster,
                avgRating,
                ratingCount
        );

        given(movieService.findMovieById(id)).willReturn(Optional.of(movie));

        Page<Comment> commentsPaged = Mockito.mock(Page.class);

//        Alternatywnie:
        List<Comment> comments = new ArrayList<>();
//        Page<Comment> commentsPaged = new PageImpl(comments);

        int pageNo = 1;
        int pageSize = 5;
        given(commentService.findAllPagedCommentsByMovieId(id, pageNo, pageSize)).willReturn(commentsPaged);

        //when
        //then

        mockMvc.perform(MockMvcRequestBuilders.get("/movie/1"))
                .andExpect(MockMvcResultMatchers.view().name("movie"))
                .andExpect(model().attribute("movie", movie))
                .andExpect(model().attribute("movie", instanceOf(MovieDto.class)))
                .andExpect(model().attribute("currentPage", pageNo))
                .andExpect(model().attribute("totalPages", commentsPaged.getTotalPages()))
                .andExpect(model().attribute("totalItems", commentsPaged.getTotalElements()))
                .andExpect(model().attribute("comments", comments));

    }

    @Test
    void itShouldThrowAnExceptionWhenMovieNotFound() throws Exception {
        //given
        long id = 1L;
        given(movieService.findMovieById(id)).willReturn(Optional.empty());
        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(status().isNotFound());
    }
}