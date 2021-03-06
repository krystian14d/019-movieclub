package pl.javastart.movieclub.web.admin;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.javastart.movieclub.domain.genre.GenreService;
import pl.javastart.movieclub.domain.genre.dto.GenreDto;

@AllArgsConstructor
@Controller
public class GenreManagementController {

    private final GenreService genreService;

    @GetMapping("/admin/add-genre")
    public String addGenreForm(Model model) {
        GenreDto genre = new GenreDto();
        model.addAttribute("genre", genre);
        return "admin/genre-form";
    }

    @PostMapping("/admin/add-genre")
    public String addGenre(GenreDto genre, RedirectAttributes redirectAttributes) {
        genreService.addGenre(genre);
        redirectAttributes.addFlashAttribute(
                AdminController.NOTIFICATION_ATTRIBUTE,
                "Gatunek %s został zapisany".formatted(genre.getName())
        );
        return "redirect:/admin";
    }

    @GetMapping("/admin/edit-genre/{id}")
    public String updateGenre(@PathVariable Long id, Model model) {
        GenreDto genre = genreService.findGenreById(id).orElseThrow();
        model.addAttribute("genre", genre);
        return "/admin/edit-genre-form";
    }

    @PostMapping("/admin/edit-genre")
    public String updateGenre(GenreDto genre, RedirectAttributes redirectAttributes) {
        genreService.updateGenre(genre);
        redirectAttributes.addFlashAttribute(AdminController.NOTIFICATION_ATTRIBUTE,
                "Gatunek %s został zmieniony.".formatted(genre.getName()));
        return "redirect:/admin";
    }

    @GetMapping("/admin/delete-genre/{id}")
    public String deleteGenre(@PathVariable Long id, RedirectAttributes redirectAttributes){
        genreService.deleteGenre(id);
        redirectAttributes.addFlashAttribute(AdminController.NOTIFICATION_ATTRIBUTE,
                "Gatunek został usunięty.");
        return "redirect:/gatunki-filmowe";
    }
}

