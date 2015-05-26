package fuliao.fuliaozhijia.weixin.controller.reqproduct;

import java.util.Date;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import fuliao.fuliaozhijia.core.controller.GenericeController;
import fuliao.fuliaozhijia.core.entity.UserEntity;
import fuliao.fuliaozhijia.core.service.ShiroDbRealm.ShiroUser;
import fuliao.fuliaozhijia.core.service.UploadPictureService;
import fuliao.fuliaozhijia.core.util.PageRequestUtil;
import fuliao.fuliaozhijia.core.util.SearchSpecifcation;
import fuliao.fuliaozhijia.core.util.SystemProperties;
import fuliao.fuliaozhijia.weixin.entity.CompetePrice;
import fuliao.fuliaozhijia.weixin.entity.RequestProduct;
import fuliao.fuliaozhijia.weixin.entity.Shops;
import fuliao.fuliaozhijia.weixin.service.CompetePriceService;
import fuliao.fuliaozhijia.weixin.service.ProductTypeService;
import fuliao.fuliaozhijia.weixin.service.RequestProductService;
import fuliao.fuliaozhijia.weixin.service.ShopsService;


@Controller
@RequestMapping("/weixin/reqproduct/")
public class FindProductController extends GenericeController<RequestProduct>{
	@Autowired
	@Qualifier("weixinRequestProductService")
	private RequestProductService ser;
	@Autowired
	@Qualifier("weixinProductTypeService")
	private ProductTypeService typeSer;
	@Autowired
	@Qualifier("weixinCompetePriceService")
	private CompetePriceService priceSer;
	@Autowired
	@Qualifier("coreUploadPictureService")
	private UploadPictureService fileSer;
	@Autowired
	@Qualifier("weixinShopsService")
	private ShopsService shopSer;
	
