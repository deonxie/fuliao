<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set value="审核商铺"  var ="pageTitle"></c:set>
<c:set value="systemCenter"  var ="pageFootTab"></c:set>
<%@include file="/WEB-INF/views/weixin/include/header.jsp" %>
<style>
label{color:#08c;padding: 2px;}
</style>

<div class="myBodyDiv" style="text-align: left;">
	<div class="form-control" style="height: auto;">
  		<img src="${ctx }${entity.logo.relativePath}" alt="单击上传图片" style="width:100%;"/>
	</div>
 	<div class="form-control" style="height: auto;">
 		<label>店铺名称:</label>${entity.name }</div>
    <div class="form-control" style="height: auto;">
    	<label>主营业务:</label>${entity.mainBusines }</div>
    <div class="form-control" style="height: auto;">
    	<label>店铺简介:</label>${entity.descript }</div>
    <div class="form-control" style="height: auto;">
    	<label>店铺地址:</label>${entity.address }</div>
    <div class="form-control" style="height: auto;">
    	<label>联系电话:</label>${entity.telNum }</div>
    <c:if test="${!empty entity.verify1.relativePath }">
    <div class="form-control" style="height: auto;">
    	<img src="${ctx}${entity.verify1.relativePath}" alt="认证图片" width="300"/>
	</div>
    </c:if><c:if test="${!empty entity.verify2.relativePath }">
    <div class="form-control" style="height: auto;">
    	<img src="${ctx}${entity.verify2.relativePath}" alt="认证图片" width="300"/>
	</div>
    </c:if><c:if test="${!empty entity.verify3.relativePath }">
    <div class="form-control" style="height: auto;">
    	<img src="${ctx}${entity.verify3.relativePath}" alt="认证图片" width="300"/>
	</div>
    </c:if><br>
   <form action="${ctx }${baseMapper}passVerify" method="post" >
    	<input name="id" value="${entity.id }" type="hidden"/>
    	<button type="submit" class="btn btn-primary form-control">通过</button>
	</form>
	<form action="${ctx }${baseMapper}failVerify" method="post" >
    	<input name="id" value="${entity.id }" type="hidden"/>
    	<div class="form-group">
    		<textarea name="reason" class="required form-control" placeholder="请填不通过的原因"></textarea>
    	</div>
    	<button type="submit" class="btn btn-danger form-control">不通过</button>
	</form>
	<div style="height:50px;"></div>
</div>
<%@include file="/WEB-INF/views/weixin/include/footer.jsp" %>