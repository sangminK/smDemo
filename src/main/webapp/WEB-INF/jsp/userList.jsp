<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/include/include-header.jspf"%>
<meta charset="UTF-8">
</head>


<body>

	<h1>USERS LIST</h1>
	<hr>

	<h3>검색</h3>
	<form action="/user/salary-search">
		<span>salary 검색 :</span> &nbsp 
		
		<select name="salary">
			<option value="1000">1000</option>
			<option value="2000">2000</option>
			<option value="3000">3000</option>
		</select>&nbsp 
		
		
		<select name="order">
			<option value="1">이름 오름차순</option>
			<option value="2">이름 내림차순</option>
		</select>&nbsp 
		
		<input type="submit" value="검색" />
	</form>


	<form action="/user/status-search">
		<span>status 검색 :</span> &nbsp <select name="status">
			<option value="1">1</option>
			<option value="2">2</option>
			<option value="3">3</option>
		</select>&nbsp 
		
		<select name="order">
			<option value="1">이름 오름차순</option>
			<option value="2">이름 내림차순</option>
		</select>&nbsp 
		
		
		
		<input type="submit" value="검색" />
	</form>

	<hr>

	<table class="user_list" border="1">
		<colgroup>
			<col width="10%" />
			<col width="*" />
			<col width="15%" />
			<col width="20%" />
		</colgroup>
		<thead>
			<tr>
				<th scope="col">ID</th>
				<th scope="col">이름</th>
				<th scope="col">salary</th>
				<th scope="col">status</th>
				<th scope="col">가입일</th>
				<th scope="col">정보수정일</th>
				<th scope="col">삭제</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${fn:length(list) > 0}">

					<!-- *****중요***** -->
					<!-- SampleController 에서 
					mv.addObject("list", list);
					로 담았었음!!
					items의 ${list}가 바로 그 list
					 -->


					<c:forEach items="${list }" var="row">
						<tr>

							<!-- // select 문에서 뽑아온 이름이 Map의 key로 사용되고, 
						화면에서는 그 키를 이용하여 데이터에 접근        -->

							<td>${row.id }</td>
							<!-- 제목에 a태그를 넣어서 링크 가능하게 -->
							<td class="title"><a href="/user/detail?id=${row.id}"
								name="title"> ${row.name} </a></td>
							<td>${row.salary }</td>
							<td>${row.status }</td>
							<td>${row.createdDate }</td>
							<td>${row.modifiedDate }</td>

							<td><a href="/user/delete?id=${row.id}">X</a></td>

							<!-- <td><input type="button" id="id" name="deleteBtn"
								value="${row.id}">X</td> -->
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr>
						<td colspan="7">조회된 결과가 없습니다.</td>
					</tr>
				</c:otherwise>
			</c:choose>
		</tbody>
	</table>
	<br>
	<!-- <input type="button" value="user add" onClick="location='/userAdd.jsp'" /> -->
	<input type="button" value="user list"
		onClick="location='/user/list'" />
		
	<input type="button" value="user add"
		onClick="location='/user/addPage'" />



	<%@ include file="/WEB-INF/include/include-body.jspf"%>
	<script type="text/javascript">
		// 삭제 버튼 클릭시 이벤트 바인딩
		$("input[name='deleteBtn']").on("click", function(e) {
			e.preventDefault();
			// this : jQuery 객체(게시글 제목인 a태그를 의미)
			fn_clickDeleteButton($(this));
		});

		function fn_clickDeleteButton(obj) {

			var comSubmit = new ComSubmit();
			comSubmit.setUrl("<c:url value='/user/delete' />");
			comSubmit.addParam("id", obj.find("#id").val());
			comSubmit.submit();
		}
	</script>

</body>
</html>