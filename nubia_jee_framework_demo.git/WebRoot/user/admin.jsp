<%@page pageEncoding="UTF-8"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%><!DOCTYPE html>
<html>
<head>
 <meta charset="utf-8">   
<title>Login User</title>
<link rel="stylesheet" href="../res/style.css">
</head>
<body>
<c:if test="${!empty user}">
Hi,<c:out value="${user.username}"/>, you have logged.
</c:if>


<c:if test="${!empty bookList}">
<br>
<br>
<br>
<b>Borrowed Books:</b>
	<table>
		<tr>
			<th>BookId</th>
			<th>BookName</th>
			<th>BorrowTime</th>
			<th>Operations</th>
		</tr>

		<c:forEach items="${bookList}" var="ub">
			<tr>
				<td><c:out value="${ub.book.bookId}"/></td>
				<td><c:out value="${ub.book.name}"/></td>
				<td><fmt:formatDate value="${ub.borrowTime}" type="both" pattern="yyyy-MM-dd HH:mm:ss" /></td>
				<td><a href="returnBook.do?returnId=${ub.relationId}">Return</a></td>
			</tr>
		</c:forEach>
	</table>
</c:if>

<c:if test="${!empty otherBookList}">
<br>
<br>
<br>
<b>Other Books:</b>
	<table>
		<tr>
			<th>BookId</th>
			<th>BookName</th>
			<th>Addtime</th>
			<th>Operations</th>
		</tr>

		<c:forEach items="${otherBookList}" var="book">
			<tr>
				<td><c:out value="${book.bookId}"/></td>
				<td><c:out value="${book.name}"/></td>
				<td><fmt:formatDate value="${book.addTime}" type="both" pattern="yyyy-MM-dd HH:mm:ss" /></td>
				<td><a href="borrowBook.do?userId=${user.userId}&bookId=${book.bookId}">Borrow</a></td>
			</tr>
		</c:forEach>
	</table>
</c:if>

</body>
</html>