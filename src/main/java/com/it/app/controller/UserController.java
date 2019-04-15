package com.it.app.controller;

import com.it.app.model.User;
import com.it.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = {"/", "/adduser"}, method = RequestMethod.GET)
    public ModelAndView addUser() {

        return new ModelAndView("adduser", "", "");
    }

    @RequestMapping(value = "/useradded", method = RequestMethod.GET)
    public String userAdded(@ModelAttribute("name") String name, ModelMap model) {
        userService.addUser(name);
        model.addAttribute("name", name);
        return "useradded";
    }

    @RequestMapping(value = "/viewusers", method = RequestMethod.GET)
    public String viewUsers(@ModelAttribute("name") String name, ModelMap model) {
        List<User> users = userService.findAll();
        int number = users.size();
        model.addAttribute("number", number);
        return "viewusers";
    }

/*    @RequestMapping(value = "/viewName", method = RequestMethod.GET)
    public String viewName(@RequestParam("value") String value, ModelMap model) {
        model.addAttribute("message", "Hello World RequestParam:" + value);
        return "main";
    }*/
}
