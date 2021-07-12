<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="com.javaex.vo.PersonVo" %>    
    
<%
	PersonVo personVo = (PersonVo)request.getAttribute("pVo");

	System.out.println(personVo);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<form action="/phonebook2/pbc" method="get">
	이름 : <input type="text" name="name" value="<%=personVo.getName()%>"> <br>
	핸드폰 : <input type="text" name="hp" value="<%=personVo.getHp() %>"> <br>
	회사번호 : <input type="text" name="company" value="<%=personVo.getCompany() %>"> <br>
	<input type="hidden" name="id" value="<%=personVo.getPresonId() %>">
	<input type="text" name="action" value="update">
	<button type="submit">등록</button>
</form>

</body>
</html>