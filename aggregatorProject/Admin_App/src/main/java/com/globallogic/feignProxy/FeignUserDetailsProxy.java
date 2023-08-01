package com.globallogic.feignProxy;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.globallogic.dto.UserDetails;

@FeignClient(name = "User-Service")
public interface FeignUserDetailsProxy {

	@GetMapping("v1/api/user/{userId}")
	UserDetails getUserDetails(@PathVariable int userId);

	@GetMapping("v1/api/users")
	List<UserDetails> getAllUser();

}
