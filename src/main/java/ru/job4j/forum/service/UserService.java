package ru.job4j.forum.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.job4j.forum.dao.repositories.UserRepository;
import ru.job4j.forum.model.User;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    public boolean saveUser(User user) {
        boolean result = false;

        if (isRegAllowed(user)) {
            user.setPassword(encoder.encode(user.getPassword()));
            userRepository.save(user);
            result = true;
        }

        return result;
    }

    public boolean isRegAllowed(User user) {
        return null == userRepository.findByUsername(user.getUsername());
    }
}
