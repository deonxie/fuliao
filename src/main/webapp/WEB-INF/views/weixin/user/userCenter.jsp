<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set value="个人中心"  var ="pageTitle"></c:set>
<c:set value="custmerCenter"  var ="pageFootTab"></c:set>
<%@include file="/WEB-INF/views/weixin/include/header.jsp" %>
<style>
a:hover{text-decoration: none; }
</style>
<div class="myBodyDiv" >
	<div class="form-group"><a href="${ctx }/weixin/user/userinfo">
	<img src="${weixinUser.headimgurl }" 
	alt="无微信头像" class="img-circle" style="width: 140px;height: 140px;"/></a>
	</div>
	<div class="form-group">
	<a href="${ctx }/weixin/user/userinfo"><strong><shiro:principal property="name"></shiro:principal></strong></a>
	</div>
   	<div class="form-group">
		<a href="${ctx }/weixin/reqproduct/myreqProductForm" class="form-control  btn-default " style="position: relative;height: 40px;">
   		<label style="position:absolute ;left: 20px;font-size: medium; color:#08c  "><b>发布求购</b></label>
   		<label style="position:absolute ;right: 0px;">》</label>
   		</a>
   	</div><div class="form-group">
		<a href="${ctx }/weixin/reqproduct/myreqProductList" class="btn-default form-control" style="position: relative;height: 40px;">
   		<label style="position:absolute ;left: 20px;font-size: medium; color:#08c  "><b>我的求购</b></label>
   		<label style="position:absolute ;right: 0px;">》</label>
   		</a>
   	</div>
   	<c:choose>
   	<c:when test="${!empty hasPassShop }">
   	<div class="form-group">
		<a href="${ctx }/weixin/product/myproductForm" class="btn-default form-control" style="position: relative;height: 40px;">
   		<label style="position:absolute ;left: 20px;font-size: medium; color:#08c ;"><b>发布产品</b></label>
   		<label style="position:absolute ;right: 0px;">》</label>
   		</a>
   	</div><div class="form-group">
		<a href="${ctx }/weixin/product/myproductList" class="btn-default form-control" style="position: relative;height: 40px;">
   		<label style="position:absolute ;left: 20px;font-size: medium; color:#08c ;"><b>我的产品</b></label>
   		<label style="position:absolute ;right: 0px;">》</label>
   		</a>
   	</div>
   	<div class="form-group">
		<a href="${ctx }/weixin/shops/shopDetail?id=${hasPassShop.id}" class="btn-default form-control" style="position: relative;height: 40px;">
   		<label style="position:absolute ;left: 20px;font-size: medium; color:#08c ;"><b>我的店铺</b></label>
   		<label style="position:absolute ;right: 0px;">》</label>
   		</a>
   	</div>
    <div class="form-group">
		<a href="${ctx }/weixin/shops/comparePirceList" class="btn-default form-control" style="position: relative;height: 40px;">
   		<label style="position:absolute ;left: 20px;font-size: medium; color:#08c;"><b>我的竞价</b></label>
   		<label style="position:absolute ;right: 0px;">》</label>
   		</a>
   	</div>
   	</c:when><c:otherwise>
   	<div class="form-group">
		<a href="${ctx }/weixin/shops/" class="btn-default form-control" style="position: relative;height: 40px;">
   		<label style="position:absolute ;left: 20px;font-size: medium; color:#08c ;"><b>我的店铺</b></label>
   		<label style="position:absolute ;right: 0px;">》</label>
   		</a>
   	</div>
   	</c:otherwise>
   	</c:choose>
   	<div class="form-group">
   		<a href="${ctx }/weixin/user/myFavtprod" class="btn-default form-control" style="position: relative;height: 40px;">
   		<label style="position:absolute ;left: 20px;font-size: medium; color:#08c  "><b>我的收藏</b></label>
   		<label style="position:absolute ;right: 0px;">》</label>
   		</a>
   	</div>
 
   	<div class="form-group">
   		<a href="${ctx }/weixin/logout" class="btn-danger form-control" style="position: relative;height: 40px;">
   		<label style="font-size: medium; color:#08c ;"><b>退 出</b></label>
   		</a>
   	</div>
   	<div style="margin-bottom: 52px;"></div>
</div>
<%@include file="/WEB-INF/views/weixin/include/footer.jsp" %>
    