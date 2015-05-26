<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set value="商铺详情"  var ="pageTitle"></c:set>
<c:set value="mainProdcuts"  var ="pageFootTab"></c:set>
<%@include file="/WEB-INF/views/weixin/include/header.jsp" %>
<style>
.input-group{width: 100%;border-bottom: 1px solid #CCC;text-align: left; font-size: medium;padding:2px;}
.input-group > label{color:#08c;padding-right: 10px;}
</style>
<div class="myBodyDiv" >
    	<div class="form-group">
    		<c:choose><c:when test="${empty entity.logo.relativePath }">
			  		<c:set value="${ctxStatic}/images/logo_sys.png" var="logoImg"></c:set>
			  	</c:when><c:otherwise>
			  		<c:set value="${ctx }${entity.logo.relativePath}" var="logoImg"></c:set>
			  </c:otherwise></c:choose>
    		<img alt="" src="${logoImg}" style="width: 100%;">
    	</div><div class="input-group">
    		<label>店铺:</label>${entity.name }
    	</div><div class="input-group" >
    		<label >主营:</label>${entity.mainBusines }
    	</div><div class="input-group">
    		<label>简介:</label>${entity.descript }
    	</div><div class="input-group">
    		<span class="glyphicon glyphicon-earphone" ></span>:${entity.telNum }
    		<span class="badge" style="position: absolute;right: 5px;" onclick="favtShop('${entity.id}')">
		<span class="glyphicon glyphicon-star-empty" ></span>收藏 </span>
    	</div><div class="input-group">
    		<label>地址:</label>${entity.address }
    	</div>
    	<c:choose>
   			<c:when test="${loginUser.id eq entity.user.id }">
   			<table class="table"><tr>
			<td align="center">
			<a href="${ctx }/weixin/shops/" class="btn btn-primary">修改</a>
			</td>
   			</tr></table>
   			</c:when>
   			<c:otherwise>
    	<table style="margin-bottom: 52px;width: 100%;text-align: center;">
    	<tr><td colspan="2" align="right" style="padding: 7px;"><a>查看更多》</a></td></tr>
    	<tr>
    		<c:forEach items="${products }" var="prod" varStatus="index">
    		<td width="50%"><div class="thumbnail">
    			<a href="${ctx }/weixin/product/productDetail?id=${prod.id}" >
    			<c:choose>
    			<c:when test="${empty prod.coverImg.relativePath }">
    				<img src="${ctxStatic }/images/logo_sys.png" alt="暂无" style="height:80px;width: 100%;"></c:when>
    				<c:otherwise><img src="${ctx }${ prod.coverImg.samllPicture}" alt="暂无" style="height:80px;width: 100%;"></c:otherwise>
    			</c:choose>
	    		</a><div class="caption" style="font-size: small;padding: 1px;text-align: left;">
	    		<div class="textdiv"><c:choose><c:when test="${fn:length(prod.name) gt 8}">${fn:substring(prod.name,0,8) }.</c:when>
	    		<c:otherwise>${prod.name}</c:otherwise> </c:choose></div>
	    		<div class="textdiv">￥${prod.price }<a href="#" style="position: absolute;right: 0px;"> 销量<span class="badge">${prod.saleNum }</span></a></div>
	    	</div></td>
	   		<c:if test="${index.count % 2 eq 0 }"></tr><tr></c:if>
    		</c:forEach>
    	</tr>
    	
    	</table>
   			</c:otherwise>
   			</c:choose>
 <script type="text/javascript">
	function favtShop(id){
		$.ajax({url:'${ctx}/weixin/favorite/shop?id='+id,type:'get',dataType:'json',
			success:function(data){
				if(data)alert('收藏成功');
				else alert('收藏失败！');
			},error:function(data){alert('收藏失败！');}});
	}
</script> 
<div style="height: 50px;"></div>  	
</div>
<%@include file="/WEB-INF/views/weixin/include/footer.jsp" %>