 var pageCurr = 1;
     if(currentPage != null && currentPage != undefined && currentPage != ""){
     	pageCurr = currentPage;
     }
     $(function() {
        //一般直接写在一个js文件中
        layui.use(['layer', 'form','laydate','table','util'], function(){
            var layer = layui.layer,
            	util = layui.util,
            	form = layui.form ;
            var laydate = layui.laydate;
            var table = layui.table;
            tableIns=table.render({
                elem: '#test'
                ,url:'/borrow/selectCreditInfo'
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
                    {field:'createtime',title: '提交时间', sort: true,align:'center',width:'11%'}
                    ,{field:'name',  title: '真实姓名',align:'center',width:'7%'}
                    ,{field:'sex', title: '性别',templet:'#sexType',align:'center',width:'5%'}
                    ,{field:'number', title: '手机号码',align:'center',width:'8%'}
                    ,{field:'bankid', title: '渠道商',templet:'#bankType',align:'center',width:'9%'}
                    ,{field:'money', title: '借款金额',templet:'#moneyType',align:'center',width:'7%'}
                    ,{field:'applystate', title: '提交状态',templet:'#applyStateType',align:'center',width:'8%'}
                    ,{field:'status', title: '审批状态',templet:'#statusType',align:'center',width:'7%'}
                    ,{field:'mark',  title: '状态备注',width:'27%'}
                    ,{field:'right',title: '操作' ,toolbar:'#optBar',width:'11%',align:'center'}
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
      		  $('#creditForm')[0].reset();
      		  load();
      		  return false;
      	  });
      	 //监听返回
      	  form.on('submit(backSubmit)', function(data){
      		location.href="/borrow/toBorrowInfoList?curr="+pageCurr;
      		  return false;
      	  });
      	 //监听刷新
      	  form.on('submit(refeshSubmit)', function(data){
      		  document.location.reload();
      		  return false;
      	  });
      	  //审批提交
      	  form.on('submit(examineSubmit)',function(data){
      		  examineFormSub();
      		  return false;
      	  });
      	//更换申请人
          form.on('submit(bindingUserSubmit)',function(data){
        	  bindingInfoUser();
        	  return false;
          });
      	  
      	//监听工具条
      	//注：test是tool是工具条事件名，table原始容器的属性 lay-filter="对应的值"
          table.on('tool(creditTable)', function(obj){
              var data = obj.data;
              var pathname=window.location.pathname;
              if(obj.event === 'see'){
            	  location.href="/borrow/toBorrowDetailPage?orderid="+data.orderid+"&userid="+data.userid+"&callback="+pathname+"&curr="+pageCurr;
              }else if(obj.event === 'examine'){
            	  //审批功能
            	  examineMethod(data,form);
              }else if(obj.event === 'binding'){
            	  //绑定功能
            	  bindingUser(data);
              }
          });
       
        });
     });  
     
function load(){
    //重新加载table
    tableIns.reload({
        where: $('#creditForm').serializeObject() ,
        page: {
            curr: pageCurr //从当前页码开始
        }
    });
 }

