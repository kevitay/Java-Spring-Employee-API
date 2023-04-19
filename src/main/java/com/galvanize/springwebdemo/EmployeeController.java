package com.galvanize.springwebdemo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/search")
    public List<Employee> findEmployeesByFirstAndLast(@RequestParam String firstName,
                                                      @RequestParam String lastName){
        List<Employee> searchResults = new ArrayList<>();
        for(Employee emp : employees) {
            if(emp.getFirstName().equals(firstName) && emp.getLastName().equals(lastName)) {
                searchResults.add(emp);
            }
        }
        return searchResults;
    }

//    @PostMapping("")
//    @ResponseStatus(HttpStatus.CREATED)
//    public Employee addEmployee(@RequestBody Employee newEmployee) {
//        employees.add(newEmployee);
//        return newEmployee;
//    }

    @PostMapping("")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee newEmployee) {
        employees.add(newEmployee);
        return new ResponseEntity<Employee>(newEmployee, HttpStatus.CREATED);
    }

//    @PostMapping("/add")
//    @ResponseStatus(HttpStatus.CREATED)
//    public ResponseEntity<Employee> addEmployee(@RequestBody Employee newEmployee) {
//        employees.add(newEmployee);
//        return ResponseEntity.status(HttpStatus.CREATED).body(newEmployee);
//    }

    @PutMapping("{id}")
    public Employee putEmployee(@PathVariable Integer id, @RequestBody Employee updatedEmployee) {
        Employee employee = null;
        for (Employee emp : employees) {
            if (emp.getId() == id) {
                emp.setFirstName(updatedEmployee.getFirstName());
                emp.setLastName(updatedEmployee.getLastName());
                employee = emp;
            }
        }
        return employee;
    }

    @PatchMapping("/{id}")
    public Employee patchEmployee(@PathVariable Integer id, @RequestBody Employee partialEmployee) {
        Employee employee = null;
        for (Employee emp : employees) {
            if (emp.getId() == id) {
                if(Objects.nonNull(partialEmployee.getFirstName())) {
                    emp.setFirstName(partialEmployee.getFirstName());
                }
                if(Objects.nonNull(partialEmployee.getLastName())) {
                    emp.setLastName(partialEmployee.getLastName());
                }
                employee = emp;
            }
        }
        return employee;
    }

}
