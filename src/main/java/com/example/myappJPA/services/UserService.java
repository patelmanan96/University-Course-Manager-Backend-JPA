package com.example.myappJPA.services;

import com.example.myappJPA.repositories.UserRepository;
import com.example.myappJPA.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(allowCredentials = "true",origins = "*")
public class UserService {
    @Autowired
    UserRepository repository;

    @PostMapping("/api/register")
    public User register(@RequestBody User user, HttpSession session) {
        //System.out.println("REGISTER HIT>>>>");
        User check = repository.findByUsername(user.getUsername());
        if(check != null && check.getUsername() != null){
            return new User();
        }
        repository.save(user);
        session.setAttribute("currentUser",user);
        //System.out.println("REG : >>>>>>>" + ((User)session.getAttribute("currentUser")).getUsername());
        return user;
    }


    @GetMapping("/api/profile")
    public User profile(HttpSession session) {
        User current = (User)session.getAttribute("currentUser");
        return repository.findByUsername(current.getUsername());
    }


    @PostMapping("/api/login")
    public User login(@RequestBody User user, HttpSession session) {
        User toLogin = repository.findByUsername(user.getUsername());
        if(toLogin != null && toLogin.getPassword().equals(user.getPassword())){
            session.setAttribute("currentUser",toLogin);
            return toLogin;
        }
        return new User();
    }

    @GetMapping("/api/logout")
    public void logout(HttpSession session) {
        session.invalidate();
    }

    @GetMapping("/api/users")
    public List<User> findAllUsers() {
        List<User> toRet = new ArrayList<>();
        repository.findAll().forEach(toRet::add);
        return toRet;
    }

    @GetMapping("api/users/{userId}")
    public User findUserById(@PathVariable("userId") int id) {
        return repository.findById(id).get();
    }
}
