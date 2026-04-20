package ra.edu.hackathon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.edu.hackathon.model.entity.Employee;
import ra.edu.hackathon.repository.EmployeeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public List<Employee> search(String keyword) {
        return employeeRepository.search(keyword);
    }

    public Employee findById(String id) {
        Optional<Employee> opt = employeeRepository.findById(id);
        return opt.orElse(null);
    }

    public void save(Employee employee) {
        employeeRepository.save(employee);
    }

    public void delete(String id) {
        employeeRepository.delete(id);
    }
}

