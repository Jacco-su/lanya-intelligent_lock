<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>--%>
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
                <td>智能锁编号:</td>
                <td><input name="name" value="" class="easyui-validatebox" required="true"/></td>
            </tr>
            <tr>
                <td>识别码:</td>
                <td>
                    <input name="cip" value="点击获取识别码" class="easyui-validatebox"
                           onclick="$('#selectArea').window('open');" required="true"/>

                </td>
            </tr>
            <tr>
                <td width="100">安装区域:</td>
                <td>
                    <input type="text" name="name" id="deptname" value="" readonly/><a class="easyui-linkbutton"
                                                                                       onclick="$('#selectDept').window('open');">选择</a>
                    <input type="hidden" name="deptid" id="deptid" value=""/>
                </td>
            </tr>
            <tr>
                <td width="100">安装时间:</td>
                <td>
                    <%
                        Date dNow = new Date();
                        SimpleDateFormat ft =
                                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                        out.print( "<h5 align=\"center\">" + ft.format(dNow)+"</h5>" );
                        String date = ft.format(new Date());
                    %>

                    <input type="text" name="lockDate" id="lockDate" value=<%=date%>  readonly/>

                    <input type="hidden" name="lockDate" id="lockDate" value=""/>


                </td>
            </tr>

            <tr>
                <td>排序:</td>
                <td>
                    <input name="cdate" class="easyui-validatebox" value="" required="true"/>

                </td>
            </tr>
        </table>
    </form>
</div>