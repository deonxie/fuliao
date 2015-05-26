package fuliao.fuliaozhijia.weixin.util;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Maps;
import org.json.JSONObject;

import fuliao.fuliaozhijia.core.util.CoreHttpClient;

public class WeixinUserUtil {
	
	/**
	 * 查询所有关注的微信用户，一次查询20条，查询下一页数据需传入上一次最后一个openid
	 * @param nextOpenid
	 * @param http
	 * @return { "total": 20,  "count": 20, "data": {"openid": [。。。。]}}
	 * total总人数，count当前获取的人数
	 */
	public static String getAllUsers(String nextOpenid,CoreHttpClient http){
		 if(null == http)
			 http= new CoreHttpClient(WeixinAccessUtil.getBaseUrl());
		return http.get("cgi-bin/user/get?access_token="+
			 WeixinAccessUtil.getAccessToken()+"&next_openid="+StringUtils.trimToEmpty(nextOpenid),null);
	}
	
	/**
	 * 获取关注详细用户信息
	 * @param userOpenId
	 * @param http
	 * @return  {"subscribe": 1,"openid": "o1c8wt40T2yDoCqxCkBJqg8G0yW4", "nickname": "deon","sex": 1,
	 * "language": "zh_CN", "city": "珠海", "province": "广东","country": "中国",
	 * "headimgurl": "http://wx.qlogo.cn/mmopen//0", "subscribe_time": 1426392468,"remark": ""}
	 */
	public static String getUserInfo(String userOpenId,CoreHttpClient http){
		 if(null == http)
			 http = new CoreHttpClient(WeixinAccessUtil.getBaseUrl());
		return http.get("cgi-bin/user/info?access_token="+
				WeixinAccessUtil.getAccessToken()+"&openid="+userOpenId, null);
	}
	
	/**查询所有分组
	 * @param http
	 * @return {"groups": [{"id":0,"name":"未分组","count": 16},{"id": 1,"name": "黑名单","count": 0}] }
	 */
	public static String getAllGroup(CoreHttpClient http){
		if(null == http)
			http = new CoreHttpClient(WeixinAccessUtil.getBaseUrl());
		return http.get("cgi-bin/groups/get?access_token="+
			WeixinAccessUtil.getAccessToken(),null);
	}
	
	/**创建一个分组
	 * @param name
	 * @param http
	 * @return {"errcode": 40003, "errmsg": "invalid openid"}
	 */
	public static String createGroup(String name,CoreHttpClient http){
		Map<String ,String> param = Maps.newHashMap();
		param.put("name", name);
		JSONObject json = new JSONObject(param);
		if(null == http)
			http = new CoreHttpClient(WeixinAccessUtil.getBaseUrl());
		return http.postJson("cgi-bin/groups/create?access_token="+
			WeixinAccessUtil.getAccessToken(), json.toString());
	}
	
	/**
	 * 更改分组名称
	 * @param id
	 * @param name
	 * @return {"errcode": 40003, "errmsg": "invalid openid"}
	 */
	public static String updateGroup(long id,String name,CoreHttpClient http){
		Map<String ,String> param = Maps.newHashMap();
		param.put("id", id+"");
		param.put("name", name);
		JSONObject json = new JSONObject(param);
		if(null == http)
			http= new CoreHttpClient(WeixinAccessUtil.getBaseUrl());
		return http.postJson("cgi-bin/groups/update?access_token="+
			WeixinAccessUtil.getAccessToken(), json.toString());
	}
	
	/**
	 * 更改用户所在的分组
	 * @param openid
	 * @param groupId
	 * @return {"errcode": 40003, "errmsg": "invalid openid"}
	 */
	public static String changeUserGroup(String openid,long groupId,CoreHttpClient http){
		Map<String ,String> param = Maps.newHashMap();
		param.put("groupid", groupId+"");
		param.put("openid", openid);
		JSONObject json = new JSONObject(param);
		if(null == http)
			http = new CoreHttpClient(WeixinAccessUtil.getBaseUrl());
		return http.postJson("cgi-bin/groups/members/update?access_token="+
				WeixinAccessUtil.getAccessToken(), json.toString());
	}
	
	/**
	 * 查询用户所在的分组
	 * @param openid
	 * @param http
	 * {"groupid": 105}
	 */
	public static String getUserGroup(String openid,CoreHttpClient http){
		if(null == http)
			http= new CoreHttpClient(WeixinAccessUtil.getBaseUrl());
		return http.postJson("cgi-bin/groups/getid?access_token="+
			WeixinAccessUtil.getAccessToken(), "{\"openid\": \""+openid+"\"}");
	}
}
