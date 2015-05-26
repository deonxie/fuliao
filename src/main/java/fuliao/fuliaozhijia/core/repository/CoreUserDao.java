package fuliao.fuliaozhijia.core.repository;



import org.springframework.data.jpa.repository.Query;

import fuliao.fuliaozhijia.core.entity.UserEntity;
/**
 * 
 * @author DEON
 ** @Description: 简化DAO接口继承.
 *	@see IStringIdDao
 */
public interface CoreUserDao extends IStringIdDao<UserEntity>{
	@Query("from coreUser where id = ?1")
	public UserEntity queryById(String id);
	@Query("from coreUser where loginName= ?1")
	public UserEntity queryByLoginName(String loginName);
	@Query("select count(id) from coreUser where loginName= ?1")
	public long checkLoginName(String loginName);
}
