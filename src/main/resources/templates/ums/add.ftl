<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>新增</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="/wmc-novel/static/lib/layui-v2.5.5/css/layui.css" media="all">
    <link rel="stylesheet" href="/wmc-novel/static/css/public.css" media="all">
    <style>
        body {
            background-color: #ffffff;
        }
    </style>
</head>
<body>
<div class="layui-form layuimini-form">
	<div class="layui-form-item">
        <label class="layui-form-label required">昵称</label>
        <div class="layui-input-block">
            <input type="text" name="nickname" lay-verify="required" lay-reqtext="昵称不能为空" placeholder="请输入昵称" value="" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label required">登录名</label>
        <div class="layui-input-block">
            <input type="text" name="username" lay-verify="required" lay-reqtext="登录名不能为空" placeholder="请输入登录名" value="" class="layui-input">
            <tip>填写管理员登录名，用于登录</tip>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label required">密码</label>
        <div class="layui-input-block">
            <input type="text" name="password" lay-verify="required" lay-reqtext="密码不能为空"  placeholder="请输入密码" value="" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">手机</label>
        <div class="layui-input-block">
            <input type="number" name="phone" lay-reqtext="手机不能为空" placeholder="请输入手机" value="" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">邮箱</label>
        <div class="layui-input-block">
            <input type="email" name="email" placeholder="请输入邮箱" value="" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item layui-form-text">
        <label class="layui-form-label">备注</label>
        <div class="layui-input-block">
            <textarea name="remark" class="layui-textarea" placeholder="请输入备注信息"></textarea>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn layui-btn-normal" lay-submit lay-filter="saveBtn">确认保存</button>
        </div>
    </div>
</div>
<script src="/wmc-novel/static/lib/layui-v2.5.5/layui.js" charset="utf-8"></script>
<script>
    layui.use(['form'], function () {
        var form = layui.form,
            layer = layui.layer,
            $ = layui.$;

        //监听提交
        form.on('submit(saveBtn)', function (data) {
        	$.ajax({
        		url: '../admin',
        		contentType: "application/json",
        		dataType: 'JSON',
        		type: 'POST',
        		data: JSON.stringify(data.field),
        		success: function(res) {
        			if (res.code != 200) {
	                    layer.msg(res.message , {icon: 2});
	                } else {
		                layer.msg('添加成功', {icon: 1});
	                    var iframeIndex = parent.layer.getFrameIndex(window.name);
                		parent.layer.close(iframeIndex);
                		parent.layui.table.reload('currentTableId');
	                }
        		},
        		error: function(res) {
        			if (res.code != 200) {
	                    layer.msg('添加失败', {icon: 2});
	                }
        		}
        	});
            return false;
        });
    });
</script>
</body>
</html>