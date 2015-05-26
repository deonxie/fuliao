package fuliao.fuliaozhijia.weixin.util;

import java.io.IOException;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheException;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;


import fuliao.fuliaozhijia.core.util.CoreHttpClient;
import fuliao.fuliaozhijia.core.util.SystemProperties;

public class WeixinAccessUtil {
	private static Logger logger = LoggerFactory.getLogger(WeixinAccessUtil.class);
	private static String systemHost = SystemProperties.getInstance().getValue("WeixinAccessUtil.system.host");
	private static String baseUrl = SystemProperties.getInstance().getValue("WeixinAccessToken.weixin.base.url");
	private static String appId = SystemProperties.getInstance().getValue("WeixinMsgController.weixin.app.id");
	private static String appSecret = SystemProperties.getInstance().getValue("WeixinMsgController.weixin.app.secret");
	private static String access_token_key = "access_token_key";
	private static CacheManager cacheManager;
	
	/**初始化缓存对象管理器*/
	static{
		try {
			cacheManager = CacheManager.create(new ClassPathResource("/ehcache/ehcache-weixin.xml").getInputStream());
			cacheManager.addCache(new Cache(access_token_key, 1, false, false, 7100, 7100));
		} catch (CacheException e) {
			logger.error("微信网络访问缓存管理器 加载失败",e);
		} catch (IOException e) {
			logger.error("微信网络访问缓存管理器 配置文件加载失败",e);
		}
	}
	
	/**
	 * 获取微信的访问码,使用缓存对象保存，微信会7200秒更新一次验证码，验证码过期需要重新联网获取
	 * @return token
	 */
	public static String getAccessToken(){
		String token = null;
		Cache cache = cacheManager.getCache(access_token_key);
		Element element = cache.get("AccessToken");
		if(null == element){
			token = accessToken();
			cache.put(new Element("AccessToken", token));
			String jsapiToken = jsapiToken(token);
			cache.put(new Element("JsapiToken", jsapiToken));
			logger.info("微信：AccessToken:{}，jsapiToken:{}",token,jsapiToken);
			return token;
		}else{
			if(null != element.getObjectValue()){
				token = element.getObjectValue().toString();
				
			}
		}
		return token;
	}
	/**
	 * 获取微信web端的jsapi签名验证码
	 * @return
	 */
	public static String getJsapiToken(){
		String jsapiToken = null;
		Cache cache = cacheManager.getCache(access_token_key);
		Element element = cache.get("JsapiToken");
		if(null == element){
			String token = accessToken();
			cache.put(new Element("AccessToken", token));
			jsapiToken = jsapiToken(token);
			cache.put(new Element("JsapiToken", jsapiToken));
			logger.info("微信：AccessToken:{}，jsapiToken:{}",token,jsapiToken);
			return token;
		}
		if(null != element.getObjectValue())
			jsapiToken = element.getObjectValue().toString();
		return jsapiToken;
	}
	
	public static enum FileType {
		IMAGE("image"),VOICE("voice"),VIDEO("video"),THUMB("thumb");
		public String type;
		FileType(String type){
			this.type = type;
		}
	};
	
	/**
	 * 上传文件到微信平台
	 * @param filePath
	 * @param type 媒体文件类型，分别有图片（image）、语音（voice）、视频（video）和缩略图（thumb）
	 * @param http
	 * @return { "type": "image", "created_at": 1427966222,
	 *  "media_id": "99KDWfJnw6P5ERHW2qF9P4dfZHtA0gNLq-3rBM3t5KAaInxmZeqsUZzevk6PEbR3", }
	 */
	public static String uploadFileToWeixin(String filePath,FileType type,CoreHttpClient http){
		if(null== http)
			http = new CoreHttpClient(baseUrl);
		return http.postFile("cgi-bin/media/upload?access_token="+getAccessToken()+"&type="+type.type,filePath);
	}
	/**
	 * 下载指定文件
	 * @param mediaId
	 * @param http
	 * @return 文件保存路径
	 */
	public static String downloadFile(String mediaId,CoreHttpClient http){
		if(null== http)
			http = new CoreHttpClient(baseUrl);
		return http.getFile("cgi-bin/media/get?access_token="+getAccessToken()+"&media_id="+mediaId);
	}
	
	/**
	 * 重置微信访问参数
	 */
	public static void reloadConfig(){
		baseUrl = SystemProperties.getInstance().getValue("WeixinAccessToken.weixin.base.url");
		appId = SystemProperties.getInstance().getValue("WeixinMsgController.weixin.app.id");
		appSecret = SystemProperties.getInstance().getValue("WeixinMsgController.weixin.app.secret");
		systemHost = SystemProperties.getInstance().getValue("WeixinAccessUtil.system.host");
		Cache cache = cacheManager.getCache(access_token_key);
		String token = accessToken();
		cache.put(new Element("AccessToken", token));
		String jsapiToken = jsapiToken(token);
		cache.put(new Element("JsapiToken", jsapiToken));
		logger.info("reload token :{}，jsapi：{}"+token,jsapiToken);
	}
	
	/**
	 * @return the baseUrl
	 */
	public static String getBaseUrl() {
		return baseUrl;
	}
	/**
	 * @return the appId
	 */
	public static String getAppId() {
		return appId;
	}
	/**
	 * @return the appSecret
	 */
	public static String getAppSecret() {
		return appSecret;
	}
	public static String getSystemHost() {
		return systemHost;
	}
	/**
	 * http获取微信的访问码
	 * @return
	 */
	private static String accessToken(){
		CoreHttpClient http = new CoreHttpClient(baseUrl);
		String result = http.get("cgi-bin/token?grant_type=client_credential&appid="+appId+"&secret="+appSecret,null);
		return jsonStringProperty(parseToJson(result),"access_token");
	}
	/**
	 * http获取微信js的访问码
	 * @return
	 */
	private static String jsapiToken(String token){
		CoreHttpClient http = new CoreHttpClient(baseUrl);
		String result = http.get("cgi-bin/ticket/getticket?access_token="+token+ "&type=jsapi",null);
		return jsonStringProperty(parseToJson(result),"ticket");
	}
	/**
	 * 将json字符串转换为jsonobject对象
	 * @param param
	 * @return
	 */
	private static JSONObject parseToJson(String param){
		JSONObject json = null;
		try {
			json = new JSONObject(param);
		} catch (JSONException e) {
			logger.error("转换为json对象失败",e);
		}
		return json;
	}
	private static String jsonStringProperty(JSONObject json,String property){
		String result = null;
		try {
			result = json.getString(property);
		} catch (Exception e) {
			logger.error("获取json对象属性失败",e);
		}
		return result;
	}
	
}
