<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Review Page</title>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<style>
    #replyContent {
        width: calc(100% - 20px);
    }
</style>
</head>
<body>
    <%@include file = "/views/common/header.jsp"%>
	
    <div class="outer">
        <br>
        <h2 align="center">리뷰 보기</h2>
        <br>
        <table border="1" align="center">
            <tr>
                <th width="70">카테고리</th>
                <td width="70">
                    ${b.category }
                </td>
                <th width="70">제목</th>
                <td width="350">${b.boardTitle} </td>
            </tr>          
            <tr>
                <th>내용</th>
                <td colspan="3">
                    <p style="height:200px; white-space:pre;">${b.boardContent }</p>
                </td>
            </tr>
            <tr>
                <th>첨부파일</th> 
                <td colspan="3">
                    <c:choose>
                        <c:when test="${empty at}">
                            첨부파일이 없습니다.
                        </c:when>
                        <c:otherwise>
                            <a download href="${contextPath}${at.filePath}${at.changeName}">${at.fileName }</a>
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
        </table>
        <br>
        <c:if test="${loginUser.userId eq b.boardWriter or loginUser.userId eq 'admin'}"> 
            <div align="center">
                <button type="button" onclick="location.href='${contextPath}/update.rv?bno=${b.boardNo}'">수정하기</button>
                
                <button type="button" onclick="deleteBoard();">삭제하기</button>
            </div>
        </c:if>          
        	
        <script>
        
            function deleteBoard(){
                var flag = confirm("정말 삭제하시겠습니까?");
                
                if(flag){
                    location.href="${contextPath}/delete.rv?bno=${b.boardNo}";
                }
            }
        </script>

        <br><br>
        <div id="reply-area">
			<table border="1" align="center">
				<thead>
			
					<c:choose>
						<c:when test="${not empty loginUser }">
							<tr>
								<th>댓글작성</th>
								 <td>
								 	<textarea id="replyContent" rows="3" cols="50" style="resize:none;" required></textarea>
								 </td>
								 <td>
								 <button  onclick="insertReply();">댓글작성</button> 
								 </td>
							</tr>
						</c:when>
						<c:otherwise>
							<tr>
								<th>댓글작성</th>
								 <td>
								 	<textarea  id="replyContent" readonly rows="3" cols="50" style="resize:none;">로그인 후 이용 가능한 서비스입니다.</textarea>
								 </td>
								 <td><button disabled>댓글작성</button> </td>
							</tr>
						
						</c:otherwise>
						
						
						
					</c:choose>
				</thead>
				<tbody>
					<tr>
						<td>작성자</td>
						<td>내용</td>
						<td>작성일</td>
					</tr>
				</tbody>
			</table>
		</div>
		
		<br><br>
	</div>
    
  
   <%@include file = "/views/common/footer.jsp"%>
    <script>
        function insertReply(){
            $.ajax({
                url: "insertReply.rv",
                type: "post",
                data: {
                    content: $("#replyContent").val(),
                    bno: "${b.boardNo}",
                    userNo: "${loginUser.userNo}"
                },
                success: function(result){
                    if(result > 0){ 
                        alert("댓글 작성 성공");
                        $("#replyContent").val("");
                        replyList(); 
                    } else {
                        alert("댓글 작성 실패");
                    }
                },
                error: function(){
                    console.log("통신 실패");
                }
            });
        }
        
        function replyList(){
            $.ajax({
                url: "replyList.rv",
                data: {
                    bno: ${b.boardNo}
                },
                success: function(list){
                    var tr = "";
                    for(var i in list){
                        tr += "<tr>"
                            + "<td>" + list[i].replyWriter + "</td>"
                            + "<td>" + list[i].replyContent + "</td>"
                            + "<td>" + list[i].createDate + "</td>"
                            + "</tr>";
                    }
                    $("#reply-area tbody").html(tr);
                },
                error: function(){
                    console.log("통신오류");
                }
            });
        }
        
        $(function(){ 
            replyList();
        });
    </script>
</body>
</html>
