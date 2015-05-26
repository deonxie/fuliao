package fuliao.fuliaozhijia.core.repository;


import org.springframework.data.jpa.repository.Query;

import fuliao.fuliaozhijia.core.entity.RoleEntity;

public interface CoreRoleDao extends IStringIdDao<RoleEntity> {

	@Query("select count(id) from coreRole where roleName=?1")
	int findByRoleName(String roleName);

}
