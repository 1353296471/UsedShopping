<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix='c'%>
<script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
<script type="text/javascript">

$(function(){
	
 $("#delete").click(function(){
	 var count =  $("#countDiv").val();
	 if(count<=1){
		 return;
	 }
	 $("#add").attr("disabled",false);
	$("#countDiv").val(parseInt($("#countDiv").val())-1);
	var count=$("#countDiv").val();
	if(parseInt(count)==1){
		 $("#delete").attr("disabled",true);
	}
});
 
 $("#add").click(function(){
	 var val = $("#wareCount").html();
	 val = $.trim(val);
	 wareCount = parseInt(val);
	 var count =  $("#countDiv").val();
	 if(wareCount<=count){
		 return;
	 }
	 $("#delete").attr("disabled",false);
	 $("#countDiv").val(parseInt($("#countDiv").val())+1);
	 var count=$("#countDiv").val();
	 var wareCount=$("#wareCount").html();
	 if((parseInt(count))==wareCount){
		 $("#add").attr("disabled",true);
	 }
});	
});

</script>
	  	         <ul class="back">
                	  <li><i class="back_arrow"> </i>返回 <a href="index.html">首页</a></li>
                    </ul>
					<h1>${requestScope.proName }</h1>
					<ul class="price_single">
					  <li class="head"><h2>￥${requestScope.price }</h2></li>
					  
					  <!-- 
					  <li class="head_desc"><a href="#">12 reviews</a><img src="images/review.png" alt=""/></li>
					  
					   -->
					  
					  <div class="clearfix"></div>
					</ul>
				    
				    
