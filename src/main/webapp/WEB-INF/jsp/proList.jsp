<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script>
$(function () {
	$("#pageNo").change(function () {
		var val = $("#pageNo").val();
		var maxPage = "${requestScope.page.maxPage }";
		if(checkPage(val,maxPage)){
			//3. 页面跳转
			toProsPage(val);
		}
	})
})

$(document).ready(function() {
		$.ajax({
			type : 'post',
			url : 'getTypes',
			success : function(data) {
				for (var i = 0; i < data.length; i++) {  
                    var option = document.createElement("option");  
                    $(option).val(data[i].catalogTypeOne);  
                    $(option).text(data[i].catalogTypeOne);  
                    $('#type').append(option);
                } 
			}
		});

	})
	
	function changepic() {
		var reads = new FileReader();
		var f = document.getElementById('i-file').files[0];
		reads.readAsDataURL(f);
		reads.onload = function(e) {
			document.getElementById('mainImg').src = this.result;
		};
	}

function toDeletePro(id) {
	var proId = id;
	swal("确定要删除此商品吗？", {
		  buttons: {
		    cancel: "取消",
		    
		    defeat: "确认",
		  },
		})
		.then((value) => {
		  switch (value) {
		    case "defeat":
		    	$.ajax({
					type : 'post',
					url : 'toDeletePro/' + proId,
					success : function(falg) {
						if (falg) {
							swal("删除成功！");
						} else {
							swal("删除失败！");
						}
					}
				});
		      break;
		 
		    default:
		      break;
		  }
		});
	var pageNo = ${requestScope.page.pageNo };
	toProsPage(pageNo);
}

function toUpdatePro(id) {
	var proId = id;
	$.ajax({
		type : 'post',
		url : 'getShowProductAdmin/' + proId,
		success : function(pro) {
			if(pro != null){
				$("#model_proName").val(pro.proName);
				$("#model_price").val(pro.price);
				$("#model_proDes").val(pro.proDes);
				$("#type").val(pro.catalogTypeOne);
				$("#mainImg").attr("src","images/"+pro.imgUrl);
			}
		}
	});
	
	//3、把员工的id传递给模态框的更新按钮
	$("#emp_update_btn").attr("edit-id",proId);
	
	$("#proUpdateModal").modal({
		backdrop:"static"
	});
	
}

//点击更新，更新商品信息
function updatePro() {
	var formData = new FormData(); //创建一个forData 
	if ($("#location").val() != "") {
		var imgEle = $("#i-file");
		var filepath = imgEle.val();
		//检查是否为图片
		if (!isImage(filepath)) {
			return false;
		}
		//检查文件大小，不能超过2M
		if (!checkFileSize(imgEle)) {
			return false;
		}

		formData.append('img', $('#i-file')[0].files[0]); //把file添加进去  name命名为img
	}

	formData.append('proId', $("#emp_update_btn").attr("edit-id"));
	formData.append('catalogTypeOne', $("#type").val());
	formData.append('proName', $("#model_proName").val());
	formData.append('price', $("#model_price").val());
	formData.append('proDes', $("#model_proDes").val());
	//swal(pro.proName + pro.price + pro.proDes);

	if (!checkMoney(formData.get('price'))
			|| !checkName(formData.get('proName'))
			|| !checkProDes(formData.get('proDes'))
			|| !checkType(formData.get('catalogTypeOne'))) {
		return;
	}
	$.ajax({
		async : false,
		url : "updateShowProductAdmin",
		type : "post",
		dataType : "json",
		cache : false,
		data : formData,
		processData : false,// 不处理数据
		contentType : false, // 不设置内容类型
		success : function(flag) {

			if (flag) {
				swal("修改成功！");
				$("#proUpdateModal").modal("hide");
				setTimeout(function(){
					var pageNo = ${requestScope.page.pageNo};
					toProsPage(pageNo);
				},500);
			} else {
				swal("修改失败！");
			}

		}
	})
}

$("#emp_update_btn").click(function(){
	updatePro();
});
</script>

