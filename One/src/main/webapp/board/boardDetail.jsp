<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>

	<div class="clear"></div> 
	
	<article>
		<div class="container">

			<!-- 글 상세 -->
			<div id="boardDetatil" class="border-top border-2 border-dark my-3">
				<!-- 글 상세 영역 -->
				<div id="innerBoardDetail" class="border w-100 my-5 px-5">
					<!-- 제목영역 -->
					<div id="boardDetailTitle" class="border-bottom py-4">
						<div class="pt-4 fs-5"><c:out value="${board.title}" /></div>
						<div id="boardDetailNickname"><c:out value="${board.writer}" /></div>
						<div id="boardDetailDate">${board.regdate }</div>
					</div>
					<!-- 내용 -->
					
						<div id="boardDetailContent" style="white-space: pre-line; word-wrap: break-word;"><c:out value="${board.content}" /></div>
					
					<div>
						<ul>
							<c:forEach items="${fileList}" var="file">
								<li>
									<div>
										<a href="<c:url value="/filedownload.do?seq=${file.SEQ}" />" style="color : blue">${file.ORI_FILENAME}</a>
										<span>&nbsp;${file.FILE_SIZE}kb</span>
									</div>
								</li>
							</c:forEach>
						</ul>
					</div>
					<!-- 버튼 - 목록 // 수정, 삭제 -->
					<div id="boardDtailBtn" class="border-top p-4" >
						<a class="btn btn-dark" onclick="location.href='boardList.do'">목록으로</a>
						<form action="replyForm.do" method="post">
							<input type="hidden" name="seq" value="${board.seq}">
							<input type="submit" class="btn btn-dark" value="답글 달기">
						</form>
							<form action="checkpassword.do" method="post">
								<input name="seq" type="hidden" value="${board.seq}">
								<input name="password" type="hidden" value="${board.password}">
								<input class="btn btn-dark" type="submit" value="수정 삭제하러 가기">
							</form>
						<div style="height: 150px;"></div>
					</div>
				</div>
				
				<!-- 댓글영역 -->
				<div>
					<form id="cmtForm" action="insertCmt.do" method="post">
						<div class="w-100">
							<input type="hidden" value="${board.seq}" name="boardseq">
							 작성자 <input title="작성자" id="writer" type="text" name="writer" onchange="checkwriter(this)"> <br>
							 비밀번호 <input title="비밀번호" id="password" type="password" name="password" onchange="checkcontent(this)"> <br> <p id="check_password" style="color:red"></p>
							<textarea id="content" class="w-100 p-4" placeholder="댓글을 작성해주세요." name="content" style="resize: none;" rows="4" onchange="checkcontent(this)"></textarea>
							<p id="content-count">(0 / 300)</p>
							<button id="send" type="button">작성하기</button>
						</div>
					</form>
				</div>
				<div id="boardCmt" class="border w-100 my-5 px-5 pb-3">
					<div id="boardCmtList">
						<ul>
							<c:forEach items="${replyList}" var="reply">
								<li class="border-bottom py-4">
								<strong><c:out value="${reply.writer }" /></strong>
								<div id="cmtContent"><c:out value="${reply.content }" /></div>
								<%-- <button onclick="location.href='commentDetail.do?seq=${reply.seq}'">댓글 수정 , 삭제</button> --%>
								<form action="CmtpassChk.do" method="post">
									<input name="password" type="hidden" value="${reply.password }">
									<input name="boardseq" type="hidden" value="${reply.boardseq }">
									<input name="seq" type="hidden" value="${reply.seq }">
									<input type="submit" value="댓글 수정 , 삭제">
								</form>
								<div id="cmtDate"><c:out value="${reply.regdate }" /></div>
								</li>
							</c:forEach>
						</ul>
					</div>
				</div>
				
			</div>	
		</div>	
		
	</article>	
	
<script type="text/javascript">


$(document).ready(function(){
	$('#cmtForm').validate({
		rules: {
			writer: {required: true, minlength: 2},
			password: {required: true},
			content : {required: true}
		},
		
		messages: {
			writer: {required: "작성자를 입력하세요." , minlength: "작성자는 최소 2자이상 입력해주세요"},
			password: {required: "비밀번호를 입력하세요."},
			content : {required: "내용을 입력하세요."}
		},
		
		submitHandler: function(frm){
			frm.submit();
		},
		success: function(e){
			//
		}
		
	});
});

//----------------------------------------------------

$('#content').keyup(function(e){
	var content = $(this).val();
	$("#content-count").text("(" + content.length + " / 300)");
	if(content.length > 300){
		alert("최대 300자까지 입력 가능합니다.");
		$(this).val(content.substring(0, 300));
		$('#content-count').text("(300 / 300)");
	}
});

$('#content').mouseout(function(e){
	var content = $(this).val();
	$("#content-count").text("(" + content.length + " / 300)");
	if(content.length > 300){
		alert("최대 300자까지 입력 가능합니다.");
		$(this).val(content.substring(0, 300));
		$('#content-count').text("(300 / 300)");
	}
});

$('#content').keydown(function(e){
	var content = $(this).val();
	$("#content-count").text("(" + content.length + " / 300)");
	if(content.length > 300){
		alert("최대 300자까지 입력 가능합니다.");
		$(this).val(content.substring(0, 300));
		$('#content-count').text("(300 / 300)");
	}
});

$('#content').keypress(function(e){
	var content = $(this).val();
	$("#content-count").text("(" + content.length + " / 300)");
	if(content.length > 300){
		alert("최대 300자까지 입력 가능합니다.");
		$(this).val(content.substring(0, 300));
		$('#content-count').text("(300 / 300)");
	}
});

$('#content').mouseover(function(e){
	var content = $(this).val();
	$("#content-count").text("(" + content.length + " / 300)");
	if(content.length > 300){
		alert("최대 300자까지 입력 가능합니다.");
		$(this).val(content.substring(0, 300));
		$('#content-count').text("(300 / 300)");
	}
});

function checkcontent(obj){
	var content = $(obj).val();
	$("#content-count").text("(" + content.length + " / 300)");
	if(content.length > 300){
		alert("최대 300자까지 입력 가능합니다.");
		$(obj).val(content.substring(0, 300));
		$('#content-count').text("(300 / 300)");
	}
}

//---------------------------------------------------


