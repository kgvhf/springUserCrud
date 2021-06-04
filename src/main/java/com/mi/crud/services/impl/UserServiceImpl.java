package com.mi.crud.services.impl;

import com.mi.crud.entities.User;
import com.mi.crud.exception.BadRequestException;
import com.mi.crud.repositories.UserRepository;
import com.mi.crud.services.UserService;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(User userDetails) {
        if (userRepository.existsByName(userDetails.getName())) {
            throw new BadRequestException("Username " + userDetails.getName() + " is already taken");
        }

        User user = new User(
                userDetails.getName(),
                userDetails.getPassportNumber(),
                userDetails.getAge(),
                userDetails.getMale(),
                userDetails.getComment()
        );
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUser(String name) {
        return userRepository.findByName(name);
    }

    @Override
    public Optional<User> deleteUser(String name) {
        Optional<User> fetchedUser = userRepository.findByName(name);
        fetchedUser.ifPresent(user -> userRepository.deleteById(user.getId()));
        return fetchedUser;
    }

    @Override
    public User updateUser(User userDetails, String name) {
        User user = userRepository.findByName(name)
                .orElseThrow(() -> new BadRequestException("User with name " + name + " does not exists"));
        user.setPassportNumber(userDetails.getPassportNumber())
                .setAge(userDetails.getAge())
                .setComment(userDetails.getComment())
                .setMale(userDetails.getMale());

        return userRepository.save(user);
    }
}
