package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>
{
	// Derived query method
	List<Employee> findByDepartment(String department);
	
	// Derived query method
	List<Employee> findByName(String name);
	
	// Pattern matching  (operator LIKE in SQL)
	List<Employee> findByNameContaining(String name);
	
	// Operators
	List<Employee> findBySalaryGreaterThan(double salary);
	
	// Multiple parameters
	List<Employee> findByDepartmentAndSalary(String department, double salary);
	
	// JPQL -> like HQL -> OOP version of SQL 
	// :dept is named parameter
	@Query("SELECT e FROM Employee e WHERE e.department = :dept")
	List<Employee> getEmployeeByDept(@Param("dept") String dept);
	
	// Native SQL -> execute SQL query
	@Query(value = "select * from CG_employee where salary < :salary", nativeQuery = true)
	List<Employee> getLowSalaryEmployee(@Param("salary") double salary);
	
}
