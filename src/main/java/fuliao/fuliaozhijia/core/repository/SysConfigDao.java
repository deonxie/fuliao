package fuliao.fuliaozhijia.core.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import fuliao.fuliaozhijia.core.entity.SysConfig;

public interface SysConfigDao extends ILongIdDao<SysConfig> {
	@Query("from SysConfig where status=?1")
	List<SysConfig> findAllCanUse(int status);
}
