<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/include/include-header.jspf"%>


</head>

<body>


	<h2>메뉴 선택</h2>

	<a href="#this" class="btn" id="user">사용자 조회</a> |
	<a href="#this" class="btn" id="post">게시물 조회</a>


<%@ include file="/WEB-INF/include/include-body.jspf"%>
	
	<script type="text/javascript">
	

	// 여긴 jQuery *********************************************************
	
	// javascript 로도 해보기 **********************************************
	
	$(document).ready(function() {
		// 사용자 조회 버튼
		$("#user").on("click", function(e) { 
			e.preventDefault();
			fn_openUserList();
		});
		
		// 게시물 조회 버튼
		$("#post").on("click", function(e) { 
			e.preventDefault();
			fn_openPostList();
		});
		
		function fn_openUserList() {
			var comSubmit = new ComSubmit();
			comSubmit.setUrl("<c:url value='/user/list' />");
			comSubmit.submit();
		}
		
		function fn_openPostList() {
			var comSubmit = new ComSubmit();
			//comSubmit.setUrl("<c:url value='/sample/openBoardList.do' />");
			comSubmit.setUrl("<c:url value='/posts/read-all' />");
			comSubmit.submit();
		}
		

	
	});
	
	</script>

</body>
</html>


