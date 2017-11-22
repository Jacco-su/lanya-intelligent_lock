<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%
    response.setHeader("Pragma", "No-Cache");
    response.setHeader("Cache-Control", "No-Cache");
    response.setDateHeader("Expires", 0);
%>
<div>
    <form name="addForm" id="addForm" action="${basePath}/collector/add" method="post">
        <table class="mytable" align="center">
            <tr>
                <td>采集器编号:</td>
                <td><input name="name" value="" class="easyui-validatebox" required="true"/></td>
            </tr>

            <tr>
                <td width="100">所属配电房:</td>
                <td>
                    <input type="text" name="name" id="areaname" value="" readonly/><a class="easyui-linkbutton"
                                                                                       onclick="$('#selectArea').window('open');">选择</a>
                    <input type="hidden" name="areacode" id="areacode" value=""/>
                </td>
            </tr>
            <tr>
                <td>IP地址:</td>
                <td>
                    <input name="cip" value="" class="easyui-validatebox" required="true"/>
                </td>
            </tr>
            <tr>
                <td>日期:</td>
                <td>
                    <input name="cdate" class="easyui-validatebox" value="" required="true"/>
                </td>
            </tr>
        </table>
    </form>
</div>