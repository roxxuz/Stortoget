package com.example.stortorget.service;

import com.example.stortorget.entity.User;
import com.example.stortorget.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void saveUser(User user){

        String hashedPass = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPass);

        userRepository.save(user);
    }

    public User getUserByUserName(String userName){

        return userRepository.findByUserName(userName);
    }

    public void deleteUser(User user){

        userRepository.delete(user);
    }

}
