<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/include/include-header.jspf"%>
<meta charset="UTF-8">
</head>


<body>

	<h1>USER DETAIL</h1>
	<hr>

	<form action="/user/update">

		<table>

			<tr>
				<td>name :</td>
				<td><input type="text" name="name" value=${user.name } /></td>
			</tr>


			<tr>
				<td>salary :</td>
				<td><select name="salary">
						<option value="" selected disabled hidden>${user.salary }</option>

						<option value="1000">1000</option>
						<option value="2000">2000</option>
						<option value="3000">3000</option>
				</select></td>
			</tr>


			<tr>
				<td>status :</td>
				<td>${user.status }</td>
			</tr>



		</table>
		<input type="hidden" name="id" value=${user.id }>
		<hr>

		<input type="submit" value="수정완료" />
		<span>현재는 salary를 수정해야 에러가 안남</span>

	</form>




</body>
</html>