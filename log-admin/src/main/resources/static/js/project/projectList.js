var pageCurr = 1;
if (currentPage != null && currentPage != undefined && currentPage != "") {
    pageCurr = currentPage;
}
$(function () {
    //一般直接写在一个js文件中
    layui.use(['layer', 'form', 'laydate', 'table', 'util'], function () {
        var layer = layui.layer,
            util = layui.util,
            form = layui.form;
        var laydate = layui.laydate;
        var table = layui.table;
        tableIns = table.render({
            elem: '#projectTable'
            , url: '/project/selectProjectInfoList'
            , method: 'post' //默认：get请求
            , cellMinWidth: 100
            , page: true,
            request: {
                pageName: 'page' //页码的参数名称，默认：page
                , limitName: 'limit' //每页数据量的参数名，默认：limit
            }, response: {
                statusName: 'code' //数据状态的字段名称，默认：code
                , statusCode: 200 //成功的状态码，默认：0
                , countName: 'totals' //数据总数的字段名称，默认：count
                , dataName: 'list' //数据列表的字段名称，默认：data
            }, page: {
                curr: pageCurr
            }
            , cols: [[
                {field: 'id', title: '编号', sort: true, align: 'center'}
                , {field: 'updateTime', title: '创建时间', sort: true, align: 'center'}
                , {field: 'projectName', title: '名称', align: 'center'}
                , {field: 'right', title: '操作', toolbar: '#optBar', align: 'center'}
            ]], done: function (res, curr, count) {
                //如果是异步请求数据方式，res即为你接口返回的信息。
                //如果是直接赋值的方式，res即为：{data: [], count: 99} data为当前页数据、count为数据总长度
                //console.log(res);
                //得到当前页码
                //console.log(curr);
                //得到数据总量
                //console.log(count);
                $("[data-field='updateTime']").children().each(function (i, el) {
                    if (i > 0) {
                        if ($(this).text()) {
                            $(this).text($(this).text().substring(0, 19));
                        }
                    }
                });
                pageCurr = curr;
            }
        });
        //执行一个laydate实例
        laydate.render({
            elem: '#startDate',
            type: 'datetime'//指定元素
        });
        //执行一个laydate实例
        laydate.render({
            elem: '#endDate',
            type: 'datetime'//指定元素
        });
        //监听搜索框
        form.on('submit(searchSubmit)', function (data) {
            //重新加载table
            load();
            return false;
        });
        //监听清空
        form.on('submit(clear)', function (data) {
            $('#projectForm')[0].reset();
            load();
            return false;
        });
        //监听添加
        form.on('submit(add)', function (data) {
            openProject();
            return false;
        });
        //监听提交添加
        form.on('submit(addSubmit)', function (data) {
            var formdata = $("#projectInfo").serializeObject();
            console.log(formdata)

            $.ajax({
                url: '/project/addProject',
                type: 'POST',
                data: formdata,
                dataType:"json",
                success: function (data) {
                    if(data.result=="success"){
                        layer.msg("操作成功", {shift: -1, time: 1000}, function(){
                            layer.closeAll();
                        });
                    }else{
                        layer.msg("操作失败", {shift: -1, time: 1000}, function(){
                            layer.closeAll();
                        });
                    }
                    load();
                },
                error:function (error) {
                    console.log(error);
                    layer.msg("网络错误,请稍后再试");
                }
            });
            return false;
        });
        //监听提交修改
        form.on('submit(updateSubmit)', function (data) {
            var formdata = $("#projectInfo").serializeObject();
            $.ajax({
                url: '/project/updateProject',
                type: 'POST',
                data: formdata,
                dataType:"json",
                success: function (data) {
                    if(data.result=="success"){
                        layer.msg("操作成功", {shift: -1, time: 1000}, function(){
                            layer.closeAll();
                        });
                    }else{
                        layer.msg("操作失败", {shift: -1, time: 1000}, function(){
                            layer.closeAll();
                        });
                    }
                    load();
                },
                error:function (error) {
                    console.log(error);
                    layer.msg("网络错误,请稍后再试");
                }
            });
            return false;
        });
        //监听工具条
        //注：test是tool是工具条事件名，table原始容器的属性 lay-filter="对应的值"
        //监听工具条
        table.on('sort(projectTable)', function (obj) {
            table.reload('projectTable', {
                initSort: obj //记录初始排序，如果不设的话，将无法标记表头的排序状态。 layui 2.1.1 新增参数
                , where: { //请求参数
                    field: obj.field //排序字段
                    , order: obj.type //排序方式
                }
            });

        });
        table.on('tool(projectTable)', function (obj) {
            var data = obj.data;
            if (obj.event == 'del') {
                layer.confirm('您确定要删除吗?', {
                    btn: ['确认','返回'] //按钮
                }, function(){
                    $.post("/project/delProjectInfo/"+data.id,function(json){
                        if(json.result=="success"){
                            //回调弹框
                            layer.alert("删除成功!",{icon: 1,closeBtn: 0 },function(){
                                layer.closeAll();
                                //加载load方法
                                load();//自定义
                            });
                        }else{
                            layer.alert("删除失败");//弹出错误提示
                            //加载load方法
                            load();//自定义
                        }
                    });
                }, function(){
                    layer.closeAll();
                });
            } else if (obj.event == 'update') {
              openProject(data.id);
            }
        });
    });
});

function load() {
    //重新加载table
    tableIns.reload({
        where: $('#projectForm').serializeObject(),
        page: {
            curr: pageCurr //从当前页码开始
        }
    });
}


function openProject(id){
    var title="添加";
    if(id){
        title="编辑";
        $("#addSubmit").hide();
        $("#updateSubmit").show();
        //发送查询
        $.post("/project/getProject/"+id,function (data) {
            if(data.data){
                $("#projectName").val(data.data.projectName);
                $("input[name='id']").val(data.data.id);
            }
        });
    }else{
        $("#addSubmit").show();
        $("#updateSubmit").hide();
    }
    layer.open({
        type:1,
        title: title,
        fixed:false,
        resize :false,
        shadeClose: true,
        area: ['550px'],
        content:$('#setProject'),
        end:function(){
            $("#projectInfo")[0].reset();
        }
    });
}