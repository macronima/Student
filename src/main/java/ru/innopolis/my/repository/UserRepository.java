package ru.innopolis.my.repository;

import org.springframework.data.repository.CrudRepository;
import ru.innopolis.my.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByLogin(String s);
}
