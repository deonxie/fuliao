package fuliao.fuliaozhijia.weixin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import fuliao.fuliaozhijia.core.repository.IStringIdDao;
import fuliao.fuliaozhijia.weixin.entity.CompetePrice;

public interface CompetePriceDao extends IStringIdDao<CompetePrice> {

	@Query("from weixinCompetePrice p where p.reqProduct.id=?1 order by p.price")
	List<CompetePrice> findByReqProId(String reqProId);
	
	@Query("select count(id) from weixinCompetePrice where shop.id=?1 and reqProduct.id=?2")
	int findShopHasOutPrice(String shopid, String reqprodid);

}
