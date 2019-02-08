<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/include/include-header.jspf"%>
<meta charset="UTF-8">
</head>


<body>

	<h1>USER ADD</h1>
	<hr>
	<form action="/user/add">
		<table>
			<tr>
				<td>name :</td>
				<td><input type="text" name="name" placeholder="이름을 입력하세요" /></td>
			</tr>

			<tr>
				<td>salary :</td>


				<td><select name="salary">
						<option value="1000">1000</option>
						<option value="2000">2000</option>
						<option value="3000">3000</option>
						<option value="4000">4000</option>
						<option value="5000">5000</option>
				</select></td>


			</tr>

			<tr>
				<td>status :</td>

				<td><select name="status">
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
						<option value="5">5</option>
				</select></td>
			</tr>


		</table>

		<hr>

		<input type="submit" value="완료" />


	</form>





	<%@ include file="/WEB-INF/include/include-body.jspf"%>
	<script type="text/javascript">
		
	</script>

</body>
</html>