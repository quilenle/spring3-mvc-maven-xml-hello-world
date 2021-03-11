package com.mkyong.web.controller;

import com.mkyong.web.domain.EmployeeEntity;
import com.mkyong.web.exception.EmployeeNotFoundException;
import com.mkyong.web.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @GetMapping
    public List<EmployeeEntity> getAll() {
        return employeeRepository.findAll();
    }

    @GetMapping("/{id}")
    public EmployeeEntity getById(@PathVariable long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    @PutMapping("/{id}/{name:.+}")
    public EmployeeEntity updateEmployeeById(@PathVariable long id, @PathVariable String name) {
        EmployeeEntity employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));

        employee.setName(name);

        return employeeRepository.save(employee);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployeeById(@PathVariable long id) {
        employeeRepository.deleteById(id);
    }
}
