<%@page pageEncoding="UTF-8"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%><!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>All User</title>
<link rel="stylesheet" href="../res/style.css">
</head>
<body>
	<h2>List User</h2>
	<div class="menu">
		<a href="../">Home</a> 
		<a href="listUser.do">List User</a> 
		<a href="viewUser.do">Add User</a>
	</div>
	<br>

	<c:if test="${!empty list}">
		<table>
			<tr>
				<th>UserId</th>
				<th>Username</th>
				<th>Password</th>
				<th>Addtime</th>
				<th>Operations</th>
			</tr>

			<c:forEach items="${list}" var="user">
				<tr>
					<td><c:out value="${user.userId}" /></td>
					<td><c:out value="${user.username}" /></td>
					<td><c:out value="${user.password}" /></td>
					<td><fmt:formatDate value="${user.addTime}" type="both" pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td class="ops"><a href="viewUser.do?userId=${user.userId}">Edit</a><a
						href="javascript:;" onclick="delUser(${user.userId},event)">Del</a></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
	
<script src="../res/js/jquery.js"></script>
<script>
function delUser(user_id,evt){
	var o=evt.target||evt.srcElement;
	if(confirm('您确定要删除此用户? ')){
		$.get('delUser.do?userId='+user_id, function(r){	//$.get(URL,data,function(data,status,xhr),dataType)
			if(r=='success') {								//使用 HTTP GET 请求从服务器加载数据。规定您需要请求的 URL。
				var tr=o.parentNode.parentNode;				//function规定当请求成功时运行的函数。
				$(tr).fadeOut('slow',function(){			//jQuery $(tr) 这的选取就会取到所有的tr里的元素
					tr.parentNode.removeChild(tr);			//删除自己 即删除此行
				});
			}else{
				alert('系统出现异常');
			}
		});
	}
}
</script>
</body>
</html>