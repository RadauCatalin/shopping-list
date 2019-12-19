package org.fasttrackit.shoppinglist.service;

import org.fasttrackit.shoppinglist.domain.User;
import org.fasttrackit.shoppinglist.persistance.UserRepository;
import org.fasttrackit.shoppinglist.transfer.UserRequests.LoginRequest;
import org.fasttrackit.shoppinglist.transfer.UserRequests.UserRegistrationRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


public class UserService {

    public static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User saveUser(UserRegistrationRequest request) {
        LOGGER.info("Creating new user {}", request);
        User user = new User();
        user.setUserName(request.getUserName());
        user.setFirst_name(request.getFirstName());
        user.setLast_name(request.getLastName());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        return userRepository.save(user);
    }

    public void login(LoginRequest request) {
        LOGGER.info("User try to logging in", request);
        User user = userRepository.findByUserName(request.getUserName());
        if (request.getPassword().equals(user.getPassword())) {
            System.out.println("acces ok");
        } else {
            System.out.println("User name or password does not match");
        }

    }
}
