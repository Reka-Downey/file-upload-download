<%--
  Created by IntelliJ IDEA.
  User: serap
  Date: 2016-03-20
  Time: 10:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>SpringMVC文件上传</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-1.8.3.js"></script>
</head>
<body>
<div>
    <form id="batch" enctype="multipart/form-data" method="post"
          action="${pageContext.request.contextPath}/upload">
        <label>请选择文件：<input type="file" name="batch"/></label><br/>
        <button id="append" type="button">继续添加</button>
        <button type="submit">上传</button>
    </form>
</div>
<script type="text/javascript">
    $(function () {
        var batch = $("#batch");
        batch.on("click", "#append", function (event) {
            event.stopPropagation();
            $("#append").before("<label>请选择文件：<input type=\"file\" name=\"batch\"/></label><br/>");
        });
    });
</script>
</body>
</html>
