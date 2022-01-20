package com.boardmall.service;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.boardmall.pro.dto.BoardVO;

public interface BoardService {
	public void write(BoardVO vo, MultipartHttpServletRequest mpRequest) throws Exception;
	public void Replywrite(BoardVO vo, MultipartHttpServletRequest mpRequest) throws Exception;
	public void update(BoardVO vo, String[] files, String[] fileNames, MultipartHttpServletRequest mpRequest) throws Exception;
}
