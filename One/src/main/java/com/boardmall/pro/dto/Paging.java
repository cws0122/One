package com.boardmall.pro.dto;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class Paging {
	
	public void paging(HttpServletRequest request, String path, 
			List<GameVO> productList, int totalPage, int cPage, int numPerPage) {
		
		String pageBar = "";
		int pageBarSize = 5;
		int pageNo = ((cPage-1)/pageBarSize)*pageBarSize+1;
		int pageEnd = pageNo + pageBarSize - 1;
		
		// �럹�씠吏�諛�
		 if(pageNo==1) {
			 	if(cPage != 1) {
			 		pageBar += "<a href='" + request.getContextPath() + path + (1) + "&numPerPage=" + numPerPage + "'>[FIRST] </a>";
			 		pageBar += "<span> [PREV] &nbsp;</span>";
			 	} else {
			 		pageBar += "<span>[FIRST] [PREV] &nbsp;</span>";
			 	}	
	        } else {
	        	pageBar += "<a href='" + request.getContextPath() + path + (1) + "&numPerPage=" + numPerPage + "'>[FIRST] </a>";
	        	pageBar += "<a href='" + request.getContextPath() + path + (pageNo-1) + "&numPerPage=" + numPerPage + "'>[PREV] </a>";
	        }
	        //�꽑�깮�럹�씠吏� 留뚮뱾湲�
	        while(!(pageNo>pageEnd || pageNo>totalPage)) {
	        	if(cPage==pageNo) {
		        	pageBar += "<span class='cPage'>[" + pageNo + "] </span>";
		        } else {
		        	pageBar += "<a href='" + request.getContextPath() + path + (pageNo) + "&numPerPage=" + numPerPage + "'>[" + pageNo + "]</a>";
		        }
	        	pageNo++;
	        }
	        
	        if(pageNo>totalPage) {
	            pageBar += "<span>&nbsp; [NEXT] [END]</span>";
	        }else {
	            pageBar +="<a href='" + request.getContextPath() + path + (pageNo) + "&numPerPage=" + numPerPage + "'> [NEXT] </a>";
	            pageBar +="<a href='" + request.getContextPath() + path + (24) + "&numPerPage=" + numPerPage + "'> [END] &nbsp;</a>";
	        }
	        
	        request.setAttribute("productList", productList);
			request.setAttribute("cPage", cPage);
			request.setAttribute("numPerPage", numPerPage);
			request.setAttribute("pageBar", pageBar);
	}
	
	
	public void Boardpaging(HttpServletRequest request, String path, 
			List<BoardVO> BoardList, int totalPage, int cPage, int numPerPage) {
		
		String pageBar = "";
		//�럹�씠吏�諛� �궗�씠利�
		int pageBarSize = 5;
		// �럹�씠吏�諛붿쓽 媛��옣 �븵遺�遺� 踰덊샇.
		int pageNo = ((cPage-1)/pageBarSize) * pageBarSize + 1;
		// �쁽�옱 �럹�씠吏��쓽 留덉�留� �럹�씠吏�. [1][2][3][4][5] �뿉�꽌 5瑜� �쓽誘�.
		int pageEnd = pageNo + pageBarSize - 1;
		
		// �럹�씠吏�諛�
		 if(pageNo==1) {
			 	if(cPage != 1) {
			 		//cPage 1濡� �룎�븘媛��뒗 踰꾪듉
			 		pageBar += "<a href='" + request.getContextPath() + path + (1) + "&numPerPage=" + numPerPage + "'>[FIRST] </a>";
			 	} else {
			 	}	
	        } else {
	        	//cPage 1濡� �룎�븘媛��뒗 踰꾪듉
	        	pageBar += "<a href='" + request.getContextPath() + path + (1) + "&numPerPage=" + numPerPage + "'>[FIRST] </a>";
	        	// �겙 �럹�씠吏�瑜� �쟾�쑝濡� �꽆湲곕뒗 踰꾪듉
	        	pageBar += "<a href='" + request.getContextPath() + path + (pageNo-1) + "&numPerPage=" + numPerPage + "'>[PREV] </a>";
	        }
	        //�꽑�깮�럹�씠吏� 留뚮뱾湲� , 議곌굔 留뚯”�떆 臾댄븳諛섎났.
	        while(!(pageNo>pageEnd || pageNo>totalPage)) {
	        	//�꽑�깮�븳 �럹�씠吏��� �쁽�옱 �럹�씠吏�媛� 媛숈쓣 寃쎌슦. 留곹겕瑜� 鍮꾪솢�꽦�솕
	        	if(cPage==pageNo) {
		        	pageBar += "<span class='cPage'>[" + pageNo + "] </span>";
		        } else {
		        	// �꽑�깮�븳 �럹�씠吏��� �쁽�옱 �럹�씠吏�媛� �떎瑜쇨꼍�슦 留곹겕瑜� �솢�꽦�솕.
		        	pageBar += "<a href='" + request.getContextPath() + path + (pageNo) + "&numPerPage=" + numPerPage + "'>[" + pageNo + "]</a>";
		        }
	        	pageNo++;
	        }
	        //�쁽�옱 �럹�씠吏�媛� 珥� �럹�씠吏�蹂대떎 �겢 寃쎌슦.
	        if(pageNo>totalPage) {
	        	//�떎�쓬 �럹�씠吏��� 留덉�留� �럹�씠吏�瑜� 鍮� 留곹겕濡�.
	        }else {
	        	//�떎�쓬 �럹�씠吏�媛� 珥� �럹�씠吏�蹂대떎 �옉�쓣 寃쎌슦 留곹겕 �솢�꽦�솕 , �쐞�뿉�꽌 pageNo++媛� �맂 �긽�깭濡� �꽆�뼱�삤湲� �뻹臾몄뿉 �떎�쓬 �럹�씠吏�濡� �꽆�뼱媛꾨떎怨� �깮媛� 媛��뒫.
	            pageBar +="<a href='" + request.getContextPath() + path + (pageNo) + "&numPerPage=" + numPerPage + "'> [NEXT] </a>";
	            pageBar +="<a href='" + request.getContextPath() + path + (totalPage) + "&numPerPage=" + numPerPage + "'> [END] &nbsp;</a>";
	        }
	        
	        
	        request.setAttribute("AllBoardList", BoardList);
			request.setAttribute("cPage", cPage);
			request.setAttribute("numPerPage", numPerPage);
			request.setAttribute("pageBar", pageBar);
	}
	
	public void SearchBoardpaging(HttpServletRequest request, String path, 
			List<BoardVO> BoardList, int totalPage, int cPage, int numPerPage) {
		
		String pageBar = "";
		//�럹�씠吏�諛� �궗�씠利�
		int pageBarSize = 5;
		// �럹�씠吏�諛붿쓽 媛��옣 �븵遺�遺� 踰덊샇.
		int pageNo = ((cPage-1)/pageBarSize) * pageBarSize + 1;
		// �쁽�옱 �럹�씠吏��쓽 留덉�留� �럹�씠吏�. [1][2][3][4][5] �뿉�꽌 5瑜� �쓽誘�.
		int pageEnd = pageNo + pageBarSize - 1;
		
		// �럹�씠吏�諛�
		 if(pageNo==1) {
			 	if(cPage != 1) {
			 		//cPage 1濡� �룎�븘媛��뒗 踰꾪듉
			 		pageBar += "<a href='" + request.getContextPath() + path + (1) + "&numPerPage=" + numPerPage + "'>[FIRST] </a>";
			 	} else {
			 	}	
	        } else {
	        	//cPage 1濡� �룎�븘媛��뒗 踰꾪듉
	        	pageBar += "<a href='" + request.getContextPath() + path + (1) + "&numPerPage=" + numPerPage + "'>[FIRST] </a>";
	        	// �겙 �럹�씠吏�瑜� �쟾�쑝濡� �꽆湲곕뒗 踰꾪듉
	        	pageBar += "<a href='" + request.getContextPath() + path + (pageNo-1) + "&numPerPage=" + numPerPage + "'>[PREV] </a>";
	        }
	        //�꽑�깮�럹�씠吏� 留뚮뱾湲� , 議곌굔 留뚯”�떆 臾댄븳諛섎났.
	        while(!(pageNo>pageEnd || pageNo>totalPage)) {
	        	//�꽑�깮�븳 �럹�씠吏��� �쁽�옱 �럹�씠吏�媛� 媛숈쓣 寃쎌슦. 留곹겕瑜� 鍮꾪솢�꽦�솕
	        	if(cPage==pageNo) {
		        	pageBar += "<span class='cPage'>[" + pageNo + "] </span>";
		        } else {
		        	// �꽑�깮�븳 �럹�씠吏��� �쁽�옱 �럹�씠吏�媛� �떎瑜쇨꼍�슦 留곹겕瑜� �솢�꽦�솕.
		        	pageBar += "<a href='" + request.getContextPath() + path + (pageNo) + "&numPerPage=" + numPerPage + "'>[" + pageNo + "]</a>";
		        }
	        	pageNo++;
	        }
	        //�쁽�옱 �럹�씠吏�媛� 珥� �럹�씠吏�蹂대떎 �겢 寃쎌슦.
	        if(pageNo>totalPage) {
	        	//�떎�쓬 �럹�씠吏��� 留덉�留� �럹�씠吏�瑜� 鍮� 留곹겕濡�.
	        }else {
	        	//�떎�쓬 �럹�씠吏�媛� 珥� �럹�씠吏�蹂대떎 �옉�쓣 寃쎌슦 留곹겕 �솢�꽦�솕 , �쐞�뿉�꽌 pageNo++媛� �맂 �긽�깭濡� �꽆�뼱�삤湲� �뻹臾몄뿉 �떎�쓬 �럹�씠吏�濡� �꽆�뼱媛꾨떎怨� �깮媛� 媛��뒫.
	            pageBar +="<a href='" + request.getContextPath() + path + (pageNo) + "&numPerPage=" + numPerPage + "'> [NEXT] </a>";
	            pageBar +="<a href='" + request.getContextPath() + path + (totalPage) + "&numPerPage=" + numPerPage + "'> [END] &nbsp;</a>";
	        }
	        
	        
	        request.setAttribute("AllBoardList", BoardList);
			request.setAttribute("cPage", cPage);
			request.setAttribute("numPerPage", numPerPage);
			request.setAttribute("pageBar", pageBar);
	}
	
	
	public void pagingSearch(String[] playerArray, String[] playtimeArray, String[] tagArray, String[] tagArray2, HttpServletRequest request, String path, 
			List<GameVO> productList, int totalPage, int cPage, int numPerPage) {
		
		String pageBar = "";
		int pageBarSize = 5;
		int pageNo = ((cPage-1)/pageBarSize)*pageBarSize+1;
		int pageEnd = pageNo + pageBarSize - 1;
		
		// �럹�씠吏�諛�
		 if(pageNo==1) {
			 	if(cPage != 1) {
			 		pageBar += "<a href='javascript:void(0)' onclick='searchOptionPaging(" + 1 + ");'>[FIRST] </a>";
			 		pageBar += "<span> [PREV] &nbsp;</span>";
			 	} else {
			 		pageBar += "<span>[FIRST][PREV] &nbsp;</span>";
			 	}	
	        } else {
	        	pageBar += "<a href='javascript:void(0)' onclick='searchOptionPaging(" + 1 + ");'>[FIRST] </a>";
	        	pageBar += "<a href='javascript:void(0)' onclick='searchOptionPaging(" + (pageNo - 1) + ");'>[PREV] </a>";
	        }
	        //�꽑�깮�럹�씠吏� 留뚮뱾湲�
	        while(!(pageNo>pageEnd || pageNo>totalPage)) {
	        	if(cPage==pageNo) {
		        	pageBar += "<span class='cPage' style='font-weight:bold;color:#ffc107;'>[" + pageNo + "] </span>";
		        } else {
		        	pageBar += "<a href='javascript:void(0)' onclick='searchOptionPaging("+pageNo+");'>[" + pageNo + "]</a>";
		        }
	        	pageNo++;
	        }
	        
	        if(pageNo>totalPage) {
	            pageBar += "<span>&nbsp; [NEXT] [END]</span>";
	        }else {
	            pageBar +="<a href='javascript:void(0)' onclick='searchOptionPaging(" + pageNo + ");'>&nbsp; [NEXT] </a>";
	            pageBar +="<a href='javascript:void(0)' onclick='searchOptionPaging(" + totalPage + ");'> [END] &nbsp;</a>";
	        }
	        
	        request.setAttribute("productList", productList);
			request.setAttribute("cPage", cPage);
			request.setAttribute("numPerPage", numPerPage);
			request.setAttribute("pageBar", pageBar);
			request.setAttribute("playerArray", playerArray);
			request.setAttribute("playtimeArray", playtimeArray);
			request.setAttribute("tagArray", tagArray);
			request.setAttribute("tagArray2", tagArray2);
	}
}