<!-- 商品修改的模态框 -->
<div class="modal fade" id="proUpdateModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title">商品修改</h4>
			</div>
			<div class="modal-body">
				<form class="form-horizontal">
					<div class="form-group">
						<label class="col-sm-2 control-label">商品名称</label>
						<div class="col-sm-10">
							<input type="text" name="proName" class="form-control" id="model_proName">
							<span class="help-block"></span>
						</div>
					</div>

					<div class="form-group">
						<label class="col-sm-2 control-label">价格</label>
						<div class="col-sm-10">
							<input type="number" name="email" class="form-control" id="model_price">
							<span class="help-block"></span>
						</div>
					</div>

					<div class="form-group">
						<label class="col-sm-2 control-label">商品描述</label>
						<div class="col-sm-10">
							<!-- 
						<input type="text" name="proDes" class="form-control" id="model_proDes">
						 -->

							<textarea class="form-control" rows="10" name="proDes" id="model_proDes"></textarea>
							<span class="help-block"></span>
						</div>
					</div>

					<div class="form-group">
						<label class="col-sm-2 control-label">商品类别</label>
						<div class="col-sm-10">
							<!-- 
						<input type="text" name="proDes" class="form-control" id="model_proDes">
					-->
							<select class="form-control" id="type">
							</select> <input type="text" id="catalogTypeOne" hidden="true" value="衣服">
							<span class="help-block"></span>
						</div>
					</div>

					<div class="form-group">
						<label class="col-sm-2 control-label">选择图片</label>
						<div class="col-sm-10">
							<div class="input-group">
								<input id='location' class="form-control" disabled="true"> <label class="input-group-btn"> <input type="button" id="i-check" value="浏览图片" class="btn btn-primary" onclick="$('#i-file').click();">
								</label>
							</div>
						</div>
						<center>
							<p class="help-block">不超过2M的图片</p>
							<input type="file" name="file" id='i-file' accept=".jpg, .png" onchange="$('#location').val($('#i-file').val());changepic(this);" style="display: none">
							<img id="mainImg" src="" class="img-rounded" width="150" height="200">
						</center>
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				<button type="button" class="btn btn-primary" id="emp_update_btn">更新</button>
			</div>
		</div>
	</div>
</div>


<div class="shopping_cart">
	<c:set var="index" value="0"></c:set>
	<c:if test="${!empty items }">
		<c:forEach items="${items }" var="item">
			<div class="cart_box">
				<div class="message">
					<div class="list_img" style="border: none;">
						<img src="images/${item.imgUrl }" class="img-responsive" alt="" width="100" height="140" />
					</div>
					<div class="list_desc">
						<h3>
							<a href="tosingle/${item.proId}">${item.proName }</a>
						</h3>
						价格：
						<span class="actual"> ￥${item.price }</span>
						<br>
						类别：
						<span class="actual"> ${item.catalogTypeOne }</span>
						&nbsp;

					</div>
					<div class="login_button">
						<a href="#" onclick="toDeletePro(${item.proId })">删除商品</a>
					</div>

					<div class="check_button">
						<a href="#" onclick="toUpdatePro(${item.proId } )">修改信息</a>
					</div>

					<div class="clearfix"></div>
				</div>
			</div>
			<c:set var="index" value="${index + 1 }"></c:set>
		</c:forEach>
	</c:if>

	<br>
	<center>
		<br>
		共${requestScope.page.maxPage }页，当前第${requestScope.page.pageNo }页
		<br>
		<br>
		<a href="#" onclick="toProsPage(${requestScope.page.fristPage })">首页</a>
		&nbsp;&nbsp;
		<a href="#" onclick="toProsPage(${requestScope.page.beforePage })">上一页</a>
		&nbsp;&nbsp;
		<a href="#" onclick="toProsPage(${requestScope.page.nextPage })">下一页</a>
		&nbsp;&nbsp;
		<a href="#" onclick="toProsPage(${requestScope.page.maxPage })">末页</a>
		&nbsp;&nbsp;转到<input type="text" size="1" id="pageNo">页
		<br>
	</center>
</div>
