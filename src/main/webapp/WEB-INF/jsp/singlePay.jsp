<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script src="js/location.js"></script>
<script>
	$(function() {
		$(".loc").change(function() {
			choseLocation();
		})
	})
</script>
<center id="topay">
	<br>
	<br>
	<br>
	<h3 id="price">您需要支付￥ ${price },请填写您的收货信息</h3>
	<br>
	<br>
	省份： <select id="province" name="sheng" class="loc"></select> 城市： <select id="city" name="shi" class="loc"></select> 区县： <select id="county" name="qu" class="loc"></select>
	<span id="address" hidden="true"></span>
	<br>
	<br>
	<form action="singlePay" method="post" name="payForm">
		<input type="text" id="userPkid" name="userPkid" value="${userId }" hidden="true">
		 <input type="text" id="sheng" name="sheng" hidden="true" value="湖南省"> 
		 <input type="text" id="shi" name="shi" hidden="true" value="长沙市"> 
		 <input type="text" id="qu" name="qu" hidden="true" value="芙蓉区"> 
		 <!-- 
		  测试：湖南省 长沙市 芙蓉区
		  -->
		
		 <label>详细地址：</label><input type="text" name="userAddress">
		<br>
		<br>
		<!-- 
			<label>电话号码：</label><input type="number" name="userPhone"  id="userPhone" value="15147474396">
			 -->
			
			<label>电话号码：</label><input type="number" name="userPhone"  id="userPhone" >
			<br>
		<br>
		<input type="button" value="取消" class="btn btn-default" onclick="toCheckout()"> <input type="button" onclick="payFormClick()" value="确认付款" class="btn btn-success">
	</form>
	<br>
	<br>
	<br>
	<br>
	<br>
</center>
<script>
	var loc = beva.locationutil.attach('province', 'city', 'county', 'address');

	// loc.setSelectedCodes([697,698]);
</script>