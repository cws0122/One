<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>


<!-- 로그인 화면 구성 -->
<main class="text-center position-absolute top-50 start-50 translate-middle">
  <form action="" method="post">
  	<input id="password" name="password" type="hidden" value="${reply.password }">
	<input id="boardseq" name="boardseq" type="hidden" value="${reply.boardseq }">
	<input id="seq" name="seq" type="hidden" value="${reply.seq }">
    <h1 class="h3 mb-5 fw-normal">비밀번호 확인</h1>

    <div class="form-floating">
      <input id="inputpassword" type="password" class="form-control mb-3" name="pwd">
      <label for="floatingPassword">비밀번호</label>
    </div>
    <a id="checkpassword" class="w-100 btn btn-lg btn-primary mb-3">확인</a>
    <a id="back" class="w-100 btn btn-lg btn-light" style="border: 1px solid black;">게시글로 돌아가기</a>
  </form>
</main>




<section style="height:670px;">
	
</section>


<script type="text/javascript">
	$('#back').click(function(){
		window.history.back();
	});
	
	$('#checkpassword').click(function(){
		var password = $('#password').val()
		var input = $('#inputpassword').val();
		var boardseq = $('#boardseq').val();
		var seq = $('#seq').val();
		if(password == input){
			$(location).attr("href" , "cmtupdateForm.do?seq=" + seq);
		}else{
			alert('암호를 다시 확인해주세요.');
			$('#inputpassword').focus();
		}
	})
</script>

<%@ include file="../footer.jsp" %>