<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<h3>
	最近添加的商品(
	<c:choose>
		<c:when test="${empty itemSize }">0</c:when>
		<c:otherwise>${itemSize}</c:otherwise>
	</c:choose>
	)
</h3>
<div class="shopping_cart">
	<c:set var="sum" value="0"></c:set>
	<!-- 判断有itemList的时候，再循环生成购物车项 -->
	<c:if test="${!empty itemList }">
		<c:forEach items="${itemList }" var="item">
			<div class="cart_box">
				<div class="message">
					<div class="alert-close" onclick="deletePro(${item.showProduct.pro.id })"></div>
					<div class="list_img">
						<img src="images/${item.showProduct.mainImg.imgUrl }" class="img-responsive" alt="" />
					</div>
					<div class="list_desc">
						<h4>
							<a href="#">${item.showProduct.pro.proName }</a>
							<a>${item.colorType }</a>
							<a>${item.sizeType }</a>
						</h4>
						${item.num } x
						<span class="actual"> ￥${item.showProduct.pro.price }</span>
					</div>
					<div class="clearfix"></div>
				</div>
			</div>
			<c:set var="sum" value="${sum + item.showProduct.pro.price*item.num }" />
		</c:forEach>
	</c:if>
</div>
<c:choose>
	<c:when test="${itemSize>0 }">
		<div class="total">
			<div class="total_left">总价 :</div>
			<div class="total_right">￥${sum }</div>
			<div class="clearfix"></div>
		</div>
	</c:when>
</c:choose>

<c:choose>
	<c:when test="${itemSize==0 }">
		<center>
			<h4>空空如也</h4><br><br><br>
		</center>
	</c:when>
	<c:otherwise>
		<div class="login_buttons">
			<div class="login_button">
				<a href="checkout.html">结算</a>
			</div>
			<div class="clearfix"></div>
		</div>
	</c:otherwise>
</c:choose>
<div class="clearfix"></div>