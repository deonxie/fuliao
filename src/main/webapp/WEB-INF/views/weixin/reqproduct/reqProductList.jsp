<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set value="求购中心"  var ="pageTitle"></c:set>
<c:set value="mainRequests"  var ="pageFootTab"></c:set>
<%@include file="/WEB-INF/views/weixin/include/header.jsp" %>

<div class="myBodyDiv" style="padding-bottom: 52px;border: 0px;">
 <style type="text/css">
    td{font-size: xx-small;padding-left: 2px;}
    .divItem{width: 100%;overflow: hidden;position: relative;text-align: left;}
 </style>
    <form action="${ctx }/weixin/reqproduct/reqProductList" method="post" id="searchForm">
	    <div class="form-group"><div class="input-group">
		    <input class="form-control" name="search_LIKE_name" placeholder="输入关键字" value="${params_value.LIKE_name }" />
		    <span class="input-group-addon" onclick="$('#searchForm').submit();"><span class="glyphicon glyphicon-search"></span>搜索</span>
		    <span class="input-group-addon" onclick="$('#myModal').modal('show');"><span class="glyphicon glyphicon-filter"></span>筛选</span>
	    </div></div>
    </form>
   
   	<c:forEach items="${page.content }" var="reqProd"><a href="${ctx }${baseMapper}reqProductDetail?id=${reqProd.id}">
   	<div class="row" style="border: 1px solid #CCC;border-bottom:0px;border-radius: 4px;margin-left: 0px;margin-right: 0px;">
  		<div class="col-xs-3 col-md-2" style="padding-right: 0px;padding-left:0px;">
	    	<div class="thumbnail" style="margin-bottom: 0px;">
	      	<img src="${ctx }${reqProd.coverImg.samllPicture}" alt="无图片" style="height: 80px;">
	    	</div>
  		</div>
  		<div class="col-xs-9 col-md-10" style="position: relative;padding-right: 0px;">
  			<div class="divItem">
	  			<span>${reqProd.user.name }：<fmt:formatDate value="${reqProd.createTime }" pattern="MM-dd HH:mm"/></span>
	  			<label style="position: absolute;right: 0px;">${reqProd.type.typeName }</label>
  			</div><div class="divItem">
	   			<c:choose>
	   			<c:when test="${fn:length(reqProd.name) gt 15}">${fn:substring(reqProd.name,0,15) }...</c:when>
	   			<c:otherwise>${reqProd.name}</c:otherwise> 
	   			</c:choose>
	   		</div><div class="divItem">
	   			材质：${reqProd.texture }
	   		</div><div class="divItem"><c:if test="${reqProd.price eq 0 }"><font color="red">待定</font>
	   		</c:if><c:if test="${reqProd.price gt 0 }"><font color="red">￥${reqProd.price }</font></c:if>
	   			<span class="badge" style="position: absolute;right: 0px;">求购数${reqProd.requestNum }</span>
	   		</div>
  		</div>
	</div></a>
  	</c:forEach>
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
      <form id="pageForm" action="${ctx }/weixin/reqproduct/reqProductList" method="post">
	      <div class="input-group" style="border: 1px solid #ccc;border-radius:4px;">
	      <label class="input-group-addon">类型:</label><c:set value=",${params_value['IN_type.id'] }," var="typeids"></c:set>
	        <c:forEach items="${prodTypes }" var="type">
	        	<label><input type="checkbox" value="${type.id }" name="search_IN_type.id" ${fn:contains(typeids,type.id) ?'checked="checked"':'' }/>${type.typeName }</label>
	       		&nbsp;&nbsp;
	        </c:forEach>
	      
	      </div><br><div class="input-group"  style="border: 1px solid #ccc;border-radius:4px;">
	        <label class="input-group-addon">排序:</label>
	      	<label><input type="checkbox" value="desc" name="orderby_createTime" ${empty pageUtil.orderby['createTime'] ?'':'checked="checked"' }/>求购日期</label>&nbsp;&nbsp;
	      	<label><input type="checkbox" value="desc" name="orderby_requestNum" ${empty pageUtil.orderby['requestNum'] ?'':'checked="checked"' }/>求购数量</label>&nbsp;&nbsp;
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