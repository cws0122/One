package com.boardmall.pro.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.web.multipart.MultipartFile;

import com.boardmall.pro.dto.BoardVO;
import com.boardmall.pro.dto.FileUtils;
import com.boardmall.pro.dto.GameVO;
import com.boardmall.pro.dto.ReplyVO;
import com.boardmall.pro.util.SqlSessionFactoryBean;

public class BoardDAO {
	
	private SqlSession mybatis;
	
	public BoardDAO() {
		mybatis = SqlSessionFactoryBean.getSqlSessionInstance();
	}
	
	public void insertBoard(BoardVO vo) {
		mybatis.insert("BoardDAO.insertBoard", vo);
		mybatis.commit();
	}
	
	//insertReply
	public void insertReply(BoardVO vo) {
		mybatis.insert("BoardDAO.insertReply", vo);
		mybatis.commit();
	}
	
	public void updateReply(BoardVO vo) {
		mybatis.update("BoardDAO.updateReply" , vo);
		mybatis.commit();
	}
	
	public void updateReplyre(BoardVO vo) {
		mybatis.update("BoardDAO.updateReplyre" , vo);
		mybatis.commit();
	}
	
	public boolean searchReply(BoardVO vo) {
		boolean search;
		if(mybatis.selectOne("BoardDAO.searchReply" , vo) == null) {
			search = false;
		}else {
			search = true;
		}
		return search;
	}
	
	public void deleted(BoardVO vo) {
		mybatis.update("BoardDAO.deleted" , vo);
		mybatis.commit();
	}
	
	public void deleteinfo(BoardVO vo) {
		mybatis.update("BoardDAO.deleteinfo" , vo);
		mybatis.commit();
	}
	
	public int getTotal() {
		return mybatis.selectOne("BoardDAO.getTotal");
	}
	
	public void freeinsertBoard(BoardVO vo) {
		mybatis.insert("BoardDAO.freeinsertBoard", vo);
		mybatis.commit();
	}
	
	public void updateBoard(BoardVO vo) {
		mybatis.update("BoardDAO.updateBoard", vo);
		mybatis.commit();
	}
	
	public void deleteBoard(BoardVO vo) {
		mybatis.delete("BoardDAO.deleteBoard", vo);
		mybatis.commit();
	}
	
	public List<BoardVO> getCategoryBoardList(BoardVO vo){
		return mybatis.selectList("BoardDAO.getCategoryBoardList", vo);
	}
	
	public List<BoardVO> MinigetCategoryBoardList(BoardVO vo){
		return mybatis.selectList("BoardDAO.MinigetCategoryBoardList", vo);
	}
	
	
	public BoardVO getBoardBySeq(int seq) {
		return mybatis.selectOne("BoardDAO.getBoardBySeq" , seq);
	}
	
	public List<GameVO> getgameList(GameVO vo){
		if(vo.getSearchTitle() != null) {
			return mybatis.selectList("BoardDAO.searchGame", vo);
		}
		return mybatis.selectList("BoardDAO.getgameList" , vo);
	}
	
	public int getBoardCount(String category) {
		return mybatis.selectOne("BoardDAO.getBoardCount" , category);
	}
	
	public int getAllBoardCount() {
		return mybatis.selectOne("BoardDAO.getAllBoardCount");
	}
	
	public int searchBoardCount(BoardVO vo) {
		return mybatis.selectOne("BoardDAO.searchBoardCount" , vo);
	}
	
	public List<BoardVO> BoardListPaging(int cPage, int numPerPage){ 
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("cPage", cPage);
		map.put("numPerPage", numPerPage);
		return mybatis.selectList("BoardDAO.BoardListPaging", map);
	}
	
	public List<BoardVO> searchBoardPaging(int cPage, int numPerPage , BoardVO vo){ 
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("cPage", cPage);
		map.put("numPerPage", numPerPage);
		map.put("searchCondition" , vo.getSearchCondition());
		map.put("searchKeyword" , vo.getSearchKeyword());
		return mybatis.selectList("BoardDAO.searchBoardPaging", map);
	}
	
