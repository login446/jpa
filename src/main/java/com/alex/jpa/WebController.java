package com.alex.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by alex on 26.10.2016.
 */
@RestController
public class WebController {
    @Autowired
    DBComponent db;

    @RequestMapping("/user/all")
    public List<User> getAllUsers() {
        return db.getAllUsers();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/user", method = RequestMethod.PUT)
    public User createsUser(@RequestParam String name) {
        if (name.isEmpty())
            throw new BadRequestException();
        if (db.getUser(name) != null)
            throw new ConflictException();
        db.createsUser(name);
        return db.getUser(name);
    }

    @RequestMapping(value = "/user", method = RequestMethod.DELETE)
    public void deleteUser(@RequestParam String name) {
        if (name.isEmpty())
            throw new BadRequestException();
        if (db.getUser(name) == null)
            throw new NotFoundException();
        db.deleteUser(name);
    }

    @RequestMapping(value = "/user/name", method = RequestMethod.PATCH)
    public void renameUser(@RequestParam String name, @RequestParam String newName) {

    }

    @RequestMapping(value = "/user/score", method = RequestMethod.PATCH)
    public void reScoreUser(@RequestParam String name, @RequestParam String score) {

    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private class BadRequestException extends RuntimeException {
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    private class ConflictException extends RuntimeException {
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    private class NotFoundException extends RuntimeException {
    }
}
