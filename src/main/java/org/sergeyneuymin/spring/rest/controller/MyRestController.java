package org.sergeyneuymin.spring.rest.controller;

import org.sergeyneuymin.spring.rest.entity.Employee;
import org.sergeyneuymin.spring.rest.exception_handling.EmployeeIncorrectData;
import org.sergeyneuymin.spring.rest.exception_handling.NoSuchEmployeeException;
import org.sergeyneuymin.spring.rest.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MyRestController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees")
    public List<Employee> showAllEmployees() {
        List<Employee> allEmployees = employeeService.getAllEmployees();
        return allEmployees;
    }

    @GetMapping("/employees/{id}")
    public Employee getEmployee(@PathVariable int id) {
        Employee employee = employeeService.getEmployee(id);

        if (employee == null) {
            throw new NoSuchEmployeeException(
                "There is no employee with id " + id);
    }

        return employee;
    }

    @PostMapping("/employees")
    public Employee addNewEmployee(@RequestBody Employee employee) {
        employeeService.saveEmployee(employee);
        return employee;
    }

    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee employee) {
        employeeService.saveEmployee(employee);
        return employee;
    }

    @DeleteMapping("/employees/{id}")
    public String deleteEmployee(@PathVariable int id) {

        Employee employee = employeeService.getEmployee(id);

        if(employee==null) {
            throw new NoSuchEmployeeException("There is no employee with id=" + id);
        }

        employeeService.deleteEmployee(id);
        return "Employee with id " + id + " was deleted";
    }

}
