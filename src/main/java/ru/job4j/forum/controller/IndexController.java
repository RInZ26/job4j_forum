package ru.job4j.forum.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.forum.service.PostService;
import ru.job4j.forum.service.TimeService;

@Controller
public class IndexController {

    private final PostService postService;
    private final TimeService timeService;

    @Autowired
    public IndexController(PostService postService, TimeService timeService) {
        this.postService = postService;
        this.timeService = timeService;
    }

    @GetMapping({"/", "/index"})
    public String index(Model model) {

        Authentication auth = SecurityContextHolder.getContext()
                                                   .getAuthentication();
        if (auth != null) {
            model.addAttribute("userName", auth.getName());
            model.addAttribute("timeZone", timeService.getSessionTimeZone()
                                                      .getDisplayName());
        }

        model.addAttribute("posts", postService.findAll(true));

        return "index";
    }
}