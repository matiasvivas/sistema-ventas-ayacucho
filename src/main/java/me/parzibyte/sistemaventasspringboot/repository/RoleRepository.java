package me.parzibyte.sistemaventasspringboot.repository;

import me.parzibyte.sistemaventasspringboot.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByRole(String role);

}
