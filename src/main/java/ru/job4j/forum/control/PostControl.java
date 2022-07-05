package ru.job4j.forum.control;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.service.PostService;

@RequiredArgsConstructor
@Controller
public class PostControl {
    private final PostService postService;

    @GetMapping("/post/{id}")
    public String detailsPost(Model model, @PathVariable("id") int id) {
        postService.findById(id).ifPresent(post -> model.addAttribute("post", post));
        return "post";
    }

    @GetMapping("/addPost")
    public String add(Model model) {
        return "addPost";
    }

    @PostMapping("/createPost")
    public String addPost(@ModelAttribute Post post) {
        postService.save(post);
        return "redirect:/post/" + post.getId();
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
