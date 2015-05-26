package fuliao.fuliaozhijia.core.service;


import java.io.Serializable;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

import fuliao.fuliaozhijia.core.util.SystemProperties;
import fuliao.fuliaozhijia.core.entity.IEntity;
import fuliao.fuliaozhijia.core.repository.IDao;
import fuliao.fuliaozhijia.core.service.ShiroDbRealm.ShiroUser;

/***
 * @author DEON
 * 通用实体服务
 * @param <E>：实体类
 * @param <ID>：id类型，Long或String
 * @see IEntity
 * @see IDao
 */
@Transactional(readOnly = true)
public abstract class GenericeService<E extends IEntity,ID extends Serializable> {
	public abstract IDao<E, ID> getDao();
	
	
	/**
	 * 按id查询实体
	 * @param id
	 * @return
	 */
	public E findOne(final ID id){
		return (E) getDao().findOne(id);
	}
	/**
	 * 按条件查询实体
	 * @param spec
	 * @return
	 */
	public E findOne(Specification<E> spec){
		return (E) getDao().findOne(spec);
	}
	/**
	 * 查询所有实体
	 * @return
	 */
	public Iterable<E> findAll(){
		return getDao().findAll();
	}
	/**
	 * 按id集合查询实体
	 * @param ids
	 * @return
	 */
	public Iterable<E> findAll(final Iterable<ID> ids){
		return getDao().findAll(ids);
	}
	/**
	 * 分页查询
	 * @param spec
	 * @param pageable
	 * @return
	 */
	public Page<E> findByPage(Specification<E> spec,Pageable pageable){
		Page<E> page = getDao().findAll(spec, pageable);
		
		return page;
	}
	/**
	 * 排序查询查询
	 * @param spec 查询规则
	 * @param sort 排序规则
	 * @return
	 */
	public List<E> findByCondition(Specification<E> spec,Sort sort){
		return getDao().findAll(spec,sort);
	}
	/**
	 * 保存实体
	 * @param entity 实体
	 * @return
	 */
	@Transactional(readOnly = false)
	public void save(E entity){
		getDao().save(entity);
	}
	/**
	 * 批量保存实体
	 * @param entities 实体集合
	 * @return
	 */
	@Transactional(readOnly = false)
	public void save(Iterable<E> entities){
		getDao().save(entities);
	}
	/**
	 * 按id删除
	 * @param id 编号值
	 * @return
	 */
	@Transactional(readOnly = false)
	public void delete(ID id){
		getDao().delete(id);
	}
	/**
	 * 按实体删除
	 * @param entity 实体
	 * @return
	 */
	@Transactional(readOnly = false)
	public void delete(E entity){
		getDao().delete(entity);
	}
	/**
	 * 删除所有
	 * @return
	 */
	@Transactional(readOnly = false)
	public void deleteAll(){
		getDao().deleteAll();
	}
	 
    /**
     * 获取系统system.properties文件中的数据
     * @param key
     * @return
     */
    public String properties(String key){
    	return SystemProperties.getInstance().getValue(key);
    }
    
    /**获取登录用户*/
    public ShiroUser getUser(){
    	Subject subject = SecurityUtils.getSubject();
    	if(subject !=null){
    		Object obj = subject.getPrincipal();
    		if(obj !=null && obj instanceof ShiroUser)
    			return (ShiroUser)obj;
    	}
    	return null;
    }
}
