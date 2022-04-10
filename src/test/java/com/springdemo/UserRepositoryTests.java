package com.springdemo;

import com.springdemo.User.User;
import com.springdemo.User.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class UserRepositoryTests {
    @Autowired
    private UserRepository repo;

    @Test
    public void testAddnew()
    {
        User user = new User();
        user.setEmail("nisa@gmail.com");
        user.setPassword("password");
        user.setName("Nisa");

        User savedUser = repo.save(user);
        Assertions.assertNotNull(savedUser);
        Assertions.assertFalse(savedUser.getId().equals(0));

    }

    @Test
    public void testListAll()
    {
      Iterable<User> users = repo.findAll();

      for(User user: users)
      {
          System.out.println(user);
      }
    }

    @Test
    public void testUpdate(){
        Integer userId= 1;
        Optional<User> optionalUser = repo.findById(userId);
        User user = optionalUser.get();
        user.setPassword("hello");
        repo.save(user);

        User updatedUser= repo.findById(userId).get();
        Assertions.assertEquals(updatedUser.getPassword(),"hello");
    }

    @Test
    public void testGet(){
        Integer userId= 1;
        Optional<User> optionalUser = repo.findById(userId);
        Assertions.assertNotNull(optionalUser);
    }

    @Test
    public void testDelete(){
        Integer userId= 1;
        repo.deleteById(userId);
        Optional<User> optionalUser = repo.findById(userId);
        Assertions.assertNull(optionalUser);
    }
}
