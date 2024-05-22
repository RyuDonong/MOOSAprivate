<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Document</title>
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
<
style>* {
	margin: 0;
	padding: 0;
	box-sizing: border-box;
}

body {
	background-color: #f8f9fa;
	font-family: 'Arial', sans-serif;
}

.container {
	max-width: 600px;
	margin: 50px auto;
	padding: 20px;
	background-color: #fff;
	border-radius: 10px;
	box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
}

h1 {
	color: #007bff;
	text-align: center;
}

.join_box {
	margin-top: 20px;
}

.checkBox {
	margin-bottom: 20px;
}

.checkBox>label {
	display: flex;
	align-items: center;
	margin-bottom: 10px;
}

.checkBox input[type="checkbox"] {
	margin-right: 10px;
	width: 20px; /* Adjust checkbox size */
	height: 20px; /* Adjust checkbox size */
}

textarea {
	width: 100%;
	height: 100px;
	margin-top: 10px;
	padding: 10px;
	resize: none;
	border: 1px solid #ced4da;
	border-radius: 5px;
}

.footBtwrap {
	margin-top: 20px;
	overflow: hidden;
}

.footBtwrap>li {
	float: left;
	width: 100%;
}

.agree {
	width: 100%;
	padding: 10px;
	border: none;
	border-radius: 9px;
	cursor: pointer;
	font-size: 18px;
	transition: background-color 0.3s;
	background-color: #007bff;
	color: #fff;
}

.agree.disabled {
	background-color: #ccc;
	cursor: not-allowed;
}
</style>

</head>
<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />

	<div class="container">
		<h1>
			<a href="<%=contextPath%>">MOOSA</a>
		</h1>


		<div class="join_box">
			<!-- <div class="checkBox check01">
				<label for="chkAll"> <input type="checkbox" name="chkAll"
					id="chkAll" class="chkAll"> 이용약관, 개인정보 수집 및 이용, 프로모션 안내 메일
					수신(선택)에 모두 동의합니다.
				</label>
			</div> -->
			<div class="checkBox check02">
				<label for="chk1"> <input type="checkbox" name="chk"
					id="chk1" class="chk"> 이용약관 동의(필수)
				</label> 
				<textarea name="" id="" rows="5" class="form-control">홈페이지 이용 약관
1. 서비스 이용

1.1 본 웹사이트를 이용함으로써 고객은 이용약관에 동의하는 것으로 간주됩니다.

1.2 본 사이트는 여행 및 관련 정보를 제공하며, 이는 참고용으로만 제공됩니다. 이용자는 자신의 판단하에 이 정보를 활용하여야 합니다.

2. 정보의 정확성

2.1 본 사이트에서 제공되는 정보는 신뢰할 수 있는 출처에서 수집되었으나, 정확성에 대한 보장은 없습니다.

2.2 여행 일정, 가격 및 기타 정보는 사전 공지 없이 변경될 수 있습니다.

3. 책임의 한계

3.1 본 사이트는 이용자가 제공하는 개인정보를 보호하기 위해 최선을 다하고 있으나, 외부 요인으로 인한 정보 유출에 대해 책임을 지지 않습니다.

3.2 여행 정보에 대한 선택, 예약, 결제 등에 따른 모든 책임은 이용자 본인에게 있습니다.

4. 저작권

4.1 본 사이트에 게시된 모든 콘텐츠는 저작권의 보호를 받습니다. 이를 무단으로 복제, 수정, 배포하는 것은 금지됩니다.

5. 분쟁 해결

5.1 본 이용약관에 따른 분쟁은 관련 법률에 따라 해결됩니다.

5.2 이용자는 본 이용약관에 동의함으로써 어떠한 분쟁도 이에 따라 해결되는 것에 동의합니다.

				</textarea>
			</div>
			<div class="checkBox check03">
				<label for="chk2"> <input type="checkbox" name="chk"
					id="chk2" class="chk"> 개인정보 수집 및 이용에 대한 안내(필수)
				</label>
				<textarea name="" id="" rows="5" class="form-control">
