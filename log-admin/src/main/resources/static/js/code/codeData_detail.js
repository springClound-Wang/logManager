$(function(){
	$("#insertTime").val(insertTime==null?"":DateUtils.formatDate(insertTime));
});

function backset(){
	if(flag==1){
		window.location.href="/code/codeDataInfoPage?currentPage="+currentPage;
	}else if(flag==2){
		window.location.href="/code/evaluateListPage?currentPage="+currentPage;
	}
}