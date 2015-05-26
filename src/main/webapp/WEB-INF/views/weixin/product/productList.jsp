<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set value="产品中心"  var ="pageTitle"></c:set>
<c:set value="mainProdcuts"  var ="pageFootTab"></c:set>
<%@include file="/WEB-INF/views/weixin/include/header.jsp" %>

<div class="myBodyDiv" style="padding-bottom: 52px;border: 0px;">
 <style type="text/css">
    .textdiv{width: 100%;overflow: hidden;position: relative;}
 </style>
    <form action="${ctx }/weixin/product/productList" method="post" id="searchForm">
	    <div class="form-group"><div class="input-group">
		    <input class="form-control" name="search_LIKE_name" placeholder="输入关键字" value="${params_value.LIKE_name }" />
		    <span class="input-group-addon" onclick="$('#searchForm').submit();"><span class="glyphicon glyphicon-search"></span>搜索</span>
		    <span class="input-group-addon" onclick="$('#myModal').modal('show');"><span class="glyphicon glyphicon-filter"></span>筛选</span>
	    </div></div>
    </form>
   
   	<div class="row">
   	<c:forEach items="${page.content }" var="prod" varStatus="index">
 		<div class="col-xs-6 col-md-3"><div class="thumbnail">
    		<a href="${ctx }${baseMapper}productDetail?id=${prod.id}" >
    			<img src="${ctx }${prod.coverImg.samllPicture}" alt="无图片" style="width: 100%;"/>
	    	</a><div class="caption" style="font-size: small;padding: 1px;text-align: left;">
	    		<div class="textdiv"><c:choose><c:when test="${fn:length(prod.name) gt 10}">${fn:substring(prod.name,0,10) }..</c:when>
	    		<c:otherwise>${prod.name}</c:otherwise> </c:choose></div>
	    		<div class="textdiv">￥${prod.price }<a href="#" style="position: absolute;right: 0px;"> 销量<span class="badge">${prod.saleNum }</span></a></div>
	    	</div></div>
  		</div>
	   <c:if test="${index.count%4 eq 0 }"></div><div class="row"></c:if>
   	</c:forEach>
   	</div>

<c:if test="${pageUtil.pageNo gt 1 }">
   <span class="glyphicon glyphicon-chevron-left" style="width: 35px;height: 35px;font-size: 35px;color: #08c;
   position: fixed; top: 50%;z-index: 5;display: inline-block;left: 0px;" onclick="page(${pageUtil.pageNo-1})"></span>
</c:if>

<c:if test="${pageUtil.pageNo lt page.totalPages}">
   <span class="glyphicon glyphicon-chevron-right" style="width: 35px;height: 35px;font-size: 35px;color: #08c;
   position: fixed; top: 50%;z-index: 5;display: inline-block;right: 0px;" onclick="page(${pageUtil.pageNo+1})"></span>
</c:if>
 </div>
 <!-- filter -->
 

<div class="modal" id="myModal">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close btn" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">过滤</h4>
      </div>
      <div class="modal-body">
      <form id="pageForm" action="${ctx }/weixin/product/productList" method="post">
	      <div class="input-group" style="border: 1px solid #ccc;border-radius:4px;">
	      <label class="input-group-addon">类型:</label><c:set value=",${params_value['IN_type.id'] }," var="typeids"></c:set>
	        <c:forEach items="${prodTypes }" var="type">
	        	<label><input type="checkbox" value="${type.id }" name="search_IN_type.id" ${fn:contains(typeids,type.id) ?'checked="checked"':'' }/>${type.typeName }</label>
	        	&nbsp;&nbsp;
	        </c:forEach>
	      
	      </div><br><div class="input-group"  style="border: 1px solid #ccc;border-radius:4px;">
	        <label class="input-group-addon">排序:</label>
	      	<label><input type="checkbox" value="desc" name="orderby_createTime" ${empty pageUtil.orderby['createTime'] ?'':'checked="checked"' }/>发布日期</label>&nbsp;&nbsp;
	      	<label><input type="checkbox" value="desc" name="orderby_saleNum" ${empty pageUtil.orderby['saleNum'] ?'':'checked="checked"' }/>销量</label>&nbsp;&nbsp;
	      	<label><input type="checkbox" value="desc" name="orderby_price" ${empty pageUtil.orderby['price'] ?'':'checked="checked"' }/>价格</label>
	      </div>
	      <input name="search_LIKE_name" value="${params_value.LIKE_name }" type="hidden"/>
	      <input id="pageNo" name="pageNo" value="${pageUtil.pageNo }" type="hidden"/>
	      <br>
	        <button type="submit" class="btn btn-primary form-control">确定</button>
      </form>
	  </div>
    </div>
  </div>
</div>
<script type="text/javascript">
	function page(n){
		$("#pageNo").val(n);
		$("#pageForm").submit();
    	return false;
    }
</script>
<%@include file="/WEB-INF/views/weixin/include/footer.jsp" %>