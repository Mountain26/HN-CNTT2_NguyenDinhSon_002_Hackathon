package ra.edu.hackathon.repository;

import org.springframework.stereotype.Repository;
import ra.edu.hackathon.model.entity.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class EmployeeRepository {
    private final List<Employee> employees = new ArrayList<>();
    private int counter = 1;

    public List<Employee> findAll() {
        return new ArrayList<>(employees);
    }

    public List<Employee> search(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return findAll();
        }
        String lowerKeyword = keyword.toLowerCase();
        return employees.stream()
                .filter(e -> e.getFullName().toLowerCase().contains(lowerKeyword) ||
                             e.getPosition().toLowerCase().contains(lowerKeyword))
                .collect(Collectors.toList());
    }

    public Optional<Employee> findById(String id) {
        return employees.stream().filter(e -> e.getId().equals(id)).findFirst();
    }

    public void save(Employee employee) {
        if (employee.getId() == null || employee.getId().isEmpty()) {
            String newId = String.format("NV%03d", counter++);
            employee.setId(newId);
            employees.add(employee);
        } else {
            for (int i = 0; i < employees.size(); i++) {
                if (employees.get(i).getId().equals(employee.getId())) {
                    employees.set(i, employee);
                    return;
                }
            }
        }
    }

    public void delete(String id) {
        employees.removeIf(e -> e.getId().equals(id));
    }
}

