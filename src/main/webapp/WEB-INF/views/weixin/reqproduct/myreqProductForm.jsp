<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set value="发布求购"  var ="pageTitle"></c:set>
<c:set value="custmerCenter"  var ="pageFootTab"></c:set>
<%@include file="/WEB-INF/views/weixin/include/header.jsp" %>
   <div class="myBodyDiv" >
    <form action="${ctx }${baseMapper }savemyReqProduct" method="post" >
    	<input name="id" value="${entity.id }" type="hidden"/>
    	<div class="form-group"><div class="input-group">
    		<span class="input-group-addon" >求购类型</span>
  			<select class="form-control" name="type.id">
  				<c:forEach items="${types }" var="type">
  					<option value="${type.id }" ${entity.type.id eq type.id ?'selected="selected"':'' }>${type.typeName }</option>
  				</c:forEach>
  			</select>
    	</div></div>
    	<div class="form-group"><div class="input-group">
    		<span class="input-group-addon" >求购产品</span>
  				<input name="name" maxlength="120" class="form-control" placeholder="求购产品名称" value="${entity.name }"/>
    		</div>
    	</div>
    	<div class="form-group"><div class="input-group">
    		<span class="input-group-addon" >产品材质</span>
  				<input name="texture" maxlength="200" class="form-control" placeholder="产品材质" value="${entity.texture }"/>
    		</div>
    	</div>
    	<div class="form-group"><div class="input-group">
   			<span class="input-group-addon" >需求描述</span>
			<textarea name="descript" class="form-control" placeholder="需求描述" >${entity.descript }</textarea>
   		</div></div>
    	<div class="form-group"><div class="input-group">
    			<span class="input-group-addon">&nbsp;&nbsp;单&nbsp;&nbsp;&nbsp;&nbsp; 价</span>
  				<input type="number"  name="price" class="form-control" placeholder="输入产品单价" value="${entity.price }"/>
    		</div>
    	</div>
    	<div class="form-group"><div class="input-group">
    			<span class="input-group-addon">&nbsp;&nbsp;单&nbsp;&nbsp;&nbsp;&nbsp; 位</span>
    			<select class="form-control" name="units">
    				<c:forEach items="${proUnits }" var="prounit">
    				<option value="${prounit.value }" ${prounit.value eq entity.units ?'selected="selected"':'' }>${prounit.value }</option>
    				</c:forEach>
    			</select>
    		</div>
    	</div>
    	<div class="form-group"><div class="input-group">
    			<span class="input-group-addon">求购数量</span>
  				<input type="number" name="requestNum" class="form-control" placeholder="输入产品求购数量" value="${entity.requestNum }"/>
    		</div>
    	</div>
    	<div class="form-group"><div class="btn-group" data-toggle="buttons">
		  <label class="btn btn-default ${entity.model eq 0 ?'':'active' }"><span class="glyphicon glyphicon-check"></span>
		    <input type="radio" name="model" value="1" autocomplete="off" ${entity.model eq 1 ?'checked':'' } >样板
		  </label>
		  <label class="btn btn-default ${entity.model eq 0 ?'active':'' }"><span class="glyphicon glyphicon-check"></span>
		    <input type="radio" name="model" value="0" autocomplete="off" ${entity.model eq 0 ?'checked':'' }>现货
		  </label>
		</div></div>
    	<div class="form-group" style="border: 1px solid #CCC;padding: 2px;">
    		<div class="row" >
			  <div class="col-xs-4">
			  	<input id="inputuploadImg1" name="imgPath1" value="" type="hidden"/>
			  	<c:choose>
			  		<c:when test="${empty entity.image1.relativePath }">
			  			<c:set value="${ctxStatic}/images/add.png" var="uploadImg1"></c:set>
			  		</c:when>
			  		<c:otherwise>
			  			<c:set value="${ctx }${entity.image1.relativePath }" var="uploadImg1"></c:set>
			  		</c:otherwise>
			  	</c:choose> 
			      <img src="${uploadImg1}" alt="单击上传图片" width="50" height="50" 
			      id="uploadImg1" title="单击上传图片" onclick="chooseIMg('uploadImg1','inputuploadImg1','raduploadImg1')"/>
			  </div>
			  <div class="col-xs-4">
			  <input id="inputuploadImg2" name="imgPath2" value="" type="hidden"/>
			  	<c:choose>
			  		<c:when test="${empty entity.image2.relativePath }">
			  			<c:set value="${ctxStatic}/images/add.png" var="uploadImg2"></c:set>
			  		</c:when>
			  		<c:otherwise>
			  			<c:set value="${ctx }${entity.image2.relativePath}" var="uploadImg2"></c:set>
			  		</c:otherwise>
			  	</c:choose>
			      <img src="${uploadImg2 }" alt="单击上传图片" width="50" height="50" 
			      id="uploadImg2" title="单击上传图片" onclick="chooseIMg('uploadImg2','inputuploadImg2','raduploadImg2')"/>
			  </div>
			  <div class="col-xs-4">
			  <input id="inputuploadImg3" name="imgPath3" value="" type="hidden"/>
			  	<c:choose>
			  		<c:when test="${empty entity.image3.relativePath }">
			  			<c:set value="${ctxStatic}/images/add.png" var="uploadImg3"></c:set>
			  		</c:when>
			  		<c:otherwise>
			  			<c:set value="${ctx }${entity.image3.relativePath }" var="uploadImg3"></c:set>
			  		</c:otherwise>
			  	</c:choose>
			      <img src="${uploadImg3}" alt="单击上传图片" width="50" height="50" 
			      id="uploadImg3" title="单击上传图片" onclick="chooseIMg('uploadImg3','inputuploadImg3','raduploadImg3')"/>
			  </div>
			</div>
			<div class="row">
				<div class="col-xs-4">
			   	<input type="radio" value="" id="raduploadImg1" name="cover" <c:if test="${entity.image1.isCoverImg eq 1 }">checked="checked"</c:if>><label for="raduploadImg1">封面</label>
			  	</div><div class="col-xs-4">
			  	<input type="radio" value="" id="raduploadImg2" name="cover" <c:if test="${entity.image2.isCoverImg eq 1 }">checked="checked"</c:if>><label for="raduploadImg2">封面</label>
				</div><div class="col-xs-4">
				<input type="radio" value="" id="raduploadImg3" name="cover" <c:if test="${entity.image3.isCoverImg eq 1 }">checked="checked"</c:if>><label for="raduploadImg3">封面</label>
				</div>
			</div>
    	</div>
    	<div class="form-group">
		<span style="color:red;">${errorMsg }</span>
		   <input id="btnSubmit" class="btn btn-primary form-control" type="submit" value="发 布"/>&nbsp;
		</div>
		<label id="errorMsg" style="color: red;margin-bottom: 42px;"></label>
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
    	   
   function chooseIMg(img,inpt,radbtn){
	    wx.chooseImage({
	      success:function(res){
	    	if(res.localIds.length<2){
	    		for(var i=0;i<res.localIds.length;i++)
	    		  wx.uploadImage({localId: res.localIds[i],
	      	        success: function (res) {
	      	        	serdownload(img,inpt,radbtn,res.serverId);
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
   
	function serdownload(img,inpt,radbtn,resid){
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
</div>
<%@include file="/WEB-INF/views/weixin/include/footer.jsp" %>
    