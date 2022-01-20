package com.boardmall.controller;

import java.io.File;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor.HSSFColorPredefined;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.boardmall.pro.dao.BoardDAO;
import com.boardmall.pro.dao.GameDAO;
import com.boardmall.pro.dao.MemberDAO;
import com.boardmall.pro.dao.SupportDAO;
import com.boardmall.pro.dao.TagDAO;
import com.boardmall.pro.dto.BoardVO;
import com.boardmall.pro.dto.FileUtils;
import com.boardmall.pro.dto.GameVO;
import com.boardmall.pro.dto.MemberVO;
import com.boardmall.pro.dto.Paging;
import com.boardmall.pro.dto.QnaVO;
import com.boardmall.pro.dto.ReplyVO;
import com.boardmall.pro.dto.TagVO;
import com.boardmall.service.BoardServiceImpl;

@Controller
public class BoardMallController {
		
	@RequestMapping("/main.do")
	public String mainForm(Model model) {
		System.out.println("筌롫뗄�뵥 占쎈읂占쎌뵠筌욑옙嚥∽옙 占쎌뵠占쎈짗");
		GameDAO gameDAO = new GameDAO();
		List<GameVO> gameList = gameDAO.getMainList();
		List<GameVO> imageSliderList = gameDAO.getImageSliderList();
		model.addAttribute("gameList", gameList);
		model.addAttribute("imageSliderList", imageSliderList);
		return "main";
		
	}
	//@@@@@@@@@@占쎄맒占쎈�� �뵳�딅뮞占쎈뱜@@@@@@@@@@@@
	@RequestMapping("/productList.do")
	public String productListForm(Model model, HttpServletRequest request) {
		System.out.println("占쎄맒占쎈�� �뵳�딅뮞占쎈뱜 占쎈읂占쎌뵠筌욑옙嚥∽옙 占쎌뵠占쎈짗");
		GameDAO gameDAO = new GameDAO();
		
		//野껓옙占쎄퉳鈺곌퀗援붺빊�뮆�젾
        TagDAO tagDAO = new TagDAO();
        List<TagVO> systemList = tagDAO.getTagByCategory("system");
        List<TagVO> gerneList = tagDAO.getTagByCategory("gerne");
        model.addAttribute("systemList", systemList);
        model.addAttribute("gerneList", gerneList);
        
		//占쎈읂占쎌뵠筌욑옙
		String path = "/productList.do?cPage=";
		int cPage;
		try {
			cPage = Integer.parseInt(request.getParameter("cPage"));
		} catch (NumberFormatException e) {
			cPage = 1;
		}
		
		int numPerPage;  
        try {
            numPerPage = Integer.parseInt(request.getParameter("numPerPage"));
        }catch (NumberFormatException e) {
            numPerPage = 10;
        }
         // 野껓옙占쎄퉳
        List<GameVO> gameTagList;
        int gameTagCount;

			gameTagList = gameDAO.getGameListPaging(cPage, numPerPage);
			gameTagCount = gameDAO.getGameCount();

        int totalPage = (int)Math.ceil((double)gameTagCount/numPerPage);
        System.out.println(gameTagCount + " " + totalPage);
        Paging paging = new Paging();
        paging.paging(request, path, gameTagList, totalPage, cPage, numPerPage);
		model.addAttribute("gameTagList", gameTagList);
		/*
		 * List<GameVO> productList = gameDAO.getGameListPaging(cPage, numPerPage); int
		 * productCount = gameDAO.getGameCount(); int totalPage =
		 * (int)Math.ceil((double)productCount/numPerPage); Paging paging = new
		 * Paging(); paging.paging(request, path, productList, totalPage, cPage,
		 * numPerPage);
		 */
        

		return "product/productList";
	}
	
