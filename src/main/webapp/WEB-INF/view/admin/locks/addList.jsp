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
    <title>门锁管理</title>
    <link rel="stylesheet" type="text/css"
          href="${basePath}/js/jquery-easyui-1.5.3/themes/default/easyui.css"/>
    <link rel="stylesheet" type="text/css"
          href="${basePath}/js/jquery-easyui-1.5.3/themes/icon.css"/>
    <script type="text/javascript" src="${basePath}/js/jquery.min.js"></script>
    <script type="text/javascript" src="${basePath}/js/jquery-easyui-1.5.3/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${basePath}/js/jquery-easyui-1.5.3/locale/easyui-lang-zh_CN.js"
            charset="UTF-8"></script>
    <script type="text/javascript" src="${basePath}/js/calendar/WdatePicker.js"></script>
    <script type="text/javascript">
        var basePath = "${basePath}";
        $(function () {

//            getkeys();
            $('#tree').tree({
                checkbox: false,
                url: basePath + '/dept/getChildren',
                onBeforeExpand: function (node, param) {
                    $('#tree').tree('options').url = basePath + "/dept/getChildren?parentId=" + node.id;
                },
                onClick: function (node) {
                    refresh(node.id);
                }
            });
            //获取钥匙
//            function getkeys() {
//                $.post(basePath+"/authorization/keys",null,function(data){
//                    var d=JSON.parse(data);
//                    $('#keys').empty();
//                    var keyData = []; //创建数组
//                    for(var i=0;i<d.length;i++){
//                        keyData.push({
//                            "id": d[i].keyssMAC,
//                            "text": d[i].keyssName
//                        });
//                    }
//                    $("#keys").combobox("clear")//下拉框加载数据,设置默认值为
//                        .combobox("loadData", keyData).combobox("setValue", d[0].keyssMAC);
//                });
//            }
            //获取站点
            function refresh(obj) {
                var data = {
                    "disaId": obj
                };
                $.post(basePath + "/authorization/distribution", data, function (data) {
                    var d = JSON.parse(data);
                    $('#disa').empty();
                    var disaData = []; //创建数组
                    for (var i = 0; i < d.length; i++) {
                        disaData.push({
                            "id": d[i].id,
                            "text": d[i].name
                        });
                    }
                    if (d[0] != null) {
                        $("#disa").combobox("clear")//下拉框加载数据,设置默认值为
                            .combobox("loadData", disaData).combobox("setValue", d[0].id);
                    }
                });
                //获取人
//                $.post(basePath+"/authorization/user",data,function(data){
//                    var d=JSON.parse(data);
//                    $('#users').empty();
//                    var userData = []; //创建数组
//                    for(var i=0;i<d.length;i++){
//                        userData.push({
//                            "id": d[i].id,
//                            "text": d[i].username
//                        });
//                    }
//                    if( d[0]!=null) {
//                        $("#users").combobox("clear")//下拉框加载数据,设置默认值为
//                            .combobox("loadData", userData).combobox("setValue", d[0].id);
//                    }
//                });
                $('#disa').combobox({
                    onSelect: function (row) {
                        if (row != null) {
                            var data = {
                                "disaId": row.id
                            };
                            $('#collectore').empty();
                            $.post(basePath + "/authorization/disa/collector", data, function (data) {
                                var d = JSON.parse(data);
                                $('#collector').empty();
                                var collectorData = []; //创建数组
                                for (var i = 0; i < d.length; i++) {
                                    collectorData.push({
                                        "id": d[i].id,
                                        "text": d[i].ccode
                                    });
                                }
                                if (d[0] != null) {
                                    $("#collector").combobox("clear")//下拉框加载数据,设置默认值为
                                        .combobox("loadData", collectorData).combobox("setValue", d[0].id);
                                }
                            });
                            //获取锁具
                            $.post(basePath + "/authorization/disa/locks", data, function (data) {
                                var d = JSON.parse(data);
                                $('#locks').empty();
                                var collectorData = []; //创建数组
                                for (var i = 0; i < d.length; i++) {
                                    collectorData.push({
                                        "id": d[i].id,
                                        "text": d[i].lockCode
                                    });
                                }
                                if (d[0] != null) {
                                    $("#locks").combobox("clear")//下拉框加载数据,设置默认值为
                                        .combobox("loadData", collectorData).combobox("setValue", d[0].id);
                                }
                            });
                        }
                    }
                });
                $('#collector').combobox({
                    onSelect: function (row) {
                        if (row != null) {
                            var data = {
                                "collectorId": row.id
                            };
                            $.post(basePath + "/authorization/collector/collectore", data, function (data) {
                                var d = JSON.parse(data);
                                $('#collectore').empty();
                                var collectorData = []; //创建数组
                                for (var i = 0; i < d.length; i++) {
                                    collectorData.push({
                                        "id": d[i].id,
                                        "text": d[i].ceMAC
                                    });
                                }
                                //console.log(collectorData);
                                if (d[0] != null) {
                                    $("#collectore").combobox("clear")//下拉框加载数据,设置默认值为
                                        .combobox("loadData", collectorData).combobox("setValue", d[0].id);
                                }
                            });
                        }
                    }
                });
            }
        });
        //        function keyBinding() {
        //            var key=$('#collector').combobox('getText')+",7,"+$('#collectore').combobox('getText')+","+$('#keys').combobox('getText')+",";
        //            var data={
        //                "key":key
        //            };
        //            $.ajax({
        //                type: "post",
        //                url: basePath+"/redis/get",
        //                cache:false,
        //                async:false,
        //                data:data,
        //                dataType: "json",
        //                success: function(data){
        //                   if(data.result=="1"){
        //                      alert(data.message);
        //                   }else{
        //                       alert("绑定蓝牙钥匙失败");
        //                   }
        //                }
        //
        //            });
        //        }

        //        function keyTiming() {
        //            var key=$('#collector').combobox('getText')+",12,"+$('#collectore').combobox('getText')+","+$('#keys').combobox('getText')+",";
        //            var data={
        //                "key":key
        //            };
        //            $.ajax({
        //                type: "post",
        //                url: basePath+"/redis/get",
        //                cache:false,
        //                async:false,
        //                data:data,
        //                dataType: "json",
        //                success: function(data){
        //                    if(data.result=="1"){
        //                        alert(data.message);
        //                    }else{
        //                        alert("蓝牙钥匙校时失败！");
        //                    }
        //                }
        //
        //            });
        //        }
        //        function onlineAuth() {
        //            var key=$('#collector').combobox('getText')
        //                 +",5,"
        //                +$('#collectore').combobox('getText')+","
        //                +$('#keys').combobox('getText')+","
        //                +$('#locks').combobox('getText')+","
        //                +$('#startDate').val()+","
        //                +$('#endDate').val()+","
        //                +$('#users').combobox('getValue');
        //            var data={
        //                "key":key
        //            };
        //            $.ajax({
        //                type: "post",
        //                url: basePath+"/redis/get",
        //                cache:false,
        //                async:false,
        //                data:data,
        //                dataType: "json",
        //                success: function(data){
        //                    if(data.result=="1"){
        //                        alert(data.message);
        //
        //                    }else{
        //                        alert("授权失败!");
        //                    }
        //                }
        //
        //            });
        //        }
        //保存
        function save() {
            var data = {
                "qgdis.id": $('#disa').combobox('getValue'),
                "collector": $('#collector').combobox('getValue'),
                "collectore": $('#collectore').combobox('getValue'),
                "lockNum": $("#lockNum").val(),
                "lockCode": $("#lockCode").val(),
                "lockDate": $("#lockDate").val(),
                "lockNum": $("#lockNum").val(),
            };
            $.ajax({
                type: "post",
//                url: basePath+"/redis/get",
                url: basePath + "/locks/add",
                cache: false,
                async: false,
                data: data,
                dataType: "json",
                success: function (data) {
                    if (data.result == "1") {
                        $.messager.alert('提示', data.message, 'success');
                    } else {
                        $.messager.alert('提示', data.message, 'warning');
                    }
                }
            });
        }

        function getLock(t) {
            var key = $('#collector').combobox('getText') + ","
                + t + ","
                + $('#collectore').combobox('getText') + ","
                + $('#keys').combobox('getText') + ","
                + $('#locks').combobox('getText') + ","
                + $('#startDate').val() + ","
                + $('#endDate').val() + ","
                + $('#users').combobox('getValue');
            var data = {
                "key": key
            };
            $.ajax({
                type: "post",
                url: basePath + "/redis/get",
                cache: false,
                async: false,
                data: data,
                dataType: "json",
                success: function (data) {
                    if (data.result == "1") {
                        if (t == 2) {
                            alert(data.message);
                        } else {
                            $("#locks").empty();
                            var collectorData = []; //创建数组
                            var lockNum = data.message.split(";")[1];
                            collectorData.push({
                                "id": lockNum,
                                "text": lockNum
                            });
                            console.log(data.message.split(";")[1]);
                            console.log(collectorData);
                            $("#locks").combobox("clear")//下拉框加载数据,设置默认值为
                                .combobox("loadData", collectorData).combobox("setValue", lockNum);
                        }
                    } else {
                        if (t == 2) {
                            alert("初始化门锁失败!");
                        } else {
                            alert("读取门锁信息失败!");
                        }
                    }
                }

            });
        }
    </script>
