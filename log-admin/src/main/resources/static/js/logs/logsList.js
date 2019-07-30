var index=$("#dataIndex").html();
$(function () {
    if(!index){
        alert("请从正确的入口进来此页面");
        return;
    }
    log.sendData(index);
    layui.use(['layer', 'form','laydate','table','util'], function(){
        var layer = layui.layer,
            form = layui.form ;
        var laydate = layui.laydate;

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
        form.on('submit(searchSubmit)', function(data){

            //重新加载table
           log.sendData(index,40,data.field.keyWord,data.field.startDate,data.field.endDate);
            return false;
        });
        //监听清空
        form.on('submit(clear)', function(data){
            $('#logsForm')[0].reset();
            log.sendData(index);
            return false;
        });

    });
});
var log={
    sendData:function(index,countSize,keyWord,startTime,endTime){
        $.ajax({
            type: "post",
            url: "/logs/searchCourseWithKeyWord",
            data: {"keyWord":keyWord, "startTime":startTime,"endTime":endTime,"index":index,"countSize":countSize},
            success: function(data){
                if(data&&data.length>0){
                    $('#resText').empty();   //清空resText里面的所有内容
                    var html = '';
                    $.each(data, function(commentIndex,comment){
                        html += '<tr> <td>'+comment.time+'</td><td>'+comment.message+'</td> </tr>';
                    });
                    $('#resText').html(html);
                    if(data[0].countSize>data.length){
                        $("#seeMore").html("暂无更多数据");
                    }else{
                        $("#seeMore").html("查看更多").attr("data-data",data[0].countSize);
                    }
                }else{
                    $('#resText').empty();
                    $("#seeMore").html("暂无更多数据");
                }

            },
            error:function (error) {
                $("#seeMore").html("接口请求错误,稍后再试");
            }
        });
    },
    clickSeeMore:function () {
        var data=$("#seeMore").attr("data-data");
        if(data){
            data=parseInt(data);
            log.sendData(index,data+20, $("#keyWord").val(), $("#startDate").val(), $("#endDate").val());
        } else{
            $("#seeMore").html("暂无更多数据");
        }

    }
}