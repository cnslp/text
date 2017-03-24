<%@page pageEncoding="UTF-8"%><!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <title>Login Page</title>
    <link rel="stylesheet" href="../res/style.css">
  </head>
  <body>
    <div> Login Page</div>
	<form method="post" action="login.do">
		<div>
			<table>
				<tr>
					<td>User Name</td>
					<td><input type="text" size="40" name="username"/></td>
				</tr>
				<tr>
					<td>Password</td>
					<td><input type="password" size="40" name="password"/></td>
				</tr>
			</table>
		</div>
		<input type="hidden" size="40" name="userId"/>
		<input type="submit" value="Login" />
	</form>
  </body>
</html>