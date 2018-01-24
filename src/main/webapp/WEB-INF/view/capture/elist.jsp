<%@ page import="com.dream.brick.equipment.action.CaptureAction" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2018/1/11
  Time: 18:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        td {
            width: 200px;
            height: 200px;
        }

        #showtu {
            left: 50px;
            margin-left: 50px;
        }
    </style>
    <script type="text/javascript">

    </script>
</head>
<body>
<form>
    <script>

        //        for (var i=0;i<cars.length;i++)
        //        {
        //            document.write(cars[i] + '<img src="http://localhost:8080/uploads/icture/001292.jpg">');
        //        }

    </script>

    <img src="http://localhost:8080/uploads/icture/+'.jpg'">
    <br/>
    起始时间:<input type="datetime-local">
    结束时间:<input type="datetime-local">
    选择设备:<select id="jingtou" name="dis.name" style="width: 220px;">

    <option value="">---请选择---</option>
</select>
    <input type="submit" value="查看">

    <input type="image"> <br/>
</form>
<datalist>
    <option value="BMW">
    <option value="Ford">
    <option value="Volvo">
</datalist>


<div id="showtu">

    <table border="1">
        <script type="text/javascript">
            var b = 2
            var c = 5;
            for (var j = 0; j < b; j++) {
                document.write('<tr>');
                for (var i = 0; i < c; i++) {
                    url:
                        <%--var  url = "<%CaptureAction.getFiles("http://localhost:8080/uploads/ictures/");%>";--%>
                        var url = "${fileList}"
                    document.write('<td><img src="${basePath}/uploads/ictures/' + url + '" /></td>');
                }
                document.write('</tr>');
            }
        </script>

    </table>
</div>
</body>

</html>
<script>
    $(function () {
        var data = '${qgdisList}';
        data = JSON.parse(data);
        $('#jingtou').empty();
        for (var i = 0; i < data.length; i++) {
            $('#jingtou').append("<option value='" + data[i].id + "'>" + data[i].name + "</option>");
        }
    })
</script>