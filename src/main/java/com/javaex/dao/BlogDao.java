package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BlogVo;

@Repository
public class BlogDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public void blogUserInsert(BlogVo blogVo) {
		System.out.println("[BlogDao blogUserInsert]");
		
		sqlSession.insert("blog.blogUserInsert", blogVo);
	}
	
	//내 블로그 정보 가져오기
	public BlogVo getBlog(String id) {
		System.out.println("[BlogDao getBlog]");
		
		return sqlSession.selectOne("blog.getBlog", id);
	}
	
	//파일 업데이트
	public int fileUpdate(BlogVo blogVo) {
		System.out.println("[BlogDao fileUpdate]");
		
		return sqlSession.insert("blog.fileUpdate", blogVo);
	}
	
	
}
