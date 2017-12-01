<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
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

            <%--<tr>--%>
            <%--<td width="100">所属配电房:</td>--%>
            <%--<td>--%>
            <%--<input type="text" name="name" id="areaname" value="" readonly/><a class="easyui-linkbutton"--%>
            <%--onclick="$('#selectArea').window('open');">选择</a>--%>
            <%--<input type="hidden" name="areacode" id="areacode" value=""/>--%>
            <%--</td>--%>
            <%--</tr>--%>
            <tr>
                <td>所属配电房:</td>
                <td>
                    <select name="qgdisId" name="qgdisId" style="width:200px;" class="easyui-validatebox"
                            required="true">
                        <option value="">---请选择---</option>
                        <c:forEach items="${qgdisList}" var="qgdis" varStatus="s">
                            <option value="${qgdis.id}"
                                    <c:if test="${qgdis.id eq seqgdis.id}">selected</c:if>>${qgdis.name}</option>
                        </c:forEach>
                    </select>
                </td>
            <tr>
                <td>IP地址:</td>
                <td>
                    <input name="cip" value="点击获取" class="easyui-validatebox" required="true"
                           onclick="$('#selectArea').window('open');"/>
                </td>
            </tr>
            <tr>
                <td>日期:</td>
                <td>
                    <%

                        SimpleDateFormat ft =
                                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        Date dNow = new Date();
//                        out.print( "<h5 align=\"center\">" + ft.format(dNow)+"</h5>" );
                        //Date date = ft.format(new Date());
                        String str = ft.format(dNow);
                        System.out.println(str);
                    %>
                    <input name="cdate" type="text" value=<%=ft.format(dNow)%> required="true"/>
                </td>
            </tr>
        </table>
    </form>
</div>