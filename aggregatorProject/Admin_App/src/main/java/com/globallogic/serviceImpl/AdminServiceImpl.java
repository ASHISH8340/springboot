package com.globallogic.serviceImpl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.globallogic.dto.GloQuoraPost;
import com.globallogic.dto.UserDetails;
import com.globallogic.exception.PostNotFoundException;
import com.globallogic.feignProxy.FeignGloQuoraProxy;
import com.globallogic.feignProxy.FeignUserDetailsProxy;
import com.globallogic.service.AdminService;

@Service
@Slf4j
public class AdminServiceImpl implements AdminService {

	@Autowired
	private FeignGloQuoraProxy gloQuoraProxy;

	@Autowired
	private FeignUserDetailsProxy userDetailsProxy;

	@Override
	public List<GloQuoraPost> getPostByUserId(long userId) {
		List<GloQuoraPost> allPost = gloQuoraProxy.getAllPost();
		List<GloQuoraPost> postOfUser=new ArrayList<>();
        Iterator<GloQuoraPost> postIterator=allPost.listIterator();
        while (postIterator.hasNext())
        {
            GloQuoraPost quoraPost=postIterator.next();
            if(quoraPost.getUserId()==userId)
            {
				log.info("returning all the post for given user");
                postOfUser.add(quoraPost);
            }
        }
		if (!postOfUser.isEmpty()) {
			return postOfUser;
		} else {
			log.info("There is 0 post for this user id");
			throw new PostNotFoundException("SERVICE.POST.NOT.FOUND");
		}
	}

	@Override
	public List<UserDetails> allUserWithPost() {
		List<UserDetails> allUser = userDetailsProxy.getAllUser();
		Iterator<UserDetails> userIterator=allUser.listIterator();
        while (userIterator.hasNext())
        {
        	UserDetails user=userIterator.next();
            user.setQuorapost(getPostByUserId(user.getUserId()));
			log.info("returning all the post for given user with user details");
        }
        return allUser;
	}

	@Override
	public List<String> getAllNamesWithPostMoreThanK(int count) {
		List<UserDetails> allUserWithPost = allUserWithPost();
		List<String> name = new ArrayList<>();
		for (UserDetails userDetails : allUserWithPost) {
			if (userDetails.getQuorapost().size() > count) {
				name.add(userDetails.getName());
				log.info("returning all the name for given user with post more than k");
			}
		}
		return name;
	}

	@Override
	public List<String> getAllCompanyName() {
		List<UserDetails> allUserWithPost = allUserWithPost();
		List<String> companyName = new ArrayList<>();
		for (UserDetails userDetails : allUserWithPost) {
			if (!userDetails.getQuorapost().isEmpty()) {
				companyName.add(userDetails.getCompany().getName());
				log.info("returning all the company for post count greater than 0");
			}
		}
		return companyName;
	}

}
