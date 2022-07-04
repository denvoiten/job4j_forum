package ru.job4j.forum.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.Objects;

@Setter
@Getter
@RequiredArgsConstructor
@ToString
public class Post {
    private int id;
    private String name;
    private String description;
    private Date created = new Date(System.currentTimeMillis());

    public static Post of(String name, String description) {
        Post post = new Post();
        post.name = name;
        post.description = description;
        return post;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Post post = (Post) o;
        return id == post.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
