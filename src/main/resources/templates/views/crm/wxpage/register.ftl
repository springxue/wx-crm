<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1,user-scalable=no">
    
    <title>注册</title>
    <!-- head 中 -->
    <link rel="stylesheet" href="https://cdn.bootcss.com/weui/1.1.3/style/weui.min.css">
    <link rel="stylesheet" href="https://cdn.bootcss.com/jquery-weui/1.2.1/css/jquery-weui.min.css">
    <!-- body 最后 -->
    <script src="https://cdn.bootcss.com/jquery/1.11.0/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/jquery-weui/1.2.1/js/jquery-weui.min.js"></script>
</head>
<body>
<div class="weui-grids" id="window">
    <div class="weui-cells weui-cells_form">
        <div class="weui-cell">
            <div class="weui-cell__hd"><label class="weui-label">qq</label></div>
            <div class="weui-cell__bd">
                <input class="weui-input" type="number" pattern="[0-9]*" placeholder="请输入qq号">
            </div>
        </div>
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
        <div class="weui-cell">
            <div class="weui-cell__hd"><label for="" class="weui-label">日期</label></div>
            <div class="weui-cell__bd">
                <input class="weui-input" type="date" value="">
            </div>
        </div>
        <div class="weui-cell">
            <div class="weui-cell__hd"><label for="" class="weui-label">时间</label></div>
            <div class="weui-cell__bd">
                <input class="weui-input" type="datetime-local" value="" placeholder="">
            </div>
        </div>
        <div class="weui-cell weui-cell_vcode">
            <div class="weui-cell__hd"><label class="weui-label">验证码</label></div>
            <div class="weui-cell__bd">
                <input class="weui-input" type="number" placeholder="请输入验证码">
            </div>
            <div class="weui-cell__ft">
                <img class="weui-vcode-img" src="./images/vcode.jpg">
            </div>
        </div>
    </div>
    <div class="weui-cells__tips">底部说明文字底部说明文字</div>

    <div class="weui-cells__title">表单报错</div>
    <div class="weui-cells weui-cells_form">
        <div class="weui-cell weui-cell_warn">
            <div class="weui-cell__hd"><label for="" class="weui-label">卡号</label></div>
            <div class="weui-cell__bd">
                <input class="weui-input" type="number" pattern="[0-9]*" value="weui input error" placeholder="请输入卡号">
            </div>
            <div class="weui-cell__ft">
                <i class="weui-icon-warn"></i>
            </div>
        </div>
    </div>


    <div class="weui-cells__title">开关</div>
    <div class="weui-cells weui-cells_form">
        <div class="weui-cell weui-cell_switch">
            <div class="weui-cell__bd">标题文字</div>
            <div class="weui-cell__ft">
                <input class="weui-switch" type="checkbox">
            </div>
        </div>
        <div class="weui-cell weui-cell_switch">
            <div class="weui-cell__bd">兼容IE Edge的版本</div>
            <div class="weui-cell__ft">
                <label for="switchCP" class="weui-switch-cp">
                    <input id="switchCP" class="weui-switch-cp__input" type="checkbox" checked="checked">
                    <div class="weui-switch-cp__box"></div>
                </label>
            </div>
        </div>
    </div>

    <div class="weui-cells__title">文本框</div>
    <div class="weui-cells">
        <div class="weui-cell">
            <div class="weui-cell__bd">
                <input class="weui-input" type="text" placeholder="请输入文本">
            </div>
        </div>
    </div>

    <div class="weui-cells__title">文本域</div>
    <div class="weui-cells weui-cells_form">
        <div class="weui-cell">
            <div class="weui-cell__bd">
                <textarea class="weui-textarea" placeholder="请输入文本" rows="3"></textarea>
                <div class="weui-textarea-counter"><span>0</span>/200</div>
            </div>
        </div>
    </div>
</div>

</body>
</html>