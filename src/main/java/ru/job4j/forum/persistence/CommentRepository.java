package ru.job4j.forum.persistence;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.forum.model.Comment;

import java.util.List;

public interface CommentRepository extends CrudRepository<Comment, Integer> {
    List<Comment> findAllCommentsByPostId(int id);
}
