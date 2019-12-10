package org.fasttrackit.shoppinglist.auth.web;

import org.fasttrackit.shoppinglist.auth.model.User;
import org.fasttrackit.shoppinglist.auth.service.SecurityService;
import org.fasttrackit.shoppinglist.auth.service.UserService;
import org.fasttrackit.shoppinglist.auth.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Objects;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private SecurityService securityService;
    @Autowired
    private UserValidator userValidator;

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        userService.save(userForm);

        securityService.autoLogin(userForm.getUsername(), userForm.getPasswordConfirm());

        return "redirect:/welcome";
    }

    public String login(Model model, String error, String logout) {
        if (Objects.nonNull(error))
            model.addAttribute("error", "Your username and password is invalid.");
        if (Objects.nonNull(logout))
            model.addAttribute("message", "You have been logged out successfully");
        return "login";
    }

    @GetMapping({"/", "welcome"})
    public String welcome(Model model) {
        return "welcome";
    }
}
