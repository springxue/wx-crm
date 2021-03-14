<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>词库管理</title>
    <link rel="stylesheet" href="${request.contextPath}/static/layuiadmin/layui/css/layui.css" media="all">
    <#--    <link rel="stylesheet" href="${request.contextPath}/static/layuiadmin/style/admin.css" media="all">-->
</head>
<body style="background-color: #F2F2F2;" >
    <div style="padding: 20px; ">
        <div class="layui-row layui-col-space15">
            <div class="layui-col-md12">
                <div class="layui-card">
                    <div class="layui-card-body">
                        <input type="text" class="layui-input" id="aassss">
                        <table class="layui-hide" id="tableId" lay-filter="tableEvent"></table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
<script type="text/html" id="toolbarDemo">
    <div class="layui-btn-container">
        <button type="button" lay-event="tableTreeEdit" id="btn1" class="layui-btn  layui-btn-sm">添加最上级节点</button>
        <button type="button" lay-event="tableTreeEdit" id="btn2" class="layui-btn  layui-btn-sm">获取勾选数据</button>
        <button type="button" lay-event="tableTreeEdit" id="btn3" class="layui-btn  layui-btn-sm">获取全部数据</button>
        <button type="button" lay-event="tableTreeEdit" id="btn4" class="layui-btn  layui-btn-sm">折叠字典管理</button>
        <button type="button" lay-event="tableTreeEdit" id="btn5" class="layui-btn  layui-btn-sm">展开字典管理</button>
        <button type="button" lay-event="tableTreeEdit" id="btn6" class="layui-btn  layui-btn-sm">关闭所有叶子节点</button>
        <button type="button" lay-event="tableTreeEdit" id="btn7" class="layui-btn  layui-btn-sm">展开所有叶子节点</button>
        <button type="button" lay-event="tableTreeEdit" id="btn8" class="layui-btn  layui-btn-sm">获取表格树配置</button>
        <button type="button" lay-event="tableTreeEdit" id="btn9" class="layui-btn  layui-btn-sm">重载表格树</button>
        <button type="button" lay-event="tableTreeEdit" id="btn10" class="layui-btn  layui-btn-sm">删除增加角色</button>
        </br>
        <button type="button" lay-event="tableTreeEdit" id="btn11" class="layui-btn  layui-btn-sm">重置搜索</button>
        <button type="button" lay-event="tableTreeEdit" id="btn12" class="layui-btn  layui-btn-sm">刷新</button>
        <button type="button" lay-event="tableTreeEdit" id="btn13" class="layui-btn  layui-btn-sm">权限管理排序</button>
    </div>
