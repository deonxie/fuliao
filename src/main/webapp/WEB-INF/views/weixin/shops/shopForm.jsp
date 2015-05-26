<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set value="商铺详情"  var ="pageTitle"></c:set>
<c:set value="custmerCenter"  var ="pageFootTab"></c:set>
<%@include file="/WEB-INF/views/weixin/include/header.jsp" %>
<div class="myBodyDiv">
   <form action="${ctx }${baseMapper }saveShops" method="post" >
    	<input name="id" value="${entity.id }" type="hidden"/>
   	<c:if test="${entity.status eq 2}">
   		<div class="form-group">
			<span style="color:red;">审核不通过原因:${entity.failReason }</span>
		</div>
	</c:if>	
    	<div class="form-group"><div class="input-group">
    		<span class="input-group-addon" >店铺名称</span>
  			<input class="form-control" name="name" value="${entity.name }"/>
    	</div></div><div class="form-group" ><div class="input-group" style="border: 1px solid #CCC;padding: 2px;">
    		<span class="input-group-addon" >主营业务</span>
    		<c:set value=",${entity.mainBusines }," var="busines"></c:set>
    		<c:forEach items="${types }" var="type">
    			<c:set value=",${type.typeName }," var="typeItem"></c:set>
    			<label><input type="checkbox" value="${type.typeName }" name="busines"
    			${fn:contains(busines,typeItem) ?'checked="checked"':'' }> ${type.typeName }</label>
    		</c:forEach>
  			<%-- <input class="form-control" name="mainBusines" value="${entity.mainBusines }"/> --%>
    	</div></div><div class="form-group"><div class="input-group">
    		<span class="input-group-addon" >店铺简介</span>
  			<textarea class="form-control" name="descript" >${entity.descript }</textarea>
    	</div></div><div class="form-group"><div class="input-group">
    		<span class="input-group-addon" >店铺地址</span>
  			<input class="form-control" name="address" value="${entity.address }"/>
    	</div></div>
    	<div class="form-group"><div class="input-group">
    		<span class="input-group-addon" >联系电话</span>
  			<input class="form-control" name="telNum" value="${entity.telNum }"/>
    	</div></div><div class="form-group" ><div class="input-group">
    		<span class="input-group-addon" >店铺标志</span>
			  <input id="inputuploadImg1" name="logoImg" value="" type="hidden"/>
			  <c:choose><c:when test="${empty entity.logo.relativePath }">
			  		<c:set value="${ctxStatic}/images/add.png" var="logoImg"></c:set>
			  	</c:when><c:otherwise>
			  		<c:set value="${ctx }${entity.logo.relativePath}" var="logoImg"></c:set>
			  </c:otherwise></c:choose>
			  <img src="${logoImg}" alt="单击上传图片" style="width:200px; height:200px;" 
			      id="uploadImg1" title="单击上传图片" onclick="chooseIMg('uploadImg1','inputuploadImg1')"/>
    	</div>
		</div><div class="form-group" style="border: 1px solid #CCC;padding: 2px;">
    		<div class="row" ><div class="col-xs-4">
    			<input id="inputuploadImg2" name="imgPath2" value="" type="hidden"/>
    			<img src="${ctxStatic}/images/add.png" alt="单击上传图片" width="50" height="50" 
			      id="uploadImg2" title="单击上传图片" onclick="chooseIMg('uploadImg2','inputuploadImg2')"/>
			  </div><div class="col-xs-4">
				  <input id="inputuploadImg3" name="imgPath3" value="" type="hidden"/>
				  <img src="${ctxStatic}/images/add.png" alt="单击上传图片" width="50" height="50" 
				      id="uploadImg3" title="单击上传图片" onclick="chooseIMg('uploadImg3','inputuploadImg3')"/>
			  </div><div class="col-xs-4">
			 	<input id="inputuploadImg4" name="imgPath4" value="" type="hidden"/>
				<img src="${ctxStatic}/images/add.png" alt="单击上传图片" width="50" height="50" 
			      id="uploadImg4" title="单击上传图片" onclick="chooseIMg('uploadImg4','inputuploadImg4')"/>
			  </div></div>
		</div><div class="form-group"  style="margin-bottom: 52px;">
			<span style="color:red;">${errorMsg }</span>
		    <input id="btnSubmit" class="btn btn-primary form-control" type="submit" value="发 布"/>&nbsp;
		</div>
	</form>
</div>
<script type="text/javascript">
$(function(){
	  var url = window.location.href;
	  $.ajax({url:'${ctx}/weixin/config/jsapiToken',async:false,
		data:{'url':url},success:function(data){
			wx.config({ debug: false, appId: 'wxdb9710f998e23b63',
	    	   nonceStr: data[0],timestamp: data[1],signature: data[2],
	    	   jsApiList: ['chooseImage', 'previewImage','uploadImage'] });
		}});
	});
 
 function chooseIMg(img,inpt) {
	    wx.chooseImage({
	      success:function(res){
	    	if(res.localIds.length<2){
	    		for(var i=0;i<res.localIds.length;i++)
	    		  wx.uploadImage({localId: res.localIds[i],
	      	        success: function (res) {
	      	        	serdownload(img,inpt,res.serverId);
	      	        },fail: function (res){
	      	          	alert("上传到微信端失败！");
	      	        }
	      	      });
	    	 }else{
	    		alert('请不要超过1张图片！')
	    	 }
	      }
	    });
	  }
	  
	  function serdownload(img,inpt,resid){
			$.ajax({url:'${ctx}/weixin/config/download',
	       		data:{'media_id':resid},
	       		success:function(data){
	       			if(data[0]=='true'){
	        			$("#"+img).attr('src','${ctx}/tmp/'+data[1]);
	        			$("#"+inpt).val(data[1]);
	        			$("#"+radbtn).val(data[1]);
	       			}else alert(data[1]);
	        }});
		}
 </script>
<%@include file="/WEB-INF/views/weixin/include/footer.jsp" %>