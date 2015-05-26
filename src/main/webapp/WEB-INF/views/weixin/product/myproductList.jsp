<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set value="我的产品列表"  var ="pageTitle"></c:set>
<c:set value="custmerCenter"  var ="pageFootTab"></c:set>
<%@include file="/WEB-INF/views/weixin/include/header.jsp" %>

<div class="myBodyDiv" style="padding-bottom: 52px;border: 0px;">
 <style type="text/css">
    .textdiv{width: 100%;overflow: hidden;position: relative;text-align: left;}
 </style>
    <form action="${ctx }/weixin/product/myproductList" method="post">
	    <div class="form-group" style="margin-bottom: 1px;"><div class="input-group">
		    <span class="input-group-addon" onclick="addproduct()"><span class="glyphicon glyphicon-plus"></span>添加</span>
		    <input class="form-control" name="searchKey" />
		    <span class="input-group-addon" for="searchBtn">搜索</span>
		    <span class="input-group-addon" for="searchBtn"><span class="glyphicon glyphicon-filter"></span></span>
	    </div></div>
    </form>
   	<c:forEach items="${page.content }" var="prod" varStatus="index">
   	 <div class="row" style="border: 1px solid #CCC;border-bottom:0px;border-radius: 4px;margin-left: 0px;margin-right: 0px;">
  		<div class="col-xs-3 col-md-2" style="padding-right: 0px;padding-left:0px;">
    	<a href="${ctx }/weixin/product/productDetail?id=${prod.id}" class="thumbnail" style="margin-bottom: 0px;">
      	<img src="${ctx }${prod.coverImg.samllPicture}" alt="无图片" style="height: 70px;">
    	</a>
  		</div>
  		<div class="col-xs-9 col-md-10" style="position: relative;padding-right: 0px;">
  			<div class="textdiv">
	  			<span><fmt:formatDate value="${prod.createTime }" pattern="yyyy-MM-dd"/> </span>
	  			<a href="#" style="position: absolute;right: 0px;">${prod.type.typeName }</a> 
  			</div>
	   		<div class="textdiv"><c:choose><c:when test="${fn:length(prod.name) gt 20}">${fn:substring(prod.name,0,20) }...</c:when>
	   		<c:otherwise>${prod.name}</c:otherwise> </c:choose></div>
	   		<div class="textdiv">￥${prod.price }<span class="badge" style="position: absolute;right: 0px;">销量${prod.saleNum }</span></div>
  		</div>
	</div>
   	</c:forEach>
 </div>
<script type="text/javascript">
	function addproduct(){
		window.location.href= '${ctx }${baseMapper}myproductForm?id=';
	}
</script>
<%@include file="/WEB-INF/views/weixin/include/footer.jsp" %>