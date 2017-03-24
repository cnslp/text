<%@page pageEncoding="UTF-8"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%><!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>All User</title>
    <link rel="stylesheet" href="../res/style.css">
</head>
<body>
<h2>List Review</h2>
<div class="menu">
    <a href="../">Home</a>
    <a href="listReview.do">List Review</a>
    <a href="viewReview.do">Add Review</a>

    <c:if test="${!empty list}">
        <table>
            <tr>
                <th>Review_id</th>
                <th>Username</th>
                <th>Comments</th>
                <th>operation<th>
            </tr>

            <c:forEach items="${list}" var="review">
                <tr>
                    <td><c:out value="${review.review_id}" /></td>
                    <td><c:out value="${review.username}" /></td>
                    <td><c:out value="${review.comments}"/></td>
                    <td class="ops"><a href="update.do?review_id=${review.review_id}">Edit</a><a
                            href="javascript:;" onclick="delReview(${review.review_id},event)">Del</a></td>
                </tr>
            </c:forEach>
        </table>
    </c:if>

</div>
<br>

<script src="../res/js/jquery.js"></script>
<script>
    function delReview(review_id,evt){
        var o=evt.target||evt.srcElement;
        if(confirm('您确定要删除? ')){
            $.get('delReview.do?review_id='+review_id, function(r){	//$.get(URL,data,function(data,status,xhr),dataType)
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
