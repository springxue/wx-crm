

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>我的商机</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href="${request.contextPath}/static/layuiadmin/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="${request.contextPath}/static/layuiadmin/style/admin.css" media="all">
</head>
<body>

<div class="layui-fluid">
    <div class="layui-card">
        <div class="layui-tab layui-tab-brief layadmin-latestData">
            <ul class="layui-tab-title">
                <li class="layui-this">我的求购</li>
                <li>我的消息</li>
            </ul>
            <div class="layui-tab-content" style="height: auto">
                <div class="layui-tab-item layui-show">
                    <table id="LAY-index-topSearch"></table>
                </div>
                <div class="layui-tab-item">
                    <table id="LAY-index-topCard"></table>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="${request.contextPath}/static/layuiadmin/layui/layui.js?t=1"></script>
<script>
    layui.config({
        base: '${request.contextPath}/static/layuiadmin/' //静态资源所在路径
    }).extend({
        index: 'lib/index' //主入口模块
    }).use(['index', 'console']);
</script>
</body>
</html>

