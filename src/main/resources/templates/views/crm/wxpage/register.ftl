<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1,user-scalable=no">
    
    <title>注册</title>
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
        <h1 class="demos-title">注册新用户</h1>
    </header>
    <div class="weui-cells weui-cells_form">
        <div class="weui-cell weui-cell_vcode" style="margin-top: 10px">
            <div class="weui-cell__hd">
                <label class="weui-label">姓名</label>
            </div>
            <div class="weui-cell__bd">
                <input class="weui-input" type="company" placeholder="请输入姓名">
            </div>
        </div>

<#--        <div class="weui-cell">-->
<#--            <div class="weui-cell__hd"><label class="weui-label">qq</label></div>-->
<#--            <div class="weui-cell__bd">-->
<#--                <input class="weui-input" type="number" pattern="[0-9]*" placeholder="请输入qq号">-->
<#--            </div>-->
<#--        </div>-->
        <div class="weui-cell weui-cell_vcode">
            <div class="weui-cell__hd">
                <label class="weui-label">手机号</label>
            </div>
            <div class="weui-cell__bd">
                <input class="weui-input" type="tel" placeholder="请输入手机号">
            </div>
            <div class="weui-cell__ft">
                <button class="weui-vcode-btn">获取验证码</button>
            </div>
        </div>
<#--        <div class="weui-cell">-->
<#--            <div class="weui-cell__hd"><label for="" class="weui-label">日期</label></div>-->
<#--            <div class="weui-cell__bd">-->
<#--                <input class="weui-input" type="date" value="">-->
<#--            </div>-->
<#--        </div>-->
<#--        <div class="weui-cell">-->
<#--            <div class="weui-cell__hd"><label for="" class="weui-label">时间</label></div>-->
<#--            <div class="weui-cell__bd">-->
<#--                <input class="weui-input" type="datetime-local" value="" placeholder="">-->
<#--            </div>-->
<#--        </div>-->
        <div class="weui-cell weui-cell_vcode">
            <div class="weui-cell__hd"><label class="weui-label">验证码</label></div>
            <div class="weui-cell__bd">
                <input class="weui-input" type="number" placeholder="请输入验证码">
            </div>
            <#--            <div class="weui-cell__ft">-->
            <#--                <img class="weui-vcode-img" src="./images/vcode.jpg">-->
            <#--            </div>-->
        </div>

        <div class="weui-cell weui-cell_vcode" style="margin-top: 10px">
            <div class="weui-cell__hd">
                <label class="weui-label">公司名称</label>
            </div>
            <div class="weui-cell__bd">
                <input class="weui-input" type="company" placeholder="请输入公司名称">
            </div>
        </div>

    </div>
<#--    <div class="weui-cells__tips">底部说明文字底部说明文字</div>-->

<#--    <div class="weui-cells__title">表单报错</div>-->
<#--    <div class="weui-cells weui-cells_form">-->
<#--        <div class="weui-cell weui-cell_warn">-->
<#--            <div class="weui-cell__hd"><label for="" class="weui-label">卡号</label></div>-->
<#--            <div class="weui-cell__bd">-->
<#--                <input class="weui-input" type="number" pattern="[0-9]*" value="weui input error" placeholder="请输入卡号">-->
<#--            </div>-->
<#--            <div class="weui-cell__ft">-->
<#--                <i class="weui-icon-warn"></i>-->
<#--            </div>-->
<#--        </div>-->
<#--    </div>-->


<#--    <div class="weui-cells__title">开关</div>-->
<#--    <div class="weui-cells weui-cells_form">-->
<#--        <div class="weui-cell weui-cell_switch">-->
<#--            <div class="weui-cell__bd">标题文字</div>-->
<#--            <div class="weui-cell__ft">-->
<#--                <input class="weui-switch" type="checkbox">-->
<#--            </div>-->
<#--        </div>-->
<#--        <div class="weui-cell weui-cell_switch">-->
<#--            <div class="weui-cell__bd">兼容IE Edge的版本</div>-->
<#--            <div class="weui-cell__ft">-->
<#--                <label for="switchCP" class="weui-switch-cp">-->
<#--                    <input id="switchCP" class="weui-switch-cp__input" type="checkbox" checked="checked">-->
<#--                    <div class="weui-switch-cp__box"></div>-->
<#--                </label>-->
<#--            </div>-->
<#--        </div>-->
    </div>

<#--    <div class="weui-cells__title">文本框</div>-->
<#--    <div class="weui-cells">-->
<#--        <div class="weui-cell">-->
<#--            <div class="weui-cell__bd">-->
<#--                <input class="weui-input" type="text" placeholder="请输入文本">-->
<#--            </div>-->
<#--        </div>-->
<#--    </div>-->

<#--    <div class="weui-cells__title">文本域</div>-->
<#--    <div class="weui-cells weui-cells_form">-->
<#--        <div class="weui-cell">-->
<#--            <div class="weui-cell__bd">-->
<#--                <textarea class="weui-textarea" placeholder="请输入文本" rows="3"></textarea>-->
<#--                <div class="weui-textarea-counter"><span>0</span>/200</div>-->
<#--            </div>-->
<#--        </div>-->
<#--    </div>-->
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