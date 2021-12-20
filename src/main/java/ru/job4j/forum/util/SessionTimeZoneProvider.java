package ru.job4j.forum.util;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.time.ZoneId;

@Component
@SessionScope
@Getter
@Setter
public class SessionTimeZoneProvider {
    private ZoneId zoneId;
}
