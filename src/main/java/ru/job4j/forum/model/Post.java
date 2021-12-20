package ru.job4j.forum.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
public class Post {
    private Integer id;
    private String name;
    private String description;
    private LocalDateTime created;

    public static Post of(int id, String name) {
        Post post = new Post();
        post.id = id;
        post.name = name;
        return post;
    }

    public static Post of(int id, String name, String description) {
        Post post = of(id, name);
        post.description = description;
        return post;
    }

    public static Post of(int id, String name, String description, LocalDateTime created) {
        Post post = of(id, name, description);
        post.created = created;
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
        return id.equals(post.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}