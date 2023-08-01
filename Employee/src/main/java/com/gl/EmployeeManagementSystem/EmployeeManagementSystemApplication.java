package com.gl.EmployeeManagementSystem;

import com.gl.EmployeeManagementSystem.model.Role;
import com.gl.EmployeeManagementSystem.repository.RoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.UUID;

@SpringBootApplication
@EnableEurekaClient
public class EmployeeManagementSystemApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeManagementSystemApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Autowired
	private RoleRepository repository;

	@Value("${admin.role.id}")
	private String role_admin_id;
	@Value("${normal.role.id}")
	private String role_normal_id;


	@Override
	public void run(String... args) throws Exception {
		try {
//			role_admin = Role.builder().roleId(UUID.randomUUID().toString()).roleName("ROLE_ADMIN").build();
			Role role_admin = Role.builder().roleId(role_admin_id).roleName("ROLE_ADMIN").build();
			Role role_normal = Role.builder().roleId(role_normal_id).roleName("ROLE_NORMAL").build();
			repository.save(role_admin);
			repository.save(role_normal);
		}catch (Exception e){
			e.printStackTrace();
		}
	}

//	@Autowired
//	PasswordEncoder passwordEncoder;

//	@Override
//	public void run(String... args) throws Exception {
//		System.out.println(passwordEncoder.encode("abcd"));
//
//	}
}
