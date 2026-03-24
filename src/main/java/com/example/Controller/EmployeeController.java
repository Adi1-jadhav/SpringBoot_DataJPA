package com.example.controller;

import com.example.entity.Employee;
import com.example.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    // Create a new employee
    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeService.saveEmployee(employee);
    }

    // Get all employees
    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    // Get employee by ID
    @GetMapping("/{id}")
    public Optional<Employee> getEmployeeById(@PathVariable Long id) {
        return employeeService.getEmployeeById(id);
    }

    // Update an employee
    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails) {
        return employeeService.getEmployeeById(id).map(employee -> {
            employee.setName(employeeDetails.getName());
            employee.setDepartment(employeeDetails.getDepartment());
            employee.setSalary(employeeDetails.getSalary());
            return employeeService.saveEmployee(employee);
        }).orElseThrow(() -> new RuntimeException("Employee not found with id " + id));
    }

    // Delete an employee
    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return "Employee with ID: " + id + " deleted successfully.";
    }

    // Get by Department
    @GetMapping("/department/{department}")
    public List<Employee> getByDepartment(@PathVariable String department) {
        return employeeService.getEmployeesByDepartment(department);
    }

    // Get by Name
    @GetMapping("/name/{name}")
    public List<Employee> getByName(@PathVariable String name) {
        return employeeService.getEmployeesByName(name);
    }

    // Get by Name containing
    @GetMapping("/name/contains/{namePart}")
    public List<Employee> getByNameContaining(@PathVariable String namePart) {
        return employeeService.getEmployeesByNameContaining(namePart);
    }

    // Get by Salary greater than
    @GetMapping("/salary/greater/{salary}")
    public List<Employee> getBySalaryGreaterThan(@PathVariable double salary) {
        return employeeService.getEmployeesBySalaryGreaterThan(salary);
    }

    // Get by Department and Salary
    @GetMapping("/filter")
    public List<Employee> getByDepartmentAndSalary(@RequestParam String department, @RequestParam double salary) {
        return employeeService.getEmployeesByDeptAndSalary(department, salary);
    }

    // Get by Department using JPQL
    @GetMapping("/jpql/department/{department}")
    public List<Employee> getByDeptJpql(@PathVariable String department) {
        return employeeService.getEmployeeByDeptJpql(department);
    }

    // Get Low Salary Employee using Native Query
    @GetMapping("/native/salary/low/{salary}")
    public List<Employee> getLowSalaryNative(@PathVariable double salary) {
        return employeeService.getLowSalaryEmployeeNative(salary);
    }
}
