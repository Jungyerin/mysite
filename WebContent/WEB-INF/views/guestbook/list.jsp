<%@page import="com.jx372.mysite.vo.UserVo"%>
<%@page import="com.jx372.mysite.vo.EmailListVo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
     <%
    	UserVo authUser=(UserVo)session.getAttribute("authUser");
    %>
 	<%
		List<EmailListVo> list=(List<EmailListVo>)request.getAttribute("list");
	%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<%=request.getContextPath() %>/assets/css/main.css" rel="stylesheet" type="text/css">
<title>mysite</title>
</head>
<body>
<div id="container">
		<jsp:include page="/WEB-INF/views/include/header.jsp" />
		<div id="wrapper">
			<div id="content">
				<form action="<%=request.getContextPath() %>/guestbook?a=add" method="post">
	<!--input type="hidden" name="a" value="add"--> 
	<table border="1" width="500">
		<tr>
			<td>이름</td><td><input type="text" name="name" value="<%=authUser.getName()%>" ></td>
			<td>비밀번호</td><td><input type="password" name="pwd"></td>
		</tr>
		<tr>
			<td colspan="4"><textarea name="message" cols="60" rows="5"></textarea></td>
		</tr>
		<tr>
			<td colspan="4" align="right"><input type="submit" VALUE=" 등록 "></td>
		</tr>
	</table>
	</form>
	<br>
	
		
	<% 	int i=1;
		for(EmailListVo vo:list) { %>
	<table width="510" border="1">
		<tr>
			<td>[<%=i %>]</td> <!-- no값이 아니라 전체 list 수의 번호 순 -->
			<td><%=vo.getName() %></td>
			<td><%=vo.getDate() %></td>
			<td><a href="<%=request.getContextPath()%>/guestbook?a=deleteform&no=<%=vo.getNo()%>&name=<%=authUser.getName()%>">삭제</a></td>
		</tr>
		<tr>
			<td colspan="4"><%=vo.getMessage().replace("\n", "<br>") %></td>
		</tr>
	</table>
	<% 		i++;
		} 
	%>
			</div>
		</div>
		<jsp:include page="/WEB-INF/views/include/navigation.jsp"/>
		<jsp:include page="/WEB-INF/views/include/footer.jsp"/>
	</div>

</body>
</html>