package com.springdemo.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository repo;

    public List<User> ListAll() {
        return (List<User> ) repo.findAll();
    }

    public void save(User user) {
       repo.save(user);
    }
}
