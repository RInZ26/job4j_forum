package ru.job4j.forum.dao.mem;

import org.springframework.stereotype.Repository;
import ru.job4j.forum.model.Post;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class PostMem {
    /**
     * Делаем вид, что у нас нет ORM и id мы проставляем сами
     */
    private final AtomicInteger identityCounter = new AtomicInteger(0);

    private final Map<Integer, Post> posts = new HashMap<>();

    public void save(Post post) {
        int id = identityCounter.addAndGet(1);
        post.setId(id);
        change(id, post);
    }

    public void change(int key, Post post) {
        posts.put(key, post);
    }

    public List<Post> findAll() {
        return new ArrayList<>(posts.values());
    }

    public Post findById(int id) {
        return posts.get(id);
    }
}
