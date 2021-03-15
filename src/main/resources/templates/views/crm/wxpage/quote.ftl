<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1,user-scalable=no">

    <title>我要报价</title>
    <!-- head 中 -->
    <link rel="stylesheet" href="${request.contextPath}/static/common/css/weui.min.css">
    <link rel="stylesheet" href="${request.contextPath}/static/common/css/demos.css">
    <link rel="stylesheet" href="${request.contextPath}/static/common/css/jquery-weui.css">
    <!-- body 最后 -->
    <script src="${request.contextPath}/static/common/js/jquery-2.1.4.js"></script>
    <script src="${request.contextPath}/static/common/js/fastclick.js"></script>
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
<body ontouchstart="">
<header class="demos-header">
    <h1 class="demos-title">商库密报</h1>
</header>
<div class="page__bd">
    <div class="weui-panel">
        <div class="weui-panel__hd">求购</div>
        <div class="weui-panel__bd">
            <div class="weui-media-box weui-media-box_text">
                <h4 class="weui-media-box__title">脱硫脱硝设备</h4>
                <p class="weui-media-box__desc">2万风量的脱硝塔多少钱，降低150一下，电话联系，姓庄</p>
                <ul class="weui-media-box__info">
                    <li class="weui-media-box__info__meta">来自</li>
                    <li class="weui-media-box__info__meta">江苏 苏州</li>
                    <li class="weui-media-box__info__meta weui-media-box__info__meta_extra">151****9117</li>
                </ul>
            </div>
        </div>
    </div>
    <div class="weui-btn-area">
        <a class="weui-btn weui-btn_primary" href="javascript:" id="showTooltips">我要报价</a>
    </div>
</div>
<script>
    $(function() {
        FastClick.attach(document.body);
    });
</script>



</body></html>