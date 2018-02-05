<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%
    response.setHeader("Pragma", "No-Cache");
    response.setHeader("Cache-Control", "No-Cache");
    response.setDateHeader("Expires", 0);
%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>

<%--/**   修改*/--%>
<div>
    <form name="editForm" id="editForm" action="${basePath}/collectore/prUpdate" method="post">
        <table class="mytable" align="center">
            <tr>
                <td>采集器ID:</td>
                <td>
                    <input type="hidden" name="cid" value="${collectore.ceCode}"/>
                    <input name="name" value="${collectore.cid}" class="easyui-validatebox" required="true"/></td>
            </tr>

            <tr>
                <td width="100">所属智能采集器</td>
                <td>
                    <input type="text" name="disame" id="areaname" value="${collectore.collertor}" readonly/><a
                        class="easyui-linkbutton" onclick="$('#selectArea').window('open');">选择</a>
                    <input type="hidden" name="discode" id="discode" value="${collectore.collector}"/>
                </td>
            </tr>
            <tr>
                <td>MAC地址:</td>
                <td>
                    <input name="cip" value="${collectore.ceMAC}" class="easyui-validatebox" required="true"/>
                </td>
            </tr>
            <tr>
                <td>日期:</td>
                <td><input name="cdate" value="${collectore.ceDate}" class="easyui-validatebox" required="true"/>
                </td>
            </tr>
        </table>
    </form>
</div>