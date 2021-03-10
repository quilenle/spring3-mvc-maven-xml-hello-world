package com.mkyong.web.controller;

import com.mkyong.web.domain.EmployeeEntity;
import com.mkyong.web.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeResourceImpl {

    private EmployeeRepository employeeRepository;

    @Autowired
    public void setEmployeeRepository(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @PostMapping("/{name:.+}")
    public EmployeeEntity createEmployee(@PathVariable String name) {
        return employeeRepository.save(new EmployeeEntity(name));
    }
}
