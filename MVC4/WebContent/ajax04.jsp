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
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script type="text/javascript">
$(document).ready(()=>{
	list();
});
function list() {
	$("#bc").css("display","none")
	$("#bf").css("display","none")
	$.ajax({     //서버로 요청하기위해 꼭 써야됨
	  	url : "ajaxlist.do",      //여기로 보내주셈
	  	type : "get",             //JSON = dic : {"idx":1,"name":"홍길동"}
	  	success : callBack,     //성공하면 콜백함수로
	  	dataType : "json",
	  	error : function() { error("error");}
  });
}
function callBack(data) {
	//alert(data);
	var view="<table class = 'table table-bordered'>";
	   view+="<tr>";
	   view+="<td>번호</td>";
	   view+="<td>제목</td>";
	   view+="<td>조회수</td>";
	   view+="<td>작성자</td>";
	   view+="<td>작성일</td>";
	   view+="</tr>";
	   $.each(data,(index,obj)=>{
		   view+="<c:set var='writer"+index+"' value='"obj.writer+"'/>";
		   view+="<tr>";
		   view+="<td id='idx"+index+"'>"+obj.idx+"</td>";
		   //view+="<td><a href='javascript:contentFn("+obj.idx+")'>"+obj.title+"</a></td>";  //링크를 클릭하고 idx값을 넘겨줌(밑에랑 다른방법)
		   view+="<td><a href='javascript:contentFn("+index+")'>"+obj.title+"</a></td>";
		   view+="<td>"+obj.count+"</td>";
		   view+="<td>"+obj.writer+"</td>";
		   view+="<td>"+obj.indate+"</td>";
		   
		   view+="<c:if test='${sessionScope.userVO==null || sessionScope.userVO.user_id!=\'admin\'}'>";
		   view+="<td><button class = 'btn btn-warning btn-sm' onclick='delBtn("+obj.idx+")' disabled='disabled'>삭제</button></td>";
		   view+="</c:if>"
		   
		   view+="<c:if test='${sessionScope.userVO.user_id==\'admin\'}'>";
		   view+="<td><button class = 'btn btn-warning btn-sm' onclick='delBtn("+obj.idx+")'>삭제</button></td>";
		   
		   view+="</c:if>"
		   view+="</tr>";
	   });     //json반복문
	   view+="<tr>";
	   view+="<td colspan='6'>";
	   view+="<c:if test='${!empty sessionScope.userVO}'>";
	   view+="<input type='button' value='글쓰기' class='btn btn-primary' onclick='btnWrite()'/>";
	   view+="</c:if>";
	   view+="<input type='reset' value='취소' class='btn btn-warning'";
	   view+="</td>";
	   view+="</tr>";
	   view+="</table>";
	   $("#msg").html(view);
}
function contentFn(index){
	   var idx = $("#idx"+index).text();
	   $.ajax({
	      url : "ajaxcontent.do",
	      type : "get",
	      data : {"idx" : idx},
	      dataType : "json",
	      success : callContent,
	      error : function(){alert("error")}
	   }); 
}
function contentFn1(){
	   var idx = $("#cidx").val();
	   $.ajax({
	      url : "ajaxcontent.do",
	      type : "get",
	      data : {"idx" : idx},
	      dataType : "json",
	      success : callContent,
	      error : function(){alert("error")}
	   }); 
}
function callContent(data) {
	$("#bf").css("display","none");
	$("#bc").css("display","block");
	var idx = data.idx;
	var title = data.title;
	var contents = data.contents;
	var writer = data.writer;
	$("#cidx").val(idx);
	$("#ctitle").val(title);
	$("#ccontents").val(contents);
	$("#cwriter").val(writer);
}

function btnWrite() {
	var writer='${sessionScope.userVO.user_name}';
	$("#bc").css("display","none");
	$("#bf").css("display","block");
	$("#resetBtn").trigger("click");   //=>강제로 resetBtn버튼에 클릭 이벤트를 발생하게 해준다.
	$("#writer").val(writer);
}
function delBtn(idx) {
	if(confirm("정말로 삭제 하시겠습니까?")==true){
	       $.ajax({
	          url : "ajaxdelete.do",
	          type : "get",
	          data : { "idx" : idx },
	          success : list,
	          error : function(){ alert("error"); }   
	       });
	   }else{
	      return false;
	   }
}
function writeFn() {
	var formdata=$("#frm").serialize();    //내가 쓴것을 한번에 다 들고오는 함수(form태그의 아이디 이름을 통해)
	$.ajax({
		url:"ajaxregister.do",    //글쓰기 버튼을 누르면 serialize을 가지고ajaxregister여기로 연결해서 처리해주고
		type:"post",
		data:formdata,
		success : list,           //처리가 성공하면 바로 list를 뽑아준다
		error : function(){ alert("error");}
	});
}
//id는 특정태그를 빠르게 접근하기위해서
//폼에서 넘어갈때는 name값을통해서 파라메터값이 넘어감
function closeFn() {
	$("#bc").css("display","none")
	$("#bf").css("display","none")
}
function updateFn() {
	var formdata=$("#ufrm").serialize();
	//alert(formdata);
	$.ajax({
		url:"ajaxupdate.do",    //글쓰기 버튼을 누르면 serialize을 가지고ajaxregister여기로 연결해서 처리해주고
		type:"post",
		data:formdata,
		success : list,           //처리가 성공하면 바로 list를 뽑아준다
		error : function(){ alert("error");}
	});
}
function loginFn() {
	var user_id=$("#user_id").val();        //id하나만 가지고 오기위해서 val을 씀
	var password=$("#password").val(); //serialize는 다 가져오는것
	$.ajax({
		url:"ajaxlogin.do",    //글쓰기 버튼을 누르면 serialize을 가지고ajaxregister여기로 연결해서 처리해주고
		type:"post",
		data: {"user_id":user_id,"password":password},  //"파라메터":벨류값
		success : function (data) {
			if(data=="No"){
				alert("회원인증이에 실패하셨습니다.")
			}else{
				
				location.href="ajax04.jsp";     //메인화면으로 돌아옴
			}
		},           //처리가 성공하면 바로 list를 뽑아준다
		error : function(){ alert("error");}
	});
}
function logoutFn() {
	   $.ajax({
	      url : "ajaxlogout.do",
	      type:"get",
	      success : function(){
	         alert("로그아웃되었습니다.")
	         location.href="ajax04.jsp";
	      },
	      error : function(){alert("error");}
	      
	   });
	}
</script>
</head>
<body>
	<div class="container">
		<h2>MVC(FrontController + POJO + HandlerMapping + MyBatis+
			Ajax)게시판</h2>
		<div class="panel panel-default">
			<div class="panel-heading">
				<c:if test="${sessionScope.userVO==null}">
					<!-- sessionScope requestScope(request로 셋어트리뷰트를 해준경우에)-->
					<form id="loginfrm" class="form-inline" method="post">
						<div class="form-group">
							<label>ID:</label> <input type="text" class="form-control"
								id="user_id" name="user_id">
						</div>
						<div class="form-group">
							<label>PWD:</label> <input type="password" class="form-control"
								id="password" name="password">
						</div>
						<button type="button" class="btn btn-info" onclick="loginFn()">Login</button>
					</form>
				</c:if>
				<c:if test="${sessionScope.userVO!=null}">
				${sessionScope.userVO.user_name}님 방문을 환영합니다
				<input type="button" value="로그아웃" onclick="logoutFn()">
				</c:if>

			</div>
			<div class="panel-body">
				<div id="msg"></div>
				<div style="display: none;" id="bf">
					<c:import url="boardForm.jsp" />
				</div>

				<div style="display: none;" id="bc">
					<c:import url="boardContent.jsp" />
				</div>
			</div>
			<div class="panel-footer">빅데이터 분석 서비스 개발자 과정 4차 (김채원)</div>
		</div>
	</div>



</body>
</html>