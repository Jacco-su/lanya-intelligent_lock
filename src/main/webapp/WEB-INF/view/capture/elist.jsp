<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2018/1/11
  Time: 18:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript">
        var basePath = "${basePath}";
        $(function () {
            var deptId = "";
            var infolist = $('#infolist');
            infolist.datagrid({
                title: '配电房列表',
                iconCls: 'icon-users',
                width: '95%',
                height: 560,
                pageSize: 20,
                pageList: [20, 30, 50, 100],
                nowrap: false,
                striped: true,
                collapsible: false,
                fitColumns: true,
                singleSelect: true,
                url: '${basePath}/disa/list',
                queryParams: {
                    'deptId': deptId
                },
                loadMsg: '数据装载中......',
                remoteSort: false,
                onDblClickRow: function (rowIndex, field, value) {
                    var rows = infolist.datagrid("getRows");
                    var id = rows[rowIndex].id;
                    showEdit(id);
                },
                columns: [[
                    {
                        title: '区域',
                        field: 'deptname',
                        formatter: function (value, rowData, rowIndx) {
                            return rowData.dept.name;
                        },
                        width: $(this).width() * 0.1,
                        align: 'center'
                    },
                    {
                        title: '配电房编号',
                        field: 'id',
                        width: $(this).width() * 0.1,
                        rowspan: 2,
                        align: 'center'
                    },
                    {
                        title: '配电房名称',
                        field: 'name',
                        width: $(this).width() * 0.1,
                        rowspan: 2,
                        align: 'center'
                    },
                    {
                        title: '配电房地址',
                        field: 'address',
                        width: $(this).width() * 0.1,
                        rowspan: 2,
                        align: 'center'
                    },
                    {
                        title: '添加时间',
                        field: 'createTime',
                        width: $(this).width() * 0.1,
                        rowspan: 2,
                        align: 'center'
                    }
                ]],
                pagination: true,
                rownumbers: true,
                toolbar: [{
                    text: '添加',
                    iconCls: 'icon-add',
                    handler: add
                }, '-', {
                    text: '修改',
                    iconCls: 'icon-edit',
                    handler: edit
                }, '-', {
                    text: '删除',
                    iconCls: 'icon-remove',
                    handler: del
                }, '-', {
                    text: '查询',
                    iconCls: 'icon-search',
                    handler: search
                }]
            });
            displayMsg();

            function refresh() {
                infolist.datagrid({
                    url: '${basePath}/disa/list',
                    queryParams: {
                        'deptId': deptId
                    },
                    loadMsg: '数据装载中......'
                });
                infolist.datagrid("clearSelections");
                displayMsg();
            }

            function displayMsg() {
                infolist.datagrid('getPager').pagination({
                    beforePageText: '第',
                    afterPageText: '页，共{pages}页',
                    displayMsg: '当前显示从{from}到{to}共{total}记录'
                });
            }

            var id;
            var addWin;
            var updateWin;

            function getSelect() {
                var select = infolist.datagrid('getSelected');
                if (select) {
                    $('#addFrom').window('open');
                } else {
                    $.messager.alert('警告', '请选择一行数据', 'warning');
                }
            }

            function save() {
                $('#addForm').form('submit', {
                    onSubmit: function () {
                        return $(this).form('validate');
                    },
                    success: function (data) {
                        var json = eval("(" + data + ")");
                        if (json.result == '1') {
                            $.messager.alert('提示', '保存成功', 'warning');
                            $.closeWin(addWin);
                            refresh();
                        } else {
                            $.messager.alert('提示', '保存失败', 'warning');
                            $.closeWin(addWin);
                            refresh();
                        }

                    }
                });
            }

            function add() {
                if (deptId != "") {
                    addWin = $.createWin({
                        title: "配电房添加",
                        url: basePath + '/disa/prAdd?deptId=' + deptId,
                        height: 550,
                        width: 800,
                        buttons: [{
                            text: '保存',
                            iconCls: 'icon-ok',
                            handler: save
                        }]
                    });
                } else {
                    $.messager.alert('警告', '请选择一个区域', 'warning');
                }
            }

            function edit() {
                var select = infolist.datagrid('getSelected');
                if (select) {
                    showEdit(select.id);
                } else {
                    $.messager.alert('警告', '请选择一行数据', 'warning');
                }
            }

            function showEdit(id) {
                updateWin = $.createWin({
                    title: "配电房修改",
                    url: basePath + '/disa/prUpdate',
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

            function update() {
                $('#editForm').form('submit', {
                    onSubmit: function () {
                        return $(this).form('validate');
                    },
                    success: function (data) {
                        var json = eval("(" + data + ")");
                        if (json.result == '1') {
                            $.messager.alert('提示', '保存成功', 'warning');
                        } else {
                            $.messager.alert('提示', '保存失败', 'warning');
                        }
                        $.closeWin(updateWin);
                        infolist.datagrid('reload');
                    }
                });
            }

            function del() {
                var select = infolist.datagrid('getSelected');
                if (select) {
                    $.messager.confirm('警告', '确认删除么?', function (f) {
                        if (f) {
                            $.ajax({
                                type: "POST",
                                url: basePath + "/disa/delete",
                                data: "id=" + select.id,
                                dataType: "text",
                                cache: false,
                                success: function (msg) {
                                    $.messager.alert('提示', '删除成功!', 'warning');
                                    infolist.datagrid('reload');
                                }
                            });
                        }
                    });
                } else {
                    $.messager.alert('警告', '请选择一行数据', 'warning');
                }
            }

            function search() {
                addWin = $.createWin({
                    title: "查询条件",
                    contents: "<table style='font-size:12px;'><tr><td>配电房：</td><td><input id='dissName' /></td></tr></table>",
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
                    url: basePath + '/disa/list',
                    queryParams: {
                        'dissName': $('#dissName').val()
                    },
                    loadMsg: '数据装载中......'
                });
                infolist.datagrid("clearSelections");
                displayMsg();
                $.closeWin(addWin);
            }

            $('#tree').tree({
                checkbox: false,
                url: basePath + '/dept/getChildren',
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

<c:forEach var="me" items="${fileNameMap}">
    <c:url value="/uploads/" var="downurl">
        <c:param name="filename" value="${me.key}"></c:param>
    </c:url>
    ${me.value}<a href="${downurl}">下载</a>
    <br/>
</c:forEach>


</body>
</html>
