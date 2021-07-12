<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ page import="java.util.List" %>
<%@ page import="com.javaex.vo.PersonVo" %>
	
<%
//request안에 데이터사용 --> pList
	List<PersonVo> personList = (List<PersonVo>)request.getAttribute(/*set으로 설정한 personList의 이름*/"pList");
	//request.getAttribute() --> 사용 시 type에 맞게 형변환 해 주어야 한다.(request가 Object형식으로 되어있기때문)
	System.out.println("jsp에서 출력 : " + personList);
	
	
	//Attribute에는 여러가지 값을 넣어놓을 수 있다.
	/*
	String name = (String)request.getAttribute("name");
	System.out.println(name);
	
	int num = (int)request.getAttribute("num");
	System.out.println(num);
	*/
%>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>전화번호 리스트</h1>
	<p>입력한 정보내역 입니다.</p>


<%
for(int i = 0; i < personList.size(); i++){ 
%>
	<table border="1">
		<tr>
			<td>이름</td>
			<td><%=personList.get(i).getName() %></td>
		</tr>
		<tr>
			<td>핸드폰</td>
			<td><%=personList.get(i).getHp() %></td>
		</tr>
		<tr>
			<td>회사번호</td>
			<td><%=personList.get(i).getCompany() %></td>
		</tr>
		<tr>
			<td><a href="/phonebook2/pbc?action=uform&id=<%=personList.get(i).getPresonId() %>">[수정]</a></td>
			<td><a href="/phonebook2/pbc?action=delete&id=<%=personList.get(i).getPresonId() %>">[삭제]</a></td>
		</tr>
	</table>
	<br>
	<% 
	}
	%>
	
	
</body>
</html>