<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%
    response.setHeader("Pragma", "No-Cache");
    response.setHeader("Cache-Control", "No-Cache");
    response.setDateHeader("Expires", 0);
%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<script>


    $(function () {
        $('#userTree').tree({
            checkbox: false,
            url: basePath+'/dept/getChildren',
            onBeforeExpand:function(node,param){
                $('#userTree').tree('options').url = basePath+"/dept/getChildren?parentId=" + node.id;
            },
            onClick:function(node){
                getUsers(node.id);
            }
        });
        //获取可用串口
        $.post(basePath+"/offline/serial",null,function(data){
            console.log("串口");
            var d=data.split(",");
            $('#serials').empty();
            var serialData = []; //创建数组
            for(var i=0;i<d.length;i++){
                serialData.push({
                    "id": d[i],
                    "text": d[i]
                });
            }
            if( d[0]!=null) {
                $("#serials").combobox("clear")//下拉框加载数据,设置默认值为
                    .combobox("loadData", serialData).combobox("setValue", d[0]);
            }
        });
        function getUsers(obj) {
            var data={
                "disaId":obj
            };
            //获取站点
            $.post(basePath+"/authorization/distribution",data,function(data){
                var d=JSON.parse(data);
                $('#disa').empty();
                $('#disa').append("<option value='" + 0 + "'>" + "--请选择--" + "</option>");
                for(var i=0;i<d.length;i++){
                    $('#disa').append("<option value='" + d[i].id + "'>" + d[i].name + "</option>");
                    /*if(){
                        $('#usersname').append("<option selected value='" + d[i].id + "'>" + d[i].username + "</option>");
                    }else{
                        $('#usersname').append("<option value='" + d[i].id + "'>" + d[i].username + "</option>");
                    }*/

                }
            });

        }
        $("#disa").change(function(){
            var options=$("#disa option:selected");
            var data={
                "disaId":options.val()
            };
            //获取控制器
            $.post(basePath+"/authorization/disa/collector",data,function(data){
                var d=JSON.parse(data);
                $('#collector').empty();
                $('#collector').append("<option value='" + 0 + "'>" + "--请选择--" + "</option>");
                for(var i=0;i<d.length;i++){
                    $('#collector').append("<option value='" + d[i].ccode + "'>" + d[i].cip + "</option>");


                }
            });
        });
        $("#butt").click(function(){
            var serial=$('#serials').combobox('getValue');
            if(serial==""){
                alert("请选择串口!");
                return;
            }
            var data={
                "serial":serial,
                "T":14
            };
            $.ajax({
                type: "post",
                url: basePath+"/offline/read",
                cache:false,
                async:false,
                data:data,
                dataType: "json",
                success: function(data){
                    if(data.result=="1"){
                        $('#keys').val(data.message.split("->")[1]);
                    }else{
                        alert("离线日志获取失败，请重试！");
                    }
                }

            });
        })
    });
</script>
<%--/**   修改*/--%>
<div>
    <%--<form name="editForm" id="editForm" action="${basePath}/keyss/update" method="post">--%>
    <table width="100%" border="0" cellpadding="0" cellspacing="0" height="530">
        <tr>
            <td width="24%" valign="top"
                style="border: 1px solid #99bbe8; border-right: 0;">
                <div class="panel-header" style="border-left: 0; border-right: 0;">区域</div>
                <ul id="userTree" style="margin-top: 10px;"></ul>
            </td>
            <td valign="top" style="border: 1px solid #99bbe8;">
                <table class="mytable" align="center">
                    <tr>
                        <td>串口:</td>
                        <td colspan="3">
                            <select class="easyui-combobox" name="serials" id="serials" style="width: 180px;"
                                    data-options="editable:false,valueField:'id', textField:'text'">
                                <option value="0">---请选择---</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <%--<td colspan="3"><button id="butt" onclick="getLog()">刷新</button></td>--%>
                        <td colspan="3"><button id="butt" class="easyui-linkbutton" >刷新</button></td>

                    </tr>
                </table>
            </td>
        </tr>
    </table>

    <%--</form>--%>
</div>
