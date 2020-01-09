<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML>
<html id="html">
<head>
<title>商品详情</title>
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
<script src="js/jquery.easydropdown.js"></script>
<link href="css/megamenu.css" rel="stylesheet" type="text/css" media="all" />
<script type="text/javascript" src="js/megamenu.js"></script>
<script>
	$(document).ready(function() {
		$(".megamenu").megamenu();
	});
</script>
<!-- 
// 移除<link rel="stylesheet" href="css/etalage.css"> 去掉放大功能
<link rel="stylesheet" href="css/etalage.css">
 -->

<!-- 引入全局函数myFunction.js -->
<script type="text/javascript" src="js/myFunction.js"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>

<link href="bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
<script src="bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>





<script src="js/jquery.etalage.min.js"></script>
<script>
	jQuery(document)
			.ready(
					function($) {

						$('#etalage')
								.etalage(
										{
											thumb_image_width : 300,
											thumb_image_height : 400,
											source_image_width : 900,
											source_image_height : 1200,
											show_hint : true,
											click_callback : function(
													image_anchor, instance_id) {
												swal('Callback example:\nYou clicked on an image with the anchor: "'
														+ image_anchor
														+ '"\n(in Etalage instance: "'
														+ instance_id + '")');
											}
										});

					});
</script>
<!--initiate accordion-->
<script type="text/javascript">
	$(function() {

		var menu_ul = $('.menu_drop > li > ul'), menu_a = $('.menu_drop > li > a');

		menu_ul.hide();

		menu_a.click(function(e) {
			e.preventDefault();
			if (!$(this).hasClass('active')) {
				menu_a.removeClass('active');
				menu_ul.filter(':visible').slideUp('normal');
				$(this).addClass('active').next().stop(true, true).slideDown(
						'normal');
			} else {
				$(this).removeClass('active');
				$(this).next().stop(true, true).slideUp('normal');
			}
		});

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
<script type="text/javascript">
	function toAddShopCarSingle() {
		var sum = $("#countDiv").val();
		var colorType = $('#colorDiv option:selected').text();
		var sizeType = $('#sizeDiv option:selected').text();
		//swal(colorType);
		//swal(sizeType);
		var warehouseId = 0;
		$.ajax({
			async : false,
			type : 'post',
			url : 'getWareIdServlet',
			data : {
				colorType : colorType,
				sizeType : sizeType
			},
			success : function(wareId) {
				warehouseId = wareId;
			}
		});
		if (warehouseId == 0) {
			swal("请先选择好商品的属性！");
			return;
		}
		toAddShopCar(warehouseId, sum);
	}

	function tobuySingle() {
		if (isLogin()) {
			var warehouseId = 0;
			var sum = $("#countDiv").val();
			var colorType = $('#colorDiv option:selected').text();
			var sizeType = $('#sizeDiv option:selected').text();
			$.ajax({
				async : false,
				type : 'post',
				url : 'getWareIdServlet',
				data : {
					colorType : colorType,
					sizeType : sizeType
				},
				success : function(wareId) {
					warehouseId = wareId;
				}
			});

			if (warehouseId == 0) {
				swal("请先选择好商品的属性！");
				return;
			}

			$.ajax({
				type : 'post',
				url : 'toSinglePay/' + warehouseId + '/' + sum,
				success : function(msg) {

					$('#singPay').html(msg);
				}
			});
		} else {
			swal("请先登录！");
			window.location.href = "login.html";
		}
	}

	$(document).ready(function() {
		init();
	});

	$(document).ready(function() {
		$.ajax({
			type : 'post',
			url : 'productDesServlet',
			success : function(msg) {
				$('#proDes').html(msg);
			}
		});
	});

	$(document).ready(function() {
		$.ajax({
			type : 'post',
			url : 'proCommentServlet',
			success : function(msg) {
				$('#proComment').html(msg);
			}
		});
	});

	$(document).ready(function() {
		$.ajax({
			type : 'post',
			url : 'productServlet',
			success : function(msg) {
				$("#proDetail").html(msg);
			}
		});
	});

	$(document).ready(function() {
		$.ajax({
			type : 'post',
			url : 'commentCountServlet',
			success : function(msg) {
				$("#commentCount").html(msg);
			}
		});
	});

	$(document).ready(function() {
		$.ajax({
			type : 'post',
			url : 'wareHouseServlet',
			success : function(msg) {
				$("#sizeDiv").html(msg);
			}
		});
	});

	$(document).ready(function() {
		$.ajax({
			type : 'post',
			url : 'wareHouseColorServlet',
			success : function(msg) {
				$("#colorDiv").html(msg);
			}
		});
	});

	$(document).ready(function() {
		$.ajax({
			type : 'post',
			url : 'relatedImgServlet',
			success : function(msg) {
				$("#imagDiv").html(msg);
			}
		});
	});

	$(document).ready(function() {
		$.ajax({
			type : 'post',
			url : 'proImgServlet',
			success : function(msg) {

				$(".etalage_thumb_image").attr("src", "images/" + msg);
				//$(".etalage_source_image").attr("src","images/"+msg);

			}
		});
	});

	$(function() {
		$("#sizeDiv,#colorDiv").change(function() {
			var sizeType = $('#sizeDiv option:selected').text();

			var colorType = $('#colorDiv option:selected').text();
			$.ajax({
				type : 'post',
				url : 'warehouseNumServlet',
				data : {
					sizeType : sizeType,
					colorType : colorType
				},
				success : function(msg) {
					$("#wareCount").html(msg);
				}
			});

		});

	});
</script>
</head>
<body>

	<div class="header_top">
		<div class="container">

			<div class="cssmenu">
				<ul>
					<li class="active">
						<a href="login.html" id="account">请登录</a>
					</li>
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
					<input id="Sea" type="text" name="s" class="textbox" value="搜索" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Search';}"> <input type="submit" value="搜索" id="submit" name="submit" onclick="search()">
					<div id="response"></div>
				</div>
				<div class="clearfix"></div>
			</div>
			<div class="clearfix"></div>
		</div>
	</div>
	<div id="singPay">
		<div class="single_top">
			<div class="container">
				<div class="single_grid">
					<div class="grid images_3_of_2" id="proImagDiv">
						<!-- 商品图片信息 -->


						<ul id="etalage">
							<li>
								<a href="#">
									<img class="etalage_thumb_image" src="#" class="img-responsive" width="300px" hight="400px" />
									<!-- 
			<img class="etalage_source_image" src="#" class="img-responsive" title="" />
			 -->
								</a>
							</li>
						</ul>
						<div class="clearfix"></div>




					</div>
					<div class="desc1 span_3_of_2" id='proDetail'>
						<!-- 商品名称和价格介绍 -->


					</div>
					<!--  class="dropdown"-->
					<div class="desc1 span_3_of_2">
						<div class="dropdown_top">
							<div class="dropdown_left">
								<div>
									
									商品描述
									
								</div>
							</div>
							<div class="dropdown_left"></div>
							<div class="clearfix"></div>
						</div>
						<a href="#" id="tobuy" onclick="tobuySingle()" class="btn1 btn2 btn-primary1">
							<span>立即购买</span>
						</a>
						<a href="#" id="toAddShopCar" onclick="toAddShopCarSingle()" class="btn1 btn2 btn-primary1">
							<span>加入购物车</span>
						</a>
					</div>

					<div class="clearfix"></div>
				</div>

				<div class="single_social_top"></div>
				<ul class="menu_drop">
					<li class="item1">
						
					</li>
					<li class="item2">
						<a href="#">
							<img src="images/product_arrow.png">
							评价
							<span id="commentCount"> </span>
						</a>
						<ul id='proComment'>


						</ul>
					</li>

				</ul>

			</div>
			<h3 class="m_2">相关商品</h3>
			<div class="container">
				<div class="box_3" id="imagDiv">
					<!-- 商品详情页的相似商品列表 -->




					<div class="clearfix"></div>
				</div>
			</div>
		</div>

	</div>

</body>
</html>


