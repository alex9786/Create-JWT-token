package com.example.jwt.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.jwt.dto.CollegeDto;
import com.example.jwt.entity.College;


public interface CollegeRepository extends JpaRepository<College, Integer> {

	
	@Query(value = "SELECT c.*, std.*, sf.* " +
            " FROM college c " +
            " INNER JOIN student std ON c.c_id = std.std_id " +
            " INNER JOIN staff sf ON std.std_id = sf.s_id " +
            " WHERE std.name = :name", nativeQuery = true)
	Optional<CollegeDto> findByName(String name);

}
