package ru.job4j.forum.persistence;

import org.springframework.stereotype.Repository;
import ru.job4j.forum.model.Post;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class PostMemStore {
    private final Map<Integer, Post> posts = new HashMap<>();

    private final AtomicInteger id = new AtomicInteger();

    public Collection<Post> getAll() {
        return posts.values();
    }

    public Post findById(int id) {
        return posts.get(id);
    }

    public void add(Post post) {
        post.setId(id.incrementAndGet());
        posts.putIfAbsent(post.getId(), post);
    }

    public void update(Post post) {
        posts.replace(post.getId(), post);
    }

    public void deleteById(int id) {
        posts.remove(id);
    }
}
