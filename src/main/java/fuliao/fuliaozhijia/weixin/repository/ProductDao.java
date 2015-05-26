package fuliao.fuliaozhijia.weixin.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import fuliao.fuliaozhijia.core.repository.IStringIdDao;
import fuliao.fuliaozhijia.weixin.entity.Product;

public interface ProductDao extends IStringIdDao<Product> {

	@Modifying
	@Query("update weixinProduct set status=?2 where id=?1")
	void updateProductStatus(String id, int status);

}
