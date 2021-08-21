<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="kr.smhrd.model.BoardVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script type="text/javascript"> //자바 스크립트 
  $(document).ready(()=>{
	 <c:if test="${ !empty sessionScope.msg}">    
	 	alert("${sessionScope.msg}");
	 	<c:remove var="msg"/>    //속성을 삭제할때 쓰는것 setAttribute가 되어있는 변수mag를 삭제
	 </c:if>
  });
  function delFn(idx) { //함수만들기(삭제)    
   //alert("DEL"); //삭제를 눌렀을때 경고창
    location.href = "<c:url value='/delete.do'/>?idx="+idx;
}
  function registerFn(writer) {
   location.href = "<c:url value='/RegisterForm.do'/>";
} 
  function out() {
	location.href="<c:url value='/logout.do'/>";
}
  </script>
</head>
<body>

	<div class="container">
		<h2>Main화면</h2>
		<div class="panel panel-default">
			<div class="panel-heading">
				<!-- 리케스트로 가져왔을때가 있을테니까 (밑에 설명과 이어짐)-->
				<c:if test="${sessionScope.userVO==null }">
					<!-- 세션으로 셋어트리뷰트 했을때는 sessionScope로 가져오고-->
					<form class="form-inline" action="<c:url value='/login.do'/>"
						method="post">
						<div class="form-group">
							<label>ID:</label> <input type="text" class="form-control"
								id="user_id" name="user_id">
						</div>
						<div class="form-group">
							<label>PWD:</label> <input type="password" class="form-control"
								id="password" name="password">
						</div>
						<button type="submit" class="btn btn-default">로그인</button>
					</form>
				</c:if>
				<c:if test="${sessionScope.userVO!=null }"> 
				${ sessionScope.userVO.user_name}님 환영합니다.
				<button type="button" class="btn btn-info" onclick="out()">로그아웃</button>
				</c:if>
			</div>
			<div class="panel-body">
				<table class="table table-bordered table-hover">
					<tr>
						<td>번호</td>
						<td>제목</td>
						<td>조회수</td>
						<td>작성자</td>
						<td>작성일</td>
						<td>삭제</td>
					</tr>
					<c:forEach var="vo" items="${list}">
						<tr>
							<td>${ vo.idx}</td>
							<td><a href="<c:url value='/content.do'/>?idx=${vo.idx}">${vo.title}</a></td>
							<td>${ vo.count}</td>
							<td>${ vo.writer}</td>
							<td>${ vo.indate}</td>
							<c:if
								test="${sessionScope.userVO==null || sessionScope.userVO.user_name!=vo.writer}">
								<td><input type="button" onclick="delFn(${ vo.idx})"
									value="삭제" class="btn btn-primary btn-sm" disabled="disabled"></td>
							</c:if>
							<c:if
								test="${sessionScope.userVO!=null && sessionScope.userVO.user_name==vo.writer}">
								<td><input type="button" onclick="delFn(${ vo.idx})"
									value="삭제" class="btn btn-primary btn-sm"></td>
							</c:if>
						</tr>
					</c:forEach>

					<tr>
						<td colspan="6" align="right"><c:if
								test="${!empty sessionScope.userVO }">
								<input type="button" value="글쓰기" onclick="registerFn()"
									class="btn btn-success btn-sm"></td>
						</c:if>
					</tr>
				</table>
			</div>
			<div class="panel-footer">빅데이터 4차(김채원)</div>
		</div>
	</div>

</body>
</html>