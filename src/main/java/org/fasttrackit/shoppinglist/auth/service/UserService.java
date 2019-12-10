package org.fasttrackit.shoppinglist.auth.service;

import org.fasttrackit.shoppinglist.auth.model.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}
