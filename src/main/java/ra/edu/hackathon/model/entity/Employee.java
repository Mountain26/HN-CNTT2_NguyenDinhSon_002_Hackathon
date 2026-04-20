package ra.edu.hackathon.model.entity;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Employee {

    private String id;

    @NotBlank(message = "Tên không được để trống")
    @Size(min = 5, max = 50, message = "Tên phải từ 5 đến 50 ký tự")
    private String fullName;

    @NotBlank(message = "Chức vụ không được để trống")
    private String position;

    @NotNull(message = "Lương không được để trống")
    @DecimalMin(value = "0.0", inclusive = false, message = "Lương phải lớn hơn 0")
    private Double salary;

    private String avatar;

    public Employee() {
    }

    public Employee(String id, String fullName, String position, Double salary, String avatar) {
        this.id = id;
        this.fullName = fullName;
        this.position = position;
        this.salary = salary;
        this.avatar = avatar;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}

