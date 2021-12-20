package ru.job4j.forum.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.job4j.forum.dao.mem.UserMem;
import ru.job4j.forum.model.User;

@Service
public class UserService {

    private final UserMem userMem;
    private final PasswordEncoder encoder;

    @Autowired
    public UserService(UserMem userMem, PasswordEncoder encoder) {
        this.userMem = userMem;
        this.encoder = encoder;
    }

    public boolean saveUser(User user) {
        boolean result = false;

        if (isRegAllowed(user)) {
            user.setPassword(encoder.encode(user.getPassword()));
            userMem.save(user);
            result = true;
        }

        return result;
    }

    public boolean isRegAllowed(User user) {
        return null == userMem.findByName(user.getUsername());
    }
}
