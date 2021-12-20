package ru.job4j.forum.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.forum.dao.repositories.PostRepository;
import ru.job4j.forum.model.Post;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final TimeService timeService;

    @Autowired
    public PostService(PostRepository postRepository, TimeService timeService) {
        this.postRepository = postRepository;
        this.timeService = timeService;
    }

    public List<Post> findAll(boolean withSessionTimeZone) {
        Stream<Post> postStream = StreamSupport.stream(postRepository.findAll()
                                                                     .spliterator(), false);
        if (withSessionTimeZone) {
            postStream = postStream.map(timeService::changeTimeZoneToSession);
        }

        return postStream.collect(Collectors.toList());
    }

    public Post findById(int id, boolean withSessionTimeZone) {
        Post post = postRepository.findById(id)
                                  .orElse(null);

        if (withSessionTimeZone && post != null) {
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
            postRepository.save(post);
        } else {
            Post oldPost = findById(id, false);
            post.setCreated(oldPost.getCreated());
            postRepository.save(post);
        }
    }
}
