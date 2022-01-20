<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>

	<div class="clear"></div>
	
	<article>
		<div class="container">
		
			<!-- 공통부분 -->
			<div id="top_menu">
				<div id="top_title"><h1><a href="boardList.do">게시판</a></h1></div>
			</div>
			
				<form action="searchBoard.do" type="post">
					<div class="col-md-12 p-3 mt-3">
				     	<div  style="float: right;">
				     		<select class="mr-3" name="searchCondition" style="width : 140px; height : 35px;">
								<c:forEach items="${conditionMap}" var="option">
									<option value="${option.key}">${option.value}</option>
								</c:forEach>
							</select>
							<input title="검색" class="formText" name="searchKeyword" type="text" id="" style="height : 35px;">
							<input type="submit" class="btn btn-primary" style="height : 35px;" value="검색">
				     	</div>
				     </div>  
			     </form>
			
			<!-- 글목록 -->
			<div id="boardList" >
			
				<div class="row justify-content-between mt-5">
					<div class="col-4 fw-bold">전체게시판</div>
					<form name="excelForm" id="excelForm" method="post" action="AllexcelDown.do">
						<input type="submit" id="excelDown" value="EXCEL 다운" >
					</form>
				</div>
				<table id="boardTable" class="table project-table table-centered table-nowrap mt-4">
					<tr class="text-center">
						<th style="width: 8%">글번호</th>
						<th style="width: 50%">제목</th>
						<th style="width: 10%">작성자</th>
						<th style="width: 10%">작성일</th>
						<th style="width: 6%">조회수</th>
						<th style="width: 10%">첨부파일 개수</th>
					</tr>
					<c:set var="num" value="${total - ((cPage-1) * numPerPage)}" />
					<c:forEach items="${AllBoardList}" var="boardVO">
					<tr>
						<td class="text-center">${num}</td>
						<td><c:forEach var="i" begin="1" end="${boardVO.depth}">${i eq boardVO.depth ? "ㄴ>" : "&nbsp;&nbsp;" }</c:forEach>
						<c:choose>
							<c:when test="${boardVO.deleted == 1}">
								<span style="color : blue">사용자에 의하여 삭제된 글입니다.</span>
							</c:when>
							<c:otherwise>
								<c:if test="${boardVO.count == 0 }">
									<a href="boardDetail.do?seq=${boardVO.seq}"><c:out value="${boardVO.title}" /></a>
								</c:if>
								<c:if test="${boardVO.count > 0 }">
									<a href="boardDetail.do?seq=${boardVO.seq}"><c:out value="${boardVO.title}" /><span style="color: red">&nbsp;(${boardVO.count})</span></a>
								</c:if>
							</c:otherwise>
						</c:choose>
						</td>
						<c:choose>
							<c:when test="${boardVO.deleted == 1 }">
								<td class="text-center"></td>
								<td class="text-center"></td>
								<td class="text-center"></td>
								<td class="text-center"></td>
							</c:when>
							<c:otherwise>
								<td class="text-center"><c:out value="${boardVO.writer}" /></td>
								<td class="text-center"><c:out value="${boardVO.regdate}" /></td>
								<td class="text-center">${boardVO.viewcnt}</td>
								<td class="text-center" style="color: blue;">${boardVO.filecount }</td>
							</c:otherwise>
						</c:choose>
					</tr>
					<c:set var="num" value="${num - 1 }" />
					</c:forEach>
				</table>
				<div class="my-5"></div>
			</div>
			
			<div>
				<a class="w-100 btn btn-lg btn-light" href="boardWrite.do">글 작성하기</a>
			</div>
			
			<div id="paginate" class="mb-5 nav justify-content-center">
					${pageBar}
				</div>
			

				
		</div>	
		
		
		
	</article>	




	







<%@ include file="../footer.jsp" %>