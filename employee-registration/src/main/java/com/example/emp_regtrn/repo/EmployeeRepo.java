package com.example.emp_regtrn.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.emp_regtrn.entity.Employee;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee,Integer > {

	boolean existsByEmail(String email);
	

    boolean existsByEmailAndIdNot(String email, Integer id);

}