</script>
<script src="${request.contextPath}/static/layuiadmin/layui/layui.js"></script>
<script>
    layui.config({
        base : '${request.contextPath}/static/layuiadmin/modules/'
    }).extend({
        tableEdit:'treeTable/tableEdit'  //表格树依赖我另外写的tableEdit模块，本项目就有。
        ,tableTree:'treeTable/tableTree'
    })
        .use(['table','form','jquery','tableEdit',"tableTree"], function(){
        var table = layui.table;
        var tableEdit = layui.tableEdit;
        var tableTree = layui.tableTree;
        var form=layui.form;
        var $=layui.$;
        var treeTable=tableTree.render({
                elem: '#tableId'
                ,id:'tableTree'
                ,url:'${request.contextPath}/getDicPageList'
                ,height: 'full-90'
                ,size:'sm'
                ,toolbar: '#toolbarDemo' //开启头部工具栏，并为其绑定左侧模板
                ,defaultToolbar: ['filter', 'exports', 'print', { //自定义头部工具栏右侧图标。如无需自定义，去除该参数即可
                    title: '提示'
                    ,layEvent: 'LAYTABLE_TIPS'
                    ,icon: 'layui-icon-tips'
                }]
                ,page: true
                ,treeConfig:{ //表格树所需配置
                    showField:'title' //表格树显示的字段
                    ,treeid:'id' //treeid所对应字段的值在表格数据中必须是唯一的，且不能为空。
                    ,treepid:'parentid'//父级id字段名称
                    ,iconClass:'layui-icon-right' //小图标class样式 窗口图标 layui-icon-layer
                    ,showToolbar: true //展示工具栏 false不展示 true展示
                }
                ,cols: [[
                    {type:'checkbox'}
                    ,{field:'id',title:'id',sort:true,event:'id',config:{type:'input'},width:120}
                    ,{field:'title',title: '名称',width:300}
                    ,{field:'sort', title: '排序',event:'sort',width:70,config:{type:'input'}}
                    ,{field:'url',title:'路径',event:'url',config:{type:'input'},width:150}
                    ,{field:'permissionId ',width:150, title: '权限标识',event:'permissionId',config:{type:'input'}}
                    ,{field:'createDate', title: '创建时间',event:'date',width:120,config:{type:'date',dateType:'date'}}
                    ,{field:'type',title:'类型',event:'type',config:{type:'select',data:[{name:1,value:'菜单'},{name:2,value:'按钮'}]},width:150}
                ]],done:function () {
                    treeTable.closeAllTreeNodes();
                    treeTable.openTreeNode(1)
                }
            });
            /**
             *表格的增删改都会回调此方法
             * 与table.on(tool(lay-filter))用法一致。
             **/
            tableTree.on('tool(tableEvent)',function (obj) {
                var field = obj.field; //单元格字段
                var value = obj.value; //修改后的值
                var data = obj.data; //当前行数据
                var event = obj.event; //当前单元格事件属性值
                //event为del为删除 add则新增 edit则修改 async则为异步请求数据。
                //这个三个值固定死了，切莫定义与之三个重复的event。
                if(event !== 'del' && event !== 'add' && event !== 'type' && event !== 'async'){
                    var update = {};
                    update[field] = value;
                    obj.update(update);
                    console.log(obj)
                }
                if(event === 'type'){
                    obj.update({type:value.value});
                }
                if(event === 'del'){
                    obj.del();
                }
                if(event === 'add'){ //点击操作栏加号图标时触发
                    //异步、同步都可以使用
                    //obj.add(arr)生成表格树,arr参数为数组，数组中元素的treeid字段值重复则被过滤掉
                    obj.add([]) //参数不传或为空数组时 => 新增空行
                }
                // if(event === 'async'){ //点击方向箭头小图标时触发
                //     //可ajax异步请求后台数据,回调obj.async(arr)生成表格树,arr参数为数组
                //     //数组中元素的treeid字段值重复则被过滤掉
                //     obj.async([{"id":'abc',"treeName":'abc',"permissionId ":'abc',"sort":'3333',createDate:'2020-02-02',type:'1'}]);
                // }
                if(event === 'id'){
                    $(this).parents('tr').attr('tree-id',value);
                }
            });

            /**
             *监听复选框选中状态
             **/
            tableTree.on('checkbox(tableEvent)', function(obj){
                console.log(obj.checked); //当前是否选中状态
                console.log(obj.data); //选中行的相关数据
                console.log(obj.type); //如果触发的是全选，则为：all，如果触发的是单选，则为：one
                console.log(obj.data);
            });
            var isAsc = true;
            table.on('toolbar(tableEvent)', function(obj){
                var id = $(this).attr("id");
                if(id==="btn1"){
                    //data可以为空，为空则创建空行，否则按照data数据生成行
                    treeTable.addTopTreeNode();//新增最上级节点
                }else if(id === 'btn2') {
                    console.log(treeTable.getCheckedTreeNodeData()) //获取选中行的树状数据
                }else if(id === 'btn3') {
                    console.log(treeTable.getTableTreeData())//获取表格树所有数据
                }else if(id === 'btn4') {
                    treeTable.closeTreeNode('6'); // 根据tr或者树id关闭相对应树节点
                }else if(id === 'btn5') {
                    treeTable.openTreeNode(6); // 根据tr或者树id展开相对应树节点
                }else if(id === 'btn6') {
                    treeTable.closeAllTreeNodes();  //关闭所有树节点
                }else if(id === 'btn7') {
                    treeTable.openAllTreeNodes(); //展开所有树节点
                }else if(id === 'btn8') {
                    console.log(treeTable.getTreeOptions()); //获取表格配置
                }else if(id === 'btn9'){
                    treeTable.reload(); //表格树进行reload
                }else if(id === 'btn10') {
                    treeTable.delTreeNode('19'); //根据tr元素或者节点id删除节点及相关叶子节点
                }else if(id === 'btn11') {
                    treeTable.clearSearch(); //重置搜索
                }else if(id === 'btn12') {
                    //传了数据进去进行刷新，那么会把原来的数据给删除了，
                    //然后把传进去的数据进行更新进去
                    treeTable.refresh();
                }else if(id === "btn13"){
                    treeTable.sortByTreeNode(28,'sort',isAsc); //指定节点或节点id排序
                    isAsc = !isAsc;
                }
            });

            /**
             * 整个表格树排序，与layui进行了整合。
             */
            table.on('sort(tableEvent)', function(obj){
                treeTable.sort({field:obj.field,desc:obj.type === 'desc'})
            });

            $('#aassss').on('change',function () {
                treeTable.keywordSearch(this.value); //关键词搜索树
            });
    });

</script>
<script type="text/html" id="imgtmp">
    <img src="{{d.headimgurl}}" style="height: 50px;width: 50px">
</script>
</body>
</html>