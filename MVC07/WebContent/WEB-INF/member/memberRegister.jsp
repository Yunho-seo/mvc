<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>      
<c:set var="ctx" value="${pageContext.request.contextPath}"/>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
  <script type="text/javascript">
  	
    function add() {  // form의 데이터 유효성 검사(체크), 회원가입
		document.form1.action="<c:url value='/memberInsert.do'/>";
		document.form1.submit();
  	}
  	
  	function frmreset() {  // 입력 필드 초기화
		document.form1.reset();
	}
  	
  	function doublecheck() {
  		if($("#id").val()=='') {  // id의 value(val)값을 가져왔더니 null일 때
  			alert("아이디를 입력하세요.");
  			$("#id").focus();     // id 입력하는 자리로 다시 포커스(커서)위치시키기
  			return;
  		}
  		var id = $("#id").val();  // 아이디 입력란('#id')의 값을 가져와 변수 id에 저장
  		$.ajax({  // 서버와 통신할때 사용하는 AJAX 함수, 서버에 누구한테 아이디를 넘겨주어 중복체크할건지?
  			url : "<c:url value='/memberDbcheck.do'/>",  // value에 저장된 서버 URL로 데이터를 전송 (비동기적)
  			type : "POST",
  			data : {"id" : id},	// url 페이지가 요청되면서 서버에 전송될 데이터 (아이디를 포함한 객체)
  			// 서버쪽으로 통신이 성공적으로 되었으면 success라는 이벤트가 발생
  			// success가 발생하면, 서버와 서버쪽으로의 요청이 잘 되었다는 것을 의미
  			// 서버가 Ajax 함수쪽으로 로그인에 성공/실패 정보를 내려보내줌
  			// 정보를 받아 다른 작업하려면 함수를 별도로 만들어줘야 함 (-> 콜백 함수)
  			// success라는 이벤트에서 서버가 응답하면, 콜백 함수가 응답을 받아 처리
  			
  			// 클라이언트가 url로 요청하면 id값이 넘어가고 서버쪽에서 id를 받아 DB작업
  			// 이후 아이디가 중복인지 아닌지의 정보를 콜백함수에 성공적으로 요청을 하고 서버가 클라이언트에게 응답
  			success : dbCheck,  // 서버로부터 응답이 성공적으로 받아졌을때 => 호출되는 dbCheck 콜백 함수
  			error : function(){ alert("error"); }  // 통신 중 오류시 호출되는 에러메시지 출력
  		});	
  	}
  	
  	function dbCheck(data) {  // 콜백 함수 (서버로부터 응답이 성공적으로 받아졌을 때 호출되는 함수)
  		if(data != "NO") {  // 데이터가 들어있으면 (중복된 id이면)(NO)
  			alert("중복된 아이디 입니다.");
  			$("#id").focus();
  		} else {			  // data == "null"이면 (데이터가 없으면)(YES)
  			alert("사용 가능한 아이디입니다.");
  			$("#id").focus();
  		}
  	}
  	
  	// 파일 선택 시 해당 파일을 서버로 전송하고, 응답을 처리하여 파일명을 입력란에 표시
  	function add2(){
  		if($("#file").val()!='') {  // file이란 value가 null이 아님 -> 파일첨부가 된 경우
  			var formData = new FormData();  // formData - 파일을 서버로 전송하는 객체
  			// [0] : 여러 개의 파일 업로드시 [0],[1],[2]...번 파일들이지만 하나밖에 없어서 [0]번 파일
  			// 데이터 추가(append), file이라는 키에 해당하는 값으로 파일 데이터를 추가
  			// 첫번째 파일 선택 후 추가 -> 선택된 파일을 FormData 객체에 file이라는 키로 추가
  			// 이후 FormData 객체는 AJAX를 통해 서버로 전송되어 파일 업로드 처리
  			formData.append("file", $("input[name=file]")[0].files[0]);  // formData 객체에 파일 추가
  			
  			$.ajax({  // 서버에 데이터 전송(요청)
  				url : "<c:url value='/fileAdd.do'/>",  // 서버에 업로드하는 컨트롤러
  				type : "post",
  				data : formData,  // 서버에 전송될 데이터를 formData로 설정
  				processData : false,  // 전송 전 jQuery가 데이터를 처리하지 않도록
  				contentType : false,  // 파일 업로드 후 리턴받을 타입
  				success : function(data){  // 응답받았을 때 콜백함수()
  					$('#filename').val(data);  // 파일명을 #filename이라는 입력란에 설정
  					document.form1.action="<c:url value='/memberInsert.do'/>?mode=fadd";  // 서버로 데이터를 전송할 url
  					document.form1.submit();  // id, pass, name, age, email, phone, filename(hidden)
  				},  
  				error : function(){ alert("error"); }
  			});
  			
  		} else {  // null인 경우 -> 파일첨부가 안된 경우
  			document.form1.action="<c:url value='/memberInsert.do'/>?mode=add";  // 서버로 데이터를 전송할 url
			document.form1.submit();  // id, pass, name, age, email, phone
  		}
  	}	
  </script>
