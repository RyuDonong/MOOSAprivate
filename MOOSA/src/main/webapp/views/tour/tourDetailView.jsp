<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String contextPath = request.getContextPath();
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

	



</head>
<body> 
<%@include file = "/views/common/header.jsp"%>
	<div class="outer">
		<br>
		<br>
		
		<form action="${contextPath }" method="post" enctype="multipart/form-data" id="detail-form">
			<table align="center" border="1">
				<tr>
					<th width="100">제목</th>
					<td colspan="3">
						${b.boardTitle }
					</td>
					<tr>
					<th>내용</th>
					<td colspan="3">
						<p>${b.boardContent }</p>
					</td>
					
				
				
			
	                      	
		            
	        
					<tr>
								<th>이미지</th>
								<td colspan="3" align="center"  width="250" height="170">
									<img id="titleImg" src="${contextPath}${tp.filePath}${tp.changeName}" width="500" height="400">
							
								</td>
							</tr> 
					    	
					    	
			 
				
			</table>
			<br><br>
	
		</form>
	
	</div>
</body>
</html>