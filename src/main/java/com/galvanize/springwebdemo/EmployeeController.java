package com.galvanize.springwebdemo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
    List<Employee> employees = new ArrayList<>();

    public EmployeeController() {
        employees.add(new Employee(1, "First", "Employee"));
        employees.add(new Employee(2, "Second", "Employee"));
        employees.add(new Employee(3, "Third", "Employee"));
        employees.add(new Employee(4, "Fourth", "Employee"));
        employees.add(new Employee(5, "Fifth", "Employee"));
    }

    @GetMapping("/{id}")
    public Employee findEmployeeById(@PathVariable int id) {
        Employee employee = null;
        for (Employee emp : employees) {
            if (emp.getId() == id) {
                employee = emp;
            }
        }
        return employee;
    }

}
