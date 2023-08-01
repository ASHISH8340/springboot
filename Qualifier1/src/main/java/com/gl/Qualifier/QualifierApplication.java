package com.gl.Qualifier;

import dog.Dog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class QualifierApplication implements CommandLineRunner {
	@Qualifier("student1")
	@Autowired
	private Student student;
	@Autowired
	private Employee employee;
	@Autowired
	private Dog dog;

	public QualifierApplication() {
	}

	public static void main(String[] args) {
		SpringApplication.run(QualifierApplication.class, args);
	}

	public void run(String... args) throws Exception {
		this.student.studying();
		this.employee.employeeName();
		this.dog.eatingFood();
	}
}