	@RequestMapping("/test.do")
	public String getCheckList(Model model, HttpServletRequest request) {
		System.out.println("占쎄맒占쎈�� �뵳�딅뮞占쎈뱜 野껓옙占쎄퉳野껉퀗�궢 占쎈읂占쎌뵠筌욑옙嚥∽옙 占쎌뵠占쎈짗");
		GameDAO gameDAO = new GameDAO();
		List<GameVO> gameTagList;
		String path = "/productList.do?cPage="; 
		int gameTagCount;
		String[] playerArray = request.getParameterValues("check1");
		if(playerArray != null && playerArray[0].equals("selectAll1")) { 
			playerArray = null; 
		}
		System.out.println("1: " + Arrays.toString(playerArray));
		
		String[] playtimeArray = request.getParameterValues("check2");
		if(playtimeArray != null && playtimeArray[0].equals("selectAll2")) { 
			playtimeArray = null; 
		}
		System.out.println("2: " + Arrays.toString(playtimeArray));
		
		String[] tagArray = request.getParameterValues("check3");
		if(tagArray != null && tagArray[0].equals("selectAll3")) {
			tagArray = null;
		}
		System.out.println("3: " + Arrays.toString(tagArray));
		
		String[] tagArray2 = request.getParameterValues("check4");
		if(tagArray2 != null && tagArray2[0].equals("selectAll4")) {
			tagArray2 = null;
		}
		System.out.println("4: " + Arrays.toString(tagArray2));

		//占쎈읂占쎌뵠筌욑옙
		int cPage;
		try {
			cPage = Integer.parseInt(request.getParameter("cPage"));
		} catch (NumberFormatException e) {
			cPage = 1;
		}

		int numPerPage;  
        try {
            numPerPage = Integer.parseInt(request.getParameter("numPerPage"));
        }catch (NumberFormatException e) {
            numPerPage = 10;
        }
        
		if(tagArray == null && tagArray2 == null && playerArray == null && playtimeArray == null) {
			gameTagList = gameDAO.getGameListPaging(cPage, numPerPage);
			gameTagCount = gameDAO.getGameCount();
		}else {
			gameTagList = gameDAO.getSearchGame(playerArray,playtimeArray,tagArray,tagArray2,cPage,numPerPage);
			gameTagCount = gameDAO.getSearchCount(playerArray,playtimeArray,tagArray,tagArray2);
		}
        int totalPage = (int)Math.ceil((double)gameTagCount/numPerPage);
        System.out.println(gameTagCount + " " + totalPage); // 占쎌삋 占쎈┷占쎈뮉占쎈쑓 占쎌뵠 �겫占썽겫袁⑹뵠 占쎈뻻占쎈뮞占쎈��, 占쎌삢�몴占� 占쎈ぎ 占쎈뼄 占쎄퐨占쎄문 占쎈뻻 0,0占쎌몵嚥∽옙 占쎄돌占쎌긾.. 占쎌넞? 占쎈쐮�겫袁⑸퓠 占쎈읂占쎌뵠筌욑옙�몴占� 占쎄퐜疫뀀챷�땾占쎈즲 占쎈씨占쎈뼄..
        Paging paging = new Paging();
        paging.pagingSearch(playerArray, playtimeArray, tagArray, tagArray2, request, path, gameTagList, totalPage, cPage, numPerPage);
		model.addAttribute("gameTagList", gameTagList);
		return "test";
	}
	
	
	@RequestMapping("/gameDetail.do")
	public String gameDetailForm(Model model, @RequestParam("seq") int seq) {
		System.out.println("野껊슣�뿫占쎄맒占쎄쉭 占쎈읂占쎌뵠筌욑옙嚥∽옙 占쎌뵠占쎈짗");
		GameDAO gameDAO = new GameDAO();
		GameVO game = gameDAO.getGameBySeq(seq);
		List<String> gameGerne = gameDAO.getGameGerneBySeq(seq);
		List<String> gameSystem = gameDAO.getGameSystemBySeq(seq);
		List<String> gameDescImg = gameDAO.getGameDescImgBySeq(seq);
		model.addAttribute("game", game);
		model.addAttribute("gameGerne", gameGerne);
		model.addAttribute("gameSystem", gameSystem);
		model.addAttribute("gameDescImg",gameDescImg);
		return "product/gameDetail";
	}
	