//极速借 查询地址
function getAddressByJSJ(type,pid,realid,work){
		//极速借
		$.post("/borrow/getAttrById",{"id":realid,"pid":pid},function(data){
			if(data.msg=="success"){
				if(type==0&&work==1){
					rendering('iaddr_prov',data.data.name);
				}else if(type==1&&work==1){
					rendering('iaddr_city',data.data.name);
				}else if(type==2&&work==1){
					rendering('iaddr_county',data.data.name);
				}
				if(type==0&&work==2){
					rendering('work_prov',data.data.name);
				}else if(type==1&&work==2){
					rendering('work_city',data.data.name);
				}else if(type==2&&work==2){
					rendering('work_county',data.data.name);
				}
			}else{
				if(type==1&&work==1){
					$("#iaddr_prov").append("<option value='1'>暂无信息</option>");
				}else if(type==2&&work==1){
					$("#iaddr_city").append("<option value='2'>暂无信息</option>");
				}else if(type==3&&work==1){
					$("#iaddr_county").append("<option value='3'>暂无信息</option>");
				}
				
				if(type==1&&work==2){
					$("#work_prov").append("<option value='1'>暂无信息</option>");
				}else if(type==2&&work==2){
					$("#work_city").append("<option value='2'>暂无信息</option>");
				}else if(type==3&&work==2){
					$("#work_county").append("<option value='3'>暂无信息</option>");
				}
			}
		});
}
function rendering(str,name){
	  //为下拉框选中值
	 $("#"+str).html("");
	 $("#"+str).append("<option value='1'>"+ name+"</option>");
	 //重新渲染下form表单 否则下拉框无效
	 layui.use(['form'], function(){
		var form = layui.form ;
		 form.render('select');
	 });
}
function showPhoto(obj){
	var address=$("#"+obj).attr("src");
	layer.open({
		  type: 1,
		  title: false,
		  closeBtn: 0,
		  area: '516px',
		  skin: 'layui-layer-nobg', //没有背景色
		  shadeClose: true,
		  content:"<img src='"+address+"' style='width:100%;height:100%;'>" ,
		  success:function(layero,index){
			  layer.style(index, {
				  top: '120px'
				});    
		  }
	});
}
//审批
function examineMethod(data,form){
	if(null != data.orderid){
		layer.open({
			 title:'审批',
			 type: 1,
			 fixed:false,
		     resize :false,
			 skin: 'layui-layer-molv', //加上边框
			 area: ['550px', '450px'], //宽高
			 content: $('#examineDetail'),
			 success: function (layero, index) { 
				  $("#orderID").val(data.orderid);
				  form.render('select');
			 },
			 end:function(){
				 $("#examineForm")[0].reset();
			 }
		});
	}
}
//审核提交
function examineFormSub(){
	var flag = true;
	var mark = $("#mark").val();
	if(mark=="" || $.trim(mark).length==0){
		layer.msg("备注不能为空！");
		flag=false;
	}
	if(flag){
		$.post("/borrow/examineCreditInfo",$("#examineForm").serialize(),function(data){
			if(data=="success"){
				layer.alert("审批成功！", {icon: 1,closeBtn: 0 },function(){
					layer.closeAll();
					load();
                });
			}else {
				layer.alert(data);
			}
		});
	}
}

//绑定申请人
function bindingUser(data){
	if(null != data.orderid && data.orderid != ''){
		layer.open({
			 title:'绑定贷款申请人',
			 type: 1,
			 fixed:false,
		     resize :false,
			 skin: 'layui-layer-molv', //加上边框
			 area: ['400px', '200px'], //宽高
			 content: $('#bindingUser'),
			 success: function (layero, index) { 
				  $("#orid").val(data.orderid);
			 },
			 end:function(){
				 $("#bindingUserForm")[0].reset();
			 }
		});
	}
}

function bindingInfoUser(){
	//获取手机号
	var flag = true;
	var mobile = $("#mobile").val();
	var mflag = ValidateUtils.checkMobile(mobile);
	if(mflag != "ok"){
		layer.msg(flag);
		flag=false;
	}
	if(flag){
		$.post("/borrow/bindingUserId",$("#bindingUserForm").serialize(),function(data){
				if(data == "success"){
					layer.alert("操作成功！", {icon: 1,closeBtn: 0 },function(){
						layer.closeAll();
						load();
	                });
				}else {
					layer.alert(data);
				}
		});
	}
}
//修改渠道商
function updateQuDao(bankid,orderID){
	var title;
	if(bankid==1){
		title="确定修改渠道商为极速借？";
	}else {
		title="确定修改渠道商为宜信？";
	}
	layer.confirm(title,{
		btn:['确定','取消']
	},function(){
		$.post("/infor/updateQuDao",{"bankid":(bankid==1?2:1),"orderID":orderID},function(result){
			if(result=="success"){
				layer.alert("修改成功！", {icon: 1,closeBtn: 0 },function(){
					layer.closeAll();
					load();
                });
			}else {
				layer.alert(result);
			}
		});
	},function(){
		layer.closeAll();
	});
}


