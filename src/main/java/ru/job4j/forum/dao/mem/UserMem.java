package ru.job4j.forum.dao.mem;

import org.springframework.stereotype.Repository;
import ru.job4j.forum.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class UserMem {
    /**
     * Делаем вид, что у нас нет ORM и id мы проставляем сами
     */
    private final AtomicInteger identityCounter = new AtomicInteger(0);

    private final Map<String, User> users = new HashMap<>();

    public void save(User user) {
        user.setId(identityCounter.addAndGet(1));
        change(user.getUsername(), user);
    }

    public void change(String name, User user) {
        users.put(name, user);
    }

    public List<User> findAll() {
        return new ArrayList<>(users.values());
    }

    public User findByName(String name) {
        return users.get(name);
    }
}
