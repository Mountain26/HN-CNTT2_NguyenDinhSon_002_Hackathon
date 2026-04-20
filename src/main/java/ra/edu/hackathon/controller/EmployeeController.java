package ra.edu.hackathon.controller;

import jakarta.servlet.ServletContext;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ra.edu.hackathon.model.dto.EmployeeRequest;
import ra.edu.hackathon.model.entity.Employee;
import ra.edu.hackathon.service.EmployeeService;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ServletContext servletContext;

    @GetMapping
    public String list(Model model, @RequestParam(value = "keyword", required = false) String keyword) {
        List<Employee> employees;
        if (keyword != null && !keyword.isEmpty()) {
            employees = employeeService.search(keyword);
            model.addAttribute("keyword", keyword);
        } else {
            employees = employeeService.findAll();
        }
        model.addAttribute("employees", employees);
        return "employee/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("employeeReq", new EmployeeRequest());
        return "employee/form";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") String id, Model model) {
        Employee employee = employeeService.findById(id);
        if (employee != null) {
            EmployeeRequest req = new EmployeeRequest();
            req.setId(employee.getId());
            req.setFullName(employee.getFullName());
            req.setPosition(employee.getPosition());
            req.setSalary(employee.getSalary());
            req.setExistingAvatar(employee.getAvatar());
            model.addAttribute("employeeReq", req);
            return "employee/form";
        }
        return "redirect:/employee";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("employeeReq") EmployeeRequest req, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "employee/form";
        }

        Employee employee = new Employee();
        employee.setId(req.getId());
        employee.setFullName(req.getFullName());
        employee.setPosition(req.getPosition());
        employee.setSalary(req.getSalary());

        MultipartFile file = req.getAvatarFile();
        if (file != null && !file.isEmpty()) {
            String uploadPath = "C:/Users/OS/Documents/BTVN Java_Application/HN-CNTT2_NguyenDinhSon_002/uploads";
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            try {
                file.transferTo(new File(uploadPath + File.separator + fileName));
                employee.setAvatar(fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            employee.setAvatar(req.getExistingAvatar());
        }

        employeeService.save(employee);
        return "redirect:/employee";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") String id) {
        employeeService.delete(id);
        return "redirect:/employee";
    }
}

