
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<!-- Popper JS -->
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<!-- Latest compiled JavaScript -->
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<title>MOOSA 소통해요</title>

<style>
.likeBtn, .dbtn, .updateBtn {
	background-color: skyblue;
}

.dbtn {
	background-color: skyblue;
}

.updateBtn {
	background-color: skyblue;
}

#board-reply-area {
	margin-left: 10%;
	margin-right: 10%;
	width: 70%;
	align: center;
}

#reply-content {
	font-size: 20px;
	font-weight: 1;
	color: #413E3E;
	font-family: 'Arvo';
	font-weight: 700;
	background-color: cornsilk;
}

#reply-firstRow {
	display: flex;
	align-items: center;
}

#replyInput {
	resize: none;
}

#reply-body {
	display: flex;
	justify-content: center;
}
</style>
</head>
<body>
	<%@include file="/views/common/header.jsp"%>

	<div class="board-detai-outer"
		style="margin-left: 10%; margin-right: 10%;">
		<br>
		<h2 align="center"
			style="color: #413E3E; font-family: 'Arvo'; font-weight: 700;">소통게시판</h2>
		<br> <a href="${contextPath }/list.bo?currentPage=1"
			class="btn btn-secondary">글목록으로</a> <br>

		<table align="center" border="1">
			<tr>
				<th width="100">카테고리 :</th>
				<td width="100">${b.categoryName }</td>
			</tr>
			<tr>
				<th width="70">제목 : </th>
				<td width="100">${b.boardTitle }</td>
				<th width="70">작성자 :</th>
				<td width="70">${b.userId }</td>
				<th width="70">작성일 :</th>
				<td width="70">${b.createDate }</td>
			</tr>
			<tr>
				<td colspan="1"></td>
				<th width="50">조회수 :</th>
				<td width="50">${b.count }</td>
			</tr>
			<tr>
				<td colspan="3">
					<p style="height: 300px; white-space: pre;">${b.boardContent }
					</p>
				</td>
			</tr>

			<tr>
				<th>첨부파일</th>
				<td colspan="3"><c:choose>
					<c:when test="${!empty at }">
						<a download href="${contextPath }${at.filePath}${at.changeName}">${at.fileName }</a>
					</c:when>
					<c:otherwise>첨부파일이 없습니다.</c:otherwise>
					</c:choose>
				</td>
			</tr>
		</table>
		<br> <br>

		<!-- 작성한 유저거나 관리자 일 경우만  -->
		<c:if test="${loginUser.userId eq b.userId or !empty loginUser or loginUser eq 'admin' }">
			<div align="center">
				<button class="updateBtn" type="button"
					onclick="location.href='${contextPath}/update.bo?bno=${b.boardNo}'">수정하기</button>
				<button class="dbtn" type="button" onclick="deleteBoard();"
					class="btn btn-danger">삭제하기</button>
			</div>
		</c:if>
	</div>

	<script> //게시글 삭제함수
		function deleteBoard(){
			var flag = confirm('정말 삭제하시겠습니까?')
			console.log(flag);
			if(flag){
				location.href="/moosa/delete.bo?bno=${b.boardNo}";
			}
		};
	</script>

	<br>
	
	<div class="like-area" style="margin-left: 10%; margin-right: 10%;">
		<button class="likeBtn" class="btn btn-primary" value="${b.boardNo }">
		글이 유익했다면 좋아요♡
		</button>
	</div>

	<br>
	<!-- 미구현 현황 -->
	<!-- 등록순 / 최신순 -->
	<!-- 신고버튼 -->
	<div id="board-reply-area">
		<table align="center">
			<thead id="reply-thead">

			</thead>
			<tbody id="reply-body">
				<tr>
					<td><textarea id="replyInput" rows="5" cols="100"
							placeholder="로그인 후 입력 가능합니다. 
	명예훼손,개인정보유출,분쟁유발,허위사실 유포 등의 글은 이용약관에 의해 제재는 물론 법률에 의해 처벌 받을 수 있습니다."></textarea>
					</td>
					<td>
						<button onclick="insertReply();">등록</button>

					</td>
				</tr>
			</tbody>
		</table>
	</div>

	<br>
	<br>
	<!-- Footer -->
	<%@include file="/views/common/footer.jsp"%>

	<script>
	
		if(${empty loginUser}){ //사용자가 로그인 없이 댓글입력란 클릭시 로그인 페이지로
			$('#replyInput').click(function(){
					location.href ="${contextPath}/loginPage.go";
					});
		} 
		
   		$(function(){
			//게시글 좋아요 클릭(좋아요), 중복클릭(좋아요 풀림)
			//좋아요 취소를 기본변수로
			var likeCancel = false;
		
			$('.likeBtn').on('click',function(){
				//댓글번호
				var likeBno = $(this).val(); 
				console.log(likeBno);
				
					if(likeCancel){ //좋아요 취소
						$.ajax({
							url : "/moosa/likes.bo",
							data : {
								likeBno : likeBno
							},
							success : function(){
								likeCancel = false;
								alert("좋아요 취소 성공!!")
								$('.likeBtn').html("글이 유익했다면 좋아요 ♡");
								selectReply();
							},
							error : function(){
								alert("좋아요 취소 실패 ㅠㅠ")
							}
						});
					}else{ //좋아요
						$.ajax({
							url : "/moosa/likes.bo",
							type : "post",
							data : {
								likeBno : likeBno
							},
							success : function(){
								likeCancel = true;
								alert("좋아요 성공!!")
								
								$('.likeBtn').html("좋아요 감사합니다. ♥");
								
								selectReply();
							},
							error : function(){
								alert("좋아요 실패 ㅠㅠ")
							}
						});
					}
			});
		});
	
		
		//비동기통신 댓글입력
		function insertReply(){
			$.ajax({
				url : "/moosa/insert.re",
				type : "post",
				data : {
					replyContent : $("#replyInput").val(),
					bno : ${b.boardNo},
					userNo : "${loginUser.userNo}"
				},
				success : function(result){
					if(result>0){
						alert("댓글 작성 성공!!");
						$('#replyInput').val(""); //댓글 입력 후 문자 비워주기
						selectReply(); //댓글 새로고침
					}else{
						alert("댓글 작성 실패 ㅠㅠ");
					}
				},
				error : function(){
					console.log("통신오류");
				}
			});
			
		}
		
		
		//댓글불러오기
		function selectReply(){
			
			$.ajax({
				url : "/moosa/list.re",
				data : {
					bno : ${b.boardNo}
				},
				success : function(brList){
					
					var loginUserId = '${loginUser.userId}';
					var thead = "";
					
					for(var i in brList) {
					    thead += "<tr id=reply-firstRow>"
					        + "<td class='reply-userId' data-user-id='" + brList[i].userId + "'>" + brList[i].userId + "</td>"
					        if (loginUserId === brList[i].userId) {
					            thead += "<td><button class='dButton' data-reply-writer='" + brList[i].replyNo + "'>삭제</button></td>";
					        } else {
					            thead += "<td></td>";
					        };
				        thead += "<td>" + "<button class='cButton' data-reply-commend='" + brList[i].replyNo + "'>추천"+brList[i].recommend+"</button>" + "</td>"
					        + "<td>" + "<button class='hButton' data-reply-hate='" + brList[i].replyNo + "'>비추천"+brList[i].hate+"</button>" + "</td>"
					        + "<td>" + brList[i].createDate + "</td>"
					        + "</tr>"
					        + "<tr>"
					        + "<td colspan='1' id='reply-content'>" + brList[i].replyContent + "</td>"
					        + "</tr>";

					}
					$('#reply-thead').html(thead);
				},
				error : function(){
					console.log('통신실패');

					    // 기본은 빈하트 누르면 꽉찬하트
				/*	    if (brList[i].likes=='N') {
					        thead += "<button class='lButton' data-reply-likes='" + brList[i].replyNo + "'>빈하트</button>";
					    } else {
					        thead += "<button class='lButton' data-reply-likes='" + brList[i].replyNo + "'>꽉찬하트</button>";

					    }
				*/	    
				}
			});
			
		}
		
		//DOM완료 이후 새로고침 없이 리스트 가져오기
		$(function(){
			selectReply();
		});
		
		//댓글 삭제버튼 
		
			$('thead').on('click','.dButton',function(){
				
				var c = confirm('정말 삭제하시겠습니까?');
				//댓글번호지만 var replyLikes 변수 겹치지 않게
				var replyNo = $(this).data('reply-writer'); 
				
				if(c){
					
					$.ajax({
						url : "/moosa/delete.re",
						data : {
							replyNo : replyNo
						},
						success : function(result){
							if(result>0){
								alert("댓글삭제에 성공했어요!!");
								selectReply();
							}
						},
						error : function(){
							alert("댓글삭제에 실패했어요 ㅠㅠ");
						}
					});
				}
				
				
			});
			
		//댓글(따봉) 추천버튼
			var thisRe = false;
		$('thead').on('click','.cButton',function(){
			
			//댓글번호지만 var replyLikes 변수 겹치지 않게
			var commendNo = $(this).data('reply-commend'); 
				
			if(thisRe){
				$.ajax({
					url : "/moosa/commend.re",
					data : {
						commendNo : commendNo
					},
					success : function(result){
						if(result>0){
							alert("따봉 도르마무!!");
							thisRe = false;
							selectReply();
						}
					},
					error : function(){
						alert("따봉 도르마무 실패 ㅠㅠ");
					}
				});
			}else{
				$.ajax({
					url : "/moosa/commend.re",
					type: "post",
					data : {
						commendNo : commendNo
					},
					success : function(result){
						if(result>0){
							alert("따봉 성공!!");
							thisRe = true;
							selectReply();
						}
					},
					error : function(){
						alert("따봉 실패 ㅠㅠ");
					}
				});
			};
		});
		
		//댓글(우~~~) 비추천버튼
			var thisHa = false;
		$('thead').on('click','.hButton',function(){
			
			//댓글번호지만 var replyLikes 변수 겹치지 않게
			var hateNo = $(this).data('reply-hate'); 
				
			if(thisHa){
				$.ajax({
					url : "/moosa/hate.re",
					data : {
						hateNo : hateNo
					},
					success : function(result){
						if(result>0){
							alert("비추천 도르마무 성공!!");
							thisHa = false;
							selectReply();
						}
					},
					error : function(){
						alert("비추천 도르마무 실패 ㅠㅠ");
					}
				});
			}else{
				$.ajax({
					url : "/moosa/hate.re",
					type: "post",
					data : {
						hateNo : hateNo
					},
					success : function(result){
						if(result>0){
							alert("비추천 성공!!");
							thisHa = true;
							selectReply();
						}
					},
					error : function(){
						alert("비추천 실패 ㅠㅠ");
					}
				});
			};
		});
		
		
	</script>



	</div>

</body>
</html>




















