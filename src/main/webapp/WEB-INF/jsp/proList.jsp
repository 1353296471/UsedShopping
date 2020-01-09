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
			}
		}
	});
	
	//3、把员工的id传递给模态框的更新按钮
	$("#emp_update_btn").attr("edit-id",proId);
	
	$("#proUpdateModal").modal({
		backdrop:"static"
	});
	
}

//点击更新，更新员工信息
$("#emp_update_btn").click(function(){
	
	
	
	var pro = {
			 proId : $("#emp_update_btn").attr("edit-id"),
			 proName : $("#model_proName").val(),
			 price : $("#model_price").val(),
			 proDes : $("#model_proDes").val()
		};
	
	
	if(!checkMoney(pro.price) || !checkName(pro.proName)){
		return;
	}
	
		$.ajax({
			async : false,
			type : 'post',
			url : 'updateShowProductAdmin',
			contentType : "application/json;charset=utf-8",
			data : JSON.stringify(pro),
			success : function(msg) {
				$("#proUpdateModal").modal("hide");
				
				if (!msg) {
					swal("修改失败！", {
						  buttons: { 
							    defeat: "确认",
							  },
							}).then((value) => {
						  switch (value) {
						    case "defeat":
						    	//2、回到本页面
								var pageNo = ${requestScope.page.pageNo };
								toProsPage(pageNo);
						      break;
						 
						    default:
						      break;
						  }
						});
				} else {
					swal("修改成功！", {
						  buttons: { 
							    defeat: "确认",
							  },
							}).then((value) => {
						  switch (value) {
						    case "defeat":
						    	//2、回到本页面
								var pageNo = ${requestScope.page.pageNo };
								toProsPage(pageNo);
						      break;
						 
						    default:
						      break;
						  }
						});
				}
			}
		});
		
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
						<img src="images/${item.imgUrl }" class="img-responsive" alt="" width="100px" height="140px" />
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
