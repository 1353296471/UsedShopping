<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<html>
<head>
<title>提示</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="Flatro Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template, 
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />
<script type="application/x-javascript">
	
	 addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } 

</script>
<link href="css/bootstrap.css" rel='stylesheet' type='text/css' />
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<!-- Custom Theme files -->
<link href="css/style.css" rel='stylesheet' type='text/css' />
<!-- Custom Theme files -->
<!--webfont-->
<link href='#css?family=Lato:100,200,300,400,500,600,700,800,900' rel='stylesheet' type='text/css'>
<script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
<!-- start menu -->
<link href="css/megamenu.css" rel="stylesheet" type="text/css" media="all" />
<script type="text/javascript" src="js/megamenu.js"></script>

<!-- 引入全局函数myFunction.js -->
<script type="text/javascript" src="js/myFunction.js"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>

<link href="bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
<script src="bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>

<script>
	$(document).ready(function() {
		$(".megamenu").megamenu();
	});
</script>

<script>

	$(document).ready(function() {
		init();
	})

</script>

</head>
<body>
	<div class="header_top">
		<div class="container">
			 
			<div class="cssmenu">
				<ul>
					<li class="active"><a href="login.html" id="account">My
							Account</a></li>
				</ul>
			</div>
		</div>
	</div>
	<div class="header_bottom men_border">
		<div class="container">
			<div class="col-xs-9 header-bottom-left">
				<div class="col-xs-2 logo">
					<h1>
						<a href="index.html">
							<span>Buy</span>
							shop
						</a>
					</h1>
				</div>
				<div class="col-xs-7 menu">
					<ul class="megamenu skyblue">
						<li class="grid">
							<a class="color1" id="meun" href="search.html?searchdata=男">男装</a>
						</li>
						<li class="grid">
							<a class="color2" id="meun" href="search.html?searchdata=女">女装</a>
						</li>
						 
					</ul>
				</div>
			</div>
			<div class="col-xs-3 header-bottom-right">
				<ul class="icon1 sub-icon1 profile_img">
					<li>
						<a class="active-icon c1" onmouseover="toShowShopCarItem()" href="#"> </a>
						<ul class="sub-icon1 list" id="shopCarItemListDiv">
							<div class="login_buttons">
								<div class="login_button">
									<a href="login.html">登录</a>
								</div>
								<div class="clearfix"></div>
							</div>
						</ul>
					</li>
				</ul>
				<div class="search">
					<input type="text" name="s" class="textbox" value="Search" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Search';}"> <input type="submit" value="Subscribe" id="submit" name="submit">
					<div id="response"></div>
				</div>
				<div class="clearfix"></div>
			</div>
			<div class="clearfix"></div>
		</div>
	</div>

	<script src="js/location.js"></script>
	<script type="text/javascript">
	$(function() {
		$("#province").change(function() {
			var location = $('#province option:selected').text();
			document.getElementById('sheng').value = location;
		})

		$("#city").change(function() {
			var location = $('#city option:selected').text();
			document.getElementById('shi').value = location;
		})

		$("#county").change(function() {
			var location = $('#county option:selected').text();
			document.getElementById('qu').value = location;
		})

	})
	</script>
	<center >
		<br>
		<br>
		<br>
		<h3 id="msg">${msg }</h3>
		<br>
		<br>
		<br>
	</center>


	<script>
		var loc = beva.locationutil.attach('province', 'city', 'county',
				'address');

		// loc.setSelectedCodes([697,698]);
	</script>

	<br>
	<br>
	<br>
	<br>
	<br>
	<br>

	 
</body>
</html>