</head>
<body>

<div class="container">
  <h2>회원가입 화면</h2>
  <div class="panel panel-default">
    <div class="panel-heading">
    	<c:if test="${sessionScope.userId != null && sessionScope.userId != ''}">
  	 	  <label>${sessionScope.userName}님이 로그인 하셨습니다.</label>
  		</c:if>
  		<c:if test="${sessionScope.userId == null || sessionScope.userId == ''}">
  		  <label>안녕하세요.</label>
  		</c:if>
    </div>
    
    <div class="panel-body">
    <form id="form1" name="form1" class="form-horizontal" method="post">  
	  <div class="form-group">
	    <label class="control-label col-sm-2" for="id">아이디:</label>
	    <div class="col-sm-10">
	    <table>
	      <tr>
	      	<td><input type="text" class="form-control" id="id" name="id" placeholder="아이디를 입력하세요."></td>
	      	<td><input type="button" value="중복체크" onclick="doublecheck()" class="btn btn-warning"></td>
	      </tr>
	    </table>
	    </div>
	  </div>
	  
	  <div class="form-group">
	    <label class="control-label col-sm-2" for="pass">비밀번호:</label>
	    <div class="col-sm-10">
	      <input type="password" class="form-control" id="pass" name="pass" placeholder="비밀번호를 입력하세요." style="width: 30%">
	    </div>
	  </div>
	  
	  <div class="form-group">
	    <label class="control-label col-sm-2" for="name">이름:</label>
	    <div class="col-sm-10">
	      <input type="text" class="form-control" id="name" name="name" placeholder="이름을 입력하세요." style="width: 30%">
	    </div>
	  </div>
	  
	  <div class="form-group">
	    <label class="control-label col-sm-2" for="age">나이:</label>
	    <div class="col-sm-10">
	      <input type="text" class="form-control" id="age" name="age" placeholder="나이입력" style="width: 10%">
	    </div>
	  </div>
	  <div class="form-group">
	    <label class="control-label col-sm-2" for="email">이메일:</label>
	    <div class="col-sm-10">
	      <input type="text" class="form-control" id="email" name="email" placeholder="이메일을 입력하세요." style="width: 30%">
	    </div>
	  </div>
	  
	  <div class="form-group">
	    <label class="control-label col-sm-2" for="phone">전화번호:</label>
	    <div class="col-sm-10">
	      <input type="text" class="form-control" id="phone" name="phone" placeholder="전화번호를 입력하세요." style="width: 30%">
	    </div>
	  </div>
	  
	  <div class="form-group">
	    <label class="control-label col-sm-2" for="">첨부파일:</label>
	    <div class="col-sm-10">
	      <input type="file" class="control-label" id="file" name="file">
	    </div>
	  </div>
	  
	  <!-- 파일 이름도 같이 넘어가야해서 등록버튼 누를 때 파일 첨부된 경우 서버로 파일 업로드 -->
	  <input type="hidden" name="filename" id="filename" value="">
	</form>    
    </div>
    
    <div class="panel-footer" style="text-align: center;">
      <!-- 로그인 상태가 아닐때 회원 가입 등록이 가능하도록 -->
      <c:if test="${sessionScope.userId == null || sessionScope.userId==''}">
        <input type="button" value="등록" class='btn btn-primary' onclick="add2()"/>
      </c:if>
      
      <!-- 로그인 상태일때에는 회원 가입 등록이 불가하도록 (비활성화) -->
      <c:if test="${sessionScope.userId != null && sessionScope.userId!=''}">
        <input type="button" value="등록" class='btn btn-primary' onclick="add()" disabled="disabled"/>
      </c:if>
      
      <input type="button" value="취소" class='btn btn-warning' onclick="frmreset()"/>
      <input type="button" value="리스트" onclick="location.href='${ctx}/memberList.do'" class='btn btn-success'/>    
    </div>
  </div>
</div>
</body>
</html>