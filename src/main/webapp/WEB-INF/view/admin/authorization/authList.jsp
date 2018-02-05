<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%
    response.setHeader("Pragma", "No-Cache");
    response.setHeader("Cache-Control", "No-Cache");
    response.setDateHeader("Expires", 0);
%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>在线授权</title>
    <link rel="stylesheet" type="text/css"
          href="${basePath}/js/jquery-easyui-1.5.3/themes/default/easyui.css"/>
    <link rel="stylesheet" type="text/css"
          href="${basePath}/js/jquery-easyui-1.5.3/themes/icon.css"/>
    <script type="text/javascript" src="${basePath}/js/jquery.min.js"></script>
    <script type="text/javascript" src="${basePath}/js/jquery-easyui-1.5.3/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${basePath}/js/jquery-easyui-1.5.3/locale/easyui-lang-zh_CN.js" charset="UTF-8"></script>
    <script type="text/javascript" src="${basePath}/js/calendar/WdatePicker.js"></script>
    <script type="text/javascript">
        var basePath="${basePath}";
        $(function() {
            $('#tree').tree({
                checkbox: false,
                url: basePath+'/dept/getChildren',
                onBeforeExpand:function(node,param){
                    $('#tree').tree('options').url = basePath+"/dept/getChildren?parentId=" + node.id;
                },
                onClick:function(node){
                    refresh(node.id);
                }
            });
            function refresh(obj) {
                var data={
                    "disaId":obj
                };
              $.post(basePath+"/authorization/distribution",data,function(data){
                  var d=JSON.parse(data);
                  $('#disa').empty();
                  var disaData = []; //创建数组
                  for(var i=0;i<d.length;i++){
                      disaData.push({
                          "id": d[i].id,
                          "text": d[i].name
                      });
                  }
                  $("#disa").combobox("clear")//下拉框加载数据,设置默认值为
                      .combobox("loadData", disaData).combobox("setValue", d[0].id);
              });
                $.post(basePath+"/authorization/user",data,function(data){
                    var d=JSON.parse(data);
                    $('#users').empty();
                    var userData = []; //创建数组
                    for(var i=0;i<d.length;i++){
                        userData.push({
                            "id": d[i].id,
                            "text": d[i].username
                        });
                    }
                    $("#users").combobox("clear")//下拉框加载数据,设置默认值为
                        .combobox("loadData", userData).combobox("setValue", d[0].id);
                });
                $('#disa').combobox({
                    onSelect: function (row) {
                        if (row != null) {
                            $.post(basePath+"/authorization/disa/collector",data,function(data){
                                var d=JSON.parse(data);
                                $('#collector').empty();
                                var collectorData = []; //创建数组
                                for(var i=0;i<d.length;i++){
                                    collectorData.push({
                                        "id": d[i].id,
                                        "text": d[i].username
                                    });
                                }
                                $("#collector").combobox("clear")//下拉框加载数据,设置默认值为
                                    .combobox("loadData", collectorData).combobox("setValue", d[0].id);
                            });
                        }
                    }
                });
            }
        });
    </script>
</head>
<body>
<table width="100%" border="0" cellpadding="0" cellspacing="0" height="530">
    <tr>
        <td width="12%" valign="top"
            style="border: 1px solid #99bbe8; border-right: 0;">
            <div class="panel-header" style="border-left: 0; border-right: 0;">区域</div>
            <ul id="tree" style="margin-top: 10px;"></ul>
        </td>
        <td valign="top" style="border: 1px solid #99bbe8;">
            <div class="easyui-panel" title="开始授权" style="width:800px">
                <div style="padding:10px 60px 20px 60px">
            <table cellpadding="5">
            <tr>
                   <td>站点:</td>
                   <td colspan="3">
                       <select class="easyui-combobox" name="disa" id="disa" style="width: 180px;" data-options="editable:false,valueField:'id', textField:'text'">
                            <option value="0">---请选择---</option>
                       </select>
                   </td>
               </tr>
               <tr>
                   <td>采集器:</td>
                   <td colspan="3">
                       <select class="easyui-combobox" id="collector" name="collector" style="width: 180px;">
                           <option value="0">---请选择---</option>
                       </select>
                   </td>
               </tr>
               <tr>
                   <td>控制器:</td>
                   <td colspan="3">
                       <select class="easyui-combobox" name="" id="" style="width: 180px;">
                           <option value="0">---请选择---</option>
                       </select>
                   </td>
               </tr>
               <tr>
                   <td>选择钥匙:</td>
                   <td>
                       <select class="easyui-combobox"  id="keys" name="keys" style="width: 180px;">
                       <option value="0">---请选择---</option>

                   </select></td>
               </tr>
                <tr>
                    <td>

                    </td>
                    <td>
                        <button class="easyui-linkbutton">钥匙校时</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <button class="easyui-linkbutton">绑定钥匙</button>
                    </td>
                </tr>
               <tr>
                   <td>选择锁具:</td>
                   <td colspan="3">
                       <select class="easyui-combobox"  name="locks" id="locks" style="width: 180px;">
                           <option value="0">---请选择---</option>
                       </select></td>
               </tr>

               <tr>
                   <td width="100">授权时间:</td>
                   <td>
                       <input id="startDate" name="startDate" class="easyui-validatebox"  value=""/>
                       <img onclick="WdatePicker({el:'startDate'})" src="${basePath}/js/calendar/skin/datePicker.gif" width="16" height="22" align="absmiddle">
                       -
                       <input id="endDate" name="endDate" class="easyui-validatebox"    value=""/>
                       <img onclick="WdatePicker({el:'endDate'})" src="${basePath}/js/calendar/skin/datePicker.gif" width="16" height="22" align="absmiddle">
                   </td>
               </tr>
                <tr>
                    <td>被授权人:</td>
                    <td colspan="3">
                        <select class="easyui-combobox"  name="users" id="users" style="width: 180px;">
                            <option value="0">---请选择---</option>
                        </select></td>
                </tr>
              <tr>
                  <td> <button class="easyui-linkbutton">在线授权</button></td>
              </tr>
           </table>
                </div></div>
        </td>
    </tr>
</table>
</body>
</html>
