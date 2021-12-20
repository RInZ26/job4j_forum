package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.util.SessionTimeZoneProvider;

import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.TimeZone;

@Service
public class TimeService {

    private final SessionTimeZoneProvider sessionTimeZoneProvider;

    public TimeService(SessionTimeZoneProvider sessionTimeZoneProvider) {
        this.sessionTimeZoneProvider = sessionTimeZoneProvider;
    }

    public void updateSessionZoneId(int offset) {

        if (null != sessionTimeZoneProvider.getZoneId()) {
            return;
        }

        ZoneOffset zoneOffset = ZoneOffset.ofTotalSeconds(-offset * 60);

        TimeZone timeZone = TimeZone.getTimeZone(zoneOffset);

        sessionTimeZoneProvider.setZoneId(timeZone.toZoneId());
    }

    public Post changeTimeZoneToSession(Post post) {
        Post result = Post.of(post.getId(), post.getName(), post.getDescription());
        result.setCreated(post.getCreated()
                              .atZone(ZoneId.of("UTC"))
                              .withZoneSameInstant(sessionTimeZoneProvider.getZoneId())
                              .toLocalDateTime());
        return result;
    }

    public TimeZone getSessionTimeZone() {
        return TimeZone.getTimeZone(sessionTimeZoneProvider.getZoneId());
    }
}
