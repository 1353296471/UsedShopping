<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix='c' %>    
    
  <c:forEach items="${requestScope.imgList }" var="_img">  
              <div class="col-md-3">
          				<div class="content_box"><a href="tosingle/${_img.proId }">
			   	          <img src="images/${_img.imgUrl }" class="img-responsive" alt="">
				   	   </a>
				         </div>
				    <h><a href="single.html">${_img.proName }</a></h>
				    <h4>￥${_img.price }</h4>
			    </div>
			        
</c:forEach>