package com.alex.jpa;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by alex on 27.10.2016.
 */
public interface UserRepository extends CrudRepository<User, Integer> {
    List<User> findByName(String name);
}
