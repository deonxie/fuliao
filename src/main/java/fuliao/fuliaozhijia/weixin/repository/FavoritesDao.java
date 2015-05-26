package fuliao.fuliaozhijia.weixin.repository;

import org.springframework.data.jpa.repository.Query;

import fuliao.fuliaozhijia.core.repository.IStringIdDao;
import fuliao.fuliaozhijia.weixin.entity.Favorites;

public interface FavoritesDao extends IStringIdDao<Favorites> {

	@Query("select count(id) from weixinFavorites where shop.id=?1 and favUser.id=?2")
	int findShop(String shopid, String userid);
	@Query("select count(id) from weixinFavorites where prodcut.id=?1 and favUser.id=?2")
	int findProd(String prodid, String userid);
}
