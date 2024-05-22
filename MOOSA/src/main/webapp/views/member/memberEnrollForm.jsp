<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Popper JS -->
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>

<!-- Latest compiled JavaScript -->
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>

<%
String contextPath = request.getContextPath();
%>
</head>
<style>
body {
	font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
	background-color: #f8f8f8;
	margin: 0;
	padding: 0;
}

h1 {
	text-align: center;
	margin: 20px 0;
	color: #1a1a1a;
	font-weight: bold;
}

form {
	background-color: #fff;
	border-radius: 10px;
	padding: 20px;
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
	max-width: 500px;
	margin: 20px auto;
}

table {
	width: 120%;
}

td {
    width : 100px;
	padding: 8px 0;
}

input[type="text"], input[type="password"], input[type="email"] {
    width: 300px;;
	padding: 10px;
	border: 1px solid #ccc;
	border-radius: 5px;
	margin-bottom: 10px;
	box-sizing: border-box;
	transition: border-color 0.3s ease;
}

input[type="text"]:focus, input[type="password"]:focus, input[type="email"]:focus
	{
	border-color: #3399ff;
}

input[type="submit"], input[type="reset"], button {
	width: calc(50% - 10px);
	padding: 10px;
	border: none;
	border-radius: 5px;
	background-color: #3399ff;
	color: #fff;
	cursor: pointer;
	transition: background-color 0.3s ease;
}

input[type="submit"]:disabled {
	background-color: #cccccc;
	cursor: not-allowed;
}

input[type="submit"]:hover, input[type="reset"]:hover, button:hover {
	background-color: #1a8cff;
}

.error-message, .success-message {
	font-size: 7px;
	margin-top: 5px;
}

.error-message {
	color: #cc3300;
}

.success-message {
	color: #008000;
}

.check-button {
	width: auto;
	padding: 10px;
	border: none;
	border-radius: 5px;
	background-color: #3399ff;
	color: #fff;
	cursor: pointer;
	transition: background-color 0.3s ease;
}

.check-button:hover {
	background-color: #1a8cff;
}
</style>



<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />


	<h1>
		<a href="<%=contextPath%>">MOOSA</a>
	</h1>
	<form id="enroll-form" action="<%=contextPath%>/insert.me"
		method="post">
		<table>
			<tr>
				<td>* 아이디</td>
				<td><input type="text" id="userId" name="userId" required></td>
			<td><button type="button"class="check-button"onclick="idCheck();">중복확인</button></td>

			</tr>
			<tr>
				<td>* 비밀번호</td>
				<td><input type="password" name="userPwd" required></td>
				<td></td>
			</tr>
			<tr>
				<td>* 비밀번호확인</td>
				<td><input type="password" id="pwChk" required></td>
				<td><span id="checkPw"></span></td>
				<input type="hidden" id="pwDoubleChk" />
			</tr>
			<tr>
				<td>* 이름</td>
				<td><input type="text" name="userName" required></td>
				<td></td>
			</tr>
			<tr>
				<td>*전화번호</td>
				<td><input type="text" name="phone" required></td>
				<td></td>
			</tr>
			<tr>
				<td>이메일</td>
				<td><input type="email" name="email"></td>
				<td></td>
			</tr>
			<tr>
				<td>주소</td>
				<td><input type="text" name="address"></td>
				<td></td>
			<tr>  
                <td>성별</td>

                <td><label><input type="radio" name="gender" value="M" required> 남자</label>
                        
                <label><input type="radio" name="gender" value="F" required> 여자</label>
                         
                    </td>
        


            </tr>
				
				
				
				
			</tr>
		</table>

		<br> <br>

		<div align="center">
			<button type="submit" disabled>회원가입</button>
			<button type="reset">초기화</button>
		</div>
	</form>

	<script>
		function idCheck() {
			var inputId = $("#userId").val();
			 var idRegExp = /^[a-zA-Z0-9]{4,12}$/;
	            if (!idRegExp.test(inputId)) {
	                alert("아이디는 영문 대소문자와 숫자 4~12자리로 입력해야합니다.");
	                $("#userId").focus();
	                return false;
	            }
			$.ajax({
				url : "${contextPath}/idCheck.me",
				data : {
					inputId : inputId
				},
				success : function(result) {
					if (result == "NNNNN") {
						alert("이미 존재하는 아이디입니다.");
					} else {
						if (confirm("정말 사용하시겠습니까?")) {
							$("#enroll-form :submit").removeAttr("disabled");
							$("#userId").attr("readonly", true);
						} else {
							$("#userId").focus();
						}
					}
				},
				error : function() {
					alert("서버와의 통신에 실패했습니다.");
				}
			});
		}
 
		
		
		
		
		$(function() {
			$("#pwChk").blur(function() {
				if ($("#pwChk").val() == $("input[name='userPwd']").val()) {
					$("#checkPw").text("비밀번호가 일치합니다.");
					$("#checkPw").css("color", "green");
					$("#pwDoubleChk").val("true");
				} else {
					$("#checkPw").text("비밀번호가 일치하지 않습니다.");
					$("#checkPw").css("color", "red");
					$("#pwDoubleChk").val("false");
				}
	        });
	    });
	    
$(function() {
    // 휴대폰 번호 입력 필드에서 포커스가 떠날 때 이벤트를 처리합니다.
    $("input[name='phone']").blur(function() {
        // 사용자가 입력한 휴대폰 번호를 가져옵니다.
        var phoneNumber = $(this).val();

        // 휴대폰 번호의 길이가 11자리가 아닌 경우
        if (phoneNumber.length !== 11) {
            // 경고 메시지를 표시하고 입력 필드에 포커스를 맞춥니다.
            alert("휴대폰 번호는 숫자 11자리로 입력해야합니다.");
            $(this).val(""); // 입력 내용을 지웁니다.
        } else {

        }
    });
});
	</script>


	<br>
	<br>




</body>
</html>