package com.example.service;

import com.example.entity.Employee;
import com.example.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    // Create or Update
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    // Read all
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    // Read by id
    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    // Delete by id
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    // Custom finder methods from Repository
    public List<Employee> getEmployeesByDepartment(String department) {
        return employeeRepository.findByDepartment(department);
    }

    public List<Employee> getEmployeesByName(String name) {
        return employeeRepository.findByName(name);
    }

    public List<Employee> getEmployeesByNameContaining(String namePart) {
        return employeeRepository.findByNameContaining(namePart);
    }

    public List<Employee> getEmployeesBySalaryGreaterThan(double salary) {
        return employeeRepository.findBySalaryGreaterThan(salary);
    }

    public List<Employee> getEmployeesByDeptAndSalary(String department, double salary) {
        return employeeRepository.findByDepartmentAndSalary(department, salary);
    }

    public List<Employee> getEmployeeByDeptJpql(String department) {
        return employeeRepository.getEmployeeByDept(department);
    }

    public List<Employee> getLowSalaryEmployeeNative(double salary) {
        return employeeRepository.getLowSalaryEmployee(salary);
    }
}
