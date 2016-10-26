package com.alex.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alex on 26.10.2016.
 */
@Component
public class DBComponent {
    @Autowired
    UserRepository repository;

    public List<User> getAllUsers() {
        Iterable<User> users = repository.findAll();
        ArrayList<User> list = new ArrayList<User>();
        for (User user : users)
            list.add(user);
        return list;
    }

    public User getUser(String name) {
        List<User> list = repository.findByName(name);
        if (list.size() == 0)
            return null;
        return list.get(0);
    }

    public void createsUser(String name) {
        repository.save(new User(name));
    }

    public void deleteUser(String name) {
        repository.delete(getUser(name));
    }
}
