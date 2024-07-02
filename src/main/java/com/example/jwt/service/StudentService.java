package com.example.jwt.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.jwt.entity.Staff;
import com.example.jwt.entity.Student;
import com.example.jwt.repository.StaffRepository;
import com.example.jwt.repository.StudentRepository;

@Service
public class StudentService {

	@Autowired
	StudentRepository studentRepo;
	
	@Autowired
	StaffRepository staffRepo;
	
	public Student post(Student student) {
		 studentRepo.save(student);
		 Staff staff = new Staff();
		 staff.setId(student.getId());
		 staff.setName(student.getName());
		 staff.setDepartment(student.getDepartment());
		 staff.setContact(student.getContact());
		   staffRepo.save(staff);
		   return student;
	}


}
