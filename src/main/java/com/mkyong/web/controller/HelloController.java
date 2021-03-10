package com.mkyong.web.controller;

import com.mkyong.web.domain.EmployeeEntity;
import com.mkyong.web.exception.NameNotFoundException;
import com.mkyong.web.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {

	private EmployeeRepository employeeRepository;

	@Autowired
	public void setEmployeeRepository(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {

		model.addAttribute("message", "Spring 3 MVC Hello World");
		return "hello";

	}

	@RequestMapping(value = "/hello/{name:.+}", method = RequestMethod.GET)
	public ModelAndView hello(@PathVariable("name") String name) {

		ModelAndView model = new ModelAndView();
		model.setViewName("hello");
		model.addObject("msg", name);

		return model;

	}

	@GetMapping("/hi/{name:.+}")
	public ModelAndView hi(@PathVariable String name) {
		throw new NameNotFoundException(name);
	}

	@GetMapping("/employee/{name:.+}")
	public ModelAndView employee(@PathVariable String name) {
		ModelAndView model = new ModelAndView();
		model.setViewName("hello");
		model.addObject("msg", employeeRepository.save(new EmployeeEntity(name)).getName());

		return model;
	}

}