package com.uncc.ticket.controller;


import com.uncc.ticket.model.BlogEntity;
import com.uncc.ticket.model.UsersEntity;
import com.uncc.ticket.service.BlogService;
import com.uncc.ticket.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class BlogController {
    private BlogService BlogService;
    private final UsersService usersService;

    @Autowired
    public BlogController(BlogService BlogService, UsersService usersService) {
        this.BlogService = BlogService;
        this.usersService = usersService;
    }

    @RequestMapping(value = {"/", ""}, method = RequestMethod.GET)
    public String getBlogs(Model model, Principal principal) {
        UsersEntity user = usersService.findByEmail(principal.getName());
        model.addAttribute("blogs", BlogService.getAllBlogsByUser(user));
        return "blogs/Blog";
    }

    @RequestMapping(value = "/blogs/storeBlogs", method = RequestMethod.GET)
    public String showStoreBlog(Model model) {
        model.addAttribute("blog", new BlogEntity());
        return "blogs/storeBlog";
    }

    @RequestMapping(value = "/blogs/storeBlogs", method = RequestMethod.POST)
    public String storeStoreBlog(Model model, @ModelAttribute(name = "blog") @Valid BlogEntity Blog, BindingResult bindingResult, Principal principal) {
        if (bindingResult.hasErrors()) {
            return "blogs/storeBlog";
        };
        Blog.setOwner(usersService.findByEmail(principal.getName()));
        BlogService.storeBlog(Blog);
        return "redirect:/";
    }

    @RequestMapping(value = "/blogs/edit/{id}", method = RequestMethod.GET)
    public String editBlog(Model model,@PathVariable("id") Long id) {
        BlogEntity hi = BlogService.findById(id);
        model.addAttribute("blog", hi);
        return "blogs/storeBlog";
    }

    @RequestMapping(value = "/blogs/delete/{id}", method = RequestMethod.GET)
    public String deleteBlog(@PathVariable("id") Long id) {
        BlogService.deleteById(id);
        return "redirect:/";
    }
}
