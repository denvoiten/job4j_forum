package ru.job4j.forum.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.persistence.PostMemStore;

import java.util.Collection;

@RequiredArgsConstructor
@Service
public class PostService {
    private final PostMemStore posts;

    public Collection<Post> getAll() {
        return posts.getAll();
    }

    public Post findById(int id) {
        return posts.findById(id);
    }

    public void add(Post post) {
        posts.add(post);
    }

    public void update(Post post) {
        posts.update(post);
    }

    public void delete(int id) {
        posts.deleteById(id);
    }

    public void deleteById(int id) {
        posts.deleteById(id);
    }
}
