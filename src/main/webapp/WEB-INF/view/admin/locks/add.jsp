<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%
    response.setHeader("Pragma", "No-Cache");
    response.setHeader("Cache-Control", "No-Cache");
    response.setDateHeader("Expires", 0);
%>

<style>

    input:focus {
        color: rgba(7, 7, 7, 0.99);
    }

    input:link {
        color: rgba(80, 76, 78, 0.8);
    }
</style>

<div>
    <form name="addForm" id="addForm" action="${basePath}/locks/add" method="post">
        <table class="mytable" align="center">
            <tr>
                <td width="100">门锁编号:</td>
                <td>
                    <input id="lockNum" name="lockNum" style="width: 220px;"/>
            </tr>
            <tr>
                <td width="100">门锁识别码:</td>
                <td>
                    <input id="lockCode" name="lockCode" style="width: 220px;"/>
                    <a class="easyui-linkbutton"
                       onclick="getLockCode()">获取</a>
            </tr>
            <tr>
                <td width="100">选择站点:</td>
                <td>
                    <select id="dissName" name="qgdis.id" style="width: 220px;"></select>
            </tr>

            <tr>
                <td width="100">添加时间:</td>
                <td>
                    <input id="lockDate" name="lockDate" class="easyui-validatebox"  required="true"  value=""/>
                    <img onclick="WdatePicker({el:'lockDate'})" src="${basePath}/js/calendar/skin/datePicker.gif" width="16" height="22" align="absmiddle">
            </tr>
            <tr>
                <td width="100">详细地址:</td>
                <td>
                    <textarea name="address" class="easyui-validatebox" id="address" cols="30" rows="3"
                              required="true"></textarea>
            </tr>

        </table>
    </form>
</div>
<script>
    $(function () {
        var data='${dissList}';
        data=JSON.parse(data);
        $('#dissName').empty();
        for (var i=0;i<data.length;i++){
            $('#dissName').append("<option value='"+data[i].id+"'>"+data[i].name+"</option>");
        }
    })
</script>