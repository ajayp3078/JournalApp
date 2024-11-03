package com.journalapp.controller;

import com.journalapp.api.response.WeatherResponse;
import com.journalapp.entity.User;
import com.journalapp.service.UserService;
import com.journalapp.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private WeatherService weatherService;

    @PutMapping
    public ResponseEntity<?> updateUserByUsername(@RequestBody User user){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User userInDb = userService.findByUsername(username);
        if(userInDb != null){
            userInDb.setUsername(user.getUsername()!=null && !user.getUsername().equals("") ? user.getUsername() : userInDb.getUsername());
            userInDb.setPassword(user.getPassword()!=null && !user.getPassword().equals("") ? user.getPassword() : userInDb.getPassword());
            userService.saveNewUser(userInDb);
            return new ResponseEntity<>(userInDb, HttpStatus.OK);
        }
        return new ResponseEntity<>(userInDb, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUserByUsername(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        userService.deleteByUsername(username);
        return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getAllUsers(){
        try{
            List<User> users = userService.getAll();
            return new ResponseEntity<>(users, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable("username") String username){
        try{
            User user = userService.findByUsername(username);
            if(user != null){
                return new ResponseEntity<>(user, HttpStatus.OK);
            }
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/greetings")
    public ResponseEntity<?> greetings(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        WeatherResponse weather = weatherService.getWeather("Mumbai");
        String greetings="";
        if(weather != null){
            greetings = ", Weather feels like " + weather.getCurrent().getFeelsLike();
        }
        return new ResponseEntity<>("Hi "+username + greetings +" in Mumbai", HttpStatus.OK);
    }

}
