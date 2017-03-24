<%@page pageEncoding="UTF-8"%><!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Add Press</title>
<link rel="stylesheet" href="../res/style.css">
</head>
<body>
	<h2>Add/Edit Press</h2>
	<div class="menu">
		<a href="../">Home</a> 
		<a href="listPress.do">List Press</a> 
		<a href="viewPress.do">Add Press</a>
	</div>
	<br>

	<form method="post" action="savePress.do">
		<input type="hidden" name="pressId" value="${press.pressId}">
		pressname: <input name="name" value="${press.name}"> <br> 
		<input type="submit" value="Save Press" />
	</form>

</body>
</html>