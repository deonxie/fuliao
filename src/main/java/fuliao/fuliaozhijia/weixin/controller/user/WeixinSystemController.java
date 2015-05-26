package fuliao.fuliaozhijia.weixin.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import fuliao.fuliaozhijia.core.controller.GenericeController;
import fuliao.fuliaozhijia.weixin.entity.RequestProduct;
import fuliao.fuliaozhijia.weixin.service.ShopsService;


@Controller
@RequestMapping("/weixin/system/")
public class WeixinSystemController extends GenericeController<RequestProduct>{
	
	@Autowired
	@Qualifier("weixinShopsService")
	private ShopsService shopSer;
	
	/**
	 * 手机网页主界面
	 * @return
	 */
	@RequestMapping("systemCenter")
	public String index(Model model){
		model.addAttribute("authcShops", shopSer.findNoVerifyCount());
		return "/weixin/user/systemCenter";
	}

}
