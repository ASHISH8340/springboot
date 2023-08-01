package com.globallogic.service;

import java.util.List;

import com.globallogic.dto.GloQuoraPost;
import com.globallogic.dto.UserDetails;

public interface AdminService {

	public List<GloQuoraPost> getPostByUserId(long userId);

	public List<UserDetails> allUserWithPost();

	public List<String> getAllNamesWithPostMoreThanK(int count);

	public List<String> getAllCompanyName();

}
