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
    <title>授权信息</title>
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
                title: '蓝牙钥匙列表',
                iconCls: 'icon-users',
                width: '95%',
                height: 500,
                pageSize: 20,
                pageList: [20, 30, 50, 100],
                nowrap: false,
                striped: true,
                collapsible: false,
                fitColumns: true,
                url: '${basePath}/keyss/list',
                queryParams: {},
                loadMsg: '数据装载中......',
                remoteSort: false,
                singleSelect: true,
                onDblClickRow: function (rowIndex, field, value) {
                    var rows = infolist.datagrid("getRows");
                    var id = rows[rowIndex].id;
                    detail(id);
                },
                columns: [[{
                    title: '钥匙编号',
                    field: 'id',
                    width: 250,
                    align: 'center'
                },
                    {
                        title: '钥匙名称',
                        field: 'keyssName',
                        width: 350,
                        align: 'center'
                    },
                    {
                        title: '领用人',
                        field: 'userName',
                        width: 350,
                        align: 'left'
                    },
//                {
//                    title : '领用人',
//                    field : 'userNume',
//                    formatter : function(value,rowData,rowIndx) {
//                        return rowData.user.username;
//                    },
//                    width : $(this).width() * 0.1,
//                    align : 'center'
//                },
                    {
                        title: '领用时间 ',
                        field: 'keyssDate',
                        width: 200,
                        align: 'left'
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
                    }, '-', {
                        text: '授权',
                        iconCls: 'icon-edit',
                        handler: edit
                    }, '-', {
                        text: '删除',
                        iconCls: 'icon-remove',
                        handler: del
                    },
                    {
                        text: '查询',
                        iconCls: 'icon-search',
                        handler: search
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
                    url: '${basePath}/authorization/prAdd',
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
                    url: '${basePath}/keyss/prView',
                    data: 'id=' + id,
                    height: 550,
                    width: 800,
                    buttons: []
                });
            }

            $('#selectArea').window({
                title: '领用人',
                width: 400,
                height: 500,
                closed: true,
                modal: true
            });


            $('#tree').tree({
                checkbox: false,
                url: '${basePath}/user/kList',
                simpleDataModel: true,
                <%--onBeforeExpand: function (node, param) {--%>
                <%--$('#tree').tree('options').url = "${basePath}/userinfo/findAreaByParentId?parentId=" + node.id;// change the url--%>
                <%--return true;--%>
                <%--}--%>
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
                    url: '${basePath}/authorization/prAdd',
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
                            $.post("${basePath}/keyss/delete", {"id": selected.id}, function (json) {
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
                show = selections.attributes.username;
                $("#usercode").val(id);
                getUsername(selections);
                fullname = fullname.substring(0, fullname.length - 1);
                $("#username").val(fullname);
            }
            $('#selectUser').window('close');
        }

        var fullname = "";

        function getUsername(node) {
            fullname = node.text + " " + fullname;
            if (node.attributes.parentcode == 0) {
                return;
            }
            var abc = $('#tree').tree('getParent', node.target);
            getAreaname(abc);
        }


        function search() {
            addWin = $.createWin({
                title: "查询条件",
                contents: "<table style='font-size:12px;'><tr><td>用户姓名：</td><td><input id=username /></td></tr><tr><td>钥匙名称：</td><td><input id=keyssname /></td></tr></table>",

                width: 300,
                buttons: [{
                    text: '查询',
                    iconCls: 'icon-search',
                    handler: query
                }]
            });
        }

        function query() {
            infolist.datagrid({
                url: basePath + '/keyss/list',
                queryParams: {
                    'username': $('#username').val()
                },
                loadMsg: '数据装载中......'
            });
            infolist.datagrid("clearSelections");
            displayMsg();
            $.closeWin(addWin);
        }

    </script>
</head>
<body>
<div>
    <table id="infolist">
        <div class="mytable">
            <form name="addForm" id="addForm" action="${basePath}/authorization/onLine" method="post">
                <table>
                    <tr>
                        <td>
                            获取钥匙MAC:
                        </td>
                        <td>
                            <input type=""> <br/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            授权期限:
                        </td>
                        <td>
                            <input type="datetime"> <br/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            授权使用人:
                        </td>
                        <td>
                            <input type=""> <br/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            锁具范围:
                        </td>
                        <td>
                            <input type="text"> <br/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            操作人:
                        </td>
                        <td>
                            <input type="text"> <br/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            工作票:
                        </td>
                        <td>
                            <input type="file" value="ss"> <br/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <input type="button" value="确认授权">
                        </td>
                        <td>
                            <input type="reset" value="重置"><br/>
                        </td>
                    </tr>


                </table>
            </form>
        </div>
    </table>
</div>
<div id="selectArea">
    <div class="easyui-layout" fit="true">
        <div region="center" border="false" style="padding: 10px;">
            <ul id="tree" style="margin-top: 10px;"></ul>
        </div>
        <div region="south" border="false" style="text-align: right; height: 30px; line-height: 30px;">
            <a class="easyui-linkbutton" icon="icon-ok" onclick="setToarea();">确定</a>
            <a class="easyui-linkbutton" onclick="$('#selectArea').window('close');">关闭</a>
        </div>
    </div>

</body>
</html>

