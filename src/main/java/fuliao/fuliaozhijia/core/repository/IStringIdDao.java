package fuliao.fuliaozhijia.core.repository;


import org.springframework.data.repository.NoRepositoryBean;

import fuliao.fuliaozhijia.core.entity.IEntity;
/**
 * 
 * @author jlusoft
 ** @Description: 简化DAO接口继承.
 *	@see IDao
 */
@NoRepositoryBean
public interface IStringIdDao<E extends IEntity> extends IDao<E, String>{

}
