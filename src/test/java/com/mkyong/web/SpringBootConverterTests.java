package com.mkyong.web;

import com.mkyong.web.domain.EmployeeEntity;
import com.mkyong.web.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class SpringBootConverterTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    public void hiShouldGetName() {
        final String EMPLOYEE_NAME = "employee-name";

        restTemplate.getForObject(
                "http://localhost:" + port + "/employee/" + EMPLOYEE_NAME, String.class);

        assertThat(employeeRepository.findById(1L).map(EmployeeEntity::getName).orElse(null))
                .contains(EMPLOYEE_NAME);
    }
}
