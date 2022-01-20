package com.boardmall.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.boardmall.pro.dao.BoardDAO;
import com.boardmall.pro.dto.BoardVO;
import com.boardmall.pro.dto.FileUtil;

public class BoardServiceImpl implements BoardService {

	@Override
	public void write(BoardVO vo, MultipartHttpServletRequest mpRequest) throws Exception {
		// TODO Auto-generated method stub
		FileUtil fileUtil = new FileUtil();
		BoardDAO dao = new BoardDAO();
		dao.insertBoard(vo);
		vo.setSeq(dao.getseq());
		List<Map<String,Object>> list = fileUtil.parseInsertFileInfo(vo, mpRequest); 
		int size = list.size();
		for(int i=0; i<size; i++){ 
			dao.insertFile(list.get(i)); 
		}
		
		int count = dao.filecount(vo.getSeq());
		vo.setFilecount(count);
		dao.filecountUpdate(vo);
	}

	@Override
	public void update(BoardVO vo, String[] files, String[] fileNames, MultipartHttpServletRequest mpRequest)
			throws Exception {
		FileUtil fileUtil = new FileUtil();
		BoardDAO dao = new BoardDAO();
		dao.updateBoard(vo);
		List<Map<String, Object>> list = fileUtil.parseUpdateFileInfo(vo , files , fileNames , mpRequest);
		Map<String, Object> tempMap = null;
		int size = list.size();
		for(int i = 0; i < size; i++) {
			tempMap = list.get(i);
			if(tempMap.get("IS_NEW").equals("Y")) {
				dao.insertFile(tempMap);
			}else {
				System.out.println(tempMap);
				dao.deleteFile(tempMap);
			}
		}
		int count = dao.filecount(vo.getSeq());
		vo.setFilecount(count);
		dao.filecountUpdate(vo);
		
	}

	@Override
	public void Replywrite(BoardVO vo, MultipartHttpServletRequest mpRequest) throws Exception {
		// TODO Auto-generated method stub
		FileUtil fileUtil = new FileUtil();
		BoardDAO dao = new BoardDAO();
		if(vo.getDepth() == 1) {
			dao.updateReply(vo);
		}else if (vo.getDepth() >= 2) {
			dao.updateReplyre(vo);
		}
		dao.insertReply(vo);
		vo.setSeq(dao.getseq());
		List<Map<String,Object>> list = fileUtil.parseInsertFileInfo(vo, mpRequest); 
		int size = list.size();
		for(int i=0; i<size; i++){ 
			dao.insertFile(list.get(i)); 
		}
		int count = dao.filecount(vo.getSeq());
		vo.setFilecount(count);
		dao.filecountUpdate(vo);
	}
	
	
	
}
