<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="cpath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Bootstrap Example</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
  <script type="text/javascript"/>
</head>
<body>
<form action="${cpath}/MinInsert.do" method="post">
	<tr>
		<td><input type="text" name="id" >id</td>
	</tr>
	<tr>
		<td><input type="password" name="pw" >pw</td>
	</tr>
	<tr>
		<td><input type="text" name="name" >name</td>
	</tr>
	<tr>
		<td><input type="text" name="age" >age</td>
	</tr>
	<tr>
		<td><input type="submit" >완료</td>
	</tr>
</table>
</form>
</body>
</html>