package io.javabrains.springbootstarter.student;

import javax.validation.constraints.*;
import java.util.Date;

public class Student {

    @NotBlank
    @Size(min = 2, message = "Name should have at least 2 characters")
    private String name;

    @Min(5)
    @Max(100)
    private Integer age;

    @Future
    private Date birthDate;

    @Email
    private String email;

    public Student() {
        //always have a default constructor if having parameterized constructor
    }

    public Student(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
}
