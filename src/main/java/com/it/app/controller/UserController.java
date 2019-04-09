package com.it.app.controller;

import com.it.app.service.UserService;
import com.it.app.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = {"/", "/main"}, method = RequestMethod.GET)
    public ModelAndView main() {

        return new ModelAndView("main", "", "");
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.GET)
    public String modelAttribute(@ModelAttribute("name") String name, ModelMap model) {
        userService.addUser(name);
        model.addAttribute("name", name);
        return "ok";
    }

/*    @RequestMapping(value = "/viewName", method = RequestMethod.GET)
    public String viewName(@RequestParam("value") String value, ModelMap model) {
        model.addAttribute("message", "Hello World RequestParam:" + value);
        return "main";
    }*/
}
