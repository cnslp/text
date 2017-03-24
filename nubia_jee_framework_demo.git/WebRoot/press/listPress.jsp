<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"> 
	<title>All Press</title>  
	  <link rel="stylesheet" href="../res/style.css">
</head>
<body>
<h2>List Press</h2>
<div class="menu">
 <a href="../">Home</a>
 <a href="listPress.do">List Press</a>
<a href="viewPress.do">Add Press</a>
</div>
 <br>

<c:if test="${!empty list}">
	<table>
		<tr>
			<th>PressId</th>
			<th>PressName</th>
			<th>Operations</th>
		</tr>

		<c:forEach items="${list}" var="press">
			<tr>
				<td><c:out value="${press.pressId}"/></td>
				<td><c:out value="${press.name}"/></td>
				<td><a href="viewPress.do?pressId=${press.pressId}">Edit</a> &nbsp;  <a href="delPress.do?pressId=${press.pressId}">Del</a></td>
			</tr>
		</c:forEach>
	</table>
</c:if>
</body>
</html>