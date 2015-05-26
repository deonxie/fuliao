package fuliao.fuliaozhijia.weixin.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import fuliao.fuliaozhijia.core.repository.IStringIdDao;
import fuliao.fuliaozhijia.weixin.entity.RequestProduct;

public interface RequestProductDao extends IStringIdDao<RequestProduct> {

	@Modifying
	@Query("update weixinRequestProduct set status=?2,solveTime=?3 where id=?1")
	void modfiyStatus(String id, int status,Date solveTime);

}
