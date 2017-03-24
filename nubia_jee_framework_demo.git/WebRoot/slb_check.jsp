<%@page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Server Information</title>
</head>
<body>
<h1>Server Information</h1>
<table border="1">
    <tr>
        <td>Session ID</td>
        <td><%= session.getId() %>
        </td>
    </tr>
    <tr>
        <td>Created on</td>
        <td><%= session.getCreationTime() %>
        </td>
    </tr>
    <tr>
        <td>Local Addr</td>
        <td><%=request.getLocalAddr()%> : <%=request.getLocalPort()%>
        </td>
    </tr>
    <tr>
        <td>Remote Addr</td>
        <td><%=request.getRemoteAddr()%> :  <%=request.getServerPort()%>
        </td>
    </tr>
    <tr>
        <td>Intranet IP</td>
        <td><%= java.net.InetAddress.getLocalHost().getHostAddress() %>
        </td>
    </tr>
    <tr>
        <td>RemoteAddr IP</td>
        <td><%=request.getRemoteAddr()%>
        </td>
    </tr>
    <tr>
        <td>X-Forwarded-For IP</td>
        <td><%=request.getHeader("x-forwarded-for") %>
        </td>
    </tr>
    <tr>
        <td>Proxy-Client IP</td>
        <td><%=request.getHeader("Proxy-Client-IP") %>
        </td>
    </tr>
    <tr>
        <td>WL-Proxy-Client IP</td>
        <td><%=request.getHeader("WL-Proxy-Client-IP")%>
        </td>
    </tr>
</table>
</body>
</html>