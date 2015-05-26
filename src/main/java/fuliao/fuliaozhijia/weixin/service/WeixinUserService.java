package fuliao.fuliaozhijia.weixin.service;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;

import fuliao.fuliaozhijia.core.repository.IDao;
import fuliao.fuliaozhijia.core.service.GenericeService;
import fuliao.fuliaozhijia.core.util.CoreHttpClient;
import fuliao.fuliaozhijia.weixin.entity.WeixinUser;
import fuliao.fuliaozhijia.weixin.repository.WeixinUserDao;
import fuliao.fuliaozhijia.weixin.util.WeixinAccessUtil;
import fuliao.fuliaozhijia.weixin.util.WeixinReturnStringUtil;
import fuliao.fuliaozhijia.weixin.util.WeixinSendMsgUtil;
import fuliao.fuliaozhijia.weixin.util.WeixinUserUtil;

@Service("weixinUserService")
@Transactional(readOnly=true)
public class WeixinUserService extends GenericeService<WeixinUser, String>{
	private static Logger logger = LoggerFactory.getLogger(WeixinUserService.class);
	@Autowired
	private WeixinUserDao dao;
	
	@Override
	public IDao<WeixinUser, String> getDao() {
		return dao;
	}
	
	@Transactional(readOnly=false)
	public void saveByOpenId(String openid) {
		long count = dao.findByOpenid(openid);
		if(count==0){
			WeixinUser user = WeixinReturnStringUtil.parseJsonToWeixinUser(openid);
			if(null != user){
				super.save(user);
				WeixinSendMsgUtil.sendMsgToUser(WeixinSendMsgUtil.welcomMsg(openid), null);
			}
		}else{
			dao.updateUnsubscribe(WeixinUser.SUBSCRIBE,openid);
		}
	}
	
	@Transactional(readOnly = false)
	public void bindLoginName2Openid(String loginName, String openid) {
		dao.bindLoginName2Openid(loginName, openid);
	}

	public void updateUnsubscribe(String openid) {
		dao.updateUnsubscribe(WeixinUser.UNSUBSCRIBE,openid);
	}

	public List<WeixinUser> findByUserLoginNames(List<String> loginNames) {
		if(loginNames ==null || loginNames.size()<1)
			return null;
		return dao.findByUserLoginNames(loginNames);
	}
	
	public WeixinUser findByLoginName(String loginName) {
		return dao.findByLoginName(loginName);
	}
	
	@Transactional(readOnly=false)
	public void importUserFromWeixin(){
		CoreHttpClient http = new CoreHttpClient(WeixinAccessUtil.getBaseUrl());
		List<String> ids = parsUserOpenid(null,http);
		if(ids !=null && ids.size()>0){
			WeixinUser tmp;
			for(String openid : ids){
				tmp = parsUserJsonStr(openid, http);
				if(null != tmp){
					long count = dao.findByOpenid(openid);
					if(count==0)
						dao.save(tmp);
				}
			}
			
		}
	}
	private List<String> parsUserOpenid(String next_openId,CoreHttpClient http){ 
		String weixinResult = WeixinUserUtil.getAllUsers(next_openId, http);
		try {
			JSONObject json = new JSONObject(weixinResult);
			JSONArray array = json.getJSONObject("data").getJSONArray("openid");
			List<String> openids = Lists.newArrayList();
			if(array !=null){
				for(int i=0;i<array.length();i++)
					openids.add(array.getString(i));
			}
			return openids;
		} catch (JSONException e) {
			logger.error("转换json失败"+weixinResult, e);
			return null;
		}
	}
	private WeixinUser parsUserJsonStr(String openId,CoreHttpClient http){ 
		String weixinResult = WeixinUserUtil.getUserInfo(openId, http);
		try {
			JSONObject json = new JSONObject(weixinResult);
			WeixinUser user = new WeixinUser();
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
			return user;
		} catch (JSONException e) {
			logger.error("转换json失败"+weixinResult, e);
			return null;
		}
	}
}
