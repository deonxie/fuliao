package fuliao.fuliaozhijia.weixin.controller.product;



import java.util.Date;
import java.util.Map;

import javax.servlet.ServletRequest;
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
import fuliao.fuliaozhijia.core.service.ShiroDbRealm.ShiroUser;
import fuliao.fuliaozhijia.core.util.PageRequestUtil;
import fuliao.fuliaozhijia.core.util.SearchSpecifcation;
import fuliao.fuliaozhijia.core.util.SystemProperties;
import fuliao.fuliaozhijia.weixin.entity.Product;
import fuliao.fuliaozhijia.weixin.entity.ProductType;
import fuliao.fuliaozhijia.weixin.entity.Shops;
import fuliao.fuliaozhijia.weixin.service.ProductService;
import fuliao.fuliaozhijia.weixin.service.ProductTypeService;
import fuliao.fuliaozhijia.weixin.service.ShopsService;


@Controller
@RequestMapping("/weixin/product/")
public class ProductController extends GenericeController<Product>{
	@Autowired
	@Qualifier("weixinProductService")
	private ProductService ser;
	@Autowired
	@Qualifier("weixinProductTypeService")
	private ProductTypeService typeSer;
	@Autowired
	@Qualifier("weixinShopsService")
	private ShopsService shopSer;
	
	/**
	 * 所以产品类型列表
	 * @param pageUtil
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("productTypeList")
	public String productTypeList(PageRequestUtil pageUtil,Model model,ServletRequest request){
		Page<ProductType> page = typeSer.findByPage(new SearchSpecifcation<ProductType>(getParams(request)), 
				pageUtil.springPageRequest());
		model.addAttribute(PAGER, page);
		model.addAttribute(PAGE_UTIL, pageUtil);
		return "/weixin/product/productTypeList";
	}
	/**
	 * 保存产品类型
	 * @param pageUtil
	 * @param model
	 * @param request
	 */
	@RequestMapping("saveType")
	public String saveType(@ModelAttribute ProductType productType,Model model){
		if(productType.getId()==0)
			productType.setCreateTime(new Date());
		typeSer.save(productType);
		return "redirect:/weixin/product/productTypeList";
	}
	
	/**
	 * 分页查询产品
	 * @param pageUtil
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("productList")
	public String productList(PageRequestUtil pageUtil,Model model,HttpServletRequest request){
		pageUtil.addSort(getOrderBy(request));
		Map<String,String> params = getParams(request);
		if(StringUtils.isNotBlank(params.get("LIKE_name")))
			params.put("ORLIKE_type.typeName", params.get("LIKE_name"));
		params.put("EQ_status", Product.status_enable+"");
		ShiroUser user = getUser();
		if(user !=null)//排除用户自己产品
			params.put("NEQ_shops.user.id", user.id);
		Page<Product> page = ser.findByPage(new SearchSpecifcation<Product>(params), 
				pageUtil.springPageRequest());
		model.addAttribute(PAGER, page);
		model.addAttribute(PAGE_UTIL, pageUtil);
		model.addAttribute("prodTypes", typeSer.findAll());
		model.addAttribute(PARAMS_FIELD, params);
		return "/weixin/product/productList";
	}
	/**
	 * 查询产品详情界面
	 * @param id
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("productDetail")
	public String productDetail(@RequestParam("id")String id,Model model,ServletRequest request){
		model.addAttribute(ENTITY, ser.findOne(id));
		model.addAttribute(SHIROUSER, getUser());
		return "/weixin/product/productDetail";
	}
	/***
	 * 修改产品上下架功能
	 * @param id
	 * @param type
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("updateProduct")
	public String updateProduct(@RequestParam("id")String id,@RequestParam("type")String type,
			Model model,ServletRequest request){
		if("cancel".equals(type))
			ser.updateProduct(id,Product.status_disable);
		else if("publish".equals(type))
			ser.updateProduct(id,Product.status_enable);
		return "redirect:/weixin/product/productDetail?id="+id;
	}
	/**
	 * 分页查询产品
	 * @param pageUtil
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("myproductList")
	public String myProductList(PageRequestUtil pageUtil,Model model,HttpServletRequest request){
		ShiroUser user = getUser();
		Map<String,String> params = getParams(request);
		Shops shop = shopSer.findByUserAndVerifyPass(user.id);
		Page<Product> page = null;
		if(shop != null){
			params.put("EQ_shops.id", shop.getId());
			pageUtil.addSort("createTime", "desc");
			page = ser.findByPage(new SearchSpecifcation<Product>(params), 
					pageUtil.springPageRequest());
		}
		model.addAttribute(PAGER, page);
		model.addAttribute(PAGE_UTIL, pageUtil);
		return "/weixin/product/myproductList";
	}
	/**
	 * 发布新产品界面
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("myproductForm")
	public String myproductForm(@RequestParam(value="id",defaultValue="")String id,Model model){
		Product entity = ser.findOne(id);
		
		model.addAttribute("entity", entity);
		model.addAttribute("types", typeSer.findAll());
		model.addAttribute("proUnits", SystemProperties.getInstance().getLike("fuliao.fuliaozhijia.weixin.entity.product.units\\d$"));
		return "/weixin/product/myproductForm";
	}
	/**
	 * 保存新发布的产品
	 * @param prodcut
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("saveMyproduct")
	public String saveMyproduct(@ModelAttribute("formInitPorduct")Product prodcut,
			Model model,HttpServletRequest request){
		String error = ser.validation(prodcut);
		if(!StringUtils.isBlank(error)){
			model.addAttribute(ERROR_Msg, error);
			model.addAttribute("types", typeSer.findAll());
			model.addAttribute("proUnits", SystemProperties.getInstance().getLike("fuliao.fuliaozhijia.weixin.entity.product.units\\d$"));
			return "/weixin/product/myproductForm";
		}
		ShiroUser user = getUser();
		prodcut.setShops(shopSer.findByUserAndVerifyPass(user.id));
		
		String img1 = request.getParameter("imgPath1");
		String img2 = request.getParameter("imgPath2");
		String img3 = request.getParameter("imgPath3");
		String cover = request.getParameter("cover");
		if(StringUtils.isNoneBlank(img1)){
			prodcut.setImage1(ser.saveProductPicture(img1, getRootPath(request),img1.equals(cover)));
		}if(StringUtils.isNoneBlank(img2)){
			prodcut.setImage2(ser.saveProductPicture(img2, getRootPath(request),img2.equals(cover)));
		}if(StringUtils.isNoneBlank(img3)){
			prodcut.setImage3(ser.saveProductPicture(img3, getRootPath(request),img3.equals(cover)));
		}
		ser.modfiy(prodcut);
		return "forward:/weixin/user/userCenter";
	}
	
	private String getRootPath(HttpServletRequest request){
		return request.getServletContext().getRealPath("");
	}
	
	@ModelAttribute("formInitPorduct")
	public Product initProduct(@RequestParam(value="id",defaultValue="")String id){
		if(StringUtils.isNotBlank(id))
			return ser.findOne(id);
		return new Product();
	}
}
