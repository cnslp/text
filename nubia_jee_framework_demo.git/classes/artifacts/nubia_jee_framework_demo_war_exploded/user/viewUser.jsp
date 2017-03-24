<%@page pageEncoding="UTF-8"%><!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Add User</title>
<link rel="stylesheet" href="../res/style.css">
</head>
<body>
	<h2>Add/Edit User</h2>
	<div class="menu">
		<a href="../">Home</a> 
		<a href="listUser.do">List User</a> 
		<a href="viewUser.do">Add User</a>
	</div>
	<br>
	<form method="post" action="saveUser.do">
		<input type="hidden" name="userId" value="${user.userId}">
		username: <input name="username" value="${user.username}"> <br>
		password: <input name="password" value="${user.password}"> <br>
		<input type="submit" value="Save User" />
	</form>
</body>
</html>