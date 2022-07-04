package ru.job4j.forum.control;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.forum.model.User;
import ru.job4j.forum.persistence.AuthorityRepository;
import ru.job4j.forum.persistence.UserRepository;

@RequiredArgsConstructor
@Controller
public class RegControl {

    private final PasswordEncoder encoder;
    private final UserRepository users;
    private final AuthorityRepository authorities;

    @PostMapping("/reg")
    public String regSave(Model model,
                          @ModelAttribute User user) {
        if (users.findUserByUsername(user.getUsername()) != null) {
            String errorMessage = "A user with this name is already registered";
            model.addAttribute("errorMessage", errorMessage);
            return "reg";
        }
        user.setPassword(encoder.encode(user.getPassword()));
        user.setAuthority(authorities.findByAuthority("ROLE_USER"));
        users.save(user);
        return "redirect:/login";
    }

    @GetMapping("/reg")
    public String regPage() {
        return "reg";
    }
}
