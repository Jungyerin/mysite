<%@ page contentType="text/html;charset=UTF-8" %>
<%
	request.setCharacterEncoding("utf-8");
	String no=request.getParameter("no");
	String name=request.getParameter("name");
%>
<!doctype html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="/mysite/assets/css/guestbook.css" rel="stylesheet" type="text/css">
</head>
<body>
<div id="container">
<jsp:include page="/WEB-INF/views/include/header.jsp" />
		<div id="content">
			<div id="guestbook" class="delete-form">
				<form method="post" action="/mysite/guestbook">
					<input type="hidden" name="a" value="delete">
					<input type='hidden' name="no" value="<%=no%>">
					<input type='hidden' name="name" value="<%=name%>">
					<label>비밀번호</label>
					<input type="password" name="pwd">
					<input type="submit" value="확인">
				</form>
				<a href="">방명록 리스트</a>
			</div>
		</div>
		<jsp:include page="/WEB-INF/views/include/navigation.jsp"/>
		<jsp:include page="/WEB-INF/views/include/footer.jsp"/>
	</div>
</body>
</html>