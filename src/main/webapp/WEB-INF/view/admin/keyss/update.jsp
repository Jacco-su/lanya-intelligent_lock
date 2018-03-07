<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%
    response.setHeader("Pragma", "No-Cache");
    response.setHeader("Cache-Control", "No-Cache");
    response.setDateHeader("Expires", 0);
%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<script>
//    //获取人
//    $(function (data) {
//        var d = JSON.parse(data);
//        $('#users').empty();
//        var userData = []; //创建数组
//        for (var i = 0; i < d.length; i++) {
//            userData.push({
//                "id": d[i].id,
//                "text": d[i].username
//            });
//        }
//        if (d[0] != null) {
//            $("#users").combobox("clear")//下拉框加载数据,设置默认值为
//                .combobox("loadData", userData).combobox("setValue", d[0].id);
//        }
//    });

    $(function () {
        var data = '${userList}';
        data = JSON.parse(data);
        $('#usersname').empty();
        <%--$("#usersname").prepend("<option value='0'>${keyss.user.username}</option>");   //为Select插入一个Option(第一个位置)--%>
        for (var i = 0; i < data.length; i++) {
            $('#usersname').append("<option value='" + data[i].id + "'>" + data[i].username + "</option>");
        }
    });
</script>
<%--/**   修改*/--%>
<div>
    <form name="editForm" id="editForm" action="${basePath}/keyss/update" method="post">
        <table class="mytable" align="center">
            <tr>
                <td>钥匙编号:</td>
                <td><input type="hidden" name="id" value="${keyss.id}"/>
                    <input name="dept.id" type="hidden" value="${keyss.user.id}"/>
                    <input name="keyssCode" value="${keyss.keyssCode}" class="easyui-validatebox" required="true"/></td>
            </tr>
            <tr>
                <td>钥匙名称:</td>
                <td><input name="keyssName" value="${keyss.keyssName}" class="easyui-validatebox" required="true"/>
                </td>
            </tr>
            <tr>
                <td>钥匙MAC:</td>
                <td><input name="keyssMAC" value="${keyss.keyssMAC}" class="easyui-validatebox" required="true"/>
                </td>
            </tr>
            <tr>
                <td width="100">领用人：</td>
                <td>
                    <%--<input type="text" name="userName" id="areaname" value="${keyss.userName}"/><a--%>
                    <%--class="easyui-linkbutton" onclick="$('#selectArea').window('open');">选择</a>--%>
                    <%--<input type="hidden" name="discode" id="discode" value="${keyss.id}"/>--%>
                    <%--<input type="hidden" name="user.id" id="discode" value="${keyss.user.username}"/>--%>
                    <select id="usersname" name="user.id" style="width: 200px;"></select>

                        <%--<select class="easyui-combobox" name="userName" id="users" style="width: 180px;"--%>
                                <%--data-options="editable:false,valueField:'id', textField:'text'">--%>
                            <%--<option value="0">---请选择---</option>--%>
                        <%--</select>--%>
                        <%--<input name="keyssDate" value="${keyss.keyssDate}" class="easyui-validatebox" required="true"/>--%>
                </td>
            </tr>
        </table>
    </form>
</div>
