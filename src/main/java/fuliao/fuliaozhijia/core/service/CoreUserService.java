package fuliao.fuliaozhijia.core.service;


import java.util.List;


import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;

import cn.gd.thinkjoy.modules.security.utils.Digests;
import cn.gd.thinkjoy.modules.utils.Encodes;
import fuliao.fuliaozhijia.core.entity.RoleEntity;
import fuliao.fuliaozhijia.core.entity.UserEntity;
import fuliao.fuliaozhijia.core.repository.CoreUserDao;
import fuliao.fuliaozhijia.core.repository.IDao;

@Service("coreUserService")
@Transactional(readOnly=true)
public class CoreUserService extends GenericeService<UserEntity, String>{
	public static final String SHA_1 = "SHA-1";
	public static final int SALT_LENGHT = 10;
	
	@Autowired
	private CoreUserDao userDao;

	@Override
	public IDao<UserEntity, String> getDao() {
		return userDao;
	}
	
	public UserEntity findByLoginName(String loginName){
		return userDao.queryByLoginName(loginName);
	}
	
	@Override
	@Transactional(readOnly = false)
	public void save(UserEntity entity) {
		if(null != entity.getTempPassword() && !"".equals(entity.getTempPassword())){
			entryptPassword(entity);
		}
		super.save(entity);
	}
	
	/**
	 * 注册用户
	 * @param user
	 * @return
	 */
	@Transactional(readOnly = false)
	public String register(UserEntity user) {
		if(StringUtils.isBlank(user.getLoginName()))
			return properties("CoreUserService.register.loginName.isblank");
		if(StringUtils.isBlank(user.getTempPassword()))
			return properties("CoreUserService.register.password.isblank");
		if(userDao.checkLoginName(user.getLoginName())>0)
			return properties("CoreUserService.register.loginName.isexist");
		entryptPassword(user);
		if(StringUtils.isBlank(user.getName()))
			user.setName(user.getLoginName());
		
		userDao.save(user);
		return Boolean.TRUE.toString();
	}
	
	private void entryptPassword(UserEntity user) {
        byte[] salt = Digests.generateSalt(SALT_LENGHT);
        user.setSalt(Encodes.encodeHex(salt));

        byte[] hashPassword = Digests.sha1(user.getTempPassword().getBytes(), salt, SALT_LENGHT);
        user.setPassword(Encodes.encodeHex(hashPassword));
	}
//	private boolean checkPassword(UserEntity user){
//		byte[] salt = Encodes.decodeHex(user.getSalt());
//		byte[] hashPassword = Digests.sha1(user.getTempPassword().getBytes(), salt, SALT_LENGHT);
//		String pwd = Encodes.encodeHex(hashPassword);
//		return pwd.equals(user.getPassword());
//	}

	@Transactional(readOnly=false)
	public String saveOrUpdate(UserEntity entity, String[] roles){
		if(StringUtils.isBlank(entity.getId())){
			long count = userDao.checkLoginName(entity.getLoginName());
			if(count >0)
				return entity.getLoginName()+"用户已存在";
		}
		if(StringUtils.isNotBlank(entity.getTempPassword()))
			entryptPassword(entity);
		if(null != roles && roles.length>0){
			List<RoleEntity> list = Lists.newArrayList();
			for(String roleId : roles){
				list.add(new RoleEntity(roleId));
			}
			entity.setRoles(list);
		}
		userDao.save(entity);
		return "";
	}

	
}
