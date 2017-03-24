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
    <a href="listReview.do">List Review</a>
    <a href="viewReview.do">Add Review</a>
</div>
<br>
<form method="post" action="saveReview.do">
    <input type="hidden" name="review_id" value="${review.review_id}">
    username: <input name="username" value="anonymous"> <br>
    comments: <input name="comments" value="Good"> <br>
    <input type="submit" value="Save Review" />
</form>
</body>
</html>