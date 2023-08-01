package com.globallogic.feignProxy;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.globallogic.dto.GloQuoraPost;

//if service discovery not used
// @FeignClient(name = "GloQuora", url = "http://localhost:9002)

@FeignClient(name = "GloQuoraPost-Service")
public interface FeignGloQuoraProxy {

	@GetMapping("v1/api/posts")
	List<GloQuoraPost> getAllPost();

}
