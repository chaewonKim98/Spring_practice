<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="cpath" value="${pageContext.request.contextPath}" />
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
<script type="text/javascript">
	function goDel(idx) {
		   location.href="${cpath}/boardDelete.do?idx="+idx;
	}
	function goList(idx) {
		   location.href="${cpath}/boardList.do";
	}
</script>
</head>
<body>


	<div class="panel panel-default">
		<div class="panel-heading">안녕 여긴 Board View페이지양</div>
		<div class="panel-body">
			<form id="frm" method="post" action="${cpath }/boardUpdate.do">
			<input type="hidden" name="idx" value="${vo.idx }">
				<div class="form-group">
					<label>제목: </label> <input type="text" class="form-control"
						id="title" name="title" value="${vo.title }">
				</div>
				<div class="form-group">
					<label>내용:</label>
					<textarea class="form-control" rows="5" id=contents name="contents">${vo.contents }</textarea>
				</div>
				<div class="form-group">
					<label>작성자: </label> <input type="text" class="form-control"
						id="writer" name="writer" value="${vo.writer}" readonly="readonly">
						<!-- readonly="readonly": 읽기만! -->
				</div>
				<input type="submit" value="수정" class="btn btn-primary btn-sm">
				<input type="reset" class="btn btn-warning" value="취소" >
				<input type='button' value='삭제' class='btn btn-info btn-sm' onclick="goDel(${vo.idx})"> 
				<input type="reset" class="btn btn-success" value="목록" onclick="goList()">
				</button>
			</form>


		</div>
		<div class="panel-footer">빅데이터 4차(김채원)</div>
	</div>


</body>
</html>