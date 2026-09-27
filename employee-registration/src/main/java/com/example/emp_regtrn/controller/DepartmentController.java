package com.example.emp_regtrn.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.emp_regtrn.entity.Department;
import com.example.emp_regtrn.service.DepartmentService;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {
	
	 private final DepartmentService departmentService;

	    public DepartmentController(DepartmentService departmentService) {
	        this.departmentService = departmentService;
	    }

	  
	    @PostMapping
	    public ResponseEntity<Department> addDepartment(
	            @RequestBody Department department) {

	        Department savedDepartment =
	                departmentService.addDepartment(department);

	        return ResponseEntity
	                .status(HttpStatus.CREATED)
	                .body(savedDepartment);
	    }

	    
	    
	    // Get All Departments
	    @GetMapping
	    public ResponseEntity<List<Department>> getAllDepartments() {

	        return ResponseEntity.ok(
	                departmentService.getAllDepartments()
	        );
	    }

//	    // Get Department by ID
//	    @GetMapping("/{id}")
//	    public ResponseEntity<Department> getDepartmentById(
//	            @PathVariable Integer id) {
//
//	        return ResponseEntity.ok(
//	                departmentService.getDepartmentById(id)
//	        );
//	    }

	    // Delete Department
	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> deleteDepartment(
	            @PathVariable Integer id) {

	        departmentService.deleteDepartment(id);

	        return ResponseEntity.noContent().build();
	    }
}
