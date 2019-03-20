var laypage;
var layer;
var currPage = 1;
layui.use(['layer','laypage'], function(){
	  laypage = layui.laypage;
	  layer = layui.layer;
	  
	  //config传递到后台的参数
	  var config = {};
	  config.pageNum = 1;
	  if(currentPage != null && 
			  currentPage != undefined && 
			  currentPage != ""){
		  config.pageNum = currentPage;
	  }
	  config.pageSize = 10;
	  config.mobile="";
	  config.startDate="";
	  config.endDate="";
	  
	  PagePlug(config);
	});

function PagePlug(config){
	 var url = "/estimate/selectEstimateInfo";
//	 console.log(config);
	  $.post(url,config,function(res){
	   laypage.render({
	      elem: 'pagesign',
	      count: res.total, //总条数
	      curr:res.pageNum,
	      limit:res.pageSize,
	      layout: ['count', 'prev', 'page','limit', 'next', 'skip'],
	      align:'center',
	      theme: '#009688', //自定义颜色
	      jump: function(obj, first){
	          if(!first){ //首次则不进入
	        	  if(obj.limit!=config.pageSize){
		    		  config.pageSize =  obj.limit;
		    	  }
	           currPage = obj.curr;
	           config.pageNum = obj.curr;
	           getListByPage(url,config);
	          }
	      }
	    });
	   if(config.pageNum == null || config.pageNum == undefined){
		   config.pageNum = 1;
	   }
	   parseList(res,config.pageNum);  
	  });
}
//点击页数从后台获取数据
function getListByPage(url,config){
	 var index = layer.load(1);
	 $.post(url,config,function(res){
		 parseList(res,config.pageNum,index);
	 });
}
//解析数据，currPage参数为预留参数，当删除一行刷新列表时，可以记住当前页而不至于显示到首页去
function parseList(res,currPage,index){
	var tds="<td><div align='center'><span>";
	var tde="</span></div></td>";
	var content = "";
	$("#estimateBody").empty();
	$.each(res.list, function (i, o) {
		content += "<tr class='toucher'>";
		content += tds + DateUtils.formatDate(o.createTime) + tde;
		content += tds + o.mobile + tde;
		if(o.shopType==1){
			content += tds + "淘宝网店" + tde;
		}else if(o.shopType==2){
			content += tds + "天猫店铺" + tde;
		}else if(o.shopType==3){
			content += tds + "京东店铺" + tde;
		}
		if(o.parm1==1){
			content += tds + "店铺名称" + tde;
		}else if(o.parm1==2){
			content += tds + "店铺地址" + tde;
		}else if(o.parm1==3){
			content += tds + "旺旺名称" + tde;
		}
		content += tds + o.parm2 + tde;
		content += tds + o.parm3 + tde;
		content += tds + o.limit + tde;
		content += tds +
			 "<a class='layui-btn layui-btn-xs' onclick='selecteDetail("+o.id+","+currPage+");'>查看</a>&nbsp;&nbsp;&nbsp;"+
			 "<a class='layui-btn layui-btn-danger layui-btn-xs' onclick='deleteData("+o.id+");'>删除</a>"+
			 tde;
	});
	$('#estimateBody').html(content);
	layer.close(index);
}
 
//删除数据
 function deleteData(id){
 	layer.confirm("确定要删除该条数据吗？",{
 		btn:["确定","取消"]
 	},
 	function(){
 		//确定
 		$.post("/estimate/updateById",{"id":id},function(data){
 			if(data=="success"){
 				layer.alert("删除成功！", {icon: 1,closeBtn: 0 },function(){
 					layer.closeAll();
 					//刷新数据
 					var config={};
 					config.pageNum = currPage;
 					config.pageSize = 10;
 					config.mobile=$("#mobile").val();
				    config.startDate=$("#startDate").val();
				    config.endDate=$("#endDate").val();
 					PagePlug(config);
                });
 			}else {
 				layer.alert(data);
 			}
 		});
 	},
 	function(){
 		layer.closeAll();
 	});
 }
// 查询详情
function selecteDetail(id, page) {
	$.post("/estimate/selectById",{"id":id},function(data){
		if(data.msg=="success"){
			$("#username").val(data.shopEstimate.username);
			$("#shoptype").val(data.shopEstimate.shoptype==1?"淘宝":data.shopEstimate.shoptype==2?"天猫":"京东");
			$("#parm1").val(data.shopEstimate.parm1==1?"店铺名称":data.shopEstimate.parm1==2?"店铺地址":"旺旺名称");
			$("#parm2").val(data.shopEstimate.parm2);
			$("#parm3").val(data.shopEstimate.parm3);
			$("#limit").val(data.shopEstimate.limit+" 万元");
			$("#mark").val(data.shopEstimate.mark);
			$("#createtime").val(DateUtils.formatDate(data.shopEstimate.createtime));
			$("#updatetime").val(DateUtils.formatDate(data.shopEstimate.updatetime));

			layer.open({
				type:1,
		        title: "额度预估详情",
		        fixed:true,
		        resize :false,
		        shadeClose: true,
		        area: ['550px'],
		        content:$('#searchDeatil')
			});
		}else {
			layer.alert(data);
		}
	});
}

layui.use([ "form", "laydate" ], function() {
	var form = layui.form
	laydate = layui.laydate;
	//日期
	laydate.render({
		elem : '#startDate',
        type: 'datetime'//指定元素
	});
	laydate.render({
		elem : '#endDate',
        type: 'datetime'//指定元素
	});

	form.on("submit(searchSubmit)", function(obj) {
		var config = {};
		config = obj.field;
		config.pageNum = 1;
		config.pageSize = 10;
		PagePlug(config);
		return false;
	});
});

