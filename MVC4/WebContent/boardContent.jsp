<%@page import="kr.smhrd.model.BoardVO" %>
<%@page import="java.util.List" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
     
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
  
</head>
<body>

 
  <div class="panel panel-default">
    <div class="panel-heading">게시판 상세보기</div>
    <div class="panel-body">Panel Content</div>
    <form  id="ufrm" class="form-horizontal"method="post">
  <div class="form-group">
    <label class="control-label col-sm-2" for="email">번호:</label>
    <div class="col-sm-10">
     <input type="text" class="form-control" name="idx" id = "cidx" readonly="readonly">
    </div>
  </div>
  <div class="form-group">
    <label class="control-label col-sm-2" for="pwd">제목:</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" name="title" id = "ctitle">
    </div>
  </div>
  	<div class="form-group">
    <label class="control-label col-sm-2" for="pwd">내용:</label>
    <div class="col-sm-10">
     <textarea rows="8" cols="60" class = "form-control"name="contents" id = "ccontents"></textarea>
     <!-- j쿼리에서 id로 접근할 수 있도록 id는 겹치지 않도록 주의요망ㅋㅋㅋ -->
    </div>
  </div>
  <div class="form-group">
    <label class="control-label col-sm-2" >작성자:</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" readonly="readonly" id="cwriter">
    </div>
  </div>
 
  <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
    <c:if test="${!empty sessionScope.userVO}">
      <button type="button" class="btn btn-primary btn-sm" onclick="updateFn()">수정</button>
     </c:if>
      
      <button type="button" class="btn btn-sm btn-sm" onclick="contentFn1()">취소</button>
      <button type="button" class="btn btn-success btn-sm" onclick="closeFn()">
   <span class="glyphicon glyphicon-remove"></span>   
      닫기</button>
                                                            <!-- goList요게 들어갔었음 -->
    </div>
  </div>
</form>
   
    
  </div>
</div>
</body>
</html>