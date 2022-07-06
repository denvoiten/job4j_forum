package ru.job4j.forum.control;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.forum.model.Comment;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.persistence.CommentRepository;
import ru.job4j.forum.persistence.UserRepository;
import ru.job4j.forum.service.PostService;

@RequiredArgsConstructor
@Controller
public class PostControl {
    private final PostService postService;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    @GetMapping("/post/{id}")
    public String detailsPost(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        model.addAttribute("comments", commentRepository.findAllCommentsByPostId(id));
        postService.findById(id).ifPresent(post -> model.addAttribute("post", post));
        return "post";
    }

    @GetMapping("/addPost")
    public String add(Model model) {
        model.addAttribute("user", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return "addPost";
    }

    @PostMapping("/createPost")
    public String addPost(@ModelAttribute Post post) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        post.setAuthor(userRepository.findUserByUsername(username));
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

    @PostMapping("/comment/{postId}")
    public String saveComment(@ModelAttribute Comment comment,
                              @PathVariable String postId,
                              @RequestParam String text) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        comment.setUser(userRepository.findUserByUsername(username));
        comment.setPost(postService.findById(Integer.parseInt(postId)).orElse(new Post()));
        comment.setDescription(text);
        commentRepository.save(comment);
        return "redirect:/post/" + postId;
    }

    @GetMapping("/deleteComment/{postId}/{commentId}")
    public String deleteComment(@PathVariable("commentId") int commentId,
                                @PathVariable("postId") int postId) {
        commentRepository.deleteById(commentId);
        return "redirect:/post/" + postId;
    }
}
