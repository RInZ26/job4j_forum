package ru.job4j.forum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.service.PostService;

@Controller
public class PostController {
    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/post")
    public String postsPage(@RequestParam("id") Integer id, Model model) {

        Authentication auth = SecurityContextHolder.getContext()
                                                   .getAuthentication();

        model.addAttribute("userName", auth.getName());
        model.addAttribute("post", postService.findById(id, true));
        return "post";
    }

    @PostMapping("/save")
    public String createPost(@ModelAttribute Post post) {
        postService.save(post);
        return "redirect:index";
    }

    @GetMapping("edit")
    public String editPost(@RequestParam(value = "id", required = false) Integer id, Model model) {

        if (null != id) {
            model.addAttribute("editedPost", postService.findById(id, false));
        }

        return "edit";
    }
}
