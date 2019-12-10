package org.fasttrackit.shoppinglist.auth.persistance;

import org.fasttrackit.shoppinglist.auth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
