<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%
    response.setHeader("Pragma", "No-Cache");
    response.setHeader("Cache-Control", "No-Cache");
    response.setDateHeader("Expires", 0);
    response.setContentType("text/html;charset=UTF-8");
%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>蓝牙控制器信息</title>
    <link rel="stylesheet" type="text/css" href="${basePath}/css/mainframe.css"/>
    <link rel="stylesheet" type="text/css" href="${basePath}/js/easyui/themes/default/easyui.css"/>
    <link rel="stylesheet" type="text/css" href="${basePath}/js/easyui/themes/icon.css"/>
    <script type="text/javascript" src="${basePath}/js/jquery-1.4.4.min.js"></script>
    <script type="text/javascript" src="${basePath}/js/easyui/jquery.easyui.min.1.2.2.js"></script>
    <script type="text/javascript" src="${basePath}/js/easyui/locale/easyui-lang-zh_CN.js" charset="UTF-8"></script>
    <script type="text/javascript" src="${basePath}/js/easyui/windowControl.js"></script>
    <script type="text/javascript" src="${basePath}/js/easyui/toolbar.js"></script>
    <script type="text/javascript">
        $(function () {
            var infolist = $('#infolist');
            infolist.datagrid({
                title: '控制器列表',
                iconCls: 'icon-collectore',
                width: '95%',
                height: 500,
                pageSize: 20,
                pageList: [20, 30, 50, 100],
                nowrap: false,
                striped: true,
                collapsible: false,
                fitColumns: true,

                url: '${basePath}/collectore/list',
                queryParams: {
                    // 'disId': disId
                },
                loadMsg: '数据装载中......',
                remoteSort: false,
                singleSelect: true,
                onDblClickRow: function (rowIndex, field, value) {
                    var rows = infolist.datagrid("getRows");
                    var id = rows[rowIndex].id;
                    detail(id);
                },
                columns: [[{
                    title: '控制器MAC地址',
                    field: 'ceMAC',
                    width: $(this).width() * 0.2,
                    align: 'center'
                }, {
                    title: '采集器ID',
                    field: 'ceCode',
                    width: $(this).width() * 0.1,
                    align: 'center'
                },
//                    {
//                        title: '所属配电房',
//                        field: 'ccode',
//                        formatter: function (value, rowData, rowIndx) {
//                            return rowData.dis.name;
//                        },
//                        width: $(this).width() * 0.2,
//                        align: 'center'
//                    },
                    {
                        title: '添加日期 ',
                        field: 'ceDate',
                        width: $(this).width() * 0.2,
                        align: 'left',
//                        formatter:function(date)
//                        { /* 调用函数显示时间 */
////                            SimpleDateFormat c = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
//                            return format(date);
//                        }
                    },
                ]],
                pagination: true,
                rownumbers: true,
                toolbar: [
                    {
                        text: '详情',
                        iconCls: 'icon-view',
                        handler: seedetail
                    },
                    {
                        iconCls: "icon-add",
                        text: "添加",
                        handler: add
                    },
//                    '-', {
//                        text: '修改',
//                        iconCls: 'icon-edit',
//                        handler: edit
//                    },
                    '-', {
                        text: '删除',
                        iconCls: 'icon-remove',
                        handler: del
                    }]
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

            function add() {
                addWin = $.createWin({
                    title: "添加",
                    url: '${basePath}/collectore/prAdd',
                    height: 350,
                    width: 800,
                    buttons: [{
                        text: '保存',
                        iconCls: 'icon-ok',
                        handler: save
                    }]
                });
            }

            function save() {
                $('#addForm').form('submit', {
                    onSubmit: function () {
                        return $(this).form('validate');
                    },
                    success: function (data) {
                        var json = eval("(" + data + ")");
                        $.messager.alert('提示', json.message, 'warning');
                        $.closeWin(addWin);
                        refresh();
                    }
                });
            }

            function seedetail() {
                var select = infolist.datagrid('getSelected');
                if (select) {
                    detail(select.id);
                } else {
                    $.messager.alert('警告', '请选择一行数据', 'warning');
                }
            }

            function detail(id) {
                seeWin = $.createWin({
                    title: "详情",
                    url: '${basePath}/collectore/prView',
                    data: 'id=' + id,
                    height: 550,
                    width: 800,
                    buttons: []
                });
            }

            $('#selectArea').window({
                title: '安装区域选择',
                width: 400,
                height: 500,
                closed: true,
                modal: true
            });


            $('#tree').tree({
                checkbox: false,
                url: '${basePath}/dept/getChildren',
                simpleDataModel: true,
                onBeforeExpand: function (node, param) {
                    $('#tree').tree('options').url = "${basePath}/dept/getChildren?parentId=" + node.id;// change the url
                    return true;
                }
            });

            function refresh() {
                infolist.datagrid("clearSelections");
                infolist.datagrid("reload");
                displayMsg();
            }

            function edit() {
                var select = infolist.datagrid('getSelected');
                if (select) {
                    showEdit(select.id);
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

            function showEdit(id) {
                updateWin = $.createWin({
                    title: "修改",
                    url: '${basePath}/collectore/prUpdate',
                    data: 'id=' + id,
                    height: 550,
                    width: 800,
                    buttons: [{
                        text: '修改',
                        iconCls: 'icon-ok',
                        handler: update
                    }]
                });

            }

            function del() {
                var selected = infolist.datagrid('getSelected');
                if (selected) {
                    $.messager.confirm('警告', '确定要删除么?', function (f) {
                        if (f) {
                            $.post("${basePath}/collectore/delete",
                                {"id": selected.id},
                                function (json) {
                                    $.messager.alert('提示', json.message);
                                    if (json.result == 1) {
                                        infolist.datagrid('reload');
                                    }
                                }, "json");
                        }
                    });
                } else {
                    $.messager.alert('警告', '未选中任何数据', 'warning');
                }
            }
        });

        function setToarea() {
            var id = "";
            var show = "";
            fullname = ""
            var selections = $('#tree').tree('getSelected');
            if (selections) {
                id = selections.id;
                show = selections.attributes.deptname;
                $("#areacode").val(id);
                getDisname(selections);
                fullname = fullname.substring(0, fullname.length - 1);
                $("#deptid").val(fullname);
            }
            $('#selectDept').window('close');
        }

        var fullname = "";

        function getDisname(node) {
            fullname = node.text + " " + fullname;
            if (node.attributes.parentcode == 0) {
                return;
            }
            var abc = $('#tree').tree('getParent', node.target);
            getDisname(abc);
        }

        function query() {
            var queryParams = infolist.datagrid('options').queryParams;
            queryParams.queryWord = $('#qq').val();
            infolist.datagrid({
                url: 'easyQuery.action'
            });
            displayMsg();
            $('#query').window('close');
        }


    </script>
</head>
<body>
<div>
    <table id="infolist"></table>
</div>
<div id="selectCt">
    <div class="easyui-layout" fit="true">
        <div region="center" border="false" style="padding: 10px;">
            <ul id="tree" style="margin-top: 10px;"></ul>
        </div>
        <div region="south" border="false" style="text-align: right; height: 30px; line-height: 30px;">
            <a class="easyui-linkbutton" icon="icon-ok" onclick="setToarea();">确定</a>
            <a class="easyui-linkbutton" onclick="$('#selectCt').window('close');">关闭</a>
        </div>
    </div>
</div>
</body>
</html>
