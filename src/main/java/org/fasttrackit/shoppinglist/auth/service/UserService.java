package org.fasttrackit.shoppinglist.auth.service;

import org.fasttrackit.shoppinglist.auth.model.User;
import org.fasttrackit.shoppinglist.auth.transfer.UserRegistrationDto;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface UserService extends UserDetailsService {

    User findByEmail(String email);

    User save(UserRegistrationDto registration);
}
