package com.example.englishclass.Controller;

import com.example.englishclass.Entity.User;
import com.example.englishclass.Repository.UserRepository;
import com.example.englishclass.UserService.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@org.springframework.stereotype.Controller
@RequiredArgsConstructor
public class Controller {

    private final UserServiceImpl userService;
    private final UserRepository userRepository;


    @GetMapping("/")
    public String start(){
        return "index";
    }


    @GetMapping("/signUp")
    public String signUp(){
        return "signUp";
    }

    @PostMapping("/signUp")
    public String signUpPost(
            @RequestParam String firstname,
            @RequestParam String lastname,
            @RequestParam String email,
            @RequestParam String password, Model model){
        if(userRepository.existsById(email)){
            model.addAttribute("error", "Пользователь с таким email существует");

            return "signUp";
        }else {
            if (email.endsWith("@gmail.com")||email.endsWith("@mail.ru")||email.endsWith("@yandex.ru")) {
                User user = new User(firstname, lastname, email, password);
                userService.createUser(user);
                return "redirect:/login";
            } else {
                model.addAttribute("valid", "Некорректный email. Должно содержать @gmail.com, @mail.ru, @yandex.ru");
                return "signUp";
            }


        }

    }

    @GetMapping("/booking")
    public String booking(){
        return "booking";
    }

    @GetMapping("/personal")
    public String personal(Model model, String email){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        email = auth.getName();
        User user = userService.getMemberByEmail(email);

        model.addAttribute("user", user);
        return "personal";
    }

    @PostMapping("/loading")
    public String loginMember(@RequestParam String email){
        org.springframework.security.core.Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        email = auth.getName();
        System.out.println(auth.getAuthorities());

        System.out.println("Personal " + email);
        return "redirect:/personal";
    }


    @GetMapping("/block")
    public String block(){
        return "block";
    }







}

