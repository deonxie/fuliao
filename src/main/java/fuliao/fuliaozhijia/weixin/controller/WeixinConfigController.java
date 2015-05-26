package fuliao.fuliaozhijia.weixin.controller;




import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import fuliao.fuliaozhijia.core.controller.GenericeController;
import fuliao.fuliaozhijia.core.service.UploadPictureService.SavePath;
import fuliao.fuliaozhijia.weixin.entity.RequestProduct;
import fuliao.fuliaozhijia.weixin.util.SHA1;
import fuliao.fuliaozhijia.weixin.util.WeixinAccessUtil;
import fuliao.fuliaozhijia.weixin.util.WeixinDownload;


@RestController
@RequestMapping("/weixin/config/")
public class WeixinConfigController extends GenericeController<RequestProduct>{
	private static Logger logger = LoggerFactory.getLogger(WeixinConfigController.class);
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	static String noncestr = "teFonericFacto";
	static long timestamp = new Date().getTime()/1000;
	/**
	 * 手机网页主界面
	 * @return
	 */
	@RequestMapping("jsapiToken")
	public String[] index(@RequestParam("url")String url){
		String jsapi = WeixinAccessUtil.getJsapiToken();
    	StringBuffer sb = new StringBuffer("jsapi_ticket=");
    	sb.append(jsapi).append("&noncestr=").append(noncestr);
    	sb.append("&timestamp=").append(timestamp).append("&url=").append(url);
		
		return new String[]{noncestr,timestamp+"",SHA1.sha1(sb.toString())};
	}
//	accessToken
	@RequestMapping(value="reloadConfig",method = RequestMethod.GET)
	public boolean login(){
		WeixinAccessUtil.reloadConfig();
		return true;
	}

	@RequestMapping("download")
	public String[] download(@RequestParam("media_id")String mediaid,
			HttpServletRequest request){
		if(StringUtils.isNoneBlank(mediaid)){
			String rootPath = request.getServletContext().getRealPath("")+SavePath.TMP.path;
			String file = WeixinDownload.download(mediaid, rootPath);
			return new String[]{Boolean.TRUE.toString(),file};
		}
		return new String[]{Boolean.FALSE.toString(),"后台保存异常"};
	}
}
