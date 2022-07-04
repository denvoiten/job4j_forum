package ru.job4j.forum.persistence;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.forum.model.User;

public interface UserRepository extends CrudRepository<User, Integer> {
    User findUserByUsername(String name);
}
