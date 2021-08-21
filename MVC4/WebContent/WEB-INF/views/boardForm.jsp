<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
<title>게시판 글쓰기</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>

	<div class="container">
		<h2>메인화면</h2>
		<div class="panel panel-default">
			<div class="panel-heading">게시판 글쓰기</div>
			<div class="panel-body">
				<form action="<c:url value='/Register.do'/>" method="post">
					<div class="form-group">
						<label>제목: </label> 
						<input type="text" class="form-control" id="title" name="title">
					</div>
					<div class="form-group">
						<label>내용:</label> 
						 <textarea class="form-control" rows="5" id=contents name="contents"></textarea>
					</div>	
					<div class="form-group">
						<label>작성자: </label> 
						<input type="text" class="form-control" id="writer" name="writer" value="${sessionScope.userVO.user_name }" readonly="readonly">
					</div>
					<input type="button" class="btn btn-primary" value="글쓰기">
					
				</form>


			</div>
			<div class="panel-footer">빅데이터 4차(김채원)</div>
		</div>
	</div>

</body>
</html>