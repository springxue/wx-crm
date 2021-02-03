<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>客户列表</title>
    <link rel="stylesheet" href="${request.contextPath}/static/layuiadmin/layui/css/layui.css" media="all">
<#--    <link rel="stylesheet" href="${request.contextPath}/static/layuiadmin/style/admin.css" media="all">-->
</head>
<body style="background-color: #F2F2F2;" >
<div style="padding: 20px; ">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md12">
            <div class="layui-card">
                <div class="layui-card-body">
                    <form class="layui-form" action="" style="margin-top: 8px">
                        <div class="layui-form-item">
                            <div class="layui-inline">
                                <label class="layui-form-label">客户名称</label>
                                <div class="layui-input-inline" style="width: 150px;">
                                    <input  type="text" name="customername" lay-verify="title" autocomplete="off" placeholder="" class="layui-input" value="${(customerQuery.customername)?if_exists}">
                                </div>
                                <label class="layui-form-label">客户电话</label>
                                <div class="layui-input-inline" style="width: 150px;">
                                    <input  type="text" name="telephone" lay-verify="title" autocomplete="off" placeholder="" class="layui-input" value="${(customerQuery.telephone)!}">
                                </div>
                                <label class="layui-form-label">联系人</label>
                                <div class="layui-input-inline" style="width: 100px;">
                                    <input  type="text" name="connectionperson" lay-verify="title" autocomplete="off" placeholder="" class="layui-input" value="${(customerQuery.connectionperson)!}">
                                </div>
                                <div class="layui-input-inline" >

                                </div>
                                <div class="layui-input-inline" >
                                    <button type="button" class="layui-btn" id="search">查询</button>
                                    <button type="button" class="layui-btn" id="reset">清空</button>
                                </div>
                            </div>


                        </div>
                    </form>
                    <#-- 数据表格-->
                    <table class="layui-hide" id="customer" lay-filter="customer"></table>

                    <script type="text/html" id="toolbar">
                        <div class="layui-btn-container">
                            <button class="layui-btn layui-btn-sm" lay-event="getCheckData">获取选中行数据</button>
                            <button class="layui-btn layui-btn-sm" lay-event="getCheckLength">获取选中数目</button>
                            <button class="layui-btn layui-btn-sm" lay-event="isAll">验证是否全选</button>
                            <button class="layui-btn layui-btn-sm" lay-event="add">新增</button>
                            <button class="layui-btn layui-btn-sm" lay-event="multiDelete">批量删除</button>
                        </div>
                    </script>
  
                    <script type="text/html" id="bar">
                        <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
                        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
                    </script>

                </div>
            </div>
        </div>
    </div>
</div>
<script src="${request.contextPath}/static/layuiadmin/layui/layui.js"></script>

