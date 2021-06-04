package com.mi.crud.controllers;

import com.mi.crud.entities.User;
import com.mi.crud.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping(path = "api/users")
public class UserController {

    UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String homePage() {
        return "home page";
    }

    @RequestMapping(value = "all", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @RequestMapping(value = "{name}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Optional<User>> getUser(@PathVariable(value = "name") String name) {
        Optional<User> getUser = userService.getUser(name);
        return new ResponseEntity<>(getUser, HttpStatus.OK);
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<User> createUser(@Valid @RequestBody User userDetails) {
        User user = new User(
                userDetails.getName(),
                userDetails.getPassportNumber(),
                userDetails.getAge(),
                userDetails.getMale(),
                userDetails.getComment()
        );
        User savedUser = userService.createUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @RequestMapping(value = "{name}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<User> updateUser(@Valid @RequestBody User usedDetails, @PathVariable(value = "name") String name) {
        return new ResponseEntity<>(userService.updateUser(usedDetails, name), HttpStatus.OK);
    }

    @RequestMapping(value = "user/{name}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Optional<User>> deleteUser(@PathVariable(value = "name") String name) {
        Optional<User> getUser = userService.deleteUser(name);
        return new ResponseEntity<>(getUser, HttpStatus.OK);
    }
}
