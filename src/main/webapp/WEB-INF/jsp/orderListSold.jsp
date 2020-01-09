<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script>
$(function () {
	$("#pageNo").change(function () {
		var val = $("#pageNo").val();
		var maxPage = "${requestScope.page.maxPage }";
		if(checkPage(val,maxPage)){
			//3. 页面跳转
			toOrderListPage(val);
		}
	})
})

function toSendOrder(orderPkid){
	var orderPkid  = orderPkid;
	var pageNo  =${requestScope.page.pageNo };
	$.ajax({
		async : false,
		type : 'post',
		url : 'toSendOrder/'+orderPkid,
		success : function(falg) {
			if(falg){
				swal("发货成功！");
				toOrderListPage(pageNo);
			}else{
				swal("发货失败！请重试！");
			}
		}
	});
	
}
</script>


<div class="shopping_cart">
	<c:if test="${!empty items }">
		<c:forEach items="${items }" var="item">
			<div class="cart_box">
				<div class="message">
					<div class="list_img" style="border: none;">
						<img src="images/${item.imgUrl }" class="img-responsive" alt="" width="100px" height="140px" />
					</div>
					<div class="list_desc">
						<h3>
							<a href="tosingle/${item.proId}">${item.proName }</a>
						</h3>
						${item.proNum } x
						<span class="actual"> ￥${item.price }</span>
						总价：
						<span class="actual"> ￥${item.price*item.proNum }</span>
						<!-- 
						颜色：
						<span class="actual"> ${item.colorType }</span>
						尺码：
						<span class="actual"> ${item.sizeType }</span>
						 -->
						
						<br>
						收货人：
						<span class="actual"> ${item.userName }</span>
						<br>
						地址：
						<span class="actual"> ${item.sheng }</span>
						<span class="actual"> ${item.shi }</span>
						<span class="actual"> ${item.qu }</span>
						<span class="actual"> ${item.userAddress }</span>
						<br>
						电话：
						<span class="actual"> ${item.userPhone }</span>
						<br>
						订单状态：
						<span class="actual"> ${item.conditionType }</span>
						<br>
						下单时间：
						<span class="actual"> ${item.orderTime }</span>
					</div>
					<div class="login_button">

						<c:if test="${2 eq item.orderConditionPkid}">
							<c:if test="${0 eq item.commentPkid}">

								<a>尚未评价</a>


							</c:if>
							<c:if test="${0 != item.commentPkid}">
								<a>已评价</a>
								<br>
								<br>
								<c:if test="${!empty item.commentDes }">
								评论：<br>
									<span class="actual"> ${item.commentDes }</span>
								</c:if>
							</c:if>
						</c:if>
					</div>


					<div class="check_button">
						<c:if test="${item.orderConditionPkid eq 1}">
							<a href="#" onclick="toSendOrder(${item.orderPkid})">确认发货</a>
						</c:if>
						<c:if test="${item.orderConditionPkid eq 2}">
							<a> ${item.conditionType }</a>
							<br>
						</c:if>
					</div>



					<div class="clearfix"></div>
				</div>
			</div>
		</c:forEach>
	</c:if>

	<br>
	<center>
		<br>
		共${requestScope.page.maxPage }页，当前第${requestScope.page.pageNo }页
		<br>
		<br>
		<a href="#" onclick="toOrderListSoldPage(${requestScope.page.fristPage })">首页</a>
		&nbsp;&nbsp;
		<a href="#" onclick="toOrderListSoldPage(${requestScope.page.beforePage })">上一页</a>
		&nbsp;&nbsp;
		<a href="#" onclick="toOrderListSoldPage(${requestScope.page.nextPage })">下一页</a>
		&nbsp;&nbsp;
		<a href="#" onclick="toOrderListSoldPage(${requestScope.page.maxPage })">末页</a>
		&nbsp;&nbsp;转到<input type="text" size="1" id="pageNo">页
		<br>
	</center>
</div>
