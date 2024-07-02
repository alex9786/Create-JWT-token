package com.example.jwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.jwt.entity.Staff;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Integer> {



}
