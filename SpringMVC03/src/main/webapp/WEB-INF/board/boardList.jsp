<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> <!-- formatting -->
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Bootstrap Example</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
  <script type="text/javascript">
  	$(document).ready(function() {
  		$("#regBtn").click(()=>{  // 람다식 표현 / #은 id를 접근할 때 사용
  			location.href="<c:url value='/register.do'/>";
  		});
  	});
  
  </script>
</head>
<body>
 
<div class="container">
  <h2>메인화면</h2>
  <div class="panel panel-default">
    <div class="panel-heading">Spring WEB MVC 게시판 만들기
    <button id="regBtn" type="button" class="btn btn-xs pull-right btn-primary">게시물 쓰기</button>
    </div>
    <div class="panel-body">
	    <div class="table-responsive">          
		  <table class="table table-hover">
		    <thead>
		      <tr>
		        <th>게시물 번호</th>
		        <th>제목</th>
		        <th>조회수</th>
		        <th>등록자</th>
		        <th>등록일</th>
		      </tr>
		    </thead>
		    <tbody>
		    <c:forEach var="vo" items="${list}">  <!-- 반복문 -->
		      <tr>
		        <td>${vo.idx}</td>
		        <td><a href="<c:url value='/get.do?bno=${vo.idx}'/>">${vo.title}</a></td>
		        <td>${vo.count}</td>
		        <td>${vo.writer}</td>
		        <td><fmt:formatDate pattern="yyyy-MM-dd" value="${vo.indate}"/></td>
		      </tr>
		    </c:forEach>
		    </tbody>
		  </table>
		  </div>
		</div>
    
    </div>
    <div class="panel-footer">인프런 화이팅!!!</div>
  </div>
</body>
</html>