package ru.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.shop.model.City;
import ru.shop.model.User;
import ru.shop.service.CityService;
import ru.shop.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {
    private UserService userService;
    private CityService cityService;

    @Autowired(required = true)
    @Qualifier(value = "userService")
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired(required = true)
    @Qualifier(value = "cityService")
    public void setCityService(CityService cityService) {
        this.cityService = cityService;
    }

    @ModelAttribute
    public void addAttributes(Model model) {
       try {
            List<City> listCities = this.cityService.listCities();
            List<String> nameCities = new ArrayList<String>();
            for (City city:listCities) {
                nameCities.add(city.getName());}
            model.addAttribute("listCities", nameCities);
       } catch (Exception e) { }
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new User());
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("userForm") @Valid User userForm, BindingResult bindingResult, Model model) {
        try {
            if (bindingResult.hasErrors()) {
                return "registration";
            }
            if (!userForm.getPassword().equals(userForm.getPasswordConfirm())) {
                model.addAttribute("passwordError", "Пароли не совпадают");
                return "registration";
            }

            if (!userService.saveUser(userForm)) {
                model.addAttribute("usernameError", "Пользователь с таким именем уже существует");
                return "registration";
            }
        } catch (Exception e) {
            model.addAttribute("error", "Пожалуйста, проверьте правильность введенных данных.");
            return "registration";
        }
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null) {
            model.addAttribute("error", "Неверно введёно имя пользователя или пароль.");
        }
        if (logout != null) {
            model.addAttribute("message", "Выход совершён успешно");
        }
        return "login";
    }

    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout=true";
    }

}
