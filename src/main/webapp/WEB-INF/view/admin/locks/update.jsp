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
    <form name="editForm" id="editForm" action="${basePath}/locks/update" method="post">
        <table class="mytable" align="center">
            <tr>
                <td>智能锁编号:</td>
                <td><input type="hidden" name="id" value="${locks.id}"/>
                    <input name="dept.id" type="hidden" value="${locks.qgdis.id}"/>
                    <input name="lockNum" value="${locks.lockNum}" class="easyui-validatebox" required="true"/>


                </td>
            </tr>
            <tr>
                <td>门锁类型:</td>
                <td colspan="3">
                    <select class="easyui-combobox" name="lockType" id="lockType" style="width: 180px;"
                            data-options="editable:false,valueField:'id', textField:'text'">
                        <option <c:if test="${locks.lockType==0}">selected</c:if> value="0">挂锁</option>
                        <option <c:if test="${locks.lockType==1}">selected</c:if> value="1">机柜锁</option>
                        <option <c:if test="${locks.lockType==2}">selected</c:if> value="2">箱变锁</option>
                        <option <c:if test="${locks.lockType==3}">selected</c:if> value="3">暗梁锁</option>
                        <option <c:if test="${locks.lockType==4}">selected</c:if> value="4">防火门锁</option>
                        <option <c:if test="${locks.lockType==5}">selected</c:if> value="5">防盗门锁</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td>识别码:</td>
                <td>
                    <input name="lockCode"   readonly value="${locks.lockCode}" class="easyui-validatebox" required="true"/>
                </td>
            </tr>


            <tr>
                <td width="100">所属站点:</td>
                <td>
                    <input type="hidden" name="qgdis.id" id="discode" value="${locks.qgdis.id}"/>
                    <input type="text" name="qgdis.name" id="areaname" value="${locks.qgdis.name}" readonly/>
                    <%--<a--%>
                    <%--class="easyui-linkbutton" onclick="$('#selectArea').window('open');">选择</a>--%>


                </td>
            </tr>
            <tr>
                <td>
                    详细地址:
                </td>
                <td>
                    <input name="address" value="${locks.address}">
                </td>
            </tr>

            <tr>
                <td>日期:</td>
                <td><input name="lockDate" value="${locks.lockDate}" class="easyui-validatebox" required="true"/>
                </td>
            </tr>
        </table>
    </form>
</div>