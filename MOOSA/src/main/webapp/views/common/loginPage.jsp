<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.kh.member.model.vo.Member"%>
    <%
	//로그인 정보 추출하기 
	Member loginUser = (Member)session.getAttribute("loginUser");
	//로그인이 되어있지 않다면 loginUser라는 key값으로 데이터를 조회할 수 없으니 
	//null이 반환된다. 로그인이 되었다면 해당 로그인 정보가 반환된다. 
	
	//알림 메세지 추출하기 
	String alertMsg = (String)session.getAttribute("alertMsg");
	
	//contextPath 변수처리해서 사용하기 
	String contextPath = request.getContextPath();
	
	//스크립틀릿에서 쿠키 접근하기 
	Cookie[] cookies = request.getCookies();
	
	String saveId="";
	
	if(cookies !=null){
		
		for(Cookie c : cookies){
			
			if(c.getName().equals("userId")){
				saveId = c.getValue();
				break;
			}
		}
	}
	
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    
    
    	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <!-- Latest compiled and minified CSS -->
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
	
	<!-- Popper JS -->
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
	
	<!-- Latest compiled JavaScript -->
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
    
    <style>
        @import url('https://fonts.googleapis.com/css?family=Montserrat:400,800');

* {
  box-sizing: border-box;
}

body {
  background: #f6f5f7;
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
  font-family: 'Montserrat', sans-serif;
  height: 100vh;
  margin: -20px 0 50px;
}

h1 {
  font-weight: bold;
  margin: 0;
}

h2 {
  text-align: center;
}

p {
  font-size: 14px;
  font-weight: 100;
  line-height: 20px;
  letter-spacing: 0.5px;
  margin: 20px 0 30px;
}

span {
  font-size: 12px;
}

a {
  color: #333;
  font-size: 14px;
  text-decoration: none;
  margin: 15px 0;
}

button {
  border-radius: 20px;
  border: 1px solid #0f0f0f;
  background-color: #141313;
  color: #FFFFFF;
  font-size: 12px;
  font-weight: bold;
  padding: 12px 45px;
  letter-spacing: 1px;
  text-transform: uppercase;
  transition: transform 80ms ease-in;
}

#saveId{
  flex-direction: row;
}

button:active {
  transform: scale(0.95);
}

button:focus {
  outline: none;
}

button.ghost {
  background-color: transparent;
  border-color: #FFFFFF;
}

button.main {
  background-color: transparent;
  border-color: #000000;
  
  color: #000;
}

form {
  background-color: #FFFFFF;
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;
  padding: 0 50px;
  height: 100%;
  text-align: center;

}


input {
  background-color: #eee;
  border: none;
  padding: 12px 15px;
  margin: 8px 0;
  width: 100%;
}

.container {
  background-color: #fff;
  border-radius: 10px;
    box-shadow: 0 14px 28px rgba(0,0,0,0.25), 
      0 10px 10px rgba(0,0,0,0.22);
  position: relative;
  overflow: hidden;
  width: 768px;
  max-width: 100%;
  min-height: 480px;
}

.form-container {
  position: absolute;
  top: 0;
  height: 100%;
  transition: all 0.6s ease-in-out;
}

.sign-in-container {
  left: 0;
  width: 50%;
  z-index: 2;
}

.container.right-panel-active .sign-in-container {
  transform: translateX(100%);
}



.container.right-panel-active .sign-up-container {
  transform: translateX(100%);
  opacity: 1;
  z-index: 5;
  animation: show 0.6s;
}

@keyframes show {
  0%, 49.99% {
    opacity: 0;
    z-index: 1;
  }
  
  50%, 100% {
    opacity: 1;
    z-index: 5;
  }
}

.overlay-container {
  position: absolute;
  top: 0;
  left: 50%;
  width: 50%;
  height: 100%;
  overflow: hidden;
  transition: transform 0.6s ease-in-out;
  z-index: 100;
}

.container.right-panel-active .overlay-container{
  transform: translateX(-100%);
}

.overlay {
  background: url(https://img.freepik.com/free-photo/nature-landscape-with-black-sand-beach_23-2151380327.jpg?t=st=1714377460~exp=1714381060~hmac=cb55e054d2934a8265bc54a99d717b6f446430155c7198adb63dd7a95aac07ec&w=740);
 
  background-repeat: no-repeat;
  background-size: cover;
  background-position: 0 0;
  color: #FFFFFF;
  position: relative;
  left: -100%;
  height: 100%;
  width: 200%;
    transform: translateX(0);
  transition: transform 0.6s ease-in-out;
}

.container.right-panel-active .overlay {
    transform: translateX(50%);
}

.overlay-panel {
  position: absolute;
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;
  padding: 0 40px;
  text-align: center;
  top: 0;
  height: 100%;
  width: 50%;
  transform: translateX(0);
  transition: transform 0.6s ease-in-out;
}

.overlay-left {
  transform: translateX(-20%);
}

.container.right-panel-active .overlay-left {
  transform: translateX(0);
}

.overlay-right {
  right: 0;
  transform: translateX(0);
}

.container.right-panel-active .overlay-right {
  transform: translateX(20%);
}

.social-container {
  margin: 20px 0;
}

.social-container a {
  border: 1px solid #DDDDDD;
  border-radius: 50%;
  display: inline-flex;
  justify-content: center;
  align-items: center;
  margin: 0 5px;
  height: 40px;
  width: 40px;
}

footer {
    background-color: #222;
    color: #fff;
    font-size: 14px;
    bottom: 0;
    position: fixed;
    left: 0;
    right: 0;
    text-align: center;
    z-index: 999;
}

footer p {
    margin: 10px 0;
}

footer i {
    color: red;
}

footer a {
    color: #3c97bf;
    text-decoration: none;
}

.Moosa{
  background-color: #FFFFFF;
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;
  padding: 0 50px;
  height: 5%;
  text-align: center;
  font-size: 50px;
  
}


    </style>
</head>
<body>

	<script>
		    //로그인 실패 알림메세지
		var msg = "<%=alertMsg%>";
		
		if(msg!="null"){ 
			alert(msg); 
			<%session.removeAttribute("alertMsg");%>
		}
	</script>
    
<div class="container" id="container">
  <div class="form-container sign-up-container">
   
  </div>
  <div class="form-container sign-in-container">
  	
        <a class="Moosa" href="${contextPath }/moosa/index.jsp"><h2></h2>Moosa</a>
      
    <form id="login-form" action="<%=contextPath%>/login.me" method="post">
      <div class="social-container">
      </div>
      <input type="text" name="userId" id="loginId" placeholder="아이디" required/>
      <input type="password" name="userPwd" placeholder="비밀번호" required/>
      <input type="checkbox" name="saveId" id="saveId"><label for="saveId">아이디 저장</label>
      <br><br>
      <button  type="submit" id="button">로그인</button>
    </form>
  </div>
  <div class="overlay-container">
 	   <div class="overlay">
       <div class="overlay-panel overlay-right">
        <h1>회원가입</h1>
        <br><br>
        <button class="ghost" id="signUp" onclick="termForm();">회원가입</button>
      </div>
    </div>
  </div>
</div>

<script>
	function main(){
		"${contextPath }/index.jsp";
	}
	
	$(function(){
		
		var saveId = "${cookie.userId.value}";
		
		if(saveId!=""){
			$("#saveId").attr("checked", true);
			
			$("#loginId").val(saveId);
		}
		
	});
  	     function termForm(){
		
	
		//회원가입 홈페이지로 이동하는 메소드
		location.href = "<%=contextPath%>/terms.me";
		
		
	}

	</script>
</body>
</html>