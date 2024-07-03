package com.example.jwt.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jwt.dto.CollegeDto;
import com.example.jwt.entity.College;
import com.example.jwt.entity.Student;
import com.example.jwt.service.StudentService;

@RestController
@RequestMapping("/student")
public class StudentController {
	
	@Autowired
	StudentService studentService;
	
	@PostMapping("/post")
	public Student post(@RequestBody Student student) {
		return studentService.post(student);
	}
	
	
	@PostMapping("/postCollege")
	public College postCollege(@RequestBody College college) {
		return studentService.postCollege(college);
	}
	
	@GetMapping("/getMultiple/{name}")
	public Optional<CollegeDto> get(@PathVariable String name) {
		return studentService.get(name);
	}
	
	
}
