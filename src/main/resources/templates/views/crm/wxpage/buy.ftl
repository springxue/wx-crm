<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1,user-scalable=no">

    <title>发布求购</title>
    <!-- head 中 -->
    <link rel="stylesheet" href="${request.contextPath}/static/common/css/weui.min.css">
    <link rel="stylesheet" href="${request.contextPath}/static/common/css/demos.css">
    <link rel="stylesheet" href="${request.contextPath}/static/common/css/jquery-weui.css">
    <!-- body 最后 -->
    <script src="${request.contextPath}/static/common/js/jquery-2.1.4.js"></script>
    <script src="${request.contextPath}/static/common/js/jquery-weui.js"></script>
    <style>
        /*.title{*/
        /*    text-align: center;*/
        /*    font-size: 34px;*/
        /*    color: #3cc51f;*/
        /*    font-weight: 400;*/
        /*    margin: 0 15%;*/
        /*}*/
        /*.header{*/
        /*    padding: 35px 0;*/
        /*}*/
    </style>
</head>
<body ontouchstart>
<div class="weui-grids" id="window">
    <header class="demos-header">
        <h1 class="demos-title">我要求购</h1>
    </header>
    <div class="weui-cells weui-cells_form">
        <div class="weui-cell weui-cell_vcode" style="margin-top: 10px">
            <div class="weui-cell__hd">
                <label class="weui-label">求购产品</label>
            </div>
            <div class="weui-cell__bd">
                <input class="weui-input" type="goods" placeholder="产品名称">
            </div>
        </div>

        <div class="weui-cell weui-cell_vcode" style="margin-top: 10px">
            <div class="weui-cell__hd">
                <label class="weui-label">数量</label>
            </div>
            <div class="weui-cell__bd">
                <input class="weui-input" type="quantity" placeholder="只能输入数字">
            </div>
        </div>

        <div class="weui-cell weui-cell_vcode" style="margin-top: 10px">
            <div class="weui-cell__hd">
                <label class="weui-label">手机</label>
            </div>
            <div class="weui-cell__bd">
                <input class="weui-input" type="goods" placeholder="11位手机号码">
            </div>
        </div>
            <label class="weui-label" style="margin-left: 15px;margin-top: 10px">描述</label>
            <div class="weui-cell">
                <div class="weui-cell__bd">
                    <textarea class="weui-textarea" placeholder="请输入文本" rows="3" style="border: #5f646e solid 1px"></textarea>
<#--                    <div class="weui-textarea-counter"><span>0</span>/200</div>-->
                </div>
            </div>
</div>
<div class="weui-btn-area">
    <a class="weui-btn weui-btn_primary" href="javascript:" id="showTooltips">确定</a>
</div>

</div>
<script>
    $("#showTooltips").click(function() {
        var tel = $('#tel').val();
        var code = $('#code').val();
        if(!tel || !/1[3|4|5|7|8]\d{9}/.test(tel)) $.toptip('请输入手机号');
        else if(!code || !/\d{6}/.test(code)) $.toptip('请输入六位手机验证码');
        else $.toptip('提交成功', 'success');
    });
</script>
</body>
</html>