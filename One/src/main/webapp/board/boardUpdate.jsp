<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>

	<div class="clear"></div> <!-- css : .clear{clear:both;} -->
	
	<article>
		<div class="container">

			<!-- 글작성 -->

			<div id="boardWrite" class="nav justify-content-center mt-5 w-100" style="display:block;">
				<h3>게시글 수정</h3>
				<form id="updateForm" class="border p-5 my-5 w-75" action="updateBoard.do" method="post" enctype="multipart/form-data">
					<input type="hidden" id="boardseq" name="boardseq" value="${board.seq }">
					<input type="hidden" id="seq" name="seq" value="${board.seq }">
					<div id="fileB">
						<input type="hidden" id="ORIfileNoDel" name="fileNoDel">
						<input type="hidden" id="fileNameDel" name="fileNameDel[]" value="">
					</div>
					
					<table class="table project-table table-centered table-nowrap mt-4 w-100" >
						<tr>
							<th class="table-secondary text-center">작성자</th>
							<td class="input-select">
								<input id="writer" type="text" name="writer" onchange="checkwriter(this)" value="<c:out value='${board.writer }'/>">
							</td>
						</tr>	
						<!-- 리뷰/질문게시판 선택시에만 나타나도록. 팝업창 띄우기: searchGame.jsp -->
						<tr>
							<th class="table-secondary col-2 text-center">비밀번호</th>
							<td class="input-select col-9">
								<input id="password" type="password" name="password" onchange="checkpassword(this)" value="<c:out value='${board.password }'/>">
								<p id="check_password" style="color : red"></p>
							</td>
						</tr>
						<!--  -->
						<tr>
							<th class="table-secondary col-2 text-center">제목</th>
							<td class="input-select col-9">
								<input id="title" type="text" name="title" class="w-100" onchange="checktitle(this)" value="<c:out value='${board.title }'/>">
								<div>
									<span id="title-count">(0 / 50)</span>
								</div>
							</td>
						</tr>	
						<tr>
							<th class="table-secondary col-2 text-center">내용</th>
							<td class="input-select col-9">
								<textarea id="content" class="w-100" rows="20" name="content" style="resize: none;" onchange="checkcontent(this)"><c:out value="${board.content }"/></textarea>
								<div>
									<span id="content-count">(0 / 700)</span>
								</div>
							</td>
						</tr>
						<tr>
							<th>
								첨부 파일
								<br>
								<button type="button" href="#this" onclick="addFile()">파일 추가하기</button>
							</th>
							<td>
								<div id="file-list">
									<c:forEach items="${file}" var="file" varStatus="var">
										<div>
											<input type="hidden" name="SEQ_${file.SEQ}" id="seq" value="${file.SEQ }">
											<input type="hidden" id="FILE_NAME" name="FILENAME" value="FILE_NO_${var.index}">
											<a href="#this" id="fileName">${file.ORI_FILENAME}</a>
											<input type="hidden" name="EXSize" value="${file.FILE_SIZE}">
											<span name="file_${var.index}">${Math.ceil(file.FILE_SIZE)}KB</span>
											<a name="check2"></a>
											<button id="fileDel" onclick="fn_del('${file.SEQ}' , 'FILE_NO_${var.index}');" type="button">삭제</button>
										</div>
									</c:forEach>
								</div>
							</td>
						</tr>
					
					</table>	
					<div id="buttons" class="w-100 nav justify-content-center">
						<button id="send" class="w-auto btn btn-md btn-primary border me-2" type="button">작성확인</button>
						<button id="delete" class="w-auto btn btn-md btn-primary border me-2" type="button">삭제하기</button>
   						<button class="w-auto btn btn-md btn-light border" type="button" onclick="location.href='boardDetail.do?seq=${board.seq}'">돌아가기</button>
					</div>
				</form>
				<form id="deleteForm" action="deleteBoard.do" method="post">
					<input type="hidden" value="${board.seq}" name="seq">
					<input type="hidden" value="${board.bgroup}" name="bgroup">
					<input type="hidden" value="${board.grouporder}" name="grouporder">
					<input type="hidden" value="${board.depth}" name="depth">
				</form>
			</div>
				
		</div>	
		
	</article>	


<!-- 글자 수 체크하는 스크립트  / 유효성 검사 벨리데이션 사용-->
<script type="text/javascript">

	$(document).ready(function(){
		$('#updateForm').validate({
			rules: {
				writer: {required: true, minlength: 2},
				password: {required: true},
				title : {required: true},
				content : {required: true}
			},
			
			messages: {
				writer: {required: "작성자를 입력하세요." , minlength: "작성자는 최소 2자이상 입력해주세요"},
				password: {required: "비밀번호를 입력하세요."},
				title: {required: "제목을 입력하세요."},
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
	
	var g_count = 1;
	var fileSizes = [];
	var AllFileSize = 0;
	var EXfiles = [];
	var EX = 0;

	$('#title').keyup(function(e){
		var title = $(this).val();
		$('#title-count').text("(" + title.length + " / 50)");
		if(title.length > 50){
			alert("제목은 최대 50글자까지 입력 가능합니다.");
			$(this).val(title.substring(0 , 50));
			$('#title-count').text("(50 / 50)");
		}
	});
	
	$('#title').keydown(function(e){
		var title = $(this).val();
		$('#title-count').text("(" + title.length + " / 50)");
		if(title.length > 50){
			alert("제목은 최대 50글자까지 입력 가능합니다.");
			$(this).val(title.substring(0 , 50));
			$('#title-count').text("(50 / 50)");
		}
	});
	
	$('#title').keypress(function(e){
		var title = $(this).val();
		$('#title-count').text("(" + title.length + " / 50)");
		if(title.length > 50){
			alert("제목은 최대 50글자까지 입력 가능합니다.");
			$(this).val(title.substring(0 , 50));
			$('#title-count').text("(50 / 50)");
		}
	});
	
	$('#title').mouseover(function(e){
		var title = $(this).val();
		$('#title-count').text("(" + title.length + " / 50)");
		if(title.length > 50){
			alert("제목은 최대 50글자까지 입력 가능합니다.");
			$(this).val(title.substring(0 , 50));
			$('#title-count').text("(50 / 50)");
		}
	});
	
	$('#title').mouseout(function(e){
		var title = $(this).val();
		$('#title-count').text("(" + title.length + " / 50)");
		if(title.length > 50){
			alert('제목은 최대 50글자까지 입력 가능합니다.');
			$(this).val(title.substring(0 , 50));
			$('#title-count').text("(50 / 50)");
		}
	});
	function checktitle(obj){
		var title = $(obj).val();
		$('#title-count').text("(" + title.length + " / 50)");
		if(title.length > 50){
			alert("제목은 최대 50글자까지 입력 가능합니다.");
			$(obj).val(title.substring(0 , 50));
			$('#title-count').text("(50 / 50)");
		}
	}

	
	//----------------------------------------------------
	
	$('#content').keyup(function(e){
		var content = $(this).val();
		$("#content-count").text("(" + content.length + " / 700)");
		if(content.length > 700){
			alert("최대 700자까지 입력 가능합니다.");
			$(this).val(content.substring(0, 700));
			$('#content-count').text("(700 / 700)");
		}
	});
	
	$('#content').keydown(function(e){
		var content = $(this).val();
		$("#content-count").text("(" + content.length + " / 700)");
		if(content.length > 700){
			alert("최대 700자까지 입력 가능합니다.");
			$(this).val(content.substring(0, 700));
			$('#content-count').text("(700 / 700)");
		}
	});
	
	$('#content').keypress(function(e){
		var content = $(this).val();
		$("#content-count").text("(" + content.length + " / 700)");
		if(content.length > 700){
			alert("최대 700자까지 입력 가능합니다.");
			$(this).val(content.substring(0, 700));
			$('#content-count').text("(700 / 700)");
		}
	});
	
	$('#content').mouseover(function(e){
		var content = $(this).val();
		$("#content-count").text("(" + content.length + " / 700)");
		if(content.length > 700){
			alert("최대 700자까지 입력 가능합니다.");
			$(this).val(content.substring(0, 700));
			$('#content-count').text("(700 / 700)");
		}
	});
	
	$('#content').mouseout(function(e){
		var content = $(this).val();
		$("#content-count").text("(" + content.length + " / 700)");
		if(content.length > 700){
			alert("최대 700자까지 입력 가능합니다.");
			$(this).val(content.substring(0, 700));
			$('#content-count').text("(700 / 700)");
		}
	});
	
	function checkcontent(obj){
		var content = $(obj).val();
		$("#content-count").text("(" + content.length + " / 700)");
		if(content.length > 700){
			alert("최대 700자까지 입력 가능합니다.");
			$(obj).val(content.substring(0, 700));
			$('#content-count').text("(700 / 700)");
		}
	}
	
	//---------------------------------------------------
	
	
	$('#password').keyup(function(e){
		var password = $(this).val();
		var special = /[`~!@#$%^*|\\\'\":\/?]/gi;
		var number = /[0-9]/;	
		var text = /[a-zA-Z]/;
		$("#check_password").text("비밀번호를 입력해주세요.");
		if(password.length < 4 || password.length > 16){
			$("#check_password").text("비밀번호는 4자리 이상 16자리 이하로 입력해주세요");
			$(this).val(password.substring(0, 16));
		}else if(special.test(password) == false || text.test(password) == false || number.test(password) == false){
			$("#check_password").text("비밀번호에 문자와 특수문자 , 숫자를 모두 포함시켜주세요");
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
		if(password.length < 4 || password.length > 16){
			$("#check_password").text("비밀번호는 4자리 이상 16자리 이하로 입력해주세요");
			$(this).val(password.substring(0, 16));
		}else if(special.test(password) == false || text.test(password) == false || number.test(password) == false){
			$("#check_password").text("비밀번호에 문자와 특수문자 , 숫자를 모두 포함시켜주세요");
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
		if(password.length < 4 || password.length > 16){
			$("#check_password").text("비밀번호는 4자리 이상 16자리 이하로 입력해주세요");
			$(this).val(password.substring(0, 16));
		}else if(special.test(password) == false || text.test(password) == false || number.test(password) == false){
			$("#check_password").text("비밀번호에 문자와 특수문자 , 숫자를 모두 포함시켜주세요");
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
		if(password.length < 4 || password.length > 16){
			$("#check_password").text("비밀번호는 4자리 이상 16자리 이하로 입력해주세요");
			$(this).val(password.substring(0, 16));
		}else if(special.test(password) == false || text.test(password) == false || number.test(password) == false){
			$("#check_password").text("비밀번호에 문자와 특수문자 , 숫자를 모두 포함시켜주세요");
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
		if(password.length < 4 || password.length > 16){
			$("#check_password").text("비밀번호는 4자리 이상 16자리 이하로 입력해주세요");
			$(this).val(password.substring(0, 16));
		}else if(special.test(password) == false || text.test(password) == false || number.test(password) == false){
			$("#check_password").text("비밀번호에 문자와 특수문자 , 숫자를 모두 포함시켜주세요");
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
		if(password.length < 4 || password.length > 16){
			$("#check_password").text("비밀번호는 4자리 이상 16자리 이하로 입력해주세요");
			$(obj).val(password.substring(0, 16));
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
		var rnumber = /[^0-9]/g;	
		var text = /[a-zA-Z]/;
		var check = false;
		
		var lengthTWO = $('a[name=check2]').length;
		var resize = 0;
		var change2 = "";
		for(var i=0; i<lengthTWO; i++){
			change2 = $('span[name=file_' + i + ']').text().replace(rnumber, "") / 10;
			resize += Number(change2);
		}
		
		var length = $('p[name=check]').length;
		var ssize = 0;
		var change = "";
		for(var i=1; i <= length; i++){
			change = $('p[name=file_' + i + ']').text().replace(rnumber, "");
			ssize += Number(change);
		}
		
		end = Math.ceil((ssize + resize) / 1024);
		console.log(end);
		
		if(password.length < 4 || password.length > 16){
			$('#password').val(password.substring(0, 16));
			alert('비밀번호 항목을 확인해주세요');
		}else if(special.test(password) == false || text.test(password) == false || number.test(password) == false){
			alert('비밀번호 항목을 확인해주세요');
		}else{
			check = true;
		}
		
		if(check){
			if($('input[name=fileNoDel]').length == 1){
				if(end > 50){
					alert("한 게시물에 50MB 이상 파일을 첨부할 수 없습니다. \n 현재 파일 용량 : " + end + "MB");
					end = 0;
				}else{
					$('#updateForm').submit();
				}
			}else if($('input[name=fileNoDel]').length > 1){
				if(end > 50){
					alert("한 게시물에 50MB 이상 파일을 첨부할 수 없습니다. \n 현재 파일 용량 : " + end + "MB");
					end = 0;
				}else{
					$('#updateForm').submit();
				}
			}
		}
	
	});
	
	function checkFile(length){
		
	}
	
	$('#delete').click(function(){
		if(confirm('삭제하시겠습니까?')) {
			$('#deleteForm').submit();	
		}else{
			return false;
		}
	})
	
	$("a[name='file-delete']").on("click", function(e){
		e.preventDefault();
		deleteFile($(this));
	});	
	
	
	$(document).on("click", "#fileDelBtn" , function(){
		$(this).parent().remove();
	});
	
	$(document).on("click", "#fileDel" , function(){
		$(this).parent().remove();
	});
	
	var fileIndex = 1;
	
	function addFile(){
		var str = "<div id='file_" + (fileIndex) + "'><input type='file' name='file_" + (fileIndex) + "'" + 
		" onchange='checkFileSize(this)'><button type='button' id='fileDelBtn'>삭제</button><p  name='file_" + (fileIndex) + "'></p><p name='check'></p></div>";
		fileIndex++;
		$('#file-list').append(str);
		$(document).on("click" , "#fileDelBtn" , function(){
			$(this).parent().remove();
		});
	}
	
	var fileNoArry = new Array();
	var fileNameArry = new Array();
	
	function fn_del(value, name){
		//<input type="hidden" id="fileNoDel" name="fileNoDel[]" value="">
		var str = "<input type='hidden' name='fileNoDel' value='" + value + "'>";
		$('#fileB').append(str);
		
		fileNameArry.push(name);
		$('#fileNameDel').attr("value", fileNameArry);
	}
	
	function checkFileSize(obj){
 		var filename = $(obj).attr('name');

 		var fileVal = $(obj).val();
 		if(fileVal != ""){
 			var ext = fileVal.split('.').pop().toLowerCase();
 			if($.inArray(ext, ["txt" , "xls" , "hwp" , "ppt" , "jpg" , "png" , "gif", "jpeg"]) == -1){
 				alert("파일 업로드는 txt, jpg, xls, hwp, ppt, png, gif, jpeg 파일만 가능합니다.");
 				obj.value = null;
 			}
 		}
		var file = obj.files;
		var chksize = 10 * 1024 * 1024;
		if(file[0].size > chksize){
			alert("10MB 이하 파일만 등록 가능합니다. \n\n" + "현재 파일 용량 ㅣ " + (Math.round(file[0].size / 1024 / 1024 * 100)) /100 + "MB");
		}else{
			fileSizes.push(file[0].size);
			$("p[name=" + filename + "]").text('파일 용량 : ' + Math.ceil((Math.round(file[0].size / 1024 * 100)) /100) + "KB");
			return;
		}
		obj.value = null;
	} 	
	
	function deleteFile(obj){
		obj.parent().remove();
	}
	
	
	
	
</script>








<%@ include file="../footer.jsp" %>