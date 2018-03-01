<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%
    response.setHeader("Pragma", "No-Cache");
    response.setHeader("Cache-Control", "No-Cache");
    response.setDateHeader("Expires", 0);
%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>

<%--&lt;%&ndash;/**   修改*/&ndash;%&gt;--%>
<div>
    <form name="editForm" id="editForm" action="${basePath}/collectore/prUpdate" method="post">
        <table class="mytable" align="center">
            <tr>
                <td>控制器ID:</td>
                <td>
                    <input type="hidden" name="id" value="${collectore.id}"/>
                    <input name="name" value="${collectore.ceCode}" class="easyui-validatebox" required="true"/></td>
            </tr>

           <tr>
                <td width="100">所属智能采集器</td>
                <td>
                    <input type="text" name="disame" id="areaname" value="${collectore.collectors.ccode}" readonly/><a
                        class="easyui-linkbutton" onclick="$('#selectArea').window('open');">选择</a>
                    <%--<input type="hidden" name="discode" id="discode" />--%>
                </td>
            </tr>
            <tr>
                <td>MAC地址:</td>
                <td>
                    <input name="ceMAC" value="${collectore.ceMAC}" class="easyui-validatebox" required="true"/>
                </td>
            </tr>
            <tr>
                <td>日期:</td>
                <td><input name="ceDate" value="${collectore.ceDate}" class="easyui-validatebox" required="true"/>
                </td>
            </tr>
        </table>
    </form>
</div>