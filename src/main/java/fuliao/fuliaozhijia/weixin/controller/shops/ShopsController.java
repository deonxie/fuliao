package fuliao.fuliaozhijia.weixin.controller.shops;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
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
import fuliao.fuliaozhijia.core.service.UploadPictureService;
import fuliao.fuliaozhijia.core.service.UploadPictureService.SavePath;
import fuliao.fuliaozhijia.core.util.PageRequestUtil;
import fuliao.fuliaozhijia.core.util.SearchSpecifcation;
import fuliao.fuliaozhijia.weixin.entity.CompetePrice;
import fuliao.fuliaozhijia.weixin.entity.Product;
import fuliao.fuliaozhijia.weixin.entity.Shops;
import fuliao.fuliaozhijia.weixin.service.CompetePriceService;
import fuliao.fuliaozhijia.weixin.service.ProductService;
import fuliao.fuliaozhijia.weixin.service.ProductTypeService;
import fuliao.fuliaozhijia.weixin.service.ShopsService;

@Controller
@RequestMapping("/weixin/shops/")
public class ShopsController extends GenericeController<Shops> {

	@Autowired
	@Qualifier("weixinShopsService")
	protected ShopsService ser;
	@Autowired
	@Qualifier("weixinProductTypeService")
	private ProductTypeService typeSer;
	@Autowired
	@Qualifier("coreUploadPictureService")
	private UploadPictureService fileSer;
	@Autowired
	@Qualifier("weixinProductService")
	private ProductService prodSer;
	@Autowired
	@Qualifier("weixinCompetePriceService")
	private CompetePriceService comPiceSer;
	
	@RequestMapping("")
	public String createShops(HttpServletRequest request,Model model){
		model.addAttribute("entity", ser.findByUser(new UserEntity(getUser().id)));
		model.addAttribute("types", typeSer.findAll());
		return "/weixin/shops/shopForm";
	}
	
	@RequestMapping("saveShops")
	public String saveShops(@ModelAttribute("formShops") Shops shop,Model model,HttpServletRequest request){
		String[] busines = request.getParameterValues("busines");
		if(null != busines){
			StringBuffer sb = new StringBuffer();
			for(String tmp :busines){
				if(!StringUtils.isBlank(tmp))
					sb.append(StringUtils.trimToEmpty(tmp)).append(",");
			}
			if(sb.length()>0)
				shop.setMainBusines(sb.substring(0, sb.length()-1));
		}
		String error = ser.validation(shop);
		if(!StringUtils.isBlank(error)){
			model.addAttribute(ERROR_Msg, error);
			model.addAttribute("types", typeSer.findAll());
			return "/weixin/shops/shopForm";
		}
			
		shop.setUser(new UserEntity(getUser().id));
		String logo = request.getParameter("logoImg");
		String img1 = request.getParameter("imgPath1");
		String img2 = request.getParameter("imgPath2");
		String img3 = request.getParameter("imgPath3");
		if(StringUtils.isNoneBlank(logo)){
			shop.setLogo(ser.saveProductPicture(logo, getRootPath(request),false));
		}if(StringUtils.isNoneBlank(img1)){
			shop.setVerify1(fileSer.saveOneFileGetEntity(img1, request, SavePath.SHOP_GROUP_IMG));
		}if(StringUtils.isNoneBlank(img2)){
			shop.setVerify2(fileSer.saveOneFileGetEntity(img2, request, SavePath.SHOP_GROUP_IMG));
		}if(StringUtils.isNoneBlank(img3)){
			shop.setVerify3(fileSer.saveOneFileGetEntity(img3, request, SavePath.SHOP_GROUP_IMG));
		}
		ser.modfiy(shop);
		return "redirect:/weixin/user/userCenter";
	}
	/**
	 * 求购查看商铺详情
	 * @param id
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("shopDetail")
	public String shopDetail(@RequestParam("id")String id ,Model model,HttpServletRequest request){
		model.addAttribute("entity", ser.findOne(id));
		Map<String, String> params = getParams(request);
		params.put("EQ_shops.id", id);
		params.put("EQ_status", Product.status_enable+"");
		PageRequestUtil page = new PageRequestUtil(1, 6);
		page.addSort("saleNum", PageRequestUtil.DESC);
		page.addSort("storeNum", PageRequestUtil.DESC);
		
		model.addAttribute("products", prodSer.findByPage(new SearchSpecifcation<Product>(params), 
				page.springPageRequest()).getContent());
		model.addAttribute("loginUser", getUser());
		return "/weixin/shops/shopDetail";
	}
	/**
	 * 通过审核商家
	 * @param pageUtil
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("passShopList")
	public String passShopList(PageRequestUtil pageUtil,Model model,HttpServletRequest request){
		Map<String,String> params = getParams(request);
		params.put("EQ_status", Shops.STATUS_VERIFY_PASS+"");
		Map<String,String> order = getOrderBy(request);
		pageUtil.addSort(order);
		Page<Shops> page = ser.findByPage(new SearchSpecifcation<Shops>(params), 
				pageUtil.springPageRequest());
		model.addAttribute(PAGER, page);
		model.addAttribute(PAGE_UTIL, pageUtil);
		return "/weixin/shops/passShopList";
	}
	/**
	 * 待审核商家列表
	 * @param pageUtil
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("verifyShopList")
	public String verifyShopList(PageRequestUtil pageUtil,Model model,HttpServletRequest request){
		Map<String,String> params = getParams(request);
		params.put("EQ_status", Shops.STATUS_NO_VERIFY+"");
		Map<String,String> order = getOrderBy(request);
		pageUtil.addSort(order);
		Page<Shops> page = ser.findByPage(new SearchSpecifcation<Shops>(params), 
				pageUtil.springPageRequest());
		model.addAttribute(PAGER, page);
		model.addAttribute(PAGE_UTIL, pageUtil);
		return "/weixin/shops/verifyShopList";
	}
	
	
	@RequestMapping("verifyShop")
	public String verifyShop(@RequestParam("id")String id ,Model model){
		model.addAttribute("entity", ser.findOne(id));
		return "/weixin/shops/verifyShop";
	}
	
	@RequestMapping("passVerify")
	public String passVerify(@RequestParam("id")String id){
		 ser.verifyShop(id,true,null);
		return "forward:/weixin/shops/verifyShopList";
	}
	@RequestMapping("failVerify")
	public String failVerify(@RequestParam("id")String id ,@RequestParam("reason")String reason){
		ser.verifyShop(id,false,reason);
		return "forward:/weixin/shops/verifyShopList";
	}
	/**
	 * 竞价列表
	 * @param pageUtil
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("comparePirceList")
	public String comparePirceList(PageRequestUtil pageUtil,Model model,HttpServletRequest request){
		Map<String,String> params = getParams(request);
		params.put("EQ_shop.user.id", getUser().id);
		Map<String,String> order = getOrderBy(request);
		pageUtil.addSort(order);
		Page<CompetePrice> page = comPiceSer.findByPage(new SearchSpecifcation<CompetePrice>(params), 
				pageUtil.springPageRequest());
		model.addAttribute(PAGER, page);
		model.addAttribute(PAGE_UTIL, pageUtil);
		return "/weixin/shops/comparePirceList";
	}

	private String getRootPath(HttpServletRequest request){
		return request.getServletContext().getRealPath("");
	}
	
	@ModelAttribute("formShops")
	public Shops initProduct(@RequestParam(value="id",defaultValue="")String id){
		if(StringUtils.isNotBlank(id))
			return ser.findOne(id);
		return new Shops();
	}
}
