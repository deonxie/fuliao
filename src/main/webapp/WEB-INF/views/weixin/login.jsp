<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="ctxStatic" value="${pageContext.request.contextPath}/static"/>
<html>
<head>
    <title>用户登录</title>
    <meta name="decorator" content="default"/>
   	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <link href="${ctxStatic }/bootstrap/3.3.2/css/bootstrap.min.css" rel="stylesheet">
    <link href="${ctxStatic }/mobileSys.css" rel="stylesheet">
    <script src="${ctxStatic }/jquery/jquery-1.9.1.min.js" type="text/javascript"></script>
    <script src="${ctxStatic }/bootstrap/3.3.2/js/bootstrap.min.js" type="text/javascript"></script>
</head>
<body>
    <div class="myBodyDiv" style="margin-top: 50px;">
    	<label><font color="red">${errorMsg }</font></label>
    	<form action="${ctx }/weixin/login" method="post" >
    	<div class="form-group" ><div class="input-group">
    		<span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
    		<input type="text" name="username" class="required form-control" maxlength="11" value="${username }" placeholder="请输入手机号码"/>
    	</div></div>
    	<div class="form-group"><div class="input-group">
    		<span class="input-group-addon"><span class="glyphicon glyphicon-eye-open"></span></span>
    		<input name="password" class="required form-control" maxlength="20" placeholder="请输入密码" type="password"/>
    	</div></div>
    	<div class="form-group" style="text-align: right;">
    		<%-- <a href="${ctx }/weixin/register" style="left: 10px;position: absolute;">
    			<span class="glyphicon glyphicon-user"></span>注&nbsp;&nbsp;&nbsp;&nbsp;册</a> --%>
    		<a href="${ctx }/weixin/forgetPwd"><span class="glyphicon glyphicon-question-sign"></span>找回密码！</a>
    	</div>
    	<div class="form-group">
    		<input class="btn btn-primary  form-control"  type="submit" value="登&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;录"/>
    	</div>
    	</form>
    	<script src="${ctxStatic }/jweixin-1.0.0.js" type="text/javascript"></script>
    	<!-- <button onclick="upload">upload</button> -->
 <%--     	<script type="text/javascript">
    	$(function(){
    		var url = window.location.href;
    		$.ajax({url:'${ctx}/weixin/config/jsapiToken',async:false,
    			data:{'url':url},
    			success:function(data){
    			alert(data[2]);
    				wx.config({
    		    	    debug: true, 
    		    	    appId: 'wxdb9710f998e23b63', // 必填，公众号的唯一标识
    		    	    nonceStr: data[0], // 必填，生成签名的随机串
    		    	    timestamp: data[1], // 必填，生成签名的时间戳
    		    	    signature: data[2],// 必填，签名，
    		    	    jsApiList: ['checkJsApi','onMenuShareTimeline','onMenuShareAppMessage',
    		    	                'onMenuShareQQ','onMenuShareWeibo','hideMenuItems','showMenuItems',
    		    	                'hideAllNonBaseMenuItem','showAllNonBaseMenuItem','translateVoice',
    		    	                'startRecord','stopRecord','onRecordEnd','playVoice','pauseVoice',
    		    	                'stopVoice', 'uploadVoice', 'downloadVoice', 'chooseImage', 'previewImage',
    		    	                'uploadImage', 'downloadImage', 'getNetworkType', 'openLocation', 'getLocation',
    		    	                'hideOptionMenu', 'showOptionMenu', 'closeWindow', 'scanQRCode', 'chooseWXPay',
    		    	                'openProductSpecificView', 'addCard', 'chooseCard', 'openCard'] });
    				// 必填，需要使用的JS接口列表，所有JS接口列表见附录2
    		    	
    				stest();
    				/* wx.checkJsApi({
    		    	    jsApiList: ['chooseImage'], // 需要检测的JS接口列表，所有JS接口列表见附录2,
    		    	    success: function(res) {
    		    	        alert(res);
    		    	    }
    		    	}); */
    			}});
    	});
    
 
    	function  stest(){
    	/* wx.ready(function(){
			alert("success");
    	}); */
    	wx.error(function(res){
    		
    		//alert(res[0]+res[1]);
			alert("verchy error:"+res);
    	});
    	wx.checkJsApi({
    	    jsApiList: ['chooseImage'], // 需要检测的JS接口列表，所有JS接口列表见附录2,
    	    success: function(res) {
    	        alert(res);
    	    }
    	});
    	}
    	// 5 图片接口
    	  // 5.1 拍照、本地选图片
    	  var images = {
    	    localId: [],
    	    serverId: []
    	  };
    	  function chooseIMg() {
    	    wx.chooseImage({
    	      success: function (res) {
    	        images.localId = res.localIds;
    	        alert('宸查�夋嫨 ' + res.localIds.length + ' 寮犲浘鐗�');
    	      }
    	    });
    	  };

    	  // 5.2 预览图片
    	 function previewIMg() {
    	    wx.previewImage({
    	      current: 'http://img5.douban.com/view/photo/photo/public/p1353993776.jpg',
    	      urls: [
    	        'http://img3.douban.com/view/photo/photo/public/p2152117150.jpg',
    	        'http://img5.douban.com/view/photo/photo/public/p1353993776.jpg',
    	        'http://img3.douban.com/view/photo/photo/public/p2152134700.jpg'
    	      ]
    	    });
    	  };

    	  // 5.3 上传图片
    	  function uploadIMg() {
    	    if (images.localId.length == 0) {
    	      alert('璇峰厛浣跨敤 chooseImage 鎺ュ彛閫夋嫨鍥剧墖');
    	      return;
    	    }
    	    var i = 0, length = images.localId.length;
    	    images.serverId = [];
    	    function upload() {
    	      wx.uploadImage({
    	        localId: images.localId[i],
    	        success: function (res) {
    	          i++;
    	          alert('宸蹭笂浼狅細' + i + '/' + length);
    	          images.serverId.push(res.serverId);
    	          if (i < length) {
    	            upload();
    	          }
    	        },
    	        fail: function (res) {
    	          alert(JSON.stringify(res));
    	        }
    	      });
    	    }
    	    upload();
    	  };
    	</script> 
    	--%>
    	
    </div>
</body>
</html>
    