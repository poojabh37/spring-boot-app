package io.javabrains.springbootstarter.employees;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employees")
public class EmployeeRestController {

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping("/{employeeId}")
    @PutMapping(consumes = "application/json",
            produces = "application/json")
    public Employee updateEmployee(@RequestBody Employee employee, @PathVariable int employeeId) {
        return employeeService.update(employeeId, employee);
    }
}