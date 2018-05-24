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
    <title>开关门信息</title>
    <link rel="stylesheet" type="text/css" href="${basePath}/js/easyui/themes/default/easyui.css"/>
    <link rel="stylesheet" type="text/css" href="${basePath}/js/easyui/themes/icon.css"/>
    <script type="text/javascript" src="${basePath}/js/jquery-1.4.4.min.js"></script>
    <script type="text/javascript" src="${basePath}/js/easyui/jquery.easyui.min.1.2.2.js"></script>
    <script type="text/javascript" src="${basePath}/js/easyui/locale/easyui-lang-zh_CN.js" charset="UTF-8"></script>
    <script type="text/javascript" src="${basePath}/js/easyui/windowControl.js"></script>
    <script type="text/javascript" src="${basePath}/js/easyui/toolbar.js"></script>
    <script type="text/javascript" src="${basePath}/js/calendar/WdatePicker.js"></script>
    <script type="text/javascript">
        var basePath = "${basePath}";
        $(function () {
            var deptId="";
            var infolist = $('#infolist');
            infolist.datagrid({
                title: '开门记录列表',
                iconCls: 'icon-users',
                width: '95%',
                fit:false,
                pageSize: 20,
                pageList: [20, 30, 50, 100],
                nowrap: false,
                striped: true,
                collapsible: false,
                fitColumns: true,
                url: '${basePath}/opendoor/list',
                loadMsg: '数据装载中......',
                remoteSort: false,
                singleSelect: true,
                onDblClickRow: function (rowIndex, field, value) {
                    var rows = infolist.datagrid("getRows");
                    var rtuId = rows[rowIndex].rtuId;
                    var openTime = rows[rowIndex].openTime;
                    detail(rtuId,openTime);
                },
                columns: [[
                    {
                        title: '开门时间',
                        field: 'openTime',
                        width: $(this).width() * 0.1,
                        align: 'left'
                    },
                    {
                        title: '类型',
                        field: 'messageType',
                        width: $(this).width() * 0.2,
                        align: 'left'
                    },
                    {
                        title: '内容',
                        field: 'context',
                        width: $(this).width() * 0.2,
                        align: 'left'
                    },
                    {
                        title: '站点',
                        field: 'deviceName',
                        width: $(this).width() * 0.2,
                        align: 'left'
                    },
                    {
                        title: '创建时间',
                        field: 'createTime',
                        width: $(this).width() * 0.1,
                        align: 'center'
                    }
                ]],
                pagination: true,
                rownumbers: true,
                toolbar : [{
                    text : '监控',
                    iconCls : 'icon-search',
                    handler : edit
                }, '-', {
                    text: '查询',
                    iconCls: 'icon-search',
                    handler: search
                } ]
            });
            displayMsg();

            function displayMsg() {
                infolist.datagrid('getPager').pagination({
                    beforePageText: '第',
                    afterPageText: '页，共{pages}页',
                    displayMsg: '当前显示从{from}到{to}共{total}记录'
                });
            }

            var addWin;
            var seeWin;
            var updateWin;
            var seeuWin;


            function seedetail() {
                var select = infolist.datagrid('getSelected');
                if (select) {
                    detail(select.rtuId,select.openTime);
                } else {
                    $.messager.alert('警告', '请选择一行数据', 'warning');
                }
            }

            function detail(deviceNum,openTime) {
                updateWin = $.createWin({
                    title: "监控",
                    url: '${basePath}/opendoor/picture?deviceNum='+deviceNum+'&openTime='+openTime+'',
                    height: 550,
                    width: 800
                });
            }

            $('#selectUser').window({
                title: '领用人',
                width: 800,
                height: 500,
                closed: true,
                modal: true
            });
            function refresh() {
                infolist.datagrid( {
                    url: '${basePath}/keyss/list',
                    queryParams:{
                        'deptId':deptId
                    },
                    loadMsg : '数据装载中......'
                });
                infolist.datagrid("clearSelections");
                infolist.datagrid("reload");
                displayMsg();
            }

            function edit() {
                var select = infolist.datagrid('getSelected');
                if (select) {
                    showEdit(select.rtuId,select.openTime);
                } else {
                    $.messager.alert('警告', '请选择一行数据', 'warning');
                }
            }

            function update() {
                $('#editForm').form('submit', {
                    onSubmit: function () {
                        var v = $(this).form('validate');
                        if (v) {
                        }
                        return v;
                    },
                    success: function (data) {
                        var json = eval("(" + data + ")");
                        $.messager.alert('提示', json.message, 'warning');
                        $.closeWin(updateWin);
                        refresh();
                    }
                });
            }

            function showEdit(deviceNum,openTime) {
                updateWin = $.createWin({
                    title: "监控",
                    url: '${basePath}/opendoor/picture?deviceNum='+deviceNum+'&openTime='+openTime,
                    height: 550,
                    width: 800
                });

            }
//----------------------------------------------
            function search() {
                addWin = $.createWin({
                    title: "查询条件",
                    contents: "<table style='font-size:12px;'>" +
                    "<tr><td>授权名称：</td><td><input id=authName /></td></tr>" +
                    "<tr><td>授权日期：</td><td><input id=\"authStartTime\" name=\"authStartTime\" class=\"easyui-validatebox\"  value=\"\"/><img onclick=\"WdatePicker({el:'authStartTime',dateFmt:'yyyy-MM-dd HH:mm:ss'})\" src=\"${basePath}/js/calendar/skin/datePicker.gif\" width=\"16\" height=\"22\" align=\"absmiddle\">-<input id=\"authEndTime\" name=\"authEndTime\" class=\"easyui-validatebox\"    value=\"\"/><img onclick=\"WdatePicker({el:'authEndTime',dateFmt:'yyyy-MM-dd HH:mm:ss'})\" src=\"${basePath}/js/calendar/skin/datePicker.gif\" width=\"16\" height=\"22\" align=\"absmiddle\"></td></tr>" +
                    "<tr><td>授权人员：</td><td><input id=userId /></td></tr></table>",
                    width: 500,
                    buttons: [{
                        text: '查询',
                        iconCls: 'icon-search',
                        handler: uquery
                    }]
                });
            }

            function uquery() {
                infolist.datagrid({
                    url: '${basePath}/authlog/list',
                    queryParams: {
                        'authName': $('#authName').val(),
                        'authStartTime': $('#authStartTime').val(),
                        'authEndTime': $('#authEndTime').val(),
                        'userId':$('#userId').val()
                    },
                    loadMsg: '数据装载中......'
                });
                infolist.datagrid("clearSelections");
                displayMsg();
                $.closeWin(addWin);
            }

            $('#infolist').tree({
                checkbox: false,
                url: basePath + '/user/list',
                onBeforeExpand: function (node, param) {
                    $('#tree').tree('options').url = basePath + "/dept/getChildren?parentId=" + node.id;
                },
                onClick: function (node) {
                    deptId = node.id;
                    refresh();
                }
            });

        });
    </script>
</head>
<body>
<table id="infolist"></table>
</body>
</html>
