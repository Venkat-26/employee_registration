package com.example.emp_regtrn.controller;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.emp_regtrn.dto.EmployeeRequest;
import com.example.emp_regtrn.entity.Employee;
import com.example.emp_regtrn.service.EmployeeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

	private final EmployeeService employeeService;

	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@PostMapping
	public ResponseEntity<String> addEmployee(@Valid @RequestBody EmployeeRequest empRequest) {

		Employee employee = employeeService.addEmployee(empRequest);

		return ResponseEntity.status(HttpStatus.CREATED).body("Employee registered successfully");
	}

	@GetMapping
	public ResponseEntity<Page<Employee>> getEmployees(@PageableDefault(size = 10) Pageable pageable) {

		return ResponseEntity.ok(employeeService.getEmployees(pageable));
	}

	@PutMapping("/{id}")
	public ResponseEntity<String> updateEmployee(@PathVariable Integer id,
			@Valid @RequestBody EmployeeRequest empRequest) {

		employeeService.updateEmployee(id, empRequest);

		return ResponseEntity.ok("Employee updated successfully");
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable Integer id) {

		employeeService.deleteEmployee(id);

		return ResponseEntity.ok("Employee deleted successfully");
	}

}