$('#password').keyup(function(e){
	var password = $(this).val();
	var special = /[`~!@#$%^*|\\\'\":\/?]/gi;
	var number = /[0-9]/;	
	var text = /[a-zA-Z]/;
	$("#check_password").text("비밀번호를 입력해주세요.");
	if(password.length < 4 || password.length > 12){
		$("#check_password").text("비밀번호는 4자리 이상 12자리 이하로 입력해주세요");
		$(this).val(password.substring(0, 12));
	}else if(special.test(password) == false || text.test(password) == false || number.test(password) == false){
		$("#check_password").text("비밀번호에 문자와 특수문자 , 숫자를 모두 포함시켜주세요 단 & \ < > 은 사용하실 수 없습니다.");
	}else{
		$("#check_password").text("통과");
	}
});

$('#password').keydown(function(e){
	var password = $(this).val();
	var special = /[`~!@#$%^*|\\\'\":\/?]/gi;
	var number = /[0-9]/;	
	var text = /[a-zA-Z]/;
	$("#check_password").text("비밀번호를 입력해주세요.");
	if(password.length < 4 || password.length > 12){
		$("#check_password").text("비밀번호는 4자리 이상 12자리 이하로 입력해주세요");
		$(this).val(password.substring(0, 12));
	}else if(special.test(password) == false || text.test(password) == false || number.test(password) == false){
		$("#check_password").text("비밀번호에 문자와 특수문자 , 숫자를 모두 포함시켜주세요 단 & \ < > 은 사용하실 수 없습니다.");
	}else{
		$("#check_password").text("통과");
	}
});

$('#password').keypress(function(e){
	var password = $(this).val();
	var special = /[`~!@#$%^*|\\\'\":\/?]/gi;
	var number = /[0-9]/;	
	var text = /[a-zA-Z]/;
	$("#check_password").text("비밀번호를 입력해주세요.");
	if(password.length < 4 || password.length > 12){
		$("#check_password").text("비밀번호는 4자리 이상 12자리 이하로 입력해주세요");
		$(this).val(password.substring(0, 12));
	}else if(special.test(password) == false || text.test(password) == false || number.test(password) == false){
		$("#check_password").text("비밀번호에 문자와 특수문자 , 숫자를 모두 포함시켜주세요 단 & \ < > 은 사용하실 수 없습니다.");
	}else{
		$("#check_password").text("통과");
	}
});

$('#password').mouseover(function(e){
	var password = $(this).val();
	var special = /[`~!@#$%^*|\\\'\":\/?]/gi;
	var number = /[0-9]/;	
	var text = /[a-zA-Z]/;
	$("#check_password").text("비밀번호를 입력해주세요.");
	if(password.length < 4 || password.length > 12){
		$("#check_password").text("비밀번호는 4자리 이상 12자리 이하로 입력해주세요");
		$(this).val(password.substring(0, 12));
	}else if(special.test(password) == false || text.test(password) == false || number.test(password) == false){
		$("#check_password").text("비밀번호에 문자와 특수문자 , 숫자를 모두 포함시켜주세요 단 & \ < > 은 사용하실 수 없습니다.");
	}else{
		$("#check_password").text("통과");
	}
});

$('#password').mouseout(function(e){
	var password = $(this).val();
	var special = /[`~!@#$%^*|\\\'\":\/?]/gi;
	var number = /[0-9]/;	
	var text = /[a-zA-Z]/;
	$("#check_password").text("비밀번호를 입력해주세요.");
	if(password.length < 4 || password.length > 12){
		$("#check_password").text("비밀번호는 4자리 이상 12자리 이하로 입력해주세요");
		$(this).val(password.substring(0, 12));
	}else if(special.test(password) == false || text.test(password) == false || number.test(password) == false){
		$("#check_password").text("비밀번호에 문자와 특수문자 , 숫자를 모두 포함시켜주세요 단 & \ < > 은 사용하실 수 없습니다.");
	}else{
		$("#check_password").text("통과");
	}
});

function checkpassword(obj){
	var password = $(obj).val();
	var special = /[`~!@#$%^*|\\\'\":\/?]/gi;
	var number = /[0-9]/;	
	var text = /[a-zA-Z]/;
	$("#check_password").text("비밀번호를 입력해주세요.");
	if(password.length < 4 || password.length > 12){
		$("#check_password").text("비밀번호는 4자리 이상 12자리 이하로 입력해주세요");
		$(obj).val(password.substring(0, 12));
	}else if(special.test(password) == false || text.test(password) == false || number.test(password) == false){
		$("#check_password").text("비밀번호에 문자와 특수문자 , 숫자를 모두 포함시켜주세요 단 & \ < > 은 사용하실 수 없습니다.");
	}else{
		$("#check_password").text("통과");
	}
}

//-------------------------------------------------

$('#writer').keyup(function(e){
	var writer = $(this).val();
	if(writer.length > 8){
		alert("작성자는 최대 8글자까지 입력 가능합니다.");
		$(this).val(writer.substring(0 , 8));
	}
});

$('#writer').keydown(function(e){
	var writer = $(this).val();
	if(writer.length > 8){
		alert("작성자는 최대 8글자까지 입력 가능합니다.");
		$(this).val(writer.substring(0 , 8));
	}
});

$('#writer').keypress(function(e){
	var writer = $(this).val();
	if(writer.length > 8){
		alert("작성자는 최대 8글자까지 입력 가능합니다.");
		$(this).val(writer.substring(0 , 8));
	}
});

$('#writer').mouseover(function(e){
	var writer = $(this).val();
	if(writer.length > 8){
		alert("작성자는 최대 8글자까지 입력 가능합니다.");
		$(this).val(writer.substring(0 , 8));
	}
});

$('#writer').mouseout(function(e){
	var writer = $(this).val();
	if(writer.length > 8){
		alert("작성자는 최대 8글자까지 입력 가능합니다.");
		$(this).val(writer.substring(0 , 8));
	}
});

function checkwriter(obj){
	var writer = $(obj).val();
	if(writer.length > 8){
		alert("작성자는 최대 8글자까지 입력 가능합니다.");
		$(obj).val(writer.substring(0 , 8));
	}
}

$('#send').click(function(){
	var password = $('#password').val();
	var special = /[`~!@#$%^*|\\\'\":\/?]/gi;
	var number = /[0-9]/;	
	var text = /[a-zA-Z]/;
	var check = false;
	if(password.length < 4 || password.length > 12){
		$('#password').val(password.substring(0, 12));
		alert('비밀번호 항목을 확인해주세요');
	}else if(special.test(password) == false || text.test(password) == false || number.test(password) == false){
		alert('비밀번호 항목을 확인해주세요');
	}else{
		check = true;
	}
	
	if(check){
		$('#cmtForm').submit();
	}

});







</script>

<%@ include file="../footer.jsp" %>