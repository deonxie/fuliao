package fuliao.fuliaozhijia.weixin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import fuliao.fuliaozhijia.core.repository.IStringIdDao;
import fuliao.fuliaozhijia.weixin.entity.WeixinUser;

public interface WeixinUserDao extends IStringIdDao<WeixinUser> {
	
	@Query("select count(id) from WeixinUser where openid=?1")
	long findByOpenid(String weixinUserId);

	@Modifying
	@Query("update WeixinUser set loginName=?1 where openid=?2")
	void bindLoginName2Openid(String loginName, String openid);
	@Modifying
	@Query("update WeixinUser set subscribe=?1 where openid=?2")
	void updateUnsubscribe(int unsubscribe, String openid);
	@Query("from WeixinUser where loginName in ?1 ")
	List<WeixinUser> findByUserLoginNames(List<String> loginNames);
	@Query("from WeixinUser where openid in ?1 ")
	WeixinUser findUserOpenid(String openid);
	@Query("from WeixinUser where loginName =?1")
	WeixinUser findByLoginName(String loginName);
	
}
