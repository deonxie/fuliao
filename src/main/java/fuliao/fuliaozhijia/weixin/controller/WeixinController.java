package fuliao.fuliaozhijia.weixin.controller;




import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import fuliao.fuliaozhijia.core.controller.GenericeController;
import fuliao.fuliaozhijia.core.entity.UserEntity;
import fuliao.fuliaozhijia.core.service.CoreUserService;
import fuliao.fuliaozhijia.core.service.SmsLogService;
import fuliao.fuliaozhijia.weixin.entity.RequestProduct;
import fuliao.fuliaozhijia.weixin.service.ShopsService;
import fuliao.fuliaozhijia.weixin.service.WeixinUserService;


@Controller
@RequestMapping("/weixin/")
public class WeixinController extends GenericeController<RequestProduct>{
	private static Logger logger = LoggerFactory.getLogger(WeixinController.class);
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
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
	@Qualifier("coreSmsLogService")
	private SmsLogService smsSer;
	/**
	 * 手机网页主界面
	 * @return
	 */
	@RequestMapping("")
	public String welcome(){
		return "/weixin/index";
	}
	
	@RequestMapping("index")
	public String index(){
		return "/weixin/index";
	}
	@RequestMapping(value="login",method = RequestMethod.GET)
	public String login() {
		return "/weixin/login";
	}

	@RequestMapping(value="login",method = RequestMethod.POST)
	public String fail(@RequestParam(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM) String userName, Model model) {
		model.addAttribute(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM, userName);
		model.addAttribute(ERROR_Msg, "用户名或密码错误！");
		return "/weixin/login";
	}

	/**
	 * 手机网页注册
	 * @return
	 */
	@RequestMapping(value="register",method=RequestMethod.GET)
	public String registerUser(@RequestParam(value="registerCode",defaultValue="")String openid, Model model){
		model.addAttribute("registerCode", openid);
		logger.info("用户注册："+openid);
		return "/weixin/register";
	}
	
	/**
	 * 手机网页注册
	 * @return
	 */
	@RequestMapping(value="register",method=RequestMethod.POST)
	public String registerUser(@ModelAttribute UserEntity user,@RequestParam("code")String code,
			@RequestParam(value="registerCode",defaultValue="")String openid,Model model){
		int minute = smsSer.checkCode(format.format(new Date()),user.getLoginName(),code);
		String result;
		if(minute<SmsLogService.LIVE_SECOND){
			result = userSer.register(user);
			if("true".equals(result)){
				if(!StringUtils.isBlank(openid))
					weixinUserSer.bindLoginName2Openid(user.getLoginName(),openid.trim());
				logger.info("微信用户注册成功！+openid"+openid);
				return "/weixin/login";
			}
		}else{
			logger.info("验证码超时或错误:"+user.getLoginName()+" code:"+code+" minute:"+minute);
			result="验证码超时或错误";
		}
		model.addAttribute("code", code);
		model.addAttribute(ENTITY, user);
		model.addAttribute("failMsg", result);
		model.addAttribute("registerCode", openid);
		return "/weixin/register";
	}
	/**
	 * 找回密码
	 * @return
	 */
	@RequestMapping(value="forgetPwd",method=RequestMethod.GET)
	public String forgetPwd(){
		return "/weixin/forgetPwd";
	}
	
	@RequestMapping(value="forgetPwd",method=RequestMethod.POST)
	public String forgetPwd(@RequestParam("tmpPassword")String tmpPassword,@RequestParam("code")String code,
			@RequestParam(value="loginName")String loginName,Model model){
		int minute = smsSer.checkCode(format.format(new Date()),loginName,code);
		if(minute<=SmsLogService.LIVE_SECOND){
			UserEntity user = userSer.findByLoginName(loginName);
			user.setTempPassword(tmpPassword);
			if(null != user){
				userSer.saveOrUpdate(user,null);
				logger.info("用户修改密码"+user.getName());
				return "redirect:/weixin/login";
			}
		}else{
			model.addAttribute("loginName", loginName);
			model.addAttribute("failMsg", "验证码超时或错误");
		}
		return "/weixin/forgetPwd";
	}
}
