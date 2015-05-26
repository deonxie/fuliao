package fuliao.fuliaozhijia.weixin.controller.rest;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fuliao.fuliaozhijia.core.controller.GenericeController;
import fuliao.fuliaozhijia.core.entity.UserEntity;
import fuliao.fuliaozhijia.weixin.entity.Favorites;
import fuliao.fuliaozhijia.weixin.service.FavoritesService;

@RestController
@RequestMapping("/weixin/favorite/")
public class FavoritesController extends GenericeController<Favorites>{
	@Autowired
	@Qualifier("weixinFavoritesService")
	FavoritesService ser;
	
	@RequestMapping("shop")
	public boolean favtShop(@RequestParam("id")String shopid,HttpServletRequest request){
		return ser.favoritesShop(shopid, new UserEntity(getUser().id));
	}
	@RequestMapping("prod")
	public boolean favtProduct(@RequestParam("id")String prodid,HttpServletRequest request){
		return ser.favoritesProduct(prodid, new UserEntity(getUser().id));
	}
}
