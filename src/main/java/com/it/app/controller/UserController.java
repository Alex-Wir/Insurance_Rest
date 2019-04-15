package com.it.app.controller;

import com.it.app.model.User;
import com.it.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
        User savedUser = userService.addUser(name);
        model.addAttribute("user", savedUser);
        return "useradded";
    }

    @RequestMapping(value = "/viewusers", method = RequestMethod.GET)
    public String viewUsers(@ModelAttribute("name") String name, ModelMap model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "viewusers";
    }

    @RequestMapping(value = "/edituser/{id}")
    public String edit(@PathVariable Long id, ModelMap model) {
        User user = userService.findById(id);
        model.addAttribute("command", user);
        return "useredit";
    }

    @RequestMapping(value = "/userupdate", method = RequestMethod.POST)
    public String userUpdated(@ModelAttribute("user") User user, ModelMap model) {
        User updatedUser = userService.update(user);
        model.addAttribute("user", updatedUser);
        return "userupdated";
    }

    @RequestMapping(value = "/deleteuser/{id}")
    public String delete(@PathVariable Long id, ModelMap model) {
        userService.delete(id);
        return "redirect:/viewusers";
    }


/*    @RequestMapping(value = "/viewName", method = RequestMethod.GET)
    public String viewName(@RequestParam("value") String value, ModelMap model) {
        model.addAttribute("message", "Hello World RequestParam:" + value);
        return "main";
    }*/
}
