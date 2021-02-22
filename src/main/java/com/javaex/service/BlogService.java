package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.dao.BlogDao;
import com.javaex.vo.BlogVo;

@Service
public class BlogService {
	@Autowired
	private BlogDao blogDao;
	
	//내 블로그 정보 가져오기
	public BlogVo getBlog(String id) {
		System.out.println("[BlogService getBlog] " + id);
		
		return blogDao.getBlog(id);
	}
	
	//파일 업데이트
	public void fileUpdate(BlogVo blogVo, MultipartFile file) {
		System.out.println("[BlogService fileUpdate]");
		
		//저장 경로
		String saveDir = "C:\\javaStudy\\workspace.web\\jblog\\webapp\\assets\\image\\upload";
		
		//확장자
		String exName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
		System.out.println("exName:" + exName);
		
		//서버 저장파일 이름
		String saveName = System.currentTimeMillis()+UUID.randomUUID().toString() + exName;
		System.out.println("saveName:  " + saveName);
		
		//서버 파일패스 --> 저장경로
		String filePath = saveDir + "\\" + saveName;
		System.out.println("filePath: " + filePath);
		
		blogVo.setLogoFile(saveName);
		
		
		
		//서버 하드디스크에 저장
				try {
					byte[] fileData = file.getBytes();
					OutputStream out = new FileOutputStream(filePath);
					BufferedOutputStream bos = new BufferedOutputStream(out);
					
					bos.write(fileData);
					bos.close();
					
				} catch (IOException e) {
					e.printStackTrace();
				}
				
		blogDao.fileUpdate(blogVo);
				
	}
	
}
