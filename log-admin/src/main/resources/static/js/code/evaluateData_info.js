var laypage;
var layer;
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
	  config.shopType=null;
	  config.username="";
	  config.shopName="";
	  config.flag=2;
	  
	  PagePlug(config);
	});

function PagePlug(config){
	 var url = "/code/getCodeDataInfo";
	  $.post(url,config,function(res){
	   laypage.render({
	      elem: 'pagesign',
	      count: res.total, //总条数
	      curr:res.pageNum
	     ,limit:res.pageSize
	     ,layout: ['count', 'prev', 'page','limit', 'next', 'skip']
	     ,theme: '#009688', //自定义颜色
	      jump: function(obj, first){
	          if(!first){ //首次则不进入
	        	  if(obj.limit!=config.pageSize){
		    		  config.pageSize =  obj.limit;
		    	  }
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
	 var content = "";
	 $("#estimateBody").empty();
	 if(null != res.list || res.list==''){
		 $.each(res.list, function (i, o) {
			  content += "<tr class='toucher'>";
			  content += "<td>"+(o.uniqueFlag==null?'':o.uniqueFlag)+"</td>";
			  content += "<td>"+DateUtils.formatDate(o.insertTime)+"</td>";
			  content += "<td>"+(o.shopName==null?'':o.shopName)+"</td>";
			  if(o.shopType==1){
				  content += "<td>天猫</td>";
			  }else if(o.shopType==2){
				  content += "<td>淘宝</td>";
			  }
			  content += "<td>"+(o.openDate==null?'':o.openDate)+"</td>";
			  content += "<td>"+(o.describeScore==null?'':o.describeScore)+"</td>";
			  content += "<td>"+(o.describeTradeLevel==null?'':o.describeTradeLevel)+"</td>";
			  content += "<td>"+(o.serviceScore==null?'':o.serviceScore)+"</td>";
			  content += "<td><a class='layui-btn layui-btn-xs' onclick='selecteDetail("+o.id+","+currPage+",2);'>查看</a></td>";
		  
		 });
		 $('#codeDataBody').html(content);
	 }
	 layer.close(index);
}

layui.use("form", function() {
	var form = layui.form;
	form.on("submit(searchSubmit)", function(obj) {
		var config = {};
		config = obj.field;
		config.pageNum = 1;
		config.pageSize = 10;
		config.flag=2;
		PagePlug(config);
		return false;
	});
});

//查询详情
var pathname = window.location.pathname;
//flag=2表示网店估价列表查看详情
function selecteDetail(id, page,flag) {
	window.location.href = "/code/searchById?id=" + id + "&currentPage="
			+ page +"&flag=" + flag  + "&callback=" + pathname;
}