package lk.hotelManagement.backend.service;
import lk.hotelManagement.backend.model.Employee;
import lk.hotelManagement.backend.model.User;
import lk.hotelManagement.backend.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    public List<Employee> employees() {
        return employeeRepository.employees();
    }
    public boolean createEmployee( Employee employee) {
        return employeeRepository.createEmployee(employee);
    }

    public Employee employeeById(String id) {
        return employeeRepository.employeeById(id);
    }
    public boolean editEmployee( String id, Employee employee) {
        return employeeRepository.editEmployee(id,employee);
    }
    public boolean deleteEmployee(@PathVariable String userId) {
        return employeeRepository.deleteEmployee(userId);
    }
}

