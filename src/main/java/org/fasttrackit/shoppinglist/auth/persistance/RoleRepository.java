package org.fasttrackit.shoppinglist.auth.persistance;

import org.fasttrackit.shoppinglist.auth.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
