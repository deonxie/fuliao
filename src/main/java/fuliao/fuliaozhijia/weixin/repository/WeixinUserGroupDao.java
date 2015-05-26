package fuliao.fuliaozhijia.weixin.repository;

import org.springframework.data.jpa.repository.Query;

import fuliao.fuliaozhijia.core.repository.ILongIdDao;
import fuliao.fuliaozhijia.weixin.entity.WeixinUserGroup;

public interface WeixinUserGroupDao extends ILongIdDao<WeixinUserGroup> {

	@Query("select count(id) from WeixinUserGroup where name=?1")
	int findNameCount(String name);

}
