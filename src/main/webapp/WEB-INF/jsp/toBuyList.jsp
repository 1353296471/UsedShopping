<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<form action="toPay" method="post">
	<div class="shopping_cart">
		<c:set var="sum" value="0"></c:set>
		<!-- 判断有itemList的时候，再循环生成购物车项 -->
		<c:if test="${!empty itemList }">
			<c:forEach items="${itemList }" var="item">
				<div class="cart_box">
					<div class="message">
						<div class="alert-close" onclick="deletePro(${item.warehouseId })"></div>
						<div class="list_img" style="border: none;">
							<img src="images/${item.showProduct.mainImg.imgUrl }" class="img-responsive" alt="" width="50px" height="67px" />
						</div>
						<div class="list_desc">
							<h4>
								<a href="#">${item.showProduct.pro.proName }</a>
								<a>${item.colorType }</a>
								<a>${item.sizeType }</a>
							</h4>
							<input type="button" name="remove" value="-" onclick="remove(${item.warehouseId })" >
							<input name="countDiv" type="text" id="countNum" disabled="disabled" value='${item.num }' size="2" style="text-align: center" />
							<input type="button" name="add" value="+" onclick="addPro(${item.warehouseId })" >
							x
							<span class="actual"> ￥${item.showProduct.pro.price }</span>
						</div>
						<br>
						<input type="checkbox" name="warehouseIds" value="${item.warehouseId }" checked="checked" onclick="checkPro()"/>
						<div class="login_button">
							<a href="toPay?warehouseIds=${item.warehouseId }">单个结算</a>
						</div>
						<div class="clearfix"></div>
					</div>
				</div>
				<c:set var="sum" value="${sum + item.showProduct.pro.price*item.num }" />
			</c:forEach>
		</c:if>
	</div>
	<div class="total">
		<div class="total_left">总价 :</div>
		<div class="total_right" id="totalPrice">￥${sum }</div>
		<div class="clearfix"></div>
	</div>
	<br>
	<div class="login_buttons">
		<div class="login_button">
			<input type="submit" value="全部结算" class="btn btn-success">
		</div>
		<div class="clearfix"></div>
	</div>
	<div class="clearfix"></div>
</form>