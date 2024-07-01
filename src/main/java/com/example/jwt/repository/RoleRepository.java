

package com.example.jwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.jwt.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

	Role findByUserNameAndPassword(String userName, String password);

}
