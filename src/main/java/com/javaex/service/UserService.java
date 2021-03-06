package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BlogDao;
import com.javaex.dao.UserDao;
import com.javaex.vo.BlogVo;
import com.javaex.vo.UserVo;

@Service
public class UserService {
	@Autowired
	private UserDao userDao;
	
	 @Autowired
	 private BlogDao blogDao;
	
	//회원가입
	public int join(UserVo userVo) {
		System.out.println("[UserService join()]");
		
		int count = userDao.insert(userVo);
		
		String id = userVo.getId();
		String blogTitle = userVo.getUserName() +"의 블로그 입니다.";
		String logoFile = "spring-logo.jpg";
		
		BlogVo blogVo = new BlogVo(id, blogTitle, logoFile);
		
		blogDao.blogUserInsert(blogVo);
		
		return count;
	}
	
	//로그인
	public UserVo login(UserVo userVo) {
		System.out.println("[UserService join()]");
		
		return userDao.selectUser(userVo);
	}
	
}
