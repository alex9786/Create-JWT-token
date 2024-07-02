

package com.example.jwt.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.jwt.dto.AdminDto;
import com.example.jwt.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

	Role findByUserNameAndPassword(String userName, String password);

	@Query(value = "select * from role where user_name = :userName", nativeQuery = true)
	Optional<Role> findByName(String userName);

}
