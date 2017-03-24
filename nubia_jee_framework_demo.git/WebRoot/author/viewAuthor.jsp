<%@page pageEncoding="UTF-8"%><!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Add Author</title>
<link rel="stylesheet" href="../res/style.css">
</head>
<body>
	<h2>Add/Edit Author</h2>
	<div class="menu">
		<a href="../">Home</a> 
		<a href="listAuthor.do">List Author</a> 
		<a href="viewAuthor.do">Add Author</a>
	</div>
	<br>

	<form method="post" action="saveAuthor.do">
		<input type="hidden" name="authorId" value="${author.authorId}">
		authorname: <input name="name" value="${author.name}"> <br>
		<input type="submit" value="Save Author" />
	</form>

</body>
</html>