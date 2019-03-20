 var pageCurr = 1;
     if(currentPage != null && currentPage != undefined && currentPage != ""){
     	pageCurr = currentPage;
     }
     $(function() {
        //一般直接写在一个js文件中
        layui.use(['layer', 'form','laydate','table','util'], function(){
            var layer = layui.layer
                ,form = layui.form , util = layui.util;
            var laydate = layui.laydate;
            var table = layui.table;
            tableIns=table.render({
                elem: '#test'
                ,url:'/apply/selectApplyInfo'
            	,method: 'post' //默认：get请求
                ,cellMinWidth: 100
                ,page: true,
                request: {
                    pageName: 'page' //页码的参数名称，默认：page
                    ,limitName: 'limit' //每页数据量的参数名，默认：limit
                },response:{
                    statusName: 'code' //数据状态的字段名称，默认：code
                    ,statusCode: 200 //成功的状态码，默认：0
                    ,countName: 'totals' //数据总数的字段名称，默认：count
                    ,dataName: 'list' //数据列表的字段名称，默认：data
                },page: {
                    curr: pageCurr 
                  }
                ,cols: [[
                    {field:'kind', width:'7%', title: '贷款类型',templet:'#kindType',align:'center'}
                    ,{field:'name', width:'10%', title: '申请人',align:'center'}
                    ,{field:'channel', width:'7%', title: '渠道商',templet:'#channelType',align:'center'}
                    ,{field:'number', title: '手机号码', width: '10%',align:'center'}
                    ,{field:'createTime', width:'15%', title: '申请时间', sort: true,align:'center'}
                    ,{field:'status', width:'10%', title: '借款状态',templet:'#statusType',align:'center'}
                    ,{field:'mark', width:'14%', title: '备注'}
                    ,{field:'receiver', width:'10%', title: '领取人',templet:'#receiverType',align:'center'}
                    ,{field:'source', width:'6%', title: '来源',templet:'#sourceType',align:'center'}
                    ,{field:'right', width:'10%', title: '操作' ,align:'center',toolbar:'#optBar'}
                ]] ,  done: function(res, curr, count){
                    //如果是异步请求数据方式，res即为你接口返回的信息。
                    //如果是直接赋值的方式，res即为：{data: [], count: 99} data为当前页数据、count为数据总长度
                    //console.log(res);
                    //得到当前页码
                    //console.log(curr);
                    //得到数据总量
                    //console.log(count);
                    pageCurr=curr;
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
            form.on('submit(searchSubmit)', function(data){
                //重新加载table
                load();
                return false;
            });
          //监听清空
      	  form.on('submit(clear)', function(data){
      		  $('#applyForm')[0].reset();
      		  load();
      		  return false;
      	  });
      	 //监听提交
          form.on('submit(editApplySubmit)', function(data){
              // TODO 校验
              formSubmit(data);
              return false;
          });
          //监听取消
          form.on('submit(closeTable)', function(data){
        	  layer.closeAll();
              return false;
          });
          //更换申请人
          form.on('submit(changeUserSubmit)',function(data){
        	  changeUserOfForm();
        	  return false;
          });
          
      	//监听工具条
      	//注：test是tool是工具条事件名，table原始容器的属性 lay-filter="对应的值"
          table.on('tool(applyTable)', function(obj){
              var data = obj.data;
              if(obj.event === 'del'){
            	  delApply(data,data.orderID,data.name);
              } else if(obj.event === 'edit'){
                  //编辑
            	  openEditWindows(data,form);
              } else if(obj.event === 'receiveClick'){
                  //领取
            	  //1 添加领取人
            	  receiveApplyInfo(data.orderID);
              } else if(obj.event === 'binding'){
            	  //绑定
            	  changeUser(data);
              }
          });
          
        });
     });  
function load(data){
    //重新加载table
    tableIns.reload({
        where: $('#applyForm').serializeObject() ,
        page: {
            curr: pageCurr //从当前页码开始
        }
    });
 }
//删除申请列表
function delApply(obj,id,name) {
    if(null!=id){
        layer.confirm('您确定要删除【 '+name+' 】的申请信息吗?', {
            btn: ['确认','返回'] //按钮
        }, function(){
            $.post("/apply/delApplyInfo",{"orderid":id},function(data){
                if(data.msg=="success"){
                    //回调弹框
                    layer.alert("删除成功！",function(){
                        layer.closeAll();
                        //加载load方法
                        load();//自定义
                    });
                }else{
                    layer.alert(data.msg);//弹出错误提示
//                    layer.closeAll();
                    //加载load方法
//                    load(null);//自定义
                }
            });
        }, function(){
            layer.closeAll();
        });
    }
}
//领取贷款信息、
function receiveApplyInfo(id){
	if(null!=id){
		$.post("/apply/addApplyReceiveInfo",{"orderid":id},function(data){
			if(data.msg=="success"){
                //回调弹框
                layer.alert("领取成功",{icon: 1,closeBtn: 0 },function(){
                    layer.closeAll();
                    //加载load方法
                    load();//自定义
                });
            }else{
            	layer.alert(data.msg);
            }
		});
	}
}
//编辑申请贷款信息
function openEditWindows(obj,form){
	layer.open({
		  title:'编辑贷款信息',
		  type: 1,
		  fixed:false,
	      resize :false,
		  skin: 'layui-layer-molv', //加上边框
		  area: ['500px', '400px'], //宽高
		  content: $('#setApplyinfo'),
		  success: function (layero, index) {  
			  $("#orderid").val(obj.orderID);
			  $("#mark").html(obj.mark);
              $("#number").val(obj.number);
              $("#name").val(obj.name);
              $("#userID").val(obj.userID);
              //为下拉框选中值
			  var select = 'dd[lay-value=' + obj.status + ']';
			  $('select#status').siblings("div.layui-form-select").find('dl').find(select).click();
			  var selectBank = 'dd[lay-value=' + obj.bankID + ']';
			  $('select#bankID').siblings("div.layui-form-select").find('dl').find(selectBank).click();
			  form.render('select');
		  },
		  end:function(){
			  $('#editApplyForm')[0].reset();
		  }
		});
}

//提交表单
function formSubmit(formData){
    $.ajax({
        type: "POST",
        data: $("#editApplyForm").serialize(),
        url: "/apply/editApplyInfo",
        success: function (data) {
            if (data.msg == "success") {
                layer.alert("编辑成功", {icon: 1,closeBtn: 0 },function(){
                    layer.closeAll();
                    //加载页面
                    load();
                });
            } else if(data.code==417){
                layer.confirm(data.msg+"；"+"如需更改贷款渠道,有两个方法：" +
                    "<p>1：这里直接改动，会在用户的贷款列表里新增一条申请的贷款信息，然后让用户在对应这条申请信息里面提交贷款申请表；</p> " +
                    "<p>2：让用户自己再提交一条申请信息；</p>", {
                    btn: ['1是直接改动', '2是取消' ] //按钮
                    ,cancel: function(index, layero){
                        //取消操作，点击右上角的X
                        layer.closeAll();
                    }
                }, function(){
                        //是,添加一条贷款信息
                    /*console.log(formData);*/
                    var paramObj={"bankID":data.data,"name":formData.field.myName,"number":formData.field.myNumber,"userID":formData.field.myUserID};
                    /*console.log(paramObj);*/
                    var promise= $.post("/apply/addCreditApply",paramObj);
                    promise.then(function(json){
                        if(json.code==200){
                            layer.alert("编辑成功", {icon: 1,closeBtn: 0 },function(){
                                layer.closeAll();
                                //加载页面
                                load();
                            });
                        }else {
                            layer.alert(json.msg,{closeBtn: 0 },function(){
                                layer.closeAll();
                            });
                        }

                    },function(error){
                        layer.alert("操作请求错误，请您稍后再试",{closeBtn: 0 },function(){
                            layer.closeAll();
                        });
                    });
                }, function(){
                        //否
                    layer.closeAll();
                });
            }else {
                layer.alert(data.msg,{closeBtn: 0 },function(){
                    layer.closeAll();
                });
            }
        },
        error: function () {
            layer.alert("操作请求错误，请您稍后再试",{closeBtn: 0 },function(){
                layer.closeAll();
                //加载load方法
                load();//自定义
            });
        }
    });
}

//更换申请信息的申请人
function changeUser(data){
	if(null != data.orderID && data.orderID != ''){
		layer.open({
			 title:'更改贷款申请人',
			 type: 1,
			 fixed:false,
		     resize :false,
			 skin: 'layui-layer-molv', //加上边框
			 area: ['400px', '200px'], //宽高
			 content: $('#changeUser'),
			 success: function (layero, index) { 
				  $("#orid").val(data.orderID);
			 },
			 end:function(){
				 $("#changeUserForm")[0].reset();
			 }
		});
	}
}

function changeUserOfForm(){
	//获取手机号
	var flag = true;
	var mobile = $("#mobile").val();
	var mflag = ValidateUtils.checkMobile(mobile);
	if(mflag != "ok"){
		layer.msg(flag);
		flag=false;
	}
	if(flag){
		$.post("/apply/changeUserId",$("#changeUserForm").serialize(),function(data){
			//if(handlerResponse){
				if(data == "success"){
					layer.alert("更换申请人成功！", {icon: 1,closeBtn: 0 },function(){
	                    layer.closeAll();
	                    //加载页面
	                    load();
	                });
				}else {
					layer.alert(data);
				}
			//}
		});
	}
}