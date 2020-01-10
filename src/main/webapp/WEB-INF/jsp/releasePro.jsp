<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script type="text/javascript">
	function changepic() {
		var reads = new FileReader();
		var f = document.getElementById('i-file').files[0];
		reads.readAsDataURL(f);
		reads.onload = function(e) {
			document.getElementById('mainImg').src = this.result;
		};
	}

	$(function() {
		$("#type").change(function() {
			var type = $('#type option:selected').text();
			document.getElementById('catalogTypeOne').value = type;
		})

	})

	function release() {
		
		var formData = new FormData();  //创建一个forData 
	    formData.append('img', $('#i-file')[0].files[0]); //把file添加进去  name命名为img
	    formData.append('catalogTypeOne', $("#catalogTypeOne").val());
	    formData.append('proName', $("#model_proName").val());
	    formData.append('price', $("#model_price").val());
	    formData.append('proDes', $("#model_proDes").val());
		//swal(pro.proName + pro.price + pro.proDes);
	    $.ajax({
            url: "releasePro",
            type: "post",
            dataType: "json",
            cache: false,
            data: formData,
            processData: false,// 不处理数据
            contentType: false, // 不设置内容类型
            success: function (flag) {
            	if(flag){
            		swal("发布成功！");
            		//clearbtn();
            	}else{
            		swal("发布失败！");
            	}
                
                
            }
        })
	}

	function clearbtn() {
		reset_data("#releaseForm");
		document.getElementById('mainImg').src = "";

	}

	function reset_data(ele) {//listForm为form的id
		$(":input", ele).not(":button", ":reset", ":hidden", ":submit").val("")
				.removeAttr("checked").removeAttr("selected");
	}
</script>

<br>
<br>

<center>
	<form class="form-horizontal" id="releaseForm" enctype="multipart/form-data" style="width: 600px">
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
					<option></option>
					<c:forEach items="${types }" var="type">
						<option>${type.catalogTypeOne }</option>
					</c:forEach>
					

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

			<p class="help-block">不超过1M的jpg或png图片</p>
			<input type="file" name="file" id='i-file' accept=".jpg, .png" onchange="$('#location').val($('#i-file').val());changepic(this);" style="display: none">
			<img id="mainImg" src="" class="img-rounded" width="300" height="400">
		</div>
		<br>
		<br>
		<button type="button" class="btn btn-default btn-lg" id="clearBtn" onclick="clearbtn()">清空</button>
		&nbsp;&nbsp;&nbsp;&nbsp;
		<button type="button" class="btn btn-success btn-lg" id="releaseBtn" onclick="release()">发布</button>
	</form>
</center>
