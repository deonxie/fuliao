package fuliao.fuliaozhijia.core.controller;


import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import fuliao.fuliaozhijia.core.entity.UserEntity;
import fuliao.fuliaozhijia.core.service.CoreRoleService;
import fuliao.fuliaozhijia.core.service.CoreUserService;
import fuliao.fuliaozhijia.core.util.PageRequestUtil;

@Controller
@RequestMapping("/core/user/")
public class CoreUserController extends GenericeController<UserEntity>{
	@Autowired
	@Qualifier("coreUserService")
	private CoreUserService userSer;
	@Autowired
	@Qualifier("coreRoleService")
	private CoreRoleService roleSer;
	
	@RequestMapping("page")
	public String page(PageRequestUtil pageUtil,Model model,ServletRequest request){
		Page<UserEntity> page = userSer.findByPage(getSpec(request), pageUtil.springPageRequest());
		model.addAttribute(PAGER, page);
		model.addAttribute(PAGE_UTIL, pageUtil);
		return "/core/user/userList";
	}
	
	@RequestMapping(value="update/{id}")
	public String update(@PathVariable("id")String id,Model model){
		model.addAttribute(ENTITY, userSer.findOne(id));
		model.addAttribute("allRoles", roleSer.findAll());
		return "/core/user/userForm";
	}
	
	@RequestMapping(value="save",method= RequestMethod.POST)
	public String save(@ModelAttribute("formUserEntity")UserEntity entity,
			HttpServletRequest request, Model model){
		String[] roles = request.getParameterValues("roles");
		String error = userSer.saveOrUpdate(entity,roles);
		if(StringUtils.isBlank(error))
			return "forward:/core/user/page";
		entity.setLoginName(null);
		model.addAttribute(ENTITY, entity);
		model.addAttribute("allRoles", roleSer.findAll());
		model.addAttribute(ERROR_Msg, error);
		return "/core/user/userForm";
	}
	
	@RequestMapping(value="delete/{id}")
	public String delete(@PathVariable("id")String id){
		userSer.delete(id);
		return "redirect:/core/user/page";
	}
	
	/**先去数据库查询需要修改的实例，再绑定form传人属性值*/
	@ModelAttribute("formUserEntity")
    public UserEntity initUser(@RequestParam(value="id", defaultValue ="") String id) {
        if(StringUtils.isNotBlank(id)) {
        	return userSer.findOne(id);
        }
        return new UserEntity();
    }
}
