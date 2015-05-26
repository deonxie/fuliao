package fuliao.fuliaozhijia.weixin.controller.user;


import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fuliao.fuliaozhijia.core.controller.GenericeController;
import fuliao.fuliaozhijia.core.entity.UserEntity;
import fuliao.fuliaozhijia.core.service.CoreUserService;
import fuliao.fuliaozhijia.core.util.PageRequestUtil;
import fuliao.fuliaozhijia.core.util.SearchSpecifcation;
import fuliao.fuliaozhijia.weixin.entity.Favorites;
import fuliao.fuliaozhijia.weixin.entity.WeixinUser;
import fuliao.fuliaozhijia.weixin.service.FavoritesService;
import fuliao.fuliaozhijia.weixin.service.ShopsService;
import fuliao.fuliaozhijia.weixin.service.WeixinUserService;


@Controller
@RequestMapping("/weixin/user/")
public class WeixinUserController extends GenericeController<WeixinUser>{
	private static Logger logger = LoggerFactory.getLogger(WeixinUserController.class);
	
	@Autowired
	@Qualifier("coreUserService")
	private CoreUserService userSer;
	@Autowired
	@Qualifier("weixinUserService")
	private WeixinUserService weixinUserSer;
	@Autowired
	@Qualifier("weixinShopsService")
	private ShopsService shopSer;
	@Autowired
	@Qualifier("weixinFavoritesService")
	FavoritesService favtSer;
	
	/**
	 * 手机网页主界面
	 * @return
	 */
	@RequestMapping("userCenter")
	public String userCenter(HttpServletRequest request,Model model){
		model.addAttribute("hasPassShop", shopSer.findByUserAndVerifyPass(getUser().id));
		model.addAttribute("weixinUser", weixinUserSer.findByLoginName(getUser().loginName));
		return "/weixin/user/userCenter";
	}
//	redirect
	@RequestMapping("improtUser")
	public String improtUser(HttpServletRequest request,Model model){
		weixinUserSer.importUserFromWeixin();
		logger.info("导入微信用户导入成功！");
		return "redirect:/weixin/user/weixinUserList";
	}
	@RequestMapping("weixinUserList")
	public String weixinUserList(PageRequestUtil pageUtil,Model model,HttpServletRequest request){
		Page<WeixinUser> page = weixinUserSer.findByPage(getSpec(request), pageUtil.springPageRequest());
		model.addAttribute(PAGER, page);
		model.addAttribute(PAGE_UTIL, pageUtil);
		return "/weixin/user/weixinUserList";
	}
	
	@RequestMapping("myFavtprod")
	public String favtProduct(PageRequestUtil pageUtil ,HttpServletRequest request,Model model){
		Map<String,String> params = getParams(request);
		params.put("EQ_favUser.id", getUser().id);
		Page<Favorites> page = favtSer.findByPage(new SearchSpecifcation<Favorites>(params), pageUtil.springPageRequest());
		model.addAttribute(PAGER, page);
		model.addAttribute(PAGE_UTIL, pageUtil);
		return "/weixin/user/favoritesrList";
	}
	@RequestMapping("deleteFavt")
	public String deleteFavt(@RequestParam("id")String id,HttpServletRequest request){
		favtSer.delete(id);
		return "redirect:/weixin/user/myFavtprod";
	}
	
	@RequestMapping("userinfo")
	public String userInfo(Model model){
		model.addAttribute(ENTITY, userSer.findOne(getUser().id));
		return "/weixin/user/userInfoForm";
	}
	
	@RequestMapping("saveuserinfo")
	public String saveUserInfo(@ModelAttribute UserEntity user,Model model){
		UserEntity old = userSer.findOne(getUser().id);
		old.setName(user.getName());
		old.setTelphone(user.getTelphone());
		old.setEmail(user.getEmail());
		old.setAddress(user.getAddress());
		userSer.save(old);
		return "redirect:/weixin/user/userCenter";
	}
	//	userInfoForm.jsp
}
