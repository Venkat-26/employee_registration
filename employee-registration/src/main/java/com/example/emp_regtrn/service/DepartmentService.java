package com.example.emp_regtrn.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.emp_regtrn.entity.Department;
import com.example.emp_regtrn.exception.DepartmentAlreadyExistsException;
import com.example.emp_regtrn.exception.DepartmentNotFoundException;
import com.example.emp_regtrn.repo.DepartmentRepo;

@Service
public class DepartmentService {

	private final DepartmentRepo departmentRepo;

	public DepartmentService(DepartmentRepo departmentRepo) {
		this.departmentRepo = departmentRepo;
	}

	// Add Department
	public Department addDepartment(Department department) {

		if (department.getName() == null || department.getName().trim().isEmpty()) {

			throw new IllegalArgumentException("Department name is required");
		}
		String name = department.getName().trim();
		System.out.println("vvv");
		department.setName(name);
		if (departmentRepo.existsByNameIgnoreCase(name)) {
			throw new DepartmentAlreadyExistsException("Department already exists");
		}

		return departmentRepo.save(department);
	}
	
	 // Get All Departments
    public List<Department> getAllDepartments() {

        return departmentRepo.findAll();
    }
//
//    // Get Department By ID
//    public Department getDepartmentById(Integer id) {
//
//        return departmentRepo.findById(id)
//                .orElseThrow(() ->
//                        new DepartmentNotFoundException(
//                                "Department not found"
//                        )
//                );
//    }
    
    // Delete Department
    public void deleteDepartment(Integer id) {

        Department department = departmentRepo.findById(id)
                .orElseThrow(() ->
                        new DepartmentNotFoundException(
                                "Department not found"
                        )
                );

        departmentRepo.delete(department);
    }

}