</head>
<body>
<div>
    <table width="100%" border="0" cellpadding="0" cellspacing="0" height="530">
        <tr>
            <td width="12%" valign="top"
                style="border: 1px solid #99bbe8; border-right: 0;">
                <div class="panel-header" style="border-left: 0; border-right: 0;">区域</div>
                <ul id="tree" style="margin-top: 10px;"></ul>
            </td>
            <td valign="top" style="border: 1px solid #99bbe8;">
                <div class="easyui-panel" title="添加门锁" style="width:800px">
                    <div style="padding:10px 60px 20px 60px">

                        <form name="addForm" id="addForm" action="${basePath}/locks/add" method="post">

                            <table cellpadding="5">
                                <tr>
                                    <td>站点:</td>
                                    <td colspan="2">
                                        <select class="easyui-combobox" name="qgdis.id" id="disa" style="width: 180px;"
                                                data-options="editable:false,valueField:'id', textField:'text'">
                                            <option value="0">---请选择---</option>
                                        </select>
                                    </td>
                                </tr>
                                <tr>
                                    <td>选择采集器:</td>
                                    <td colspan="2">
                                        <select class="easyui-combobox" id="collector" name="collector"
                                                style="width: 180px;"
                                                data-options="editable:false,valueField:'id', textField:'text'">
                                            <option value="0">---请选择---</option>
                                        </select>
                                    </td>
                                </tr>
                                <tr>
                                    <td>控制器:</td>
                                    <td colspan="2">
                                        <select class="easyui-combobox" name="collectore" id="collectore"
                                                style="width: 200px;"
                                                data-options="editable:false,valueField:'id', textField:'text'">
                                            <option value="0">---请选择---</option>
                                        </select>
                                    </td>
                                </tr>
                                <tr>
                                    <td width="100">门锁编号:</td>
                                    <td colspan="2">
                                        <input id="lockNum" name="lockNum" style="width: 200px;"/>
                                </tr>
                                <tr>
                                    <td width="100">门锁识别码:</td>
                                    <td colspan="2">
                                        <input id="lockCode" name="lockCode" style="width: 200px;"/>
                                        <a class="easyui-linkbutton"
                                           onclick="getLock(2)">初始化</a>
                                        <a class="easyui-linkbutton"
                                           onclick="getLock(1)">获取</a>
                                </tr>

                                <tr>
                                    <td width="100">添加时间:</td>
                                    <td colspan="2">
                                        <input id="lockDate" name="lockDate" class="easyui-validatebox" required="true"
                                               value=""/>
                                        <img onclick="WdatePicker({el:'lockDate'})"
                                             src="${basePath}/js/calendar/skin/datePicker.gif" width="16" height="22"
                                             align="absmiddle">
                                </tr>
                                <tr>
                                    <td width="100">详细地址:</td>
                                    <td colspan="2">
                    <textarea name="address" class="easyui-validatebox" id="address" cols="30" rows="3"
                              required="true"></textarea>
                                </tr>
                                <tr>
                                    <td>
                                        <button class="easyui-linkbutton" onclick="save()">确认添加</button>
                                    </td>
                                </tr>
                            </table>
                        </form>
                    </div>
                </div>
            </td>
        </tr>
    </table>
</div>
</body>
</html>
