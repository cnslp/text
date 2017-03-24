<%@page pageEncoding="UTF-8"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Add Book</title>
<link rel="stylesheet" href="../res/style.css">
</head>
<body>
	<h2>Add/Edit Book</h2>
	<div class="menu">
	<a href="../">Home</a>
	<a href="listBook.do">List Book</a>
	<a href="viewBook.do">Add Book</a>
	</div>
	<br>

	<form method="post" action="saveBook.do" style="line-height:30px;">
		<input type="hidden" name="bookId" value="${book.bookId}">
		Press name: <select name="pressId">
			<c:forEach items="${listPress}" var="press">
				<option value="<c:out value="${press.pressId}"/>"
					<c:if test="${book.press.pressId == press.pressId}">selected</c:if>><c:out
						value="${press.name}" /></option>
			</c:forEach>
		</select> <br> Category name: <select name="categoryId">
			<c:forEach items="${listCategory}" var="category">
				<option value="<c:out value="${category.categoryId}"/>"
					<c:if test="${book.bookCategory.categoryId == category.categoryId}">selected</c:if>><c:out
						value="${category.name}" /></option>
			</c:forEach>
		</select> <br> bookname: <input name="name" value="${book.name}"> <br />
		<input type="submit" value="Save Book" />
	</form>

</body>
</html>