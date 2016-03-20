<%--
  Created by IntelliJ IDEA.
  User: serap
  Date: 2016-03-20
  Time: 10:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>SpringMVC文件下载</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/static/css/table.css"/>
</head>
<body>
<div>
    <table>
        <thead>
        <tr>
            <th>文件名称</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${files}" var="file">
            <tr>
                <td>${file}</td>
                <td><a href="${pageContext.request.contextPath}/download?filename=${file}">
                    <button type="button">下载</button>
                </a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
