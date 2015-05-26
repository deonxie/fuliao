package fuliao.fuliaozhijia.core.controller;


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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fuliao.fuliaozhijia.core.entity.PermissionEnum;
import fuliao.fuliaozhijia.core.entity.RoleEntity;
import fuliao.fuliaozhijia.core.service.CoreRoleService;
import fuliao.fuliaozhijia.core.util.PageRequestUtil;

@Controller
@RequestMapping("/core/role/")
public class CoreRoleController extends GenericeController<RoleEntity>{
	@Autowired
	@Qualifier("coreRoleService")
	private CoreRoleService roleSer;
	
	@RequestMapping("page")
	public String page(PageRequestUtil pageUtil,Model model,ServletRequest request){
		Map<String,String> roders = getOrderBy(request);
		pageUtil.addSort(roders);
		Page<RoleEntity> page = roleSer.findByPage(getSpec(request), pageUtil.springPageRequest());
		model.addAttribute(PAGER, page);
		model.addAttribute(PAGE_UTIL, pageUtil);
		return "/core/role/roleList";
	}
	
	@RequestMapping(value="update/{id}")
	public String update(@PathVariable("id")String id,Model model){
		model.addAttribute(ENTITY, roleSer.findOne(id));
		model.addAttribute("allTypsPerms", PermissionEnum.allTypePerms());
		return "/core/role/roleForm";
	}
	
	@RequestMapping(value="save",method= RequestMethod.POST)
	public String save(@ModelAttribute RoleEntity entity,HttpServletRequest request, Model model){
		String[] perms = request.getParameterValues("perms");
		String error = roleSer.save(entity,perms);
		if(StringUtils.isBlank(error))
			return "redirect:/core/role/page";
		entity.setRoleName(null);
		model.addAttribute(ENTITY, entity);
		model.addAttribute("allTypsPerms", PermissionEnum.allTypePerms());
		model.addAttribute(ERROR_Msg, error);
		return "/core/role/roleForm";
	}
	
	@RequestMapping(value="delete/{id}")
	public String delete(@PathVariable("id")String id){
		roleSer.delete(id);
		return "redirect:/core/role/page";
	}
}