	public List<BoardVO> getFreeBoardListPaging(int cPage, int numPerPage){ 
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("cPage", cPage);
		map.put("numPerPage", numPerPage);
		return mybatis.selectList("BoardDAO.getFreeBoardListPaging", map);
	}
	
	public List<BoardVO> getReviewBoardListPaging(int cPage, int numPerPage){ 
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("cPage", cPage);
		map.put("numPerPage", numPerPage);
		return mybatis.selectList("BoardDAO.getReviewBoardListPaging", map);
	}
	
	public List<BoardVO> getQnaBoardListPaging(int cPage, int numPerPage){ 
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("cPage", cPage);
		map.put("numPerPage", numPerPage);
		return mybatis.selectList("BoardDAO.getQnaBoardListPaging", map); 
	}
	
	//野껊슣�뻻占쎈솇 �겫�뜄�쑎占쎌궎疫뀐옙 (占쎈읂占쎌뵠筌욑옙)
	
	public List<BoardVO> getAllBoardList(){
		return mybatis.selectList("BoardDAO.getAllBoardList");
	}
	
	public void viewcnt(int seq) {
		mybatis.update("BoardDAO.viewcnt", seq);
		mybatis.commit();
	}
	
	public List<BoardVO> searchBoard(BoardVO vo){
		return mybatis.selectList("BoardDAO.searchBoard" , vo);
	}
	
	// 댓글
	
	public List<ReplyVO> replyList(int seq){
		return mybatis.selectList("BoardDAO.replyList" , seq);
	}
	
	public void insertCmt(ReplyVO vo) {
		mybatis.insert("BoardDAO.insertCmt" , vo);
		mybatis.commit();
	}
	
	public void countup(int seq) {
		mybatis.update("BoardDAO.countup" , seq);
		mybatis.commit();
	}
	
	public void countdown(int seq) {
		mybatis.update("BoardDAO.countdown" , seq);
		mybatis.commit();
	}
	
	public ReplyVO getcommentByseq(ReplyVO vo) {
		return mybatis.selectOne("BoardDAO.getcommentByseq" , vo);
	}
	
	public void cmtupdate(ReplyVO vo) {
		mybatis.update("BoardDAO.cmtupdate", vo);
		mybatis.commit();
	}
	
	public void deleteCmt(ReplyVO vo) {
		mybatis.delete("BoardDAO.deleteCmt" , vo);
		mybatis.commit();
	}
	
	public void insertFile(Map<String , Object> map) throws Exception {
		System.out.println(map);
		mybatis.insert("BoardDAO.insertFile" , map);
		mybatis.commit();
	}
	
	public int getseq() {
		return mybatis.selectOne("BoardDAO.getseq");
	}
	
	public List<Map<String, Object>> detailFileSeq(int seq){
		return mybatis.selectList("BoardDAO.detailFileSeq" , seq);
	}
	
	public List<Map<String, Object>> detailFile(Map<String, Object> map){
		return mybatis.selectList("BoardDAO.detailFile" , map);
	}
	
	public Map<String, Object> selectFileInfo(int seq){
		return mybatis.selectOne("BoardDAO.selectFileInfo" , seq);
	}
	
	public void deleteFile(Map<String, Object> map) {
		mybatis.update("BoardDAO.deleteFile" , map);
		mybatis.commit();
	}
	
	public void deleteAllFile(BoardVO vo) {
		mybatis.delete("BoardDAO.deleteAllFile" , vo);
		mybatis.commit();
	}
	
	public int getOneTotal(){
		return mybatis.selectOne("BoardDAO.getOneTotal"); 
	}
	
	public void updateFile(Map<String, Object> map) {
		mybatis.update("BoardDAO.updateFile" , map);
		mybatis.commit();
	}
	
	public int filecount(int seq) {
		return mybatis.selectOne("BoardDAO.filecount" , seq);
	}
	//filecountUpdate
	public void filecountUpdate(BoardVO vo) {
		mybatis.update("BoardDAO.filecountUpdate" , vo);
		mybatis.commit();
	}
	
	public List<BoardVO> excelList(){
		return mybatis.selectList("BoardDAO.excelList");
	}
	
	public int Allcount() {
		return mybatis.selectOne("BoardDAO.Allcount");
	}
}


