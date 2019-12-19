package org.fasttrackit.shoppinglist.persistance;

import org.fasttrackit.shoppinglist.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "SELECT * FROM user WHERE userName LIKE '?'", nativeQuery = true)
    User findByUserName(String userName);
}
