<%@page pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%><!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>All Book</title>
<link rel="stylesheet" href="../res/style.css">
</head>
<body>
	<h2>List Book</h2>
	<div class="menu">
		<a href="../">Home</a> <a href="listBook.do">List Book</a> <a
			href="javascript:;" onclick="view()">Add Book</a>
	</div>
	<br>

	<c:if test="${!empty list}">
		<table>
			<tr>
				<th>BookId</th>
				<th>BookName</th>
				<th>PressName</th>
				<th>CategoryName</th>
				<th>AddTime</th>
				<th>Edit</th>
				<th>Del</th>
			</tr>

			<c:forEach items="${list}" var="book">
				<tr>
					<td><c:out value="${book.bookId}" /></td>
					<td><c:out value="${book.name}" /></td>
					<td><c:out value="${book.press.name}" /></td>
					<td><c:out value="${book.bookCategory.name}" /></td>
					<td><fmt:formatDate value="${book.addTime}" type="both" pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td><a href="javascript:;" onclick="view(${book.bookId})">Edit</a></td>
					<td><a href="javascript:;" onclick="del(${book.bookId},event)">Del</a></td>
				</tr>
			</c:forEach>
		</table>
		<div>${pageBean.pageNum}</div>
	</c:if>
	<script src="../res/js/jquery.js"></script>
	<script>
function view(bookId){
	 $.getScript('../res/js/jquery.popup.js', function() {
	      $.getCss('../res/css/jquery.popup.css');
	    $(document.body).popup({
	    	/*
	        buttons: {
	            保存: function(){
	                if(callback){
	                    callback.apply(this, arguments);
	                }else{
	                    $('#popup_iframe')[0].contentWindow.apply();   
	                }
	            },
	            取消: function(){
	                $(document.body).popup({close:true});
	            }                              
	        },*/
	        title: (bookId == null ? '新增' : '编辑')+'书籍',
	        url: 'viewBook.do'+(bookId==null?'':'?bookId='+bookId),
	        width:400,
	        height:300
	    });
	 });
}

function del(bookId,evt){
    var o=evt.target||evt.srcElement;
    if(confirm('您确定要删除此用户? ')){
        $.get('delBook.do?bookId='+bookId, function(r){
            if(r=='success') {
                var tr=o.parentNode.parentNode;
                $(tr).fadeOut('slow',function(){
                    tr.parentNode.removeChild(tr);
                });
            }else{
            	 $.fn.popup({title:'提示',type:'warning',text:'系统出现异常:'+r});
            }
        });
    }
}
</script>
</body>
</html>