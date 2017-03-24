<%@page pageEncoding="UTF-8"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"> 
	<title>All Author</title>  
	  <link rel="stylesheet" href="../res/style.css">
</head>
<body>
<h2>List Author</h2>
<div class="menu">
 <a href="../">Home</a>
 <a href="listAuthor.do">List Author</a>
<a href="viewAuthor.do">Add Author</a>
</div>
 <br>

<c:if test="${!empty list}">
	<table>
		<tr>
			<th>AuthorId</th>
			<th>AuthorName</th>
			<th>Operations</th>
		</tr>

		<c:forEach items="${list}" var="author">
			<tr>
				<td><c:out value="${author.authorId}"/></td>
				<td><c:out value="${author.name}"/></td>
				<td><a href="viewAuthor.do?authorId=${author.authorId}">Edit</a> &nbsp;  <a href="delAuthor.do?authorId=${author.authorId}">Del</a></td>
			</tr>
		</c:forEach>
	</table>
</c:if>
</body>
</html>