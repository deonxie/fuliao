package fuliao.fuliaozhijia.weixin.util;


import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fuliao.fuliaozhijia.weixin.entity.WeixinUser;


/**
 * 解析微信推送的信息
 * @author DEON
 *
 */
public class WeixinReturnStringUtil {
	
	private static Logger logger = LoggerFactory.getLogger(WeixinReturnStringUtil.class);
	
	public static WeixinUser parseJsonToWeixinUser(String userOpenId){
		String jsonStr = WeixinUserUtil.getUserInfo(userOpenId, null);
		WeixinUser user = new WeixinUser();
		try {
			JSONObject json = new JSONObject(jsonStr);
			user.setSubscribe(json.getInt("subscribe"));
			user.setOpenid(json.getString("openid"));
			user.setNickName(json.getString("nickname"));
			user.setSex(json.getInt("sex"));
			user.setLanguage(json.getString("language"));
			user.setCity(json.getString("city"));
			user.setProvince(json.getString("province"));
			user.setCountry(json.getString("country"));
			user.setHeadimgurl(json.getString("headimgurl"));
			user.setSubscribe_time(json.getLong("subscribe_time"));
			user.setRemark(json.getString("remark"));
			logger.info("解析成功一个用户："+jsonStr);
			return user;
		} catch (JSONException e) {
			logger.error("转换为json对象失败",e);
			return null;
		}
    }
	
}
