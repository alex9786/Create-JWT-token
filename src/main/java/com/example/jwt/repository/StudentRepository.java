package com.example.jwt.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.jwt.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer>{
	
	
	
@Query(value="select * from staff as sf inner  join student as std"
		+ " on sf.id =  std.id "
		+ "where sf.name =:name ",nativeQuery = true)
	Optional<Student> findByName(String name);

}
