package fuliao.fuliaozhijia.weixin.util;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import fuliao.fuliaozhijia.core.util.CoreHttpClient;
import fuliao.fuliaozhijia.core.util.SystemProperties;

public class WeixinMenuUtil {
	
	/**
	 * 获取所有自定义菜单
	 * @param http
	 * @return
	 */
	public static String getAllMenu(CoreHttpClient http){
		if(null == http)
			http = new CoreHttpClient(WeixinAccessUtil.getBaseUrl());
		return http.get("cgi-bin/menu/get?access_token="+WeixinAccessUtil.getAccessToken(), null);
	}
	
	/**
	 * 创建所有菜单
	 * @param http
	 * @return
	 * @format { "button": [{"type": "click", "name": "测试", "key": "V1001_TODAY_MUSIC" "sub_button":[]}]}
	 */
	public static String createMenu(CoreHttpClient http){
		List<Map<String, Object>> list = initMenu(4, "Weixin.menu.frist.level.");
		if(list.size()>0){
			Map<String, Object> menu = Maps.newHashMap();
			menu.put("button", list);
			if(null == http)
				http = new CoreHttpClient(WeixinAccessUtil.getBaseUrl());
			return http.postJson("cgi-bin/menu/create?access_token="+
					WeixinAccessUtil.getAccessToken(), new JSONObject(menu).toString());
		}
		return "";
	}
	
	/**
	 * 删除自定义菜单
	 * @param menuid
	 * @param http
	 * @return
	 */
	public static String deleteMenu(CoreHttpClient http){
		if(null == http)
			http = new CoreHttpClient(WeixinAccessUtil.getBaseUrl());
		return http.get("cgi-bin/menu/delete?access_token="+WeixinAccessUtil.getAccessToken(), null);
	}
	
	/**
	 * 通过system.properties文件获取自定义菜单
	 * @param maxSzie
	 * @param properyKey
	 * @return
	 */
	private static List<Map<String, Object>> initMenu(int maxSzie,String properyKey){
		List<Map<String, Object>> list = Lists.newArrayList();
		Map<String, Object> map;
		String name,type,key,url;
		for(int i=1 ;i<maxSzie; i++){
			name = SystemProperties.getInstance().getValue(properyKey+i);
			if(!StringUtils.isBlank(name)){
				type = SystemProperties.getInstance().getValue(properyKey+i+".type");
				key = SystemProperties.getInstance().getValue(properyKey+i+".key");
				url = SystemProperties.getInstance().getValue(properyKey+i+".url");
				if("click".equals(type) && !StringUtils.isBlank(key)){
					map = Maps.newHashMap();
					map.put("name", name);
					map.put("type", "click");
					map.put("key", StringUtils.trim(key));
					List<Map<String, Object>> sublist = initMenu(6,properyKey+i+".second.");
					if(sublist.size()>0)
						map.put("sub_button", sublist);
					list.add(map);
					continue;
				}
				if("view".equals(type) && !StringUtils.isBlank(url)){
					map = Maps.newHashMap();
					map.put("name", name);
					map.put("type", "view");
					map.put("url", StringUtils.trim(url));
					List<Map<String, Object>> sublist = initMenu(6,properyKey+i+".second.");
					if(sublist.size()>0)
						map.put("sub_button", sublist);
					list.add(map);
					continue;
				}
			}
		}
		return list;
	}
}
