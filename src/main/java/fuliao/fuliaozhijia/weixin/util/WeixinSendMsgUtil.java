package fuliao.fuliaozhijia.weixin.util;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import fuliao.fuliaozhijia.core.util.CoreHttpClient;
import fuliao.fuliaozhijia.weixin.entity.RequestProduct;
import fuliao.fuliaozhijia.weixin.entity.WeixinUser;

/**
 * 发送微信消息到用户，获取平台客服
 * @author DEON
 *
 */
public class WeixinSendMsgUtil {
	private final static Logger logger = LoggerFactory.getLogger(WeixinSendMsgUtil.class);
	
	/**
	 * 向关注用户发送消息
	 */
	public static String sendMsgToUser(String jsonMsg,CoreHttpClient http){
		if(null == http)
			http = new CoreHttpClient(WeixinAccessUtil.getBaseUrl());
		return http.postJson("cgi-bin/message/custom/send?access_token="+
			WeixinAccessUtil.getAccessToken(), jsonMsg);
	}
	/**
	 * 获取所有客服列表
	 * @return
	 */
	public static String getKFlist(CoreHttpClient http){
		if(null == http)
			http = new CoreHttpClient(WeixinAccessUtil.getBaseUrl());
		return http.get("cgi-bin/customservice/getkflist?access_token="+
			WeixinAccessUtil.getAccessToken(),null);
		
	}
	/**
	 * 添加客服账号
	 * @param kfCount
	 * @param nickName
	 * @param pwd
	 * @param http
	 * @return
	 */
	public static String createKF(String kfCount,String nickName,String pwd,CoreHttpClient http){
		if(null == http)
			http = new CoreHttpClient(WeixinAccessUtil.getBaseUrl());
		Map<String, Object> param = Maps.newHashMap();
		param.put("kf_account", kfCount);
		param.put("nickname", nickName);
		param.put("password", pwd);
		     
		return http.postJson("customservice/kfaccount/add?access_token="+
				WeixinAccessUtil.getAccessToken(),new JSONObject(param).toString());
		
	}
	/**
	 * 删除客服账号
	 * @param kfCount
	 * @param nickName
	 * @param pwd
	 * @param http
	 * @return
	 */
	public static String deleteKF(String kfCount,String nickName,String pwd,CoreHttpClient http){
		if(null == http)
			http = new CoreHttpClient(WeixinAccessUtil.getBaseUrl());
		Map<String, Object> param = Maps.newHashMap();
		param.put("kf_account", kfCount);
		param.put("nickname", nickName);
		param.put("password", pwd);
		     
		return http.postJson("customservice/kfaccount/del?access_token="+
				WeixinAccessUtil.getAccessToken() ,new JSONObject(param).toString());
		
	}
	public static String kfHelpMsg(String openid){
		Map<String, Object> param = Maps.newHashMap();
		param.put("touser", openid);
        param.put("msgtype","text");
        Map<String, Object> content = Maps.newHashMap();
        content.put( "content","您好，有什么可以帮到您呢。");
		param.put("text", content);
		Map<String,Object> kf = Maps.newHashMap();
		kf.put("kf_account", "deon@zhongda_fuliao");
		param.put("customservice", kf);
		
		return new JSONObject(param).toString();  
			
	}
	public static String welcomMsg(String openid){
		Map<String, Object> param = Maps.newHashMap();
		param.put("touser", openid);
		param.put("msgtype", "news");
		List<Map<String, String>> items = Lists.newArrayList();
		Map<String, String> item1 = Maps.newHashMap();
		item1.put("title", "欢迎您注册，中大辅料");
		item1.put("picurl", WeixinAccessUtil.getSystemHost()+"/static/images/logo_zd.png");
		item1.put("url", WeixinAccessUtil.getSystemHost()+"/weixin/register?registerCode="+openid);
		items.add(item1);
		Map<String, String> item2 = Maps.newHashMap();
		item2.put("title", "成为中大辅料平台用户从这里开始，我们将竭诚为您服务，希望能与您共发展！");
		item2.put("picurl", WeixinAccessUtil.getSystemHost()+"/static/images/userinfo_sys.jpg");
		item2.put("url", WeixinAccessUtil.getSystemHost()+"/weixin/register?registerCode="+openid);
		items.add(item2);
		Map<String, Object> articles = Maps.newHashMap();
		articles.put("articles", items);
		param.put("news", articles);
		return new JSONObject(param).toString();
	}//  http://120.25.210.86/fuliao//upload/productImg/bee929fc-9f50-4e17-a624-0e5270cc4d72.jpg
	public static String sysLoginMsg(String openid){
		Map<String, Object> param = Maps.newHashMap();
		param.put("touser", openid);
		param.put("msgtype", "news");
		List<Map<String, String>> items = Lists.newArrayList();
		Map<String, String> item1 = Maps.newHashMap();
		item1.put("title", "中大辅料代购平台登录");
		item1.put("picurl", WeixinAccessUtil.getSystemHost()+"/static/images/logo_zd.png");
		item1.put("url", WeixinAccessUtil.getSystemHost()+"/weixin/");
		items.add(item1);
		Map<String, Object> articles = Maps.newHashMap();
		articles.put("articles", items);
		param.put("news", articles);
		return new JSONObject(param).toString();
	}
	
	public static void sendReqProdcut2Shop(final List<WeixinUser> users,final RequestProduct prod) {
		new Thread(){
			@Override
			public void run() {
				CoreHttpClient http = new CoreHttpClient(WeixinAccessUtil.getBaseUrl());
				if(null != users && users.size()>0){
					for(WeixinUser user: users){
						sendOneUserReqProdcut(user,prod,http);
					}
				}else
					logger.info("没找到适合的商家推送");
			}
		}.start();
	}
	private static void sendOneUserReqProdcut(WeixinUser user,RequestProduct prod,CoreHttpClient http){
		Map<String, Object> param = Maps.newHashMap();
		param.put("touser", user.getOpenid());
		param.put("msgtype", "news");
		List<Map<String, String>> items = Lists.newArrayList();
		Map<String, String> item1 = Maps.newHashMap();
		item1.put("title", "寻找："+prod.getName());
		item1.put("picurl", WeixinAccessUtil.getSystemHost()+prod.getCoverImg().getRelativePath());
		items.add(item1);
		Map<String, String> item2 = Maps.newHashMap();
		item2.put("title", "产品材质要求："+prod.getTexture());
		items.add(item2);
		Map<String, String> item3 = Maps.newHashMap();
		item3.put("title", "产品描述："+prod.getDescript());
		items.add(item3);
		Map<String, String> item4 = Maps.newHashMap();
		item4.put("title", "价格：￥"+prod.getPrice()+" 求购数量："+prod.getRequestNum()+prod.getUnits() +" 求购时间："+dateFormat.format(prod.getCreateTime()));
		items.add(item4);
		Map<String, Object> articles = Maps.newHashMap();
		articles.put("articles", items);
		param.put("news", articles);
		String json = new JSONObject(param).toString();
		logger.info("推送消息："+json);
		json = http.postJson("cgi-bin/message/custom/send?access_token="+
			WeixinAccessUtil.getAccessToken(), json);
		logger.info("推送结果："+json);
	}
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
}
