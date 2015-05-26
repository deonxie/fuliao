package fuliao.fuliaozhijia.weixin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import fuliao.fuliaozhijia.core.repository.IStringIdDao;
import fuliao.fuliaozhijia.weixin.entity.Shops;

public interface ShopsDao extends IStringIdDao<Shops> {
	@Query("from weixinShops where user.id = ?1")
	Shops findByUserId(String id);
	@Query("from weixinShops where user.id = ?1 and status=?2")
	Shops findByUserIdAndPass(String id, int statusVerifyPass);
	@Query("from weixinShops where mainBusines like ?1 and status=?2")
	List<Shops> findShopByMainBusines(String type, int statusVerifyPass);
	@Modifying
	@Query("update weixinShops set status=?3 ,authcUserName=?2 ,failReason=?4 where id=?1")
	void verifyShop(String id, String name, int statusVerifyPass, String reason);
	@Query("select count(status) from weixinShops where status=?1")
	long findNotVerifyShop(int statusNoVerify);

}
