package com.examples.spring.boot.docker.controller;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

	private static final Logger logger = LoggerFactory.getLogger(HelloWorldController.class);

	@RequestMapping(value = "/", method = RequestMethod.GET) // Handler Mapping
	public String home(Locale locale, Model model) {
		return "Hello World Docker Example :)";
	}

}
