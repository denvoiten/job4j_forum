package ru.job4j.forum.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.persistence.PostRepository;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository posts;

    public Iterable<Post> findAll() {
        return posts.findAll();
    }

    public Optional<Post> findById(int id) {
        return posts.findById(id);
    }

    public void save(Post post) {
        posts.save(post);
    }

    public void delete(int id) {
        posts.deleteById(id);
    }

    public void deleteById(int id) {
        posts.deleteById(id);
    }
}