	@RequestMapping("/AllexcelDown.do")
	public void excelDown(BoardVO vo,HttpServletResponse response) {
		System.out.println("엑셀 다운로드");
		BoardDAO boardDAO = new BoardDAO();
		Map<String, Object> map = new HashMap<>();
		List<BoardVO> list = boardDAO.getAllBoardList();
		int total = boardDAO.Allcount();
		
		try {
			//엑셀 다운 시작
			Workbook workbook = new HSSFWorkbook();
			//시트 생성
			Sheet sheet = workbook.createSheet("게시판");
			sheet.setColumnWidth(0, 1500);
			sheet.setColumnWidth(1, 2000);
			sheet.setColumnWidth(2, 3000);
			sheet.setColumnWidth(3, 1500);
			sheet.setColumnWidth(4, 3000);	
			
			//핼 , 열  , 열번호
			Row row = null;
			Cell cell = null;
			int rowNo = 0;
			
			//테이블 헤더용 스타일
			CellStyle headStyle = workbook.createCellStyle();
			
			// 가는 경계선을 가집니다.
			headStyle.setBorderTop(BorderStyle.THIN);
			headStyle.setBorderBottom(BorderStyle.THIN);
			headStyle.setBorderLeft(BorderStyle.THIN);
			headStyle.setBorderRight(BorderStyle.THIN);
			
			//배경색은 노란색입니다.
			headStyle.setFillForegroundColor(HSSFColorPredefined.YELLOW.getIndex());
			headStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			
			//데이터는 가운데 정렬합니다.
			CellStyle bodyStyle = workbook.createCellStyle();
			bodyStyle.setBorderTop(BorderStyle.THIN);
			bodyStyle.setBorderBottom(BorderStyle.THIN);
			bodyStyle.setBorderLeft(BorderStyle.THIN);
			bodyStyle.setBorderRight(BorderStyle.THIN);
			
			
			String[] headArray = {"번호" , "제목" , "작성자" , "조회수" , "첨부파일 개수"};
			row = sheet.createRow(rowNo++);
			for(int i=0; i < headArray.length; i++) {
				cell = row.createCell(i);
				cell.setCellStyle(headStyle);
				cell.setCellValue(headArray[i]);
			}
			
			
			for(BoardVO excelData : list) {
				
				row = sheet.createRow(rowNo++);
				cell = row.createCell(0);
				cell.setCellStyle(bodyStyle);
				cell.setCellValue(total);
				cell = row.createCell(1);
				cell.setCellStyle(bodyStyle);
				cell.setCellValue(excelData.getTitle());
				cell = row.createCell(2);
				cell.setCellStyle(bodyStyle);
				cell.setCellValue(excelData.getWriter());
				cell = row.createCell(3);
				cell.setCellStyle(bodyStyle);
				cell.setCellValue(excelData.getCount());
				cell = row.createCell(4);
				cell.setCellStyle(bodyStyle);
				cell.setCellValue(excelData.getFilecount());
				total = total - 1;
			}
			
			response.setContentType("ms-vnd/excel");
			response.setHeader("Content-Disposition", "attachment;filename=" + java.net.URLEncoder.encode("게시판.xls" , "UTF-8"));
			
			workbook.write(response.getOutputStream());
			workbook.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/SearchexcelDown.do")
	public void SearchexcelDown(BoardVO vo ,HttpServletResponse response) {
		System.out.println("검색 리스트 엑셀 다운로드");
		System.out.println(vo.getSearchCondition());
		System.out.println(vo.getSearchKeyword());
		BoardDAO boardDAO = new BoardDAO();
		Map<String, Object> map = new HashMap<>();
		List<BoardVO> list = boardDAO.searchBoard(vo);
		int total = boardDAO.searchBoardCount(vo);
		
		try {
			//엑셀 다운 시작
			Workbook workbook = new HSSFWorkbook();
			//시트 생성
			Sheet sheet = workbook.createSheet("게시판");
			sheet.setColumnWidth(0, 1500);
			sheet.setColumnWidth(1, 5000);
			sheet.setColumnWidth(2, 3000);
			sheet.setColumnWidth(3, 1500);
			sheet.setColumnWidth(4, 3000);	
			sheet.setColumnWidth(5, 3000);	
			
			//핼 , 열  , 열번호
			Row row = null;
			Cell cell = null;
			int rowNo = 0;
			
			//테이블 헤더용 스타일
			CellStyle headStyle = workbook.createCellStyle();
			
			// 가는 경계선을 가집니다.
			headStyle.setBorderTop(BorderStyle.THIN);
			headStyle.setBorderBottom(BorderStyle.THIN);
			headStyle.setBorderLeft(BorderStyle.THIN);
			headStyle.setBorderRight(BorderStyle.THIN);
			
			//배경색은 노란색입니다.
			headStyle.setFillForegroundColor(HSSFColorPredefined.YELLOW.getIndex());
			headStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			
			//데이터는 가운데 정렬합니다.
			CellStyle bodyStyle = workbook.createCellStyle();
			bodyStyle.setBorderTop(BorderStyle.THIN);
			bodyStyle.setBorderBottom(BorderStyle.THIN);
			bodyStyle.setBorderLeft(BorderStyle.THIN);
			bodyStyle.setBorderRight(BorderStyle.THIN);
			
			
			String[] headArray = {"번호" , "제목" , "작성자" , "작성일" , "조회수" , "첨부파일 개수"};
			row = sheet.createRow(rowNo++);
			for(int i=0; i < headArray.length; i++) {
				cell = row.createCell(i);
				cell.setCellStyle(headStyle);
				cell.setCellValue(headArray[i]);
			}
			
			
			for(BoardVO excelData : list) {
				
				row = sheet.createRow(rowNo++);
				cell = row.createCell(0);
				cell.setCellStyle(bodyStyle);
				cell.setCellValue(total);
				cell = row.createCell(1);
				cell.setCellStyle(bodyStyle);
				cell.setCellValue(excelData.getTitle());
				cell = row.createCell(2);
				cell.setCellStyle(bodyStyle);
				cell.setCellValue(excelData.getWriter());
				cell = row.createCell(3);
				cell.setCellStyle(bodyStyle);
				cell.setCellValue(excelData.getRegdate());
				cell = row.createCell(4);
				cell.setCellStyle(bodyStyle);
				cell.setCellValue(excelData.getCount());
				cell = row.createCell(5);
				cell.setCellStyle(bodyStyle);
				cell.setCellValue(excelData.getFilecount());
				total = total - 1;
			}
			
			response.setContentType("ms-vnd/excel");
			response.setHeader("Content-Disposition", "attachment;filename=" + java.net.URLEncoder.encode("게시판.xls" , "UTF-8"));
			
			workbook.write(response.getOutputStream());
			workbook.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
/* @@@@@@@@@@@@@@@@@@@@寃뚯떆�뙋 �떆�옉@@@@@@@@@@@@@@@@@@@@ */
	
	@RequestMapping("/boardList.do")
	public String boardListForm(Model model ,HttpServletRequest request) {
		System.out.println("게시판 목록으로 이동");
		BoardDAO boardDAO = new BoardDAO();
	
		String path = "/boardList.do?cPage=";
		int cPage;
		try {
			cPage = Integer.parseInt(request.getParameter("cPage"));
		} catch (NumberFormatException e) {
			cPage = 1;
		}
		
		int numPerPage;  
        try {
            numPerPage = Integer.parseInt(request.getParameter("numPerPage"));
        }catch (NumberFormatException e) {
            numPerPage = 15;
        }
        List<BoardVO> AllBoardList = boardDAO.BoardListPaging(cPage, numPerPage);
        
        int Allboardcount = boardDAO.getAllBoardCount();
        model.addAttribute("total" , Allboardcount);
        int totalPage = (int)Math.ceil((double)Allboardcount/numPerPage);
        Paging paging = new Paging();
        paging.Boardpaging(request, path, AllBoardList, totalPage, cPage, numPerPage);
		
		return "board/boardList";
	}
	

	@RequestMapping("/boardWrite.do")
	public String boardWriteForm() {
		System.out.println("게시글 작성 페이지로 이동");
		return "board/boardWrite";
	}
		
//	@RequestMapping("/insertBoard.do")
//	public String insertBoard(BoardVO vo , MultipartFile[] file) {
//		System.out.println("게시글 등록");
//		BoardDAO boardDAO = new BoardDAO();
//		FileUtils fileutils = new FileUtils();
//		boardDAO.insertBoard(vo);
//		vo.setSeq(boardDAO.getseq());
//		for(int i=0; i<file.length; i++) {
//			if(file[i].isEmpty()) {
//				System.out.println("응 안돼 ㅋㅋ");
//			}else {
//				List<Map<String, Object>> fileList = fileutils.parseFileInfo(vo, file[i]);
//				for(int a=0; a<fileList.size(); a++) {
//					boardDAO.insertFile(fileList.get(a));
//				}
//			}
//		}
//		int count = boardDAO.filecount(vo.getSeq());
//		vo.setFilecount(count);
//		boardDAO.filecountUpdate(vo);
//		return "redirect:boardList.do";
//	}
	
	@RequestMapping("/insertBoard.do")
	public String insertBoard(BoardVO vo , MultipartHttpServletRequest mpRequest) throws Exception {
		System.out.println("게시글 등록");
		BoardServiceImpl service = new BoardServiceImpl();
		service.write(vo, mpRequest);
		return "redirect:boardList.do";
	}
	
	@Resource(name="uploadPath")
	String uploadPath;
	
	//filedownload.do
	@RequestMapping("/filedownload.do")
	public void filedownload(@RequestParam("seq") int seq, HttpServletRequest request , HttpServletResponse response) throws Exception{
		System.out.println("첨부파일 다운로드");
		BoardDAO boardDAO = new BoardDAO();
		Map<String, Object> file = boardDAO.selectFileInfo(seq);
		
		String saveFileName = (String)file.get("SAVED_FILENAME");
		String originalFileName = (String)file.get("ORI_FILENAME");
		
		File downloadFile = new File(uploadPath + saveFileName);
		
		byte fileByte[] = org.apache.commons.io.FileUtils.readFileToByteArray(downloadFile);
		
		response.setContentType("application/octet-stream");
		response.setContentLength(fileByte.length);
		
		response.setHeader("Content-Disposition", "attachment; fileName=\"" + URLEncoder.encode(originalFileName,"UTF-8") +"\";");
        response.setHeader("Content-Transfer-Encoding", "binary");
        
        response.getOutputStream().write(fileByte);
        response.getOutputStream().flush();
        response.getOutputStream().close();
	}
	
	//checkpassword.do
	@RequestMapping("/checkpassword.do")
	public String checkpassword(BoardVO vo, Model model) {
		System.out.println("게시판 비밀번호 확인");
		model.addAttribute("board" , vo);
		System.out.println(vo.getPassword());
		return "board/checkpassword";
	}

	
//	@RequestMapping("/updateBoard.do")
//	public String updateBoard(BoardVO vo, MultipartFile[] file , HttpServletRequest request , @RequestParam Map<String, Object> map) throws Exception{
//		System.out.println("게시글 수정");
//		BoardDAO boardDAO = new BoardDAO();
//		FileUtils fileutils = new FileUtils();
//		
//		boardDAO.deleteFile(vo.getSeq());
//		
//		List<Map<String, Object>> list = fileutils.parseUpdateFileInfo(map, request);
//		Map<String, Object> tempMap = null;
//		for(int i=0, size = list.size(); i<size; i++) {
//			tempMap = list.get(i);
//			if(tempMap.get("IS_NEW").equals("Y")){
//				for(int a=0; a<file.length; a++) {
//					if(file[a].isEmpty()) {
//						System.out.println("응 안돼 ㅋㅋ");
//					}else {
//						List<Map<String, Object>> fileList = fileutils.parseFileInfo(vo, file[a]);
//						for(int j=0; j<fileList.size(); j++) {
//							boardDAO.insertFile(fileList.get(j));
//						}
//					}
//				}
//			}else {
//				boardDAO.updateFile(tempMap);
//			}
//		}
//		int count = boardDAO.filecount(vo.getSeq());
//		vo.setFilecount(count);
//		boardDAO.filecountUpdate(vo);
//		return "redirect:boardList.do";
//	}
	
	@RequestMapping("/updateBoard.do")
	public String updateBoard(BoardVO vo, @RequestParam(value="fileNoDel") String[] files , @RequestParam(value="fileNameDel[]") String[] fileNames,
			MultipartHttpServletRequest mpRequest) throws Exception{
		System.out.println("게시글 수정");
		BoardServiceImpl service = new BoardServiceImpl();
		service.update(vo, files, fileNames, mpRequest);
		
		
		return "redirect:boardList.do";
	}
	
	@RequestMapping("/deleteBoard.do")
	public String deleteBoard(BoardVO vo) {
		System.out.println("게시물 삭제");
		BoardDAO boardDAO = new BoardDAO();
		if(boardDAO.searchReply(vo)) {
			boardDAO.deleted(vo);
			boardDAO.deleteinfo(vo);
			boardDAO.deleteAllFile(vo);
		}else {
			boardDAO.deleteBoard(vo);
			boardDAO.deleteAllFile(vo);
		}
		return "redirect:boardList.do";
	}
	
	@RequestMapping("/boardDetail.do")
	public String boardDetail(Model model, @RequestParam("seq") int seq) {
		System.out.println("글 상세보기");
		BoardDAO boardDAO = new BoardDAO();
		BoardVO board = new BoardVO();
		ReplyVO reply = new ReplyVO();
		List<Map<String, Object>> fileList = boardDAO.detailFileSeq(seq);
		board = boardDAO.getBoardBySeq(seq);
		boardDAO.viewcnt(seq);
		System.out.println("viewcnt +");
		List<ReplyVO> replyList = boardDAO.replyList(seq);
		model.addAttribute("board" , board);
		model.addAttribute("replyList" , replyList);
		model.addAttribute("fileList" , fileList);
		return "board/boardDetail";
	}
	//replyForm.do
	@RequestMapping("/replyForm.do")
	public String replyForm(Model model, BoardVO vo) {
		System.out.println("寃뚯떆湲� �긽�꽭蹂닿린");
		BoardDAO boardDAO = new BoardDAO();
		BoardVO board = new BoardVO();
		board = boardDAO.getBoardBySeq(vo.getSeq());
		model.addAttribute("board" , board);
		return "board/replyForm";
	}
	
	//insertReply.do
	@RequestMapping("/insertReply.do")
	public String insertReply(BoardVO vo, MultipartHttpServletRequest mpRequest ) throws Exception {
		System.out.println("답글 삽입");
		BoardDAO boardDAO = new BoardDAO();
		vo.setSeq(boardDAO.getseq() + 1);
		BoardServiceImpl service = new BoardServiceImpl();
		service.Replywrite(vo, mpRequest);
		int count = boardDAO.filecount(vo.getSeq());
		vo.setFilecount(count);
		boardDAO.filecountUpdate(vo);
		return "redirect:boardList.do";
	}
	
//	@RequestMapping("/insertReply.do")
//	public String insertReply(BoardVO vo, MultipartFile[] file) {
//		System.out.println("답글 삽입");
//		BoardDAO boardDAO = new BoardDAO();
//		vo.setSeq(boardDAO.getseq() + 1);
//		FileUtils fileutils = new FileUtils();
//		System.out.println(file);
//		for(int i=0; i<file.length; i++) {
//			if(file[i].isEmpty()) {
//				System.out.println("응 안돼 ㅋㅋ");
//			}else {
//				List<Map<String, Object>> fileList = fileutils.parseFileInfo(vo, file[i]);
//				for(int a=0; a<fileList.size(); a++) {
//					boardDAO.insertFile(fileList.get(a));
//				}
//			}
//		}
//		if(vo.getDepth() == 1) {
//			boardDAO.updateReply(vo);
//		}else if (vo.getDepth() >= 2) {
//			boardDAO.updateReplyre(vo);
//		}
//		boardDAO.insertReply(vo);
//		int count = boardDAO.filecount(vo.getSeq());
//		vo.setFilecount(count);
//		boardDAO.filecountUpdate(vo);
//		return "redirect:boardList.do";
//	}
	

	//insertCmt.do
	@RequestMapping("/insertCmt.do")
	public String insertCmt(ReplyVO vo) {
		System.out.println("�뙎湲� �궫�엯");
		System.out.println(vo);
		int seq = vo.getBoardseq();
		BoardDAO boardDAO = new BoardDAO();
		boardDAO.insertCmt(vo);
		boardDAO.countup(vo.getBoardseq());
		return "redirect:boardDetail.do?seq=" + seq;
	}
	//CmtpassChk.do
	@RequestMapping("/CmtpassChk.do")
	public String CmtpassChk(ReplyVO vo , Model model) {
		System.out.println("댓글 비밀번호 확인");
		model.addAttribute("reply" , vo);
		System.out.println(vo.getPassword());
		return "board/cmtpasschk";
	}
	
	//cmtupdateForm.do
	@RequestMapping("/cmtupdateForm.do")
	public String cmtupdateForm(ReplyVO vo , Model model) {
		System.out.println("댓글 수정페이지 이동");
		BoardDAO boardDAO = new BoardDAO();
		ReplyVO reply = new ReplyVO();
		reply = boardDAO.getcommentByseq(vo);
		model.addAttribute("reply" , reply);
		System.out.println(reply);
		return "board/cmtupdate";
	}
	
	//updateCmt.do
	@RequestMapping("/updateCmt.do")
	public String updateCmt(ReplyVO vo) {
		System.out.println("댓글 수정");
		BoardDAO boardDAO = new BoardDAO();
		int seq = vo.getBoardseq();
		boardDAO.cmtupdate(vo);
		return "redirect:boardDetail.do?seq=" + seq;
	}
	
	//deleteCmt.do
	@RequestMapping("/deleteCmt.do")
	public String deleteCmt(ReplyVO vo) {
		System.out.println("댓글 삭제");
		BoardDAO boardDAO = new BoardDAO();
		int seq = vo.getBoardseq();
		boardDAO.deleteCmt(vo);
		boardDAO.countdown(vo.getBoardseq());
		return "redirect:boardDetail.do?seq=" + seq;
	}
	
	@RequestMapping("/boardUpdateForm.do")
	public String boardUpdateForm(Model model, @RequestParam("seq") int seq) throws Exception {
		System.out.println("게시글 수정 화면 이동");
		BoardDAO boardDAO = new BoardDAO();
		BoardVO board = new BoardVO();
		List<Map<String , Object>> file = boardDAO.detailFileSeq(seq);
		board = boardDAO.getBoardBySeq(seq);
		model.addAttribute("board" , board);
		model.addAttribute("file", file);
		return "board/boardUpdate";
	}
	
	@ModelAttribute("conditionMap")
	public Map<String, String> searchConditionMap(){
		Map<String, String> conditionMap = new HashMap<>();
		conditionMap.put("title", "제목");
		conditionMap.put("content", "내용");
		conditionMap.put("writer", "작성자");
		return conditionMap;
	}
	
	  @RequestMapping("/searchBoard.do")
	  public String search(BoardVO vo , Model model, HttpServletRequest request) {
		  BoardDAO boardDAO = new BoardDAO();
			String path = "/searchBoard.do?searchCondition=" + vo.getSearchCondition() + "&searchKeyword=" + vo.getSearchKeyword() + "&cPage=";
			int cPage;
			try {
				cPage = Integer.parseInt(request.getParameter("cPage"));
			} catch (NumberFormatException e) {
				cPage = 1;
			}
			
			int numPerPage;  
	        try {
	            numPerPage = Integer.parseInt(request.getParameter("numPerPage"));
	        }catch (NumberFormatException e) {
	            numPerPage = 15;
	        }
	        int searchboardcount = boardDAO.searchBoardCount(vo);
	        System.out.println(searchboardcount);
	        model.addAttribute("search" , vo);
	        
	        List<BoardVO> boardList = boardDAO.searchBoardPaging(cPage, numPerPage , vo);
	        
	        model.addAttribute("total" , searchboardcount);
	        int totalPage = (int)Math.ceil((double)searchboardcount/numPerPage);
	        Paging paging = new Paging();
	        paging.SearchBoardpaging(request, path, boardList, totalPage, cPage, numPerPage);
	        
		  return "board/searchList";
	  }

	  /*
	   @RequestMapping("/boardList.do")
		public String boardListForm(Model model ,HttpServletRequest request) {
		System.out.println("게시판 목록으로 이동");
		BoardDAO boardDAO = new BoardDAO();
	
		String path = "/boardList.do?cPage=";
		int cPage;
		try {
			cPage = Integer.parseInt(request.getParameter("cPage"));
		} catch (NumberFormatException e) {
			cPage = 1;
		}
		
		int numPerPage;  
        try {
            numPerPage = Integer.parseInt(request.getParameter("numPerPage"));
        }catch (NumberFormatException e) {
            numPerPage = 15;
        }
        List<BoardVO> AllBoardList = boardDAO.BoardListPaging(cPage, numPerPage);
        
        int Allboardcount = boardDAO.getAllBoardCount();
        model.addAttribute("total" , Allboardcount);
        int totalPage = (int)Math.ceil((double)Allboardcount/numPerPage);
        Paging paging = new Paging();
        paging.Boardpaging(request, path, AllBoardList, totalPage, cPage, numPerPage);
		
		return "board/boardList";
	}
	   
	   
	   */
	
	/* @@@@@@@@@@@@@@@@@@@@野껊슣�뻻占쎈솇 占쎄국@@@@@@@@@@@@@@@@@@@@ */
	
	@RequestMapping("/notice.do")
	public String noticeListForm() {
		System.out.println("�⑤벊占쏙옙沅쀯옙鍮� 占쎈읂占쎌뵠筌욑옙嚥∽옙 占쎌뵠占쎈짗");
		return "board/notice";
	}
	
	@RequestMapping("/noticeDetail.do")
	public String noticeDetailForm() {
		System.out.println("�⑤벊占쏙옙沅쀯옙鍮� 占쎄맒占쎄쉭癰귣떯由� 占쎈읂占쎌뵠筌욑옙嚥∽옙 占쎌뵠占쎈짗");
		return "board/noticeDetail";
	}
	
	@RequestMapping("/noticeWrite.do")
	public String noticeWriteForm() {
		System.out.println("�⑤벊占쏙옙沅쀯옙鍮� 占쎌삂占쎄쉐 占쎈읂占쎌뵠筌욑옙嚥∽옙 占쎌뵠占쎈짗");
		return "board/noticeWrite";
	}
	
	@RequestMapping("/support.do")
    public String supportForm(Model model, @RequestParam(value = "paging") int paging) {

        System.out.println("�⑥쥒而쇽쭪占쏙옙�뜚 占쎈읂占쎌뵠筌욑옙嚥∽옙 占쎌뵠占쎈짗");
        SupportDAO qnaDao = new SupportDAO();
        List<QnaVO> qnaList = qnaDao.getQnaList();
        model.addAttribute("qnaList",qnaList);
        if(!qnaList.isEmpty()) {
        	
        
        QnaVO vo = qnaList.get(0);
        int seq = vo.getSeq();
        int maxPage = seq/9+1;
        System.out.println(maxPage);
        model.addAttribute("maxPage",maxPage);
        model.addAttribute("lastSeq",seq);
        if(paging<0) {
            paging = 0;
        }else if(paging > maxPage) {
            paging = maxPage;
        }
        model.addAttribute("paging",paging);
        }
        return "member/supportQna";

    }
	 @RequestMapping("/submitQna.do")
	    public String submitQna(@ModelAttribute() QnaVO vo) {
	        System.out.println("�눧紐꾩벥占쎄텢占쎈퉮 占쎈쾻嚥∽옙");

	        SupportDAO qnaDao = new SupportDAO();
	        qnaDao.insertQna(vo);

	        return "redirect:support.do?paging=0";
	    }
	@RequestMapping("/faq.do")
	public String faqForm() {
		System.out.println("faq 占쎈읂占쎌뵠筌욑옙嚥∽옙 占쎌뵠占쎈짗");
		return "member/supportFaq";
	}
	@RequestMapping("/login.do")
	public String loginForm() {
		System.out.println("嚥≪뮄�젃占쎌뵥 占쎈읂占쎌뵠筌욑옙嚥∽옙 占쎌뵠占쎈짗");
		return "member/login";
	}
	@RequestMapping("/loginCheck.do")
    public String loginForm(Model model,@RequestParam("userid") String userid,@RequestParam("pwd") String pwd) {
        System.out.println("嚥≪뮄�젃占쎌뵥 占쎈읂占쎌뵠筌욑옙嚥∽옙 占쎌뵠占쎈짗");
        String path = "";

        MemberDAO dao = new MemberDAO();
        MemberVO vo = new MemberVO();

        model.addAttribute("userid",userid);
        model.addAttribute("pwd",pwd);
        vo.setUserid(userid);
        vo.setPwd(pwd);

        int result = dao.Login(vo);

        if (result == 1) {
            path = "redirect:index.html";
        } else {
            path = "member/login";
        }
        return path;
    }
	
	@RequestMapping("/contract.do")
	public String contractForm() {
		System.out.println("占쎈튋�꽴占쏙옙猷욑옙�벥 占쎈읂占쎌뵠筌욑옙嚥∽옙 占쎌뵠占쎈짗");
		return "member/contract";
	}
	
	@RequestMapping("/join.do")
	public String joinForm() {
		System.out.println("占쎌돳占쎌뜚揶쏉옙占쎌뿯 占쎈읂占쎌뵠筌욑옙嚥∽옙 占쎌뵠占쎈짗");
		return "member/join";
	}
	
	//�빊酉��뜎占쎈퓠 占쎌삢獄쏅떽�럡占쎈빍 占쎈읂占쎌뵠筌욑옙 筌띾슢諭억옙堉깍쭪占� 占쎈뻻 �빊遺쏙옙boardWrite.do
	@RequestMapping("/cartList.do")
	public String cartListForm() {
		System.out.println("占쎌삢獄쏅떽�럡占쎈빍 占쎈읂占쎌뵠筌욑옙嚥∽옙 占쎌뵠占쎈짗");
		return "order/cart";
	}
	
	//�빊酉��뜎占쎈퓠 �뤃�됤꼻 占쎈읂占쎌뵠筌욑옙 筌띾슢諭억옙堉깍쭪占� 占쎈뻻 �빊遺쏙옙
	@RequestMapping("/buy.do")
	public String buyForm() {
		System.out.println("�뤃�됤꼻 占쎈읂占쎌뵠筌욑옙嚥∽옙 占쎌뵠占쎈짗");
		return "order/order";
	}
	
	@RequestMapping("/mypage.do")
	public String mypageForm() {
		System.out.println("筌띾뜆�뵠占쎈읂占쎌뵠筌욑옙嚥∽옙 占쎌뵠占쎈짗");
		return "member/mypage";
	}
	//疫꿸퀬占� 占쎈읂占쎌뵠筌욑옙 疫꿸퀡�뮟
	
@RequestMapping("/etc.do")
public String etcForm() {
	System.out.println("疫꿸퀬占� 占쎈읂占쎌뵠筌욑옙嚥∽옙 占쎌뵠占쎈짗");
	return "etc/gameTest";
}
@RequestMapping("/gameTest.do")
public String gameTestForm(HttpServletRequest request, Model model) {
	System.out.println("野껉퀗�궢 占쎌넇占쎌뵥");
	//
	//野껉퀗�궢 占쎌넇占쎌뵥占쏙옙 3揶쏉옙筌욑옙 占쎌뒄占쎈꺖
	//占쎌굤占쎈짗 vs 野껋럩�쎋 占쎈연�겫占�(cooperative)
	//筌욊쑴占쏙옙釉� 占쎌읈占쎌셽野껊슣�뿫 vs 占쎌끉占쎌쁽筌욑옙�뙼占� 占쎈솁占쎈뼒野껊슣�뿫(strategic)
	//占쎌뒲 占쎌뒄占쎈꺖揶쏉옙 筌띾‘�벉 vs 占쎌뒲 占쎌뒄占쎈꺖 占쎌읅占쎌벉(luck)
	//
	//3揶쏉옙筌욑옙 占쎌뒄占쎈꺖揶쏉옙 占쎈펶占쎈땾占쎌뵥筌욑옙 占쎌벉占쎈땾占쎌뵥筌욑옙占쎈퓠 占쎈뎡占쎌뵬 野껉퀗�궢揶쏉옙 占쎈뼎占쎌뵬筌욑옙 
	//
	
	int cooperative = 0;
	int strategic = 0;
	int luck = 0;
	
	
	//10揶쏆뮇�벥 筌욌뜄揆 value 1占쎌굢占쎈뮉 2嚥∽옙 占쎄퐜占쎈선占쎌긾
	
	String q1 = request.getParameter("q1");
	String q2 = request.getParameter("q2");
	String q3 = request.getParameter("q3");
	String q4 = request.getParameter("q4");
	String q5 = request.getParameter("q5");
	String q6 = request.getParameter("q6");
	String q7 = request.getParameter("q7");
	String q8 = request.getParameter("q8");
	String q9 = request.getParameter("q9");
	String q10 = request.getParameter("q10");
	
	//野껉퀗�궢占쎈퓠 占쎈뎡占쎌뵬 野껊슣�뿫 占쎌젟癰귣�占쏙옙 占쎈뼖占쎌뱽 占쎈땾 占쎌뿳占쎈즲嚥∽옙 VO占쏙옙 DAO 占쎄문占쎄쉐
	GameVO game1 = null;
	GameVO game2 = null;
	GameDAO gameDAO = new GameDAO();
	
	//10揶쏉옙筌욑옙 筌욌뜄揆占쎈퓠 占쎈뎡占쎌뵬 3揶쏉옙筌욑옙 占쎌뒄占쎈꺖占쎈퓠 癰귨옙占쎌넅�몴占� 雅뚯눖�뮉 占쎈걗揶쏉옙占쎈뼄
	if(q1.equals("1")) {
		cooperative++;
		strategic++;
		luck++;
	}else {
		cooperative--;
		strategic--;
		luck--;
	}
	
	if(q2.equals("1")) {
		cooperative--;
		strategic++;
		luck++;
	}else {
		cooperative++;
		strategic--;
		luck--;
	}
	
	if(q3.equals("1")) {
		cooperative++;
		strategic--;
		luck++;
	}else {
		cooperative--;
		strategic++;
		luck--;
	}
	
	if(q4.equals("1")) {
		cooperative++;
		strategic++;
		luck--;
	}else {
		cooperative--;
		strategic--;
		luck++;
	}
	
	if(q5.equals("1")) {
		cooperative--;
		strategic--;
		luck++;
	}else {
		cooperative++;
		strategic--;
		luck--;
	}
	
	if(q6.equals("1")) {
		cooperative--;
		strategic++;
		luck--;
	}else {
		cooperative++;
		strategic--;
		luck++;
	}
	
	if(q7.equals("1")) {
		cooperative++;
		strategic--;
		luck--;
	}else {
		cooperative--;
		strategic++;
		luck++;
	}
	
	if(q8.equals("1")) {
		cooperative--;
		strategic--;
		luck--;
	}else {
		cooperative++;
		strategic++;
		luck++;
	}
	
	if(q9.equals("1")) {
		cooperative+=2;
		strategic-=2;
		luck-=2;
	}else {
		cooperative-=2;
		strategic+=2;
		luck+=2;
	}
	
	if(q10.equals("1")) {
		cooperative+=2;
		strategic+=2;
		luck-=2;
	}else {
		cooperative-=2;
		strategic-=2;
		luck+=2;
	}
	
	
	//野껉퀗�궢 占쎈솇占쎌젟 
	if(cooperative>0) {
		if(strategic>0) {
			if(luck>0) {
				//占쎌굤占쎌젾 占쎌읈占쎌셽 占쎌뒲�뜮占� 野껊슣�뿫
				//86甕곤옙 占쎈쑓占쎈굡 占쎌궎�뇡占� 占쎌맊占쎄숲
				//81甕곤옙 占쎈솴占쎈쑓沃섓옙 
				game1 = gameDAO.getGameBySeq(86);
				game2 = gameDAO.getGameBySeq(81);
			}else {
				//占쎌굤占쎌젾 占쎌읈占쎌셽 占쎈뼄占쎌젾 野껊슣�뿫
				//172甕곤옙 占쎈섧占쎌뵭
				//87甕곤옙 占쎌넅占쎌뵠占쎈뱜筌�袁る탣占쎈퓠占쎄퐣 占쎌궔 占쎈젶筌욑옙
				game1 = gameDAO.getGameBySeq(172);
				game2 = gameDAO.getGameBySeq(87);
			}
		}else {
			if(luck>0) {
				//占쎌굤占쎌젾 占쎈솁占쎈뼒 占쎌뒲�뜮占� 野껊슣�뿫
				//95甕곤옙 獄��굝�뼄占쎌뵠占쎈뮞
				//179甕곤옙 5�겫袁⑤쐲占쎌읈
				game1 = gameDAO.getGameBySeq(95);
				game2 = gameDAO.getGameBySeq(179);
			}else {
				//占쎌굤占쎌젾 占쎈솁占쎈뼒 占쎈뼄占쎌젾 野껊슣�뿫
				//89甕곤옙 占쎌쟿筌욑옙占쎈뮞占쎄묄占쎈뮞 占쎈툡獄쏆뮆以�
				//15甕곤옙 占쏙옙�겫�뜄�뵬占쎌벥 占쎈뮅占쏙옙
				game1 = gameDAO.getGameBySeq(89);
				game2 = gameDAO.getGameBySeq(15);
			}
		}
	}else {
		if(strategic>0) {
			if(luck>0) {
				//野껋럩�쎋 占쎌읈占쎌셽 占쎌뒲�뜮占� 野껊슣�뿫 
				//129甕곤옙 占쎄퐤疫꿸퀣�뻻占쏙옙
				//51甕곤옙 占쎈즲沃섎챶�빍占쎈섧
				game1 = gameDAO.getGameBySeq(129);
				game2 = gameDAO.getGameBySeq(51);
			}else {
				//野껋럩�쎋 占쎌읈占쎌셽 占쎈뼄占쎌젾 野껊슣�뿫 
				//47甕곤옙 �뜎�눖�봺占쎈즲
				//150甕곤옙 筌띾뜄�뀮�굜占� 占쎈쨨嚥∽옙
				game1 = gameDAO.getGameBySeq(47);
				game2 = gameDAO.getGameBySeq(150);
			}
		}else {
			if(luck>0) {
				//野껋럩�쎋 占쎈솁占쎈뼒 占쎌뒲�뜮占� 野껊슣�뿫 
				//21甕곤옙 占쎈막�뵳�덉퍦�뵳占�
				//213甕곤옙 5�룯占� 餓ο옙占쎈뼄
				game1 = gameDAO.getGameBySeq(21);
				game2 = gameDAO.getGameBySeq(213);
			}else {
				//野껋럩�쎋 占쎈솁占쎈뼒 占쎈뼄占쎌젾 野껊슣�뿫
				//123甕곤옙 7 占쎌뜚占쎈쐭占쎈뮞
				//110甕곤옙 占쎈뼎�눧�똾�뼒
				game1 = gameDAO.getGameBySeq(123);
				game2 = gameDAO.getGameBySeq(110);
			}
		}
	}
	
	//野껊슣�뿫 占쎌젟癰귨옙 占쎈뼖占쎈툡占쎄퐣 result.jsp嚥∽옙 癰귣�源�
	model.addAttribute("game1", game1);
	model.addAttribute("game2", game2);
	return "etc/result";
	}
	
}
