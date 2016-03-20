<%--
  Created by IntelliJ IDEA.
  User: serap
  Date: 2016-03-20
  Time: 10:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>SpringMVC文件下载</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/dwr/engine.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/dwr/interface/FileDownloadService.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-1.8.3.js"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/table.css"/>
</head>
<body>
<div id="downloadDiv">
    <div>
        <button id="list" type="button">罗列可下载文件</button>
    </div>
    <hr/>
    <div>
        <table>
            <thead>
            <tr>
                <th>文件名称</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            </tbody>
        </table>
    </div>
</div>

<script type="text/javascript">
    $(function () {

        var downloadDiv = $("#downloadDiv");
        downloadDiv.on("click", "#list", function (event) {
            event.stopPropagation();
            FileDownloadService.listFiles(function (fileList) {
                var tbodyContent = "";
                for (var i = 0; i < fileList.length; i++) {
                    tbodyContent += "<tr><td>";
                    tbodyContent += fileList[i];
                    tbodyContent += "</td><td></td></tr>";
                }
                downloadDiv.find("tbody").html(tbodyContent);
                var btn = $("<button type=\"button\">下载</button>");
                var rows = downloadDiv.find("tbody tr");
                for (i = 0; i < rows.length; i++) {
                    $(rows[i]).find("td:nth-child(2)").append(btn.clone());
                }
            });
        });

        // 页面加载完毕之后立即出发list按钮的事件
        $("#list").trigger("click");

        downloadDiv.on("click", "td button", function (event) {
            event.stopPropagation();
            // 第一步：获取要下载的文件名称
            var filename = $(this).parent().prev("td").html();
            // 第二步：使用DWR完成文件下载
            FileDownloadService.downloadFile(filename, {
                // 使用DWR创建iframe下载文件
                callback: function (responseFile) {
                    dwr.engine.openInDownload(responseFile);
                },
                // 异常处理
                errorHandler: function (msg, e) {
                    // 弹出警告框显示错误信息
                    alert(msg);
                    // 控制台打印错误发生位置
                    console.log(e.stackTrace[0]);
                },
                // 不使用异步请求
                async: false
            });
        });

    });
</script>
</body>
</html>
