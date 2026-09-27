package com.example.emp_regtrn.service;


import java.time.LocalDate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.emp_regtrn.dto.EmployeeRequest;
import com.example.emp_regtrn.entity.Department;
import com.example.emp_regtrn.entity.Employee;
import com.example.emp_regtrn.exception.DepartmentNotFoundException;
import com.example.emp_regtrn.exception.EmailAlreadyExistsException;
import com.example.emp_regtrn.exception.EmployeeRecordNotFoundException;
import com.example.emp_regtrn.exception.InvalidEmployeeAgeException;
import com.example.emp_regtrn.repo.DepartmentRepo;
import com.example.emp_regtrn.repo.EmployeeRepo;

@Service
public class EmployeeService {

	private final EmployeeRepo employeeRepo;
	private final DepartmentRepo departmentRepo;

	public EmployeeService(EmployeeRepo employeeRepo, DepartmentRepo departmentRepo) {
		this.employeeRepo = employeeRepo;
		this.departmentRepo = departmentRepo;

	}

	public Employee addEmployee(EmployeeRequest empRequest) {

		if (employeeRepo.existsByEmail(empRequest.getEmail())) {
			throw new EmailAlreadyExistsException("This email is already registered");
		}

		LocalDate age = LocalDate.now().minusYears(18);

		if (empRequest.getDob().isAfter(age)) {
			throw new InvalidEmployeeAgeException("Employee must be at least 18 years old");
		}

//		Department department = departmentRepo.findById(empRequest.getDepartment().getId())
//				.orElseThrow(() -> new DepartmentNotFoundException("Department not found"));

		Employee employee = new Employee();

		employee.setFirstName(empRequest.getFirstName());
		employee.setLastName(empRequest.getLastName());
		employee.setEmail(empRequest.getEmail());
		employee.setDob(empRequest.getDob());
		employee.setAddress(empRequest.getAddress());
		employee.setSalary(empRequest.getSalary());

		Department getDepartData = departmentRepo.findByNameIgnoreCase(empRequest.getDepartment().getName())
				.orElseThrow(() -> new DepartmentNotFoundException("Department not found"));
		;

		employee.setDepartment(getDepartData);

		employee.setPincode(empRequest.getPincode());
		employee.setPostOffice(empRequest.getPostOffice());
		employee.setCity(empRequest.getCity());
		employee.setState(empRequest.getState());
		employee.setCountry(empRequest.getCountry());

		return employeeRepo.save(employee);
	}
	
	//get employees
	public Page<Employee> getEmployees(Pageable pageable) {
	    return employeeRepo.findAll(pageable);
	}
	
	

	// EDIT EMPLOYEE
	public Employee updateEmployee(Integer id, EmployeeRequest empRequest) {

		// 1. Find existing employee
		Employee employee = employeeRepo.findById(id)
				.orElseThrow(() -> new EmployeeRecordNotFoundException("Employee not found"));

		
		if (!employee.getEmail().equalsIgnoreCase(empRequest.getEmail())
				&& employeeRepo.existsByEmail(empRequest.getEmail())) {

			throw new EmailAlreadyExistsException("This email is already registered");
		}

	
		LocalDate minimumDob = LocalDate.now().minusYears(18);

		if (empRequest.getDob().isAfter(minimumDob)) {
			throw new InvalidEmployeeAgeException("Employee must be at least 18 years old");
		}

		// 4. Find department by name
		Department department = departmentRepo.findByNameIgnoreCase(empRequest.getDepartment().getName())
				.orElseThrow(() -> new DepartmentNotFoundException("Department not found"));

		// 5. Update employee fields
		employee.setFirstName(empRequest.getFirstName());
		employee.setLastName(empRequest.getLastName());
		employee.setEmail(empRequest.getEmail());
		employee.setDob(empRequest.getDob());
		employee.setAddress(empRequest.getAddress());
		employee.setSalary(empRequest.getSalary());
		employee.setDepartment(department);
		employee.setPincode(empRequest.getPincode());
		employee.setPostOffice(empRequest.getPostOffice());
		employee.setCity(empRequest.getCity());
		employee.setState(empRequest.getState());
		employee.setCountry(empRequest.getCountry());

		
		return employeeRepo.save(employee);
	}

	// delete employee
	public void deleteEmployee(Integer id) {

		Employee employee = employeeRepo.findById(id)
				.orElseThrow(() -> new EmployeeRecordNotFoundException("Employee not found"));

		employeeRepo.delete(employee);
	}

}
