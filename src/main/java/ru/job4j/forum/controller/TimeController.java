package ru.job4j.forum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.forum.service.TimeService;

@Controller
public class TimeController {

    private final TimeService timeService;

    @Autowired
    public TimeController(TimeService timeService) {
        this.timeService = timeService;
    }

    @GetMapping({"/timeZone"})
    public String getTimeZone(@RequestParam(name = "timeOffset", required = false) Integer offset) {
        if (null != offset) {
            timeService.updateSessionZoneId(offset);
        }
        return "login";
    }

}
