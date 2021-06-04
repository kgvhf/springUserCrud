package com.mi.crud.services;

import com.mi.crud.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User createUser(User userDetails);
    List<User> getAllUsers();
    Optional<User> getUser(String name);
    Optional<User> deleteUser(String name);
    User updateUser(User userDetails, String name);
}
