package ru.job4j.forum.control;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.service.PostService;

@RequiredArgsConstructor
@Controller
public class PostControl {
    private final PostService postService;

    @GetMapping("/postInfo/{id}")
    public String detailsPost(Model model, @PathVariable("id") int id) {
        model.addAttribute("post", postService.findById(id));
        return "post";
    }

    @GetMapping("/addPost")
    public String add(Model model) {
        return "addPost";
    }

    @PostMapping("/createPost")
    public String addPost(@ModelAttribute Post post) {
        if (postService.getAll().contains(post)) {
            postService.update(post);
            return "redirect:/index";
        }
        postService.add(post);
        return "redirect:/index";
    }

    @GetMapping("deletePost/{postId}")
    public String delete(@PathVariable("postId") int id) {
        postService.deleteById(id);
        return "redirect:/index";
    }

    @GetMapping("/edit/{postId}")
    public String edit(Model model, @PathVariable("postId") int id) {
        model.addAttribute("post", postService.findById(id));
        return "edit";
    }

}
