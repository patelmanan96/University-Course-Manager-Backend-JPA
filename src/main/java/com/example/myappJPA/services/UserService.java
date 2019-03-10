package com.example.myappJPA.services;

import com.example.myappJPA.FacultyRepository;
import com.example.myappJPA.models.Faculty;
import com.example.myappJPA.models.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(allowCredentials = "true",origins = "*")
public class UserService {
    @Autowired
    FacultyRepository repository;

    private ArrayList<Faculty> userStore = new ArrayList<>();

    @PostMapping("/api/register")
    public Faculty register(@RequestBody Faculty user, HttpSession session) {
        System.out.println("REQUEST HIT : " + user);
        for(Faculty i: userStore){
            if(i.getUsername().equals(user.getUsername())){
                return new Faculty();
            }
        }
        userStore.add(user);
        session.setAttribute("currentUser", user);
        System.out.println("SESSION SET : "+((Faculty)session.getAttribute("currentUser")).getUsername());
        return user;
    }


    @GetMapping("/api/profile")
    public Faculty profile(HttpSession session) {
        Faculty toRet = (Faculty) session.getAttribute("currentUser");
        if(toRet != null){
            System.out.println("SESSION GET HIT : "+toRet.getUsername());
            return toRet;
        }
        System.out.println("SESSION GET HIT : EMPTY");
        return new Faculty();
    }


    @PostMapping("/api/login")
    public Faculty login(@RequestBody Faculty user, HttpSession session) {
        System.out.println("LOGIN REQ : "+user.getUsername());
        Faculty inp = userStore.stream().filter(u -> u.getUsername()
                .equals(user.getUsername()) &&
                u.getPassword().equals(user.getPassword()))
                .findAny()
                .orElse(null);
        if (inp != null) {
            session.setAttribute("currentUser", inp);
            System.out.println("REGISTERED : "+inp.getUsername());
            return inp;
        }
        return new Faculty();
    }

    @GetMapping("/api/logout")
    public void logout(HttpSession session) {
        session.invalidate();
    }

    @GetMapping("/api/users")
    public List<Faculty> findAllUsers() {
        System.out.println("ALL USERS HIT");
        return userStore;
    }

    @GetMapping("api/users/{userId}")
    public Faculty findUserById(@PathVariable("userId") int id) {
        return userStore.stream().filter(user -> user.getUserId() == id).findAny().orElse(null);
    }
}
