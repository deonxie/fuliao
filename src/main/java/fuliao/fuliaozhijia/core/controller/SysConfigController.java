package fuliao.fuliaozhijia.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import fuliao.fuliaozhijia.core.entity.SysConfig;
import fuliao.fuliaozhijia.core.service.SysConfigService;

@Controller
@RequestMapping("/core/sysconfig/")
public class SysConfigController extends GenericeController<SysConfig> {
	@Autowired
	@Qualifier("core_sysconfig")
	SysConfigService ser;
	
	@RequestMapping("")
	public String listPage(){
		
		return "";
	}
}
