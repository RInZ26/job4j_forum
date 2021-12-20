package ru.job4j.forum.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.forum.dao.mem.PostMem;
import ru.job4j.forum.model.Post;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

    private final PostMem postMem;
    private final TimeService timeService;

    @Autowired
    public PostService(PostMem postMem, TimeService timeService) {
        this.postMem = postMem;
        this.timeService = timeService;
    }

    public List<Post> findAll(boolean withSessionTimeZone) {
        List<Post> posts = postMem.findAll();
        if (withSessionTimeZone) {
            posts = posts.stream()
                         .map(timeService::changeTimeZoneToSession)
                         .collect(Collectors.toList());
        }
        return posts;
    }

    public Post findById(int id, boolean withSessionTimeZone) {
        Post post = postMem.findById(id);
        if (withSessionTimeZone) {
            post = timeService.changeTimeZoneToSession(post);
        }
        return post;
    }

    /**
     * Хотим хранить время создания в UTC, а потом переводить уже для удобства показа во время юзера
     */
    public void save(Post post) {
        Integer id = post.getId();
        if (null == id) {
            post.setCreated(LocalDateTime.now(ZoneId.of("UTC")));
            postMem.save(post);
        } else {
            Post oldPost = findById(id, false);
            post.setCreated(oldPost.getCreated());
            postMem.change(id, post);
        }
    }
}
