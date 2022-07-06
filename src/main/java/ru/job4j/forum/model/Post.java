package ru.job4j.forum.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Setter
@Getter
@RequiredArgsConstructor
@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
    @JoinColumn(name = "user_id", nullable = false)
    private User author;
    private Date created = new Date(System.currentTimeMillis());
    @OneToMany(mappedBy = "post", fetch = FetchType.EAGER)
    private List<Comment> comments = new ArrayList<>();

    public static Post of(String name, String description, User author) {
        Post post = new Post();
        post.name = name;
        post.description = description;
        post.author = author;
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

    @Override
    public String toString() {
        return "Post{"
                + "id=" + id + ", name='" + name + '\''
                + ", description='" + description + '\''
                + ", author=" + author
                + ", created=" + created
                + ", comments=" + comments
                + '}';
    }
}
