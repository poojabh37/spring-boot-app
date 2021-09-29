package io.javabrains.springbootstarter.employees;

import io.javabrains.springbootstarter.employees.Employee;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class EmployeeService {

    private static final Map<Integer, Employee> idToEmployees = new HashMap<>();

    static {
        idToEmployees.put(1, new Employee(1, "pooja", "backend"));
        idToEmployees.put(2, new Employee(2, "siddharth", "front-end"));
        idToEmployees.put(3, new Employee(3, "sazia", "optymyze"));
    }

    public Employee find(int id) {
        return idToEmployees.get(id);
    }

    public Employee save(Employee employee) {
        if (employee.getId() == null) {
            employee.setId(idToEmployees.size() + 1);
        }
        idToEmployees.put(employee.getId(), employee);
        return employee;
    }

    public Employee update(int id, Employee updated) {
        Employee emp = find(id);
        if (updated.getName() != null && !updated.getName().isEmpty()) {
            emp.setName(updated.getName());
        }
        if (updated.getDepartment() != null && !updated.getDepartment().isEmpty()) {
            emp.setDepartment(updated.getDepartment());
        }
        return emp;
    }


}
