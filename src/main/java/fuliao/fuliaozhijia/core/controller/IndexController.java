package fuliao.fuliaozhijia.core.controller;

import javax.servlet.ServletRequest;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import fuliao.fuliaozhijia.core.service.CoreUserService;

@Controller
public class IndexController {
	@Autowired
	@Qualifier("coreUserService")
	private CoreUserService userSer;

	@RequestMapping(value="login",method = RequestMethod.POST)
	public String fail(@RequestParam(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM) String userName, Model model) {
		model.addAttribute(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM, userName);
		return "/login";
	}
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String login(ServletRequest request) {
		return "/login";
	}
	
	@RequestMapping("/index")
	public String index() {
		return "/index";
	}
	
	@RequestMapping("/menuList/{file}")
	public String menu(@PathVariable("file")String menu) {
		return "/menu/"+menu;
	}
}
