package com.example.emp_regtrn.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.emp_regtrn.entity.Department;

@Repository
public interface DepartmentRepo extends JpaRepository<Department, Integer> {
	boolean existsByNameIgnoreCase(String name);
	Optional<Department> findByNameIgnoreCase(String name);

}
