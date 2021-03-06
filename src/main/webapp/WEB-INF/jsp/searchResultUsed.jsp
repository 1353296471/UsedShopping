<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<html>
<head>
<title>搜索结果</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="Flatro Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template, 
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />
<script type="application/x-javascript">
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	 addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } 















</script>

<%
	pageContext.setAttribute("APP_PATH", request.getContextPath());
%>
<!-- web路径：
不以/开始的相对路径，找资源，以当前资源的路径为基准，经常容易出问题。
以/开始的相对路径，找资源，以服务器的路径为标准(http://localhost:3306)；需要加上项目名
		http://localhost:3306/crud
 -->

<link href="/css/bootstrap.css" rel='stylesheet' type='text/css' />
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<!-- Custom Theme files -->
<link href="/css/style.css" rel='stylesheet' type='text/css' />
<!-- Custom Theme files -->
<!--webfont-->
<link href='#css?family=Lato:100,200,300,400,500,600,700,800,900' rel='stylesheet' type='text/css'>
<script type="text/javascript" src="/js/jquery-1.11.1.min.js"></script>
<!-- start menu -->
<link href="/css/megamenu.css" rel="stylesheet" type="text/css" media="all" />
<script type="text/javascript" src="/js/megamenu.js"></script>
<script type="text/javascript" src="/js/myFunction.js"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>

<link href="/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
<script src="/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<script>
	$(document).ready(function() {
		$(".megamenu").megamenu();
	});
</script>
<script>
	$(document).ready(function(c) {
		$('.alert-close').on('click', function(c) {
			$('.message').fadeOut('slow', function(c) {
				$('.message').remove();
			});
		});
	});
</script>
<script>
	$(document).ready(function(c) {
		$('.alert-close1').on('click', function(c) {
			$('.message1').fadeOut('slow', function(c) {
				$('.message1').remove();
			});
		});
	});
</script>

<script>
	$(document).ready(function() {
		init();
	})
</script>

<script type="text/javascript">
		$(function () {
			$("#pageNo").change(function () {
				
				var val = $("#pageNo").val();
				var maxPage = "${requestScope.page.maxPage }";
				if(checkPage(val,maxPage)){
					//3. 页面跳转
					toSearchPage(val);
				}
			})
		})
			
		
		</script>
</head>
<body>
	<div class="header_top">
		<div class="container">

			<div class="cssmenu">
				<ul>
					<li class="active">
						<a href="/login.html" id="account">请登录</a>
					</li>
				</ul>
			</div>
		</div>
	</div>
	<div class="wrap-box"></div>
	<div class="header_bottom">
		<div class="container">
			<div class="col-xs-9 header-bottom-left">
				<div class="col-xs-2 logo">
					<h1>
						<a href="/index.html">
							<span>Buy</span>
							shop
						</a>
					</h1>
				</div>
				<div class="col-xs-7 menu">
					<ul class="megamenu skyblue">
						<!-- <li class="active grid"> -->
						<li class="grid">
							<a class="color1" id="meun" href="/search.html?searchdata=男">男装</a>
						</li>
						<li class="grid">
							<a class="color2" id="meun" href="/search.html?searchdata=女">女装</a>
						</li>

					</ul>
				</div>
			</div>
			<div class="col-xs-3 header-bottom-right">
				<ul class="icon1 sub-icon1 profile_img">
					<li>
						<a class="active-icon c1" onmouseover="toShowShopCarItem()" href="/#"> </a>
						<ul class="sub-icon1 list" id="shopCarItemListDiv">
							<div class="login_buttons">
								<div class="login_button">
									<a href="/login.html">登录</a>
								</div>
								<div class="clearfix"></div>
							</div>
						</ul>
					</li>
				</ul>
				<div class="search">
					<input id="Sea" type="text" name="s" class="textbox" value="搜索" onfocus="this.value = '';"> <input type="submit" value="搜索" id="submit" name="submit" onclick="search()">
					<div id="response"></div>
				</div>
				<div class="clearfix"></div>
			</div>
			<div class="clearfix"></div>
		</div>
	</div>

	<div class="content_top">
		<h3 class="m_1">搜索结果</h3>
		<div class="container">
			<div class="box_1" id="serPro">

				<!-- 搜索结果 -->
				<div class="col-md-12">
					<c:forEach items="${requestScope.searchPro }" var="_search">
						<div class="col-md-3" style="height: 450px;">
							<div class="shop-holder">
								<div class="product-img">
									<a href="/tosingle/${_search.pro.id }">
										<img width="225px" height="265px" src="/images/${_search.mainImg.imgUrl }" class="img-responsive" alt="item4">
									</a>
									<a href="" class="hidden"></a>
								</div>
							</div>
							<div class="shop-content" style="height: 120px;">
								<h3>
									<a href="/tosingle/${_search.pro.id }">${_search.pro.proName }</a>
								</h3>
								<span>
									<span class="amount">¥${_search.pro.price }</span>
								</span>

							</div>

						</div>





					</c:forEach>

					<br>



				</div>
				<div>
					<center>
						<br>
						共${requestScope.page.maxPage }页，当前第${requestScope.page.pageNo }页
						<br>
						<br>
						<a href="#" onclick="toSearchPage(${requestScope.page.fristPage })">首页</a>
						&nbsp;&nbsp;
						<a href="#" onclick="toSearchPage(${requestScope.page.beforePage })">上一页</a>
						&nbsp;&nbsp;
						<a href="#" onclick="toSearchPage(${requestScope.page.nextPage })">下一页</a>
						&nbsp;&nbsp;
						<a href="#" onclick="toSearchPage(${requestScope.page.maxPage })">末页</a>
						&nbsp;&nbsp;转到<input type="text" size="1" id="pageNo">页
						<br>
					</center>
				</div>
				<div class="clearfix"></div>
				<div class="clearfix"></div>
				<div class="clearfix"></div>
			</div>
		</div>




	</div>

	<!-- 
<div class="col-sm-6 col-md-4" style="width: 25%">
							<div class="thumbnail">
								<a href="/tosingle/${_search.pro.id }">
									<img src="/images/${_search.mainImg.imgUrl }" width="150" height="200">
								</a>
								<div class="caption">
									<a href="/tosingle/${_search.pro.id }">${_search.pro.proName }</a>
									<p>¥${_search.pro.price }</p>
								</div>
							</div>
						</div>
 -->

</body>
</html>