	/**
	 * 所以我的求购产品列表
	 * @param pageUtil
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("myreqProductList")
	public String myReqProductList(PageRequestUtil pageUtil,Model model,HttpServletRequest request){
		Map<String,String> params = getParams(request);
		params.put("EQ_user.id", getUser().id);
		pageUtil.addSort("createTime", "desc");
		Page<RequestProduct> page = ser.findByPage(new SearchSpecifcation<RequestProduct>(params), pageUtil.springPageRequest());
		model.addAttribute(PAGER, page);
		model.addAttribute(PAGE_UTIL, pageUtil);
		return "/weixin/reqproduct/myreqProductList";
	}
	/**
	 * 发布求购界面
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("myreqProductForm")
	public String myReqProduct(@RequestParam(value="id",defaultValue="")String id,Model model){
		model.addAttribute("entity", ser.findOne(id));
		model.addAttribute("types", typeSer.findAll());
		model.addAttribute("proUnits", SystemProperties.getInstance().getLike("fuliao.fuliaozhijia.weixin.entity.product.units\\d$"));
		return "/weixin/reqproduct/myreqProductForm";
	}
	/**
	 * 保存发布的求购信息
	 * @param prod
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value="savemyReqProduct", method=RequestMethod.POST)
	public String saveMyReqProduct(@ModelAttribute("formInitRequestProduct")RequestProduct prod,
			Model model,HttpServletRequest request){
		prod.setUser(new UserEntity(getUser().id));
		
		String error = ser.validation(prod);
		if(!StringUtils.isBlank(error)){
			model.addAttribute(ERROR_Msg, error);
			model.addAttribute("types", typeSer.findAll());
			model.addAttribute("proUnits", SystemProperties.getInstance().getLike("fuliao.fuliaozhijia.weixin.entity.product.units\\d$"));
			return "/weixin/reqproduct/myreqProductForm";
		}
		
		String img1 = request.getParameter("imgPath1");
		String img2 = request.getParameter("imgPath2");
		String img3 = request.getParameter("imgPath3");
		String cover = request.getParameter("cover");
		if(StringUtils.isNoneBlank(img1)){
			prod.setImage1(ser.saveProductPicture(img1, getRootPath(request),img1.equals(cover)));
		}if(StringUtils.isNoneBlank(img2)){
			prod.setImage2(ser.saveProductPicture(img2, getRootPath(request),img2.equals(cover)));
		}if(StringUtils.isNoneBlank(img3)){
			prod.setImage3(ser.saveProductPicture(img3, getRootPath(request),img3.equals(cover)));
		}
		ser.modfiy(prod);
		return "redirect:/weixin/user/userCenter";
	}
	
	@RequestMapping("updatemyReqProduct")
	public String upateReqPorductStatus(@RequestParam(value="id")String id,
			@RequestParam(value="type")String type,	RedirectAttributes redirect){
		if("cancel".equals(type)){
			ser.modfiyStatus(id,RequestProduct.status_cancel);
		}else if("finish".equals(type)){
			ser.modfiyStatus(id,RequestProduct.status_finish);
		}
		redirect.addAttribute("id",id);
		return "redirect:/weixin/reqproduct/reqProductDetail";
	}
	
	
	
	/**
	 * 系统求购产品列表
	 * @param pageUtil
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("reqProductList")
	public String sysReqProductList(PageRequestUtil pageUtil,Model model,HttpServletRequest request){
		pageUtil.addSort(getOrderBy(request));
		Map<String,String> params = getParams(request);
		if(StringUtils.isNotBlank(params.get("LIKE_name")))
			params.put("ORLIKE_type.typeName", params.get("LIKE_name"));
		params.put("EQ_status", RequestProduct.status_doing+"");
		ShiroUser user = getUser();
		if(user !=null)//排除用户自己发布求购
			params.put("NEQ_user.id", user.id);
		Page<RequestProduct> page = ser.findByPage(new SearchSpecifcation<RequestProduct>(params), pageUtil.springPageRequest());
		model.addAttribute(PAGER, page);
		model.addAttribute(PAGE_UTIL, pageUtil);
		model.addAttribute("prodTypes", typeSer.findAll());
		model.addAttribute(PARAMS_FIELD, params);
		return "/weixin/reqproduct/reqProductList";
	}
	
	/**
	 * 查看求购详细信息
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("reqProductDetail")
	public String reqProductDetail(@RequestParam(value="id")String id,
			HttpServletRequest request, Model model){
		model.addAttribute("entity", ser.findOne(id));
		ShiroUser user = getUser();
		Shops shop = shopSer.findByUserAndVerifyPass(user==null?null:user.id);
		boolean canOutPrice = (shop != null && priceSer.findShopHasOutPrice(shop.getId(),id)<1);
		model.addAttribute("canOutPrice", canOutPrice);
		model.addAttribute(SHIROUSER, user);
		return "/weixin/reqproduct/reqProductDetail";
	}
	
	/**
	 * 报价
	 * @param reqProId
	 * @param model
	 * @return
	 */
	@RequestMapping("saveOutPrice")
	public String saveOutPrice(@ModelAttribute CompetePrice compete,
			HttpServletRequest request,RedirectAttributes redirect){
		Shops shop = shopSer.findByUserAndVerifyPass(getUser().id);
		String error = "";
		if(shop != null){
			compete.setShop(shop);
			error = priceSer.validate(compete);
			if(StringUtils.isBlank(error)){
				compete.setCreateTime(new Date());
				priceSer.save(compete);
			}
		}else{
			error ="您还没有注册商铺或还未登录，无法竞价！";
		}
		redirect.addAttribute(ERROR_Msg, error);
		redirect.addAttribute("id", compete.getReqProduct().getId());
		return "redirect:/weixin/reqproduct/reqProductDetail";
	}
	
	private String getRootPath(HttpServletRequest request){
		return request.getServletContext().getRealPath("");
	}
	
	@ModelAttribute("formInitRequestProduct")
	public RequestProduct initRequestProduct(@RequestParam(value="id",defaultValue="")String id){
		if(StringUtils.isNotBlank(id))
			return ser.findOne(id);
		return new RequestProduct();
	}
}
