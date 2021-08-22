package lk.hotelManagement.backend.controller;
import lk.hotelManagement.backend.model.Employee;
import lk.hotelManagement.backend.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;


    @GetMapping("/")
    public List<Employee> employees() {
        return employeeService.employees();
    }

    @PostMapping("/")
    public boolean createEmployee(@RequestBody Employee employee) {
        return employeeService.createEmployee(employee);
    }

    @GetMapping("/employee")
    public Employee employeeById(@RequestParam String id) {
        return employeeService.employeeById(id);
    }
    @PutMapping("/{id}")
    public boolean editUser(@PathVariable String id,@RequestBody Employee employee) {
        return employeeService.editEmployee(id, employee);
    }
    @DeleteMapping("/{employeeId}")
    public boolean deleteUser(@PathVariable String employeeId) {
        return employeeService.deleteEmployee(employeeId);
    }
}