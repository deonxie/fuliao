<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set value="我的求购列表"  var ="pageTitle"></c:set>
<c:set value="custmerCenter"  var ="pageFootTab"></c:set>
<%@include file="/WEB-INF/views/weixin/include/header.jsp" %>

<div class="myBodyDiv" style="padding-bottom: 52px;border: 0px;">
 <style type="text/css">
    td{font-size: xx-small;padding-left: 2px;}
    .divItem{width: 100%;overflow: hidden;position: relative;text-align: left;}
 </style>
    <form action="${ctx }${baseMapper }myreqProductList" method="post">
	    <div class="form-group"><div class="input-group">
		    <span class="input-group-addon" onclick="reqproduct()"><span class="glyphicon glyphicon-plus"></span>添加</span>
		    <input class="form-control" name="searchKey" />
		    <span class="input-group-addon" for="searchBtn">搜索</span>
		    <span class="input-group-addon" for="searchBtn"><span class="glyphicon glyphicon-filter"></span></span>
	    </div></div>
    </form>
   	<c:forEach items="${page.content }" var="reqProd">
   	<div class="row" style="border: 1px solid #CCC;border-bottom:0px;border-radius: 4px;margin-left: 0px;margin-right: 0px;">
  		<div class="col-xs-3 col-md-2" style="padding-right: 0px;padding-left:0px;">
	    	<a href="${ctx }${baseMapper}reqProductDetail?id=${reqProd.id}" class="thumbnail" style="margin-bottom: 0px;">
	      	<img src="${ctx }${reqProd.coverImg.samllPicture}" alt="暂无" style="height: 65px;">
	    	</a>
  		</div>
  		<div class="col-xs-9 col-md-10" style="position: relative;padding-right: 0px;">
  			<div class="divItem">
	  			<span>${reqProd.user.name }：<fmt:formatDate value="${reqProd.createTime }" pattern="yyyy-MM-dd"/></span>
	  			<a href="#" style="position: absolute;right: 0px;">${reqProd.type.typeName }</a> 
  			</div><div class="divItem">
	   			<c:choose>
	   			<c:when test="${fn:length(reqProd.name) gt 20}">${fn:substring(reqProd.name,0,20) }...</c:when>
	   			<c:otherwise>${reqProd.name}</c:otherwise> 
	   			</c:choose>
	   		</div><div class="divItem"><font color="#08c">
		   		<c:if test="${reqProd.status eq 0}">求购中</c:if>
		   		<c:if test="${reqProd.status eq 1}">已取消</c:if>
		   		<c:if test="${reqProd.status eq 2}">已完成</c:if>
		   		</font><font color="red">
		   			<c:if test="${reqProd.price eq 0}">￥待定</c:if>
		   			<c:if test="${reqProd.price gt 0}">￥${reqProd.price }</c:if></font>
	   			<span class="badge" style="position: absolute;right: 0px;">求购数${reqProd.requestNum }</span>
	   		</div>
  		</div>
	</div>
  	</c:forEach>
 </div>
   <script type="text/javascript">
	function reqproduct(){
		window.location.href= '${ctx }${baseMapper}myreqProductForm?id=';
	}
</script>
<%@include file="/WEB-INF/views/weixin/include/footer.jsp" %>