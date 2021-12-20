package ru.job4j.forum.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.job4j.forum.dao.mem.UserMem;
import ru.job4j.forum.model.User;

import java.util.ArrayList;
import java.util.List;

@Component("memAuthProvider")
public class MemoryAuthenticationProvider implements AuthenticationProvider {

    private final UserMem userMem;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public MemoryAuthenticationProvider(UserMem userMem, @Lazy PasswordEncoder passwordEncoder) {
        this.userMem = userMem;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {
        Authentication result = null;
        String name = authentication.getName();
        String password = authentication.getCredentials()
                                        .toString();

        User storedUser = userMem.findByName(name);

        if (storedUser != null && passwordEncoder.matches(password, storedUser.getPassword())) {
            result = new UsernamePasswordAuthenticationToken(name, password,
                    new ArrayList<>(List.of(() -> "USER")));
        }

        return result;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
