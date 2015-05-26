<%@ page contentType="text/html;charset=UTF-8" %>
    <link rel="stylesheet" type="text/css" href="${ctxStatic }/jquery/ajaxupload/ajaxfileupload.css">
    <script src="${ctxStatic }/jquery/jquery-1.9.1.min.js" type="text/javascript"></script>
	<script type="text/javascript" src="${ctxStatic }/jquery/ajaxupload/ajaxfileupload.js"></script>

	<div id="wait_loading" style="padding: 50px 0 0 0;display:none;">
        <div style="width: 103px;margin: 0 auto;"><img src="${ctxStatic }/jquery/ajaxupload/loading.gif"/></div>
        <br></br>
        <div style="width: 103px;margin: 0 auto;"><span>请稍等...</span></div>
        <br></br>
    </div>
<div class="input-group">
    <input type="text" id="ajaxUploadText" onclick="doChooseFfile()" readonly="readonly" 
    style="margin-bottom: 0px;" placeholder="点击选择上传文件！"/>
    <span id="btnUpload" onclick="ajaxFileUpload()" class="btn btn-primary">上传图片</span>
    <span id="btnUpload" onclick="doCancelUpload()" class="btn btn-warning"> 取 消</span>
</div>
    <input type="file" id="ajaxUploadFfile" name="file" style="display: none;" onchange="doChooseFfileChange()">
    <script type="text/javascript">
    	function doChooseFfile(){
    		$("#ajaxUploadFfile").click();
    	}
    	function doChooseFfileChange(){
    		$("#ajaxUploadText").val($("#ajaxUploadFfile").val()).attr('title',$("#ajaxUploadFfile").val());
    	}
    	function doCancelUpload(){
    		$("#ajaxUploadText").val('');
    		$("#ajaxUploadFfile").val('');
    	}
    	function ajaxFileUpload() {
    		if($("#ajaxUploadFfile").val()=='')
    			return ;
            // 开始上传文件时显示一个图片
            $("#wait_loading").ajaxStart(function() {
                $(this).show();
            // 文件上传完成将图片隐藏起来
            }).ajaxComplete(function() {
                $(this).hide();
            });
            $.ajaxFileUpload({
                url: '${ctx}/file/updload/', 
                type: 'post',
                secureuri: false, //一般设置为false
                fileElementId: 'ajaxUploadFfile', // 上传文件的id、name属性名
                dataType: 'josn', //返回值类型，一般设置为json、application/json
                success: function(data, status){  
                    alert(data);
                    $("#dfdf").attr("src",'${ctx}/tmp'+data);
                },
                error: function(data, status, e){ 
                    alert('上传失败！');
                }
            });
        }
    </script>
