<%@page import="fuliao.fuliaozhijia.core.util.SystemProperties"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set value="产品详情"  var ="pageTitle"></c:set>
<c:set value="mainProdcuts"  var ="pageFootTab"></c:set>
<%@include file="/WEB-INF/views/weixin/include/header.jsp" %>
<style type="text/css">
.lineDiv{font-size: medium;text-align: left;width: 100%;padding:3px;
	border-top: 1px solid #CCC;position: relative;}
.lineDiv > label{color:#08c;font-size: medium;}
</style>
<div class="myBodyDiv" style="margin-bottom: 52px;">
    <div id="myCarousel" class="carousel slide" > 
	<c:choose>
      <c:when test="${empty entity.coverImg.relativePath }">
		 <ol class="carousel-indicators">
			<li data-target="#myCarousel" data-slide-to="0" class="active"></li> 
		 </ol> 
		 <div class="carousel-inner" style="width: 100%;">
			<div class="active item"><img src="${ctxStatic }/images/logo_sys.png" /></div>
		 </div>
      </c:when><c:otherwise>
      	<ol class="carousel-indicators">
			<c:set value="0" var="dataslideto"></c:set>
			<c:if test="${!empty entity.image1.relativePath }">
				<li data-target="#myCarousel" data-slide-to="${dataslideto}" ${dataslideto eq 0?'class="active"':'' }></li>
				<c:set value="${dataslideto+1 }" var="dataslideto"></c:set>
			</c:if>
			<c:if test="${!empty entity.image2.relativePath }">
				<li data-target="#myCarousel" data-slide-to="${dataslideto}" ${dataslideto eq 0?'class="active"':'' }></li>
				<c:set value="${dataslideto+1 }" var="dataslideto"></c:set>
			</c:if>
			<c:if test="${!empty entity.image3.relativePath }">
				<li data-target="#myCarousel" data-slide-to="${dataslideto}" ${dataslideto eq 0?'class="active"':'' }></li>
				<c:set value="${dataslideto+1 }" var="dataslideto"></c:set>
			</c:if>
	   </ol>
	   <div class="carousel-inner" style="width: 100%;">
			<c:set value="0" var="imgslideto"></c:set>
			<c:if test="${!empty entity.image1.relativePath }">
				<div class="${imgslideto eq 0?'active ':'' }item"><c:set value="${imgslideto+1 }" var="imgslideto"></c:set>
				<img src="${ctx }${entity.image1.relativePath}" style="width:100%;" onclick="previewIMg('${entity.image1.largerPicture}')"/></div>
			</c:if>
			<c:if test="${!empty entity.image2.relativePath }">
				<div class="${imgslideto eq 0?'active ':'' }item"><c:set value="${imgslideto+1 }" var="imgslideto"></c:set>
				<img src="${ctx }${entity.image2.relativePath}" style="width:100%;" onclick="previewIMg('${entity.image2.largerPicture}')"/></div>
			</c:if><c:if test="${!empty entity.image3.relativePath }">
				<div class="${imgslideto eq 0?'active ':'' }item"><c:set value="${imgslideto+1 }" var="imgslideto"></c:set>
				<img src="${ctx }${entity.image3.relativePath}" style="width:100%;" onclick="previewIMg('${entity.image3.largerPicture}')"/></div>
			</c:if>
		</div>
      </c:otherwise>
    </c:choose>
      <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
	    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
	    <span class="sr-only">Previous</span>
	  </a>
	  <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
	    <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
	    <span class="sr-only">Next</span>
	  </a>
    </div> 
    
	<div class="lineDiv">
		<label>类型：</label> ${entity.type.typeName } 
		<span class="badge" style="position: absolute;right: 5px;" onclick="favtProduct('${entity.id}')">
		<span class="glyphicon glyphicon-star-empty" ></span>收藏 </span>
	</div>
	<div class="lineDiv">
		<label>商铺：</label><a href="${ctx }/weixin/shops/shopDetail?id=${entity.shops.id }"> ${entity.shops.name }</a>
		<shiro:hasPermission name="shoptel:view">
			<label style="position: absolute;right: 5px;">
			<span class="glyphicon glyphicon-earphone"></span>${entity.shops.telNum }</label>
		</shiro:hasPermission>
	</div>
	<div class="lineDiv"><label>发布时间：</label> <fmt:formatDate value="${entity.createTime }" pattern="yyyy-MM-dd"/> 
	<span class="badge" style="position: absolute;right: 5px;"></span>
	</div>
	<div class="lineDiv"><label>名称：</label> ${entity.name }</div>
	<div class="lineDiv"><label>材质：</label> ${entity.texture }</div>
	<div class="lineDiv"><label>描述：</label> ${entity.descript }</div>
	<div class="lineDiv"><label>货源：</label>
		<c:if test="${entity.model eq 0}">现货</c:if><c:if test="${entity.model eq 1}">样板</c:if>
		<span class="badge" style="position: absolute;right: 5px;">月销售量${entity.saleNum }</span>
	</div>
	
	<c:if test="${shiroUser.id eq entity.shops.user.id }">
	<!-- 所属用户修改自己的产品 -->
		<div class="lineDiv"><label>状态：</label> 
			<c:if test="${entity.status eq 0}">销售中</c:if><c:if test="${entity.status eq 1}"><font color="red">已下架</font> </c:if>
		</div>
		<table class="table"><tr>
			<c:if test="${entity.status eq 0}">
			<td align="center">
			<a href="${ctx }/weixin/product/myproductForm?id=${entity.id}" class="btn btn-primary">修改</a>
			</td><td align="center">
			<a href="${ctx }/weixin/product/updateProduct?id=${entity.id}&type=cancel" class="btn btn-primary">下架</a>
			</td></c:if><c:if test="${entity.status eq 1}">
			<td align="center">
			<a href="${ctx }/weixin/product/updateProduct?id=${entity.id}&type=publish" class="btn btn-primary">发布</a>
			</td></c:if>
		</tr></table>
	</c:if>
	<div style="margin-bottom: 52px"></div>
</div>
<c:set value="<%= SystemProperties.getInstance().getValue("WeixinAccessUtil.system.host")%>" var="hostname"></c:set>
<script type="text/javascript">
$(function(){
	  var url = window.location.href;
	  $.ajax({url:'${ctx}/weixin/config/jsapiToken',async:false,
		data:{'url':url},success:function(data){
			wx.config({ debug: false, appId: 'wxdb9710f998e23b63',
	    	   nonceStr: data[0],timestamp: data[1],signature: data[2],
	    	   jsApiList: ['previewImage'] });
		}});
	});

	function favtProduct(id){
		$.ajax({url:'${ctx}/weixin/favorite/prod?id='+id,type:'get',dataType:'json',
				success:function(data){
					if(data)alert('收藏成功');
					else alert('收藏失败！');
				},error:function(data){alert('收藏失败！');}});
	}
	
	function previewIMg(url) {
	    wx.previewImage({
	      current: '${ctx}'+url,
	      urls: [
			<c:if test="${!empty entity.image1.largerPicture }">'${hostname}${entity.image1.largerPicture}',</c:if>
			<c:if test="${!empty entity.image2.largerPicture }">'${hostname}${entity.image2.largerPicture}',</c:if>
			<c:if test="${!empty entity.image3.largerPicture }">'${hostname}${entity.image3.largerPicture}',</c:if>
			]});
	}
</script>
<%@include file="/WEB-INF/views/weixin/include/footer.jsp" %>
    