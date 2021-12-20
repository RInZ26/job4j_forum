package ru.job4j.forum.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class User {

    private Integer id;

    private String password;

    private String username;

    public static User of(int id, String password, String username) {
        User user = new User();
        user.id = id;
        user.password = password;
        user.username = username;
        return user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return id.equals(user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