개인정보 약관 
1.1 본 웹사이트는 서비스 제공을 위해 다음과 같은 개인 정보를 수집할 수 있습니다:

이름
이메일 주소
연락처 정보
2. 개인 정보 이용 목적

2.1 본 사이트에서 수집한 개인 정보는 다음과 같은 목적으로 이용됩니다:

서비스 제공 및 관리
이용자와의 의사 소통
새로운 서비스나 이벤트 소개
법적 의무의 준수
3. 개인 정보의 보관 및 보호

3.1 본 사이트는 이용자의 개인 정보를 안전하게 보호하기 위해 적절한 보안 조치를 취하고 있습니다.

3.2 개인 정보는 원칙적으로 사용자의 동의 없이 제3자에게 제공되지 않습니다. 다만, 관련 법률에 의거하여 제공될 수 있습니다.

4. 개인 정보 열람, 수정 및 삭제

4.1 이용자는 자신의 개인 정보에 대해 열람, 수정, 삭제를 요청할 수 있습니다. 이에 대한 절차는 별도의 안내를 통해 진행됩니다.

5. 쿠키의 사용

5.1 본 사이트는 쿠키를 사용하여 사용자 경험을 개선하고 서비스를 제공합니다. 쿠키에 대한 자세한 내용은 개인 정보 보호 정책에서 확인하실 수 있습니다.

6. 개인 정보 보호 정책의 변경

6.1 본 사이트는 개인 정보 보호 정책을 변경할 수 있습니다. 변경 사항은 웹사이트에 공지되며, 이용자는 변경 사항을 확인하고 동의할 수 있습니다.

				</textarea>
			</div>
			<div class="checkBox check03">
				<label for="chk3"> <input type="checkbox" name="chk"
					id="chk3" class="chk"> 이벤트 등 프로모션 알림 메일 수신(선택)
				</label>
			</div>
		</div>
		<ul class="footBtwrap clearfix">
			<button type="submit" class="agree" onclick="enrollForm();" disabled>동의</button>
		</ul>

	</div>

	<script>
		$(function() {
			/*  */const checkAll = $('#chkAll');
			const chks = $('.chk');
			/*  */const chkBoxLength = chks.length;
			const chk1 = $('#chk1');
			const chk2 = $('#chk2');
			/*  */const chkAll =$('#chkAll2');

			// 전체 선택 체크박스 이벤트 리스너 추가
			/* checkAll.on('click', function(event) {
				if (event.target.checked) {
					chks.prop('checked', true);
				} else {
					chks.prop('checked', false);
				}
			}); */

			// 각 체크박스의 이벤트 리스너 추가
			chks.on('click',
					function() {
						let count = 0;
						chks.each(function() {
							if ($(this).prop('checked')) {
								count++;
							}
						});
						// chk1과 chk2가 모두 선택되었을 때 동의 버튼 활성화
						if (chk1.prop('checked') && chk2.prop('checked'))/* ||(chkAll.prop('checked'))) */
						//(chkAll.prop('checked'))를 해봤는데 안됨
						{
							$('.agree').prop('disabled', false).removeClass(
									'disabled');
						} else {
							$('.agree').prop('disabled', true).addClass(
									'disabled');
						}

					/* 	// 모든 체크박스가 선택되었을 때 전체 선택 체크박스도 체크
						/* if (count !== chkBoxLength) {
							checkAll.prop('checked', false);
						} else {
							checkAll.prop('checked', true);
						} */
					});
			
			});
		
			
			function enrollForm(){
        	//회원가입 페이지로 이동시키는 함수 
        		//location.href = "이동시킬 경로"
        		//단순 페이지 이동 요청을 보내면 url에 프로젝트 디렉토리 구조가 노출되기 때문에 보안상 문제가 있을 수 있다.
        		//때문에 단순 페이지 이동요청도 servlet을 거쳐서 요청처리 해야한다.
        		
        		location.href = "<%=contextPath%>/enrollForm.me";
		};
	</script>







</body>
</html>
