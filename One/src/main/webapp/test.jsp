<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	try{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		System.out.println("jdbc연결댐");
	}catch(Exception e){
		e.printStackTrace();
	}

%>
댓냐 ㅋㅋ
</body>
</html>