<%--
  Created by IntelliJ IDEA.
  User: serap
  Date: 2016-03-20
  Time: 10:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>SpringMVC文件上传</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-1.8.3.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/dwr/engine.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/dwr/interface/FileUploadService.js"></script>
</head>
<body>
<div id="batch">
    <label>请选择文件：<input type="file" name="batch"/></label><br/>
    <button id="append" type="button">继续添加</button>
    <button id="upload" type="button">上传</button>
</div>
<script type="text/javascript">
    $(function () {
        var batch = $("#batch");
        batch.on("click", "#append", function (event) {
            event.stopPropagation();
            $("#append").before("<label>请选择文件：<input type=\"file\" name=\"batch\"/></label><br/>");
        });

        batch.on("click", "#upload", function (event) {
            event.stopPropagation();
            var jQueryFiles = batch.find("input[type='file'][name='batch']");
            var DOMFiles = [];
            for (var i = 0; i < jQueryFiles.length; i++) {
                DOMFiles[i] = jQueryFiles[i];
            }
            FileUploadService.batchUpload(DOMFiles, {
                callback: function () {
                    batch.find("label").remove();
                    batch.find("br").remove();
                    $("#append").before("<label>请选择文件：<input type=\"file\" name=\"batch\"/></label><br/>");
                },
                errorHandler: function (errorMsg) {
                    console.log(errorMsg);
                }
            });
        });
    });
</script>
</body>
</html>
