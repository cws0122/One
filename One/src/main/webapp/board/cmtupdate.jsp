<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>

	<div class="clear"></div> <!-- css : .clear{clear:both;} -->
	
	<article>
		<div class="container">
			<div>
				 <form id="cmtForm" action="updateCmt.do" method="post">
					<div class="w-100">
						<input type="hidden" value="${reply.boardseq}" name="boardseq">
						<input type="hidden" value="${reply.seq}" name="seq">
						 작성자 <input id="writer" type="text" name="writer" onchange="checkwriter(this)" value="<c:out value='${reply.writer }'/>"> <br>
						 비밀번호 <input id="password" type="password" name="password" onchange="checkpassword(this)" value="<c:out value='${reply.password }'/>"> <br> 
						<p id="check_password" style="color:red"></p>
						<textarea id="content" class="w-100 p-4" name="content" style="resize: none;" rows="4" onchange="checkcontent(this)"><c:out value="${reply.content}" /></textarea>
						<p id="content-count">(0 / 300)</p>
						<button id="send" class="w-auto btn btn-md btn-primary border me-2" type="button">수정 확인</button>
						<a id="delete" class="w-auto btn btn-md btn-primary border me-2">삭제하기</a>
   						<a class="w-auto btn btn-md btn-light border" href="boardDetail.do?seq=${reply.boardseq}">돌아가기</a>
					</div>
				</form> 
				<form id="deleteForm" action="deleteCmt.do">
					<input type="hidden" value="${reply.seq}" name="seq">
					<input type="hidden" value="${reply.boardseq}" name="boardseq">
				</form>
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

$('#delete').click(function(){
	if(confirm('삭제하시겠습니까?'))
	$('#deleteForm').submit();
});

</script>


<%@ include file="../footer.jsp" %>