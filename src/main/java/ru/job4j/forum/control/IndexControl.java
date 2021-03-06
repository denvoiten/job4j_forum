package ru.job4j.forum.control;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.forum.service.PostService;

@RequiredArgsConstructor
@Controller
public class IndexControl {
    private final PostService postService;

    @GetMapping({"/", "/index"})
    public String index(Model model) {
        model.addAttribute("posts", postService.findAll());
        model.addAttribute("user", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return "index";
    }
}
