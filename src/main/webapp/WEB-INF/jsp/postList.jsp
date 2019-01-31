<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/include/include-header.jspf"%>
<meta charset="UTF-8">
</head>


<body>

<h1>POSTS LIST</h1>


<table class="post_list">
		<colgroup>
			<col width="10%" />
			<col width="*" />
			<col width="15%" />
			<col width="20%" />
		</colgroup>
		<thead>
			<tr>
				<th scope="col">글번호</th>
				<th scope="col">제목</th>
				<th scope="col">내용</th>
				<th scope="col">작성자ID</th>
				<th scope="col">작성일</th>
				<th scope="col">수정일</th>
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
							<td class="title"><a href="#this" name="title">${row.title }</a>
								<input type="hidden" id="IDX" value="${row.title }"></td>
							<td>${row.content }</td>
							<td>${row.user_id }</td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr>
						<td colspan="4">조회된 결과가 없습니다.</td>
					</tr>
				</c:otherwise>
			</c:choose>
		</tbody>
	</table>






<script type="text/javascript"></script>



</body>
</html>