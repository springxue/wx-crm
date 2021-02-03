<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
测试从定向
<script src="${request.contextPath}/static/layuiadmin/layui/layui.js"></script>
<script>
    layui.use(['form','jquery'], function(){
        var form=layui.form;

        var $ = layui.$;
        var obj={
        }
        obj.customername='aa'
        obj.zip='bb'
        $.ajax({
            type:"post",
            // dataType: 'json',
            // async:false,
            // async : false,
            url: "${request.contextPath}/customer/add",
            contentType:'application/json',
            data: JSON.stringify(obj),
            // data: {customer:data.field},
            // data:data.field,
            success: function x(a) {
                console.log("===========")
                console.log(a);

            },
            error: function x (result) {
                console.log(result)
            }
        })

    })
</script>
</body>
</html>