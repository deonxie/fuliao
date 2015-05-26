<%@page import="fuliao.fuliaozhijia.core.util.SystemProperties"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set value="求购详情"  var ="pageTitle"></c:set>
<c:set value="mainRequests"  var ="pageFootTab"></c:set>
<%@include file="/WEB-INF/views/weixin/include/header.jsp" %>
<style type="text/css">
.lineDiv{font-size: small;text-align: left;position: relative;}
.lineDiv > label{color:#08c;font-size: medium;}
</style>
<div class="myBodyDiv">
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
    
	<div class="form-control lineDiv">
		<label>类&nbsp;&nbsp;&nbsp;&nbsp; 型：</label> ${entity.type.typeName } 
		<span class="badge" style="position: absolute;right: 5px;">
		<span class="glyphicon glyphicon-star-empty" ></span>收藏 </span>
	</div>
<shiro:hasPermission name="reqtel:view">
	<div class="form-control lineDiv">
		<label>发 布 人：</label> ${entity.user.name }
		<label style="position: absolute;right: 5px;">
		<span class="glyphicon glyphicon-earphone"></span>${entity.user.loginName }</label> 
	</div>
</shiro:hasPermission>
	<div class="form-control lineDiv"><label>发布时间：</label> <fmt:formatDate value="${entity.createTime }" pattern="yyyy-MM-dd HH:mm"/> </div>
	<div class="form-control lineDiv"><label>产品名称：</label> ${entity.name }</div>
	<div class="form-control lineDiv"><label>材质要求：</label> ${entity.texture }</div>
	<div class="form-control lineDiv"><label>求购需求：</label> ${entity.descript }</div>
	<div class="form-control lineDiv"><label>货源：</label> ${entity.model eq 1 ? '样板':'现货' }</div>
	<div class="form-control lineDiv"><label>状态：</label> 
		<c:if test="${entity.status eq 1 }"><font color="red">已取消</font></c:if>
		<c:if test="${entity.status eq 2 }"><font color="#08c">已完成</font></c:if>
		<c:if test="${entity.status eq 0 }"><font color="#08c">求购中</font></c:if>
		<label style="position: absolute;right: 2px;"><fmt:formatDate value="${entity.solveTime }" pattern="yyyy-MM-dd HH:mm"/></label>
	</div>
	
<c:if test="${entity.status eq 0 }">
	<c:if test="${entity.user.id eq shiroUser.id}">
		<table class="table" ><tr><td align="center">
			<a href="${ctx }/weixin/reqproduct/myreqProductForm?id=${entity.id}" class="btn btn-primary">修改</a>
			</td><td align="center">
			<a href="${ctx }/weixin/reqproduct/updatemyReqProduct?id=${entity.id}&type=cancel" class="btn btn-primary">取消发布</a>
			</td><td align="center">
			<a href="${ctx }/weixin/reqproduct/updatemyReqProduct?id=${entity.id}&type=finish" class="btn btn-primary">已解决</a>
		</td></tr></table>
	</c:if>

	<c:if test="${canOutPrice && !entity.user.id eq shiroUser.id}">
		<form id="outForm" action="${ctx }${baseMapper}saveOutPrice" method="post">
		<div class="form-control lineDiv" style="padding-top: 0px;">
			<div class="input-group">
			<input type="hidden" name="reqProduct.id" value="${entity.id }">
				<span class="input-group-addon">出 价：</span>
				<input name="price" class="form-control" type="number"/>
				<span class="input-group-addon"><input type="checkbox" name="status" value="1">有现货</span>
				<span class="input-group-addon" onclick="$('#outForm').submit();">提交</span>
			</div>
 		</div>
		</form>
	</c:if>
</c:if>

<shiro:hasPermission name="outprice:view">
	<table class="table table-striped table-bordered"> 
		<caption style="text-align:right"><label>竞价商铺</label></caption>
		<tr><th>商铺</th><th>出价</th><th>货源</th><th>联系方式</th></tr>
		<c:forEach items="${entity.outPrices }" var="outprice">
			<tr><td><a href="${ctx }/weixin/shops/shopDetail?id=${outprice.shop.id}">${outprice.shop.name }</a></td>
			<td>${outprice.price }</td>
			<td>${outprice.status eq 1?'有现货':''}</td>
			<td>${outprice.shop.telNum }</td></tr>
		</c:forEach>
	</table>
</shiro:hasPermission>
	<div class="input-group" style="margin-bottom: 50px;">
		<font color="red">${errorMsg }</font>
	</div>
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
function previewIMg(url) {
    wx.previewImage({
      current: '${ctx}'+url,
      urls: [
		<c:if test="${!empty entity.image1.largerPicture }">'${hostname}${entity.image1.largerPicture}',</c:if>
		<c:if test="${!empty entity.image2.largerPicture }">'${hostname}${entity.image2.largerPicture}',</c:if>
		<c:if test="${!empty entity.image3.largerPicture }">'${hostname}${entity.image3.largerPicture}',</c:if>
      ]
    });
}
</script>
<%@include file="/WEB-INF/views/weixin/include/footer.jsp" %>
    