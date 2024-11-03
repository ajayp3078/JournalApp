package com.journalapp.service;

import com.journalapp.entity.User;
import com.journalapp.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public boolean saveNewUser(User user){
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(Arrays.asList("USER"));
            userRepository.save(user);
            return true;
        }catch (Exception e){
//            log.error("Error occurred for {} :",user.getUsername(),e);
            log.error("error log");
            log.warn("logger info");
            log.info("logger info");
            log.debug("logger info");
            log.trace("logger info");
            return false;
        }
    }

    public void saveAdmin(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER","ADMIN"));
        userRepository.save(user);
    }

    public void saveUser(User user){
        userRepository.save(user);
    }

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public User findByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public void deleteByUsername(String username){
        User user = userRepository.findByUsername(username);
        if(user != null){
            userRepository.delete(user);
        }
    }

    public User updateEntryById(ObjectId id, User newEntry){
        User entry = userRepository.findById(id).orElse(null);
        if(entry != null){
            entry.setUsername(newEntry.getUsername() !=null && !newEntry.getUsername().equals("") ? newEntry.getUsername() : entry.getUsername());
            entry.setPassword(newEntry.getPassword() != null && !newEntry.getPassword().equals("") ? newEntry.getPassword() : entry.getPassword());
            userRepository.save(entry);
        }
        return entry;
    }



}

// controller -> service -> repository
//     private static final Logger logger = LoggerFactory.getLogger(UserService.class);
//  logger.error("Error occurred for {} :",user.getUsername(),e);
