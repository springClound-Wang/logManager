var pageCurr = 1;
$(function(){
	
	layui.use(['laydate','table','form','table','layer'],function(){
		var laydate = layui.laydate,
			table = layui.table,
			form = layui.form,
			table = layui.table,
			layer = layui.layer;
		
		tableIns = table.render({
			elem : '#uesrList',
			url : '/user/selectUsers',
			method : 'post',
			page : true,
			request: {
                pageName: 'page' //页码的参数名称，默认：page
                ,limitName: 'limit' //每页数据量的参数名，默认：limit
            },
            response:{
                statusName: 'code' //数据状态的字段名称，默认：code
                ,statusCode: 200 //成功的状态码，默认：0
                ,countName: 'totals' //数据总数的字段名称，默认：count
                ,dataName: 'list' //数据列表的字段名称，默认：data
            },
            page:{
            	curr : pageCurr
            },
            cols: [[
                    {field:'userID',  title: '用户ID',align:'center'}
                    ,{field:'username',  title: '用户名',align:'center'}
                    ,{field:'mobile', title: '手机号',align:'center'}
                    ,{field:'insertTime', title: '注册时间',templet:'<div>{{DateUtils.formatDate(d.insertTime)}}</div>',align:'center'}
                    ,{field:'updateTime',  title: '修改时间',templet:'<div>{{DateUtils.formatDate(d.updateTime)}}</div>',align:'center'}
                    ,{field:'right', title: '操作' ,align:'center',toolbar:'#optBar',align:'center'}
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
            elem: '#insertTimeStart',
            	type: 'datetime'//指定元素
        });
        //执行一个laydate实例
        laydate.render({
            elem: '#insertTimeEnd',
            	type: 'datetime'//指定元素
        });
		
        //提交查询条件
        form.on('submit(searchSubmit)',function(data){
        	load();
        	return false;
        });
        //提交查询条件
        form.on('submit(updateUserSubmit)',function(data){
//        	console.log("data:"+data);
        	updateUserData(data);
        	return false;
        });
        
        //删除
        table.on('tool(userTable)',function(obj){
        	var data = obj.data;
        	if(obj.event=="del"){
        		delUser(data);
        	}else if(obj.event=="recover"){
        		recoverUser(data);
        	}else if(obj.event=="update"){
        		updateUser(data);
        	}
        });
        
	});
	
});

function load(){
	//重新加载表格
	tableIns.reload({
		where:$('#userSearch').serializeObject(),
		page:{
			curr:pageCurr
		}
	});
}

//删除功能
var flag=null;
function delUser(data){
	if(flag==null){
		layer.confirm("确定删除用户"+data.username+"？",{
			btn:['确定','返回']
		},
		function(){
			//删除请求
			$.post("/user/delCustomer",{"userID":data.userID,"mobile":data.mobile,"flag":flag},function(result){
				if(result.indexOf("中有信息！确定要删除吗？")>0){
					layer.alert(result,{
						btn:["确定","取消"],
						shift:-1,
						yes:function(){
							flag=1;
							delUser(data);
						},
						btn2:function(){
							layer.closeAll();
						}
					});
				}else if(result == "success"){
					layer.alert("删除成功！",{
						closeBtn:0,
						btn:['确定'],
						yes:function(){
							layer.closeAll();
							load();
						}
					});
				}else {
					layer.alert(result);
				}
			});
		},
		function(){
			layer.closeAll();
		});
	}else {
		//删除请求
		$.post("/user/delCustomer",{"userID":data.userID,"mobile":data.mobile,"flag":flag},function(result){
			flag=null;
			if(result=="success"){
				layer.alert("删除成功！",{
					closeBtn:0,
					btn:['确定'],
					yes:function(){
						layer.closeAll();
						load();
					}
				});
			}else {
				layer.alert(result);
			}
		});
	}
	
}

//恢复用户
function recoverUser(data){
	layer.confirm("确定恢复用户"+data.username+"?",{
		btn:['确定','返回']
	},
	function(){
		$.post("/user/recoverCustomer",{"userID":data.userID,"mobile":data.mobile},function(result){
			if(result=="success"){
				layer.alert("恢复成功！",{
					closeBtn:0,
					btn:['确定'],
					yes:function(){
						layer.closeAll();
						load();
					}
				});
			}else {
				layer.alert(result);
			}
		});
	});
}

//updateUser
//绑定申请人
function updateUser(data){
	if(null != data.userID && data.userID != ''){
		layer.open({
			 title:'编辑用户',
			 type: 1,
			 fixed:false,
		     resize :false,
			 skin: 'layui-layer-molv', //加上边框
			 area: ['400px', '400px'], //宽高
			 content: $('#updateUser'),
			 success: function (layero, index) { 
				  $("#uid").val(data.userID);
				  $("#userId").val(data.userID);
				  $("#userphone").val(data.mobile);
			 },
			 end:function(){
				 $("#updateUserForm")[0].reset();
			 }
		});
	}
}

function updateUserData(data){
	//获取手机号
	var flag = true;
	var uid = $("#uid").val();
	var userId = $("#userId").val();
	var mobile = $("#userphone").val();
	if(uid==null || uid =="" || userId==null || userId ==""){
		layer.msg("参数有误，请稍后再试");
		flag=false;
	}
	var mflag = ValidateUtils.checkMobile(mobile);
	if(mflag != "ok"){
		layer.msg(flag);
		flag=false;
	}
	if(flag){
		$.post("/user/updateUser",$("#updateUserForm").serialize(),function(data){
				if(data == "success"){
					layer.alert("操作成功！",{
						closeBtn:0,
						btn:['确定'],
						yes:function(){
							layer.closeAll();
							load();
						}
					});
				}else {
					layer.alert(data);
				}
		});
	}
}



