package fuliao.fuliaozhijia.weixin.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fuliao.fuliaozhijia.core.entity.UserEntity;
import fuliao.fuliaozhijia.core.repository.IDao;
import fuliao.fuliaozhijia.core.service.CoreUserService;
import fuliao.fuliaozhijia.core.service.GenericeService;
import fuliao.fuliaozhijia.weixin.entity.Favorites;
import fuliao.fuliaozhijia.weixin.entity.Product;
import fuliao.fuliaozhijia.weixin.entity.Shops;
import fuliao.fuliaozhijia.weixin.repository.FavoritesDao;
@Service("weixinFavoritesService")
@Transactional(readOnly=true)
public class FavoritesService extends GenericeService<Favorites, String> {
	@Autowired
	FavoritesDao dao;

	@Autowired
	@Qualifier("weixinProductService")
	ProductService prodcutSer;
	@Autowired
	@Qualifier("weixinShopsService")
	ShopsService shopSer;
	@Autowired
	@Qualifier("coreUserService")
	CoreUserService userSer;
	
	@Override
	public IDao<Favorites, String> getDao() {
		return dao;
	}
	
	@Transactional(readOnly=false)
	public boolean favoritesShop(String shopid,UserEntity user){
		Shops shop = shopSer.findOne(shopid);
		if(null != shop && !shop.getUser().getId().equals(user.getId())){
			int count = dao.findShop(shopid,user.getId());
			if(count>0)
				return false;
			Favorites fav = new Favorites();
			fav.setShop(shop);
			fav.setFavUser(user);
			fav.setCreateTime(new Date());
			super.save(fav);
			shop.setSotreNum(shop.getSotreNum()+1);
			shopSer.save(shop);
			return true;
		}
		return false;
	}
	@Transactional(readOnly=false)
	public boolean favoritesProduct(String prodId,UserEntity user){
		Product prod = prodcutSer.findOne(prodId);
		if(null != prod && !prod.getShops().getUser().getId().equals(user.getId())){
			int count = dao.findProd(prodId, user.getId());
			if(count>0)
				return false;
			Favorites fav = new Favorites();
			fav.setProdcut(prod);
			fav.setFavUser(user);
			fav.setCreateTime(new Date());
			super.save(fav);
			prod.setStoreNum(prod.getStoreNum()+1);
			prodcutSer.save(prod);
			return true;
		}
		return false;
	}
}