<script>
    layui.use(['table','form','jquery'], function(){
        var table = layui.table;
        var form=layui.form;
        var $=layui.$;
        var addLayerIndex='';
        table.render({
            elem: '#customer'
            ,url:'${request.contextPath}/customer/getPageList'
            ,method:'post'
            ,contentType: "application/json;charset=UTF-8"
            ,dataType: 'json'
            ,done:function (res,curr,count) {
                console.log(res)
            }
            ,toolbar: '#toolbar' //开启头部工具栏，并为其绑定左侧模板
            ,defaultToolbar: ['filter', 'exports', 'print', { //自定义头部工具栏右侧图标。如无需自定义，去除该参数即可
                title: '提示'
                ,layEvent: 'LAYTABLE_TIPS'
                ,icon: 'layui-icon-tips'
            }]
            ,title: '客户表'
            ,cols: [[
                {type: 'checkbox', fixed: 'left'}
                ,{field:'id', title:'客户id', fixed: 'left',hide:true}
                ,{field:'customername', title:'客户名称',fixed:'left'}
                ,{field:'zip', title:'客户邮编'}

                ,{field:'address', title:'客户地址'}
                ,{field:'telephone', title:'客户电话'}
                ,{field:'connectionperson', title:'联系人'}
                ,{field:'phone', title:'联系人电话'}
                ,{field:'bank', title:'开户行'}
                ,{field:'account', title:'账户'}
                ,{field:'email', title:'邮箱',templet: function(res){
                        return '<em>'+ res.email +'</em>'
                    }}
                ,{fixed: 'right', title:'操作', toolbar: '#bar', width:120}
            ]]
            ,page: true
        });

        //头工具栏事件
        table.on('toolbar(customer)', function(obj){
            var checkStatus = table.checkStatus(obj.config.id);


            switch(obj.event){
                case 'getCheckData':
                    var data = checkStatus.data;
                    console.log(data)
                    layer.alert(JSON.stringify(data));
                    break;
                case 'getCheckLength':
                    var data = checkStatus.data;
                    layer.msg('选中了：'+ data.length + ' 个');
                    break;
                case 'isAll':
                    layer.msg(checkStatus.isAll ? '全选': '未全选');
                    break;

                //自定义头工具栏右侧图标 - 提示
                case 'LAYTABLE_TIPS':
                    layer.alert('这是工具栏右侧自定义的一个图标按钮');
                    break;
                //自定义头工具栏右侧图标 - 提示
                case 'add':
                    layer.open({
                        title:'新增客户信息',
                        type: 2,
                        area: ['500px', '710px'],
                        // area: '30%',
                        content: ['/customer/customerLayer','no'], //这里content是一个URL，如果你不想让iframe出现滚动条，你还可以content: ['http://sentsin.com', 'no']
                        btn: ['确认','取消'], //按钮
                        yes:function (index,layero) {
                            addLayerIndex=index;
                            // var son = window['layui-layer-iframe' + index];
                            // son.child(111);
                            //获取子窗口的函数
                            var formSubmit=layer.getChildFrame('form', index);
                            console.log(formSubmit)
                            var submited = formSubmit.find('button')[0];
                            submited.click()

                        }
                    });
                    break;
                    //批量删除
                    case 'multiDelete':

                        layer.confirm('确认删除选中的数据吗？', function(index){
                            var data = checkStatus.data;
                            var idAry=[];
                            if(data.length===0){
                                layer.msg("请至少选择一条数据！！");
                                return false;
                            }
                            for(var i=0;i<data.length;i++){
                                var row=data[i];
                                idAry.push(row.id);
                            }
                            $.ajax({
                                type:"post",
                                url: "${request.contextPath}/customer/deleteCustomersByIds",
                                //这里没有用json传值，所以contentType
                                // contentType:'application/json; charset=UTF-8',
                                data : "ids="+idAry,
                                success: function(res) {
                                    if(res.code===200){
                                        layer.msg(res.msg)
                                        layer.close(index);
                                        table.reload('customer')
                                    }
                                },
                                error: function(res) {
                                    layer.alert(res.msg,{icon:2})
                                }
                            })
                            layer.close(index);
                        });
            };
        });

        //监听行工具事件
        table.on('tool(customer)', function(obj){
            var data = obj.data;
            var idAry=[];
            idAry.push(data.id);
            if(obj.event === 'del'){
                layer.confirm('确认删除吗？', function(index){
                    $.ajax({
                        type:"post",
                        url: "${request.contextPath}/customer/deleteCustomersByIds",
                        //这里没有用json传值，所以contentType
                        // contentType:'application/json; charset=UTF-8',
                        data : "ids="+idAry,
                        success: function(res) {
                           if(res.code===200){
                               layer.msg(res.msg)
                               layer.close(index);
                               table.reload('customer')
                           }
                        },
                        error: function(res) {
                            layer.alert(res.msg,{icon:2})
                        }
                    })
                    layer.close(index);
                });
            } else if(obj.event === 'edit'){
                layer.open({
                    title:'修改客户信息',
                    type: 2,
                    area: ['500px', '710px'],
                    // area: '30%',
                    content: ['/customer/customerLayer?id='+data.id,'no'], //这里content是一个URL，如果你不想让iframe出现滚动条，你还可以content: ['http://sentsin.com', 'no']
                    btn: ['确认','取消'], //按钮
                    yes:function (index,layero) {
                        addLayerIndex=index;
                        // var son = window['layui-layer-iframe' + index];
                        // son.child(111);
                        //获取子窗口的函数
                        var formSubmit=layer.getChildFrame('form', index);
                        console.log(formSubmit)
                        var submited = formSubmit.find('button')[0];
                        submited.click()

                    }
                });


            }
        });

        //查询按钮
        $("#search").click(function () {
            var customername=$("input[name='customername']").val();
            var telephone=$("input[name='telephone']").val();
            var connectionperson=$("input[name='connectionperson']").val();

            //客户列表的重载
            table.reload('customer', {where: {customername: customername,telephone:telephone,connectionperson:connectionperson}});
            //另一种重载的方法
            // tableIns.reload({
            //     where: { //设定异步数据接口的额外参数，任意设
            //         aaaaaa: 'xxx'
            //         ,bbb: 'yyy'
            //         //…
            //     }
            //     ,page: {
            //         curr: 1 //重新从第 1 页开始
            //     }
            // });

        });

        //清空按钮
        $("#reset").click(function () {
            $("input[name='customername']").val("");
            $("input[name='telephone']").val("");
            $("input[name='connectionperson']").val("");
        });
        //关闭新增layer
        window.closeAddLayer=function () {
            layer.close(addLayerIndex)
        }
        //重载客户列表
        window.reloadCustomerList=function(){
            table.reload('customer')
        }
    });

</script>
</body>
</html>