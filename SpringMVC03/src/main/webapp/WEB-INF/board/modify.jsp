<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Board Modify</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
  <script type="text/javascript">
  	$(document).ready(()=>{
  		$("#list").click(()=>{
  			location.href="<c:url value='/list.do'/>";
  		});
  		
  		$("#remove").click(()=>{
  			location.href="<c:url value='/remove.do'/>?bno=${board.idx}";
  		});
  	});
  </script>

</head>
<body>
 
<div class="container">
  <h2>Board Modify Page</h2>
  <div class="panel panel-default">
    <div class="panel-heading">Board Modify Page</div>
    <div class="panel-body">
    
	<form action="<c:url value='/modify.do'/>" method="post">
		
		<div class="form-group">
			<label>Bno</label>
			<input type="text" class="form-control" name="idx" id="idx" value="${board.idx}" readonly="readonly">
		</div>
		
		<div class="form-group">
			<label>Title</label>
			<input type="text" class="form-control" name="title" id="title" value="${board.title}">
		</div>
		
		<div class="form-group">
			<label>Text area</label>
			<textarea class="form-control" rows="3" name="contents">${board.contents}</textarea>
		</div>
		
		<div class="form-group">
			<label>Writer</label>
			<input type="text" class="form-control" name="writer" value="${board.writer}" readonly="readonly">
		</div>
		
		<button type="submit" class="btn btn-primary">Modify</button>
		<button id="remove" type="button" class="btn btn-danger">Remove</button>
		<button id="list" type="button" class="btn btn-info">List</button>
	</form>
    
    </div>
    <div class="panel-footer">인프런 화이팅!</div>
  </div>
</div>

</body>
</html>