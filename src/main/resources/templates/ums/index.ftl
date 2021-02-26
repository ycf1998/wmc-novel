<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="/wmc-novel/static/lib/layui-v2.5.5/css/layui.css" media="all">
    <link rel="stylesheet" href="/wmc-novel/static/css/public.css" media="all">
    <style>
        .layui-form-item .layui-input-company {width: auto;padding-right: 10px;line-height: 38px;}
    </style>
</head>
<body>
<div class="layuimini-container">
    <div class="layuimini-main">

        <script type="text/html" id="toolbar">
            <div class="layui-btn-container">
                <button class="layui-btn layui-btn-normal layui-btn-sm data-add-btn" lay-event="add"> 添加 </button>
            </div>
        </script>

        <table class="layui-hide" id="currentTableId" lay-filter="currentTableFilter"></table>

        <script type="text/html" id="currentTableBar">
            <a class="layui-btn layui-btn-normal layui-btn-xs data-count-edit" lay-event="edit">编辑</a>
            <a class="layui-btn layui-btn-xs layui-btn-danger data-count-edit" lay-event="resetPwd">重置密码</a>
        </script>

        <script type="text/html" id="status">
            <input type="checkbox" data-id={{d.id}} lay-filter="showStatus" lay-skin="switch" lay-text="ON|OFF"
                   {{ d.status == 1 ? 'checked' : '' }} >
        </script>

    </div>
</div>
<script src="/wmc-novel/static/lib/layui-v2.5.5/layui.js" charset="utf-8"></script>
<script>
    layui.use(['form', 'table'], function () {
        var $ = layui.jquery,
            form = layui.form,
            table = layui.table;
        table.render({
            title: "管理员",
            elem: '#currentTableId',
            url: '../admin/list',
            height: 'full-172',
            cellMinWidth: 150,
            parseData: function(res){ //res 即为原始返回的数据
                return {
                    "code": res.code, //解析接口状态
                    "msg": res.message, //解析提示文本
                    "total": res.page.total, //解析数据长度
                    "data": res.data //解析数据列表
                };
            },
            toolbar: '#toolbar',
            defaultToolbar: [],
            totalRow: true,
            cols: [[
                {type: "checkbox", width: 30},
                {type: 'numbers', width: 50, title: '编号', align: "center", totalRowText: '合计'},
                {field: 'id', title: 'id', hide: true},
                {field: 'nickname', title: '昵称',  align: "center"},
                {field: 'username', title: '登录名', align: "center", sort: "true", width: 150},
                {field: 'password', title: '密码',  align: "center"},
                {field: 'phone', title: '电话',  align: "center"},
                {field: 'email', title: '邮箱',  align: "center"},
                {field: 'status', title: '状态', templet: '#status', align: "center"},
                {field: 'lastTime', title: '最近登录',  align: "center", sort: "true", width: 200},
                {field: 'publishCount', title: '发布应用数',  align: "center", totalRow: true},
                {field: 'remark', title: '备注',  align: "center"},
                {title: '操作', toolbar: '#currentTableBar', align: "center"}
            ]],
            limits: [10, 15, 20, 25, 50, 100],
            limit: 15,
            page: true,
            skin: 'line'
        });

        /**
         * toolbar监听事件
         */
        table.on('toolbar(currentTableFilter)', function (obj) {
            if (obj.event === 'add') {  // 监听添加操作
                var index = layer.open({
                    title: '添加管理员',
                    type: 2,
                    shade: 0.2,
                    maxmin:true,
                    shadeClose: true,
                    area: ['65%', '65%'],
                    content: './add',
                });
                $(window).on("resize", function () {
                    layer.full(index);
                });
            }
        });
        table.on('tool(currentTableFilter)', function (obj) {
            var data = obj.data;
            if (obj.event === 'edit') {
                var index = layer.open({
                    title: '编辑',
                    type: 2,
                    shade: 0.2,
                    maxmin:true,
                    shadeClose: true,
                    area: ['65%', '65%'],
                    content: './edit',
                    success: function (layero, index) {
                        let iframe = $(layero).find('iframe').contents();
                        iframe.find('[name="id"]').val(data.id);
                        iframe.find('[name="nickname"]').val(data.nickname);
                        iframe.find('[name="username"]').val(data.username);
                        iframe.find('[name="password"]').val(data.password);
                        iframe.find('[name="phone"]').val(data.phone);
                        iframe.find('[name="email"]').val(data.email);
                        iframe.find('[name="remark"]').val(data.remark);
                    }
                });
                $(window).on("resize", function () {
                    layer.full(index);
                });
                return false;
            } else if (obj.event === 'resetPwd') {
                layer.confirm('确认重置密码', function (index) {
                    $.ajax({
                        url: "admin/resetPwd",
                        dataType: 'JSON',
                        type: 'POST',
                        data: {"id": data.id},
                        success: function(res) {
                            if (res.code != 200) {
                                layer.msg('重置密码失败', {icon: 2});
                            } else {
                                layer.msg('重置密码成功', {icon: 1});
                            }
                        },
                        error: function(res) {
                            if (res.code != 200) {
                                layer.msg('重置密码失败', {icon: 2});
                            }
                        }
                    });
                });
            }
        });

        /**
         * 开关监听事件
         */
        form.on('switch(showStatus)', function(obj){
            let status = obj.elem.checked ? 1 : 0;
            let body = {
                "id": obj.elem.dataset.id,
                "status": status
            }
            $.ajax({
                url: 'admin/update/status',
                dataType: 'JSON',
                type: 'POST',
                data: body,
                success: function(res) {
                    if (res.code != 200) {
                        layer.msg('修改失败', {icon: 2});
                    }
                },
                error: function(res) {
                    if (res.code != 200) {
                        layer.msg('修改失败', {icon: 2});
                    }
                }
            });
        });
    });
</script>

</body>
</html>