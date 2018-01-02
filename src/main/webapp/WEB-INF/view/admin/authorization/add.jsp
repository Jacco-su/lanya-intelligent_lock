<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%
    response.setHeader("Pragma", "No-Cache");
    response.setHeader("Cache-Control", "No-Cache");
    response.setDateHeader("Expires", 0);
%>
<%--<link rel="stylesheet" type="text/css" href="../../themes/default/easyui.css">--%>
<%--<link rel="stylesheet" type="text/css" href="../../themes/icon.css">--%>
<%--<link rel="stylesheet" type="text/css" href="../demo.css">--%>
<%--<script type="text/javascript" src="../../jquery.min.js"></script>--%>
<%--<script type="text/javascript" src="../../jquery.easyui.min.js"></script>--%>

<meta charset="UTF-8">
<title>Basic DateTimeBox - jQuery EasyUI Demo</title>
<link rel="stylesheet" type="text/css" href="${basePath}/js/easyui/themes/default/easyui.css"/>
<link rel="stylesheet" type="text/css" href="${basePath}/js/easyui/themes/icon.css"/>
<link rel="stylesheet" type="text/css" href="${basePath}/js/jquery-easyui-1.5.3/demo/demo.css">

<script type="text/javascript" src="${basePath}/js/easyui/jquery.easyui.min.1.2.2.js"></script>
<script type="text/javascript" src="${basePath}/js/jquery-easyui-1.5.3/jquery.easyui.min.js"></script>

<script type="text/javascript" src="${basePath}/js/jquery-easyui-1.5.3/jquery.min.js"></script>

<script>
    $('#dt').datetimebox({
        value: '',
        required: true,
        showSeconds: true,
        timespinner: true,
        width: '80px',

    });

    //$('#ss').timespinner({
    //    min: '',
    //    required: true,
    //    showSeconds: true,
    //    width:80px;
    //
    //});
    //$('#ss').timespinner({
    //    required:true,
    //    increment:10
    //    width:80px;
    //});
    $(function () {
        $('#ss').timespinner({
            value: '00:00',
            min: '00:00',
            max: '23:59',
            editable: false,
            width: '80px',
            height: '100px',
        });
    });


</script>

<div>
    <form name="addForm" id="addForm" action="${basePath}/authorization/onLine" method="post">
        <table class="mytable" align="center">
            <tr>
                <td>
                    获取钥匙MAC:
                </td>
                <td>
                    <input type="" onclick="keyssMAC"> <br/>
                </td>
            </tr>
            <tr>
                <td>
                    授权期限:
                </td>
                <td>
                    <div style="margin:20px 0;"></div>
                    <div class="" style="width:100%;max-width:400px;padding:30px 60px;">
                        <div style="margin-bottom:20px">
                            <input class="easyui-datetimebox" label="Select DateTime:" labelPosition="top"
                                   style="width:100%;">
                        </div>
                        <div style="margin-bottom:20px">
                            <input class="easyui-textbox" label="Description:" labelPosition="top" style="width:100%;">
                        </div>
                    </div>
                </td>

            </tr>
            <tr>
                <td>
                    授权使用人:
                </td>
                <td>
                    <input type=""> <br/>
                </td>
            </tr>
            <tr>
                <td>
                    锁具范围:
                </td>
                <td>
                    <input type="text"> <br/>
                </td>
            </tr>
            <tr>
                <td>
                    操作人:
                </td>
                <td>
                    <input type="text"> <br/>
                </td>
            </tr>
            <tr>
                <td>
                    工作票:
                </td>
                <td>
                    <input type="file" value="ss"> <br/>
                </td>
            </tr>
            <tr>
                <td>
                    <input type="button" value="确认授权">
                </td>
                <td>
                    <input type="reset" value="重置"><br/>
                </td>
            </tr>


        </table>
    </form>
</div>