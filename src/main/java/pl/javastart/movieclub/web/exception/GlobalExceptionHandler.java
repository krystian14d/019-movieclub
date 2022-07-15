package pl.javastart.movieclub.web.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import pl.javastart.movieclub.domain.exception.CommentNotFoundException;
import pl.javastart.movieclub.domain.exception.MovieNotFoundException;
import pl.javastart.movieclub.domain.exception.RoleNotFoundException;
import pl.javastart.movieclub.domain.exception.UserNotFoundException;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    public static final String DEFAULT_ERROR_VIEW = "error";


    @ExceptionHandler(MovieNotFoundException.class)
    public ModelAndView handleMovieNotFoundException(HttpServletRequest request, Exception exception) {

        log.info("MovieNotFoundException, URL: " + request.getRequestURL() + " raised " + exception);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("exception", exception);
        modelAndView.addObject("url", request.getRequestURL());
        modelAndView.setViewName(DEFAULT_ERROR_VIEW);
        return modelAndView;
    }


    @ExceptionHandler(UserNotFoundException.class)
    public ModelAndView handleUserNotFoundException(HttpServletRequest request, Exception exception) {

        log.info("UserNotFoundException, URL: " + request.getRequestURL() + " raised " + exception);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("exception", exception);
        modelAndView.addObject("url", request.getRequestURL());
        modelAndView.setViewName(DEFAULT_ERROR_VIEW);
        return modelAndView;
    }

    @ExceptionHandler(RoleNotFoundException.class)
    public ModelAndView handleRoleNotFoundException(HttpServletRequest request, Exception exception) {

        log.info("RoleNotFoundException, URL: " + request.getRequestURL() + " raised " + exception);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("exception", exception);
        modelAndView.addObject("url", request.getRequestURL());
        modelAndView.setViewName(DEFAULT_ERROR_VIEW);
        return modelAndView;
    }

    @ExceptionHandler(CommentNotFoundException.class)
    public ModelAndView handleCommentNotFoundException(HttpServletRequest request, Exception exception) {

        log.info("CommentNotFoundException, URL: " + request.getRequestURL() + " raised " + exception);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("exception", exception);
        modelAndView.addObject("url", request.getRequestURL());
        modelAndView.setViewName(DEFAULT_ERROR_VIEW);
        return modelAndView;
    }

}
