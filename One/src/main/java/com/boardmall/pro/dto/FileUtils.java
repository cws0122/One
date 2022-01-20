package com.boardmall.pro.dto;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Component("fileUtils")
public class FileUtils {
	
	 public List<Map<String, Object>> parseFileInfo(BoardVO vo, MultipartFile file) {
		 	String uploadPath = "C:\\mp\\file\\";
		 	String boardseq = (String) String.valueOf(vo.getSeq());
	        
	        List<Map<String, Object>> fileList = new ArrayList<Map<String, Object>>();
	 
	        File target = new File(uploadPath);
	        if(!target.exists()) target.mkdirs();
	        
	 
	            String orgFileName = file.getOriginalFilename();
	            String orgFileExtension = orgFileName.substring(orgFileName.lastIndexOf("."));
	            String saveFileName = UUID.randomUUID().toString().replaceAll("-", "") + orgFileExtension;
	            Long saveFileSize = file.getSize();
	            
	            System.out.println("================== file start ==================");
	            System.out.println("파일 실제 이름: "+orgFileName);
	            System.out.println("파일 저장 이름: "+saveFileName);
	            System.out.println("파일 크기: "+saveFileSize);
	            System.out.println("content type: "+file.getContentType());
	            System.out.println("================== file   END ==================");
	 
	            target = new File(uploadPath, saveFileName);
	            try {
	            	file.transferTo(target);
	            }catch(Exception e) {
	            	e.printStackTrace();
	            }
	            
	            Map<String, Object> fileInfo = new HashMap<String, Object>();
	 
	            fileInfo.put("boardseq", boardseq);
	            fileInfo.put("ORG_FILE_NAME", orgFileName);
	            fileInfo.put("SAVE_FILE_NAME", saveFileName);
	            fileInfo.put("FILE_SIZE", saveFileSize);
	            fileList.add(fileInfo);
	            
	        return fileList;
	    }
	 
	 public List<Map<String, Object>> parseUpdateFileInfo(Map<String, Object> map , HttpServletRequest request) throws Exception{
		 String uploadPath = "C:\\mp\\file\\";
		 MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest)request;
		 Iterator<String> iteartor = multipartHttpServletRequest.getFileNames();
		 
		 MultipartFile multipartFile = null;
		 String originalFileName = null;
		 String originalFileExtension = null;
		 String storedFileName = null;
		 
		 List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		 Map<String, Object> listMap = null;
		 String boardseq = (String)map.get("boardseq");
		 String requestName = null;
		 String seq = null;
		 
		 while(iteartor.hasNext()) {
			 multipartFile = multipartHttpServletRequest.getFile(iteartor.next());
			 if(multipartFile.isEmpty() == false) {
				 originalFileName = multipartFile.getOriginalFilename();
				 originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
				 storedFileName = UUID.randomUUID().toString().replaceAll("-", "") + originalFileExtension;
				 
				 multipartFile.transferTo(new File(uploadPath + storedFileName));
				 
				 listMap = new HashMap<String, Object>();
				 listMap.put("IS_NEW" , "Y");
				 listMap.put("boardseq" , boardseq);
				 listMap.put("ORIGINAL_FILENAME" , originalFileName);
				 listMap.put("STORED_FILE_NAME" , storedFileName);
				 listMap.put("FILE_SIZE" , multipartFile.getSize());
				 list.add(listMap);
			 }else {
				 requestName = multipartFile.getName();
				 seq = "SEQ_" + requestName.substring(requestName.indexOf("_") + 1);
				 if(map.containsKey(seq) == true && map.get(seq) != null) {
					 listMap = new HashMap<String, Object>();
					 listMap.put("IS_NEW" , "N");
					 listMap.put("FILE_SEQ", map.get(seq));
					 list.add(listMap);
				 }
			 }
		 }
		 
		 return list;
	 }
	 
	 
	 
	 
	 
	 

	
}
