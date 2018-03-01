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
            <%--<tr>--%>
                <%--<td>控制器ID:</td>--%>
                <%--<td>--%>
                    <%--<input type="hidden" name="cid" value="${collectore.cid}"/>--%>
                    <%--<input name="name" value="${collectore.ceCode}" class="easyui-validatebox" required="true"/></td>--%>
            <%--</tr>--%>

            <%--<tr>--%>
                <%--<td width="100">所属智能采集器</td>--%>
                <%--<td>--%>
                    <%--<input type="text" name="disame" id="areaname" value="${collectore.collertor}" readonly/><a--%>
                        <%--class="easyui-linkbutton" onclick="$('#selectArea').window('open');">选择</a>--%>
                    <%--<input type="hidden" name="discode" id="discode" value="${collectore.collector}"/>--%>
                <%--</td>--%>
            <%--</tr>--%>
            <%--<tr>--%>
                <%--<td>MAC地址:</td>--%>
                <%--<td>--%>
                    <%--<input name="cip" value="${collectore.ceMAC}" class="easyui-validatebox" required="true"/>--%>
                <%--</td>--%>
            <%--</tr>--%>
            <%--<tr>--%>
                <%--<td>日期:</td>--%>
                <%--<td><input name="cdate" value="${collectore.ceDate}" class="easyui-validatebox" required="true"/>--%>
                <%--</td>--%>
            <%--</tr>--%>
换个环境空间看了
        </table>
    </form>
</div>

<%--<%@ page language="java" contentType="text/html; charset=UTF-8"--%>
         <%--pageEncoding="UTF-8" %>--%>
<%--<%--%>
    <%--response.setHeader("Pragma", "No-Cache");--%>
    <%--response.setHeader("Cache-Control", "No-Cache");--%>
    <%--response.setDateHeader("Expires", 0);--%>
<%--%>--%>
<%--<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>--%>
<%--<div>--%>
    <%--<form name="editForm" id="editForm" action="${basePath}/disa/update" method="post">--%>
        <%--<table class="mytable" align="center">--%>
            <%--<tr>--%>
                <%--<td>站点名称:</td>--%>
                <%--<td>--%>
                    <%--<input  name="id" type="hidden" value="${qgdis.id}"/>--%>
                    <%--<input  name="dept.id" type="hidden" value="${qgdis.dept.id}"/>--%>
                    <%--<input name="name" class="easyui-validatebox" width="280px" value="${qgdis.name}" required="true"/>--%>
                <%--</td>--%>
            <%--</tr>--%>
            <%--<tr>--%>
                <%--<td>所在地址:</td>--%>
                <%--<td>--%>
                    <%--<textarea name="address" class="easyui-validatebox" id="address" cols="25" rows="3"--%>
                              <%--required="true">${qgdis.address}</textarea>--%>
                    <%--&lt;%&ndash;<input name="address" class="easyui-validatebox" id="addres" cols="30" rows="10" value="${qgdis.address}" required="true">></input>&ndash;%&gt;--%>
                    <%--<input type="hidden" name="createTime" class="easyui-validatebox" id="time" cols="30" rows="10"--%>
                           <%--value="${qgdis.createTime}" required="true"/>--%>
                <%--</td>--%>
            <%--</tr>--%>
            <%--&lt;%&ndash;<tr>&ndash;%&gt;--%>
            <%--&lt;%&ndash;<td>修改时间:</td>&ndash;%&gt;--%>
            <%--&lt;%&ndash;<td>&ndash;%&gt;--%>

            <%--&lt;%&ndash;<input name="time" class="easyui-validatebox" id="time" cols="30" rows="10"&ndash;%&gt;--%>
            <%--&lt;%&ndash;value="${qgdis.createTime}" required="true"/>&ndash;%&gt;--%>
            <%--&lt;%&ndash;</td>&ndash;%&gt;--%>
            <%--&lt;%&ndash;</tr>&ndash;%&gt;--%>
        <%--</table>--%>
    <%--</form>--%>
<%--</div>--%>