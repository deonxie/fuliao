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
import fuliao.fuliaozhijia.core.service.CoreUserService;
import fuliao.fuliaozhijia.core.util.PageRequestUtil;
import fuliao.fuliaozhijia.core.util.SearchSpecifcation;
import fuliao.fuliaozhijia.weixin.entity.WeixinUser;
import fuliao.fuliaozhijia.weixin.entity.WeixinUserGroup;
import fuliao.fuliaozhijia.weixin.service.WeixinUserGroupService;
import fuliao.fuliaozhijia.weixin.service.WeixinUserService;


@Controller
@RequestMapping("/weixin/user/")
public class WeixinUserGroupController extends GenericeController<WeixinUserGroup>{
	private static Logger logger = LoggerFactory.getLogger(WeixinUserGroupController.class);
	
	@Autowired
	@Qualifier("coreUserService")
	private CoreUserService userSer;
	@Autowired
	@Qualifier("weixinUserService")
	private WeixinUserService weixinUserSer;
	@Autowired
	@Qualifier("weixinUserGroupService")
	WeixinUserGroupService groupSer;
	
	@RequestMapping("saveGroup")
	public String saveGroup(@RequestParam("name")String name,Model model){
		model.addAttribute(ERROR_Msg, groupSer.saveName(name));
		return "redirect:/weixin/user/userGroup";
	}
	@RequestMapping("userGroup")
	public String favtProduct(PageRequestUtil pageUtil ,HttpServletRequest request,Model model){
		Map<String,String> params = getParams(request);
		Page<WeixinUserGroup> page = groupSer.findByPage(new SearchSpecifcation<WeixinUserGroup>(params), pageUtil.springPageRequest());
		model.addAttribute(PAGER, page);
		model.addAttribute(PAGE_UTIL, pageUtil);
		return "/weixin/user/weixinUserGroup";
	}
	@RequestMapping("updateGroup")
	public String deleteFavt(@ModelAttribute WeixinUserGroup group){
		if(group.getId()>0)
			groupSer.save(group);
		logger.info("修改分组成功"+group.getName());
		return "redirect:/weixin/user/userGroup";
	}
	@RequestMapping("usergroupList")
	public String user2GroupList(PageRequestUtil pageUtil ,HttpServletRequest request,Model model){
		Map<String,String> params = getParams(request);
		Page<WeixinUser> page = weixinUserSer.findByPage(new SearchSpecifcation<WeixinUser>(params), pageUtil.springPageRequest());
		model.addAttribute(PAGER, page);
		model.addAttribute(PAGE_UTIL, pageUtil);
		model.addAttribute("allgroups", groupSer.findAll());
		return "/weixin/user/weixinUserGroupList";
	}
	
	@RequestMapping("saveUsergroup")
	public String user2Group(@RequestParam("userid")String userid,@RequestParam("groupid")long groupid,Model model){
		WeixinUser user =  weixinUserSer.findOne(userid);
		WeixinUserGroup group = groupSer.findOne(groupid);
		if(user !=null ){
			user.setUserGroup(group);
			weixinUserSer.save(user);
		}
		return "redirect:/weixin/user/usergroupList";
	}
}
