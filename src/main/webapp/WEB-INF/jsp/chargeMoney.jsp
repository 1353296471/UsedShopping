<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<h1>充值</h1>
<center>
	<h3>我的余额：￥ ${cash }</h3>
	<br>
		<br>
		<label >充值金额：￥</label><input type="number" name="money" class="form-control money" style="width: 30%">
		<br>
		<br>
		
		<input type="button" value="确认充值" onclick="chargeMoney()" class="btn btn-success"> 
</center>
