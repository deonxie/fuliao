package fuliao.fuliaozhijia.core.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fuliao.fuliaozhijia.core.entity.RoleEntity;
import fuliao.fuliaozhijia.core.repository.CoreRoleDao;
import fuliao.fuliaozhijia.core.repository.IDao;

@Service("coreRoleService")
@Transactional(readOnly=true)
public class CoreRoleService extends GenericeService<RoleEntity, String>{
	@Autowired
	private CoreRoleDao roleDao;

	@Override
	public IDao<RoleEntity, String> getDao() {
		return roleDao;
	}

	@Transactional(readOnly=false)
	public String save(RoleEntity entity, String[] perms) {
		if(StringUtils.isBlank(entity.getId())){
			int count = roleDao.findByRoleName(entity.getRoleName());
			if(count >0)
				return entity.getRoleName()+"角色已存在";
		}
		if(null != perms && perms.length>0){
			StringBuffer sb = new StringBuffer();
			for(int i=0;i<perms.length;i++){
				if(i== perms.length-1)
					sb.append(perms[i]);
				else
					sb.append(perms[i]).append(RoleEntity.split);
			}
			entity.setPermissions(sb.toString());
		}
		super.save(entity);
		return null;
	}
	
}
