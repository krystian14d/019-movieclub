package pl.javastart.movieclub.web;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.javastart.movieclub.domain.user.UserService;
import pl.javastart.movieclub.domain.user.dto.UserRegistrationDto;

@AllArgsConstructor
@Controller
public class RegistrationController {

    private final UserService userService;

    @GetMapping("/register")
    public String registrationForm(Model model){
        UserRegistrationDto userRegistration = new UserRegistrationDto();
        model.addAttribute("user", userRegistration);
        return "registration-form";
    }

    @PostMapping("/register")
    public String register(UserRegistrationDto userRegistration){
        userService.registerUserWithDefaultRole(userRegistration);
        return "redirect:/";
    }
}
