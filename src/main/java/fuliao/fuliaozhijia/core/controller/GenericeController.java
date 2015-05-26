package fuliao.fuliaozhijia.core.controller;

import java.util.Map;

import javax.servlet.ServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import fuliao.fuliaozhijia.core.entity.IEntity;
import fuliao.fuliaozhijia.core.service.ShiroDbRealm.ShiroUser;
import fuliao.fuliaozhijia.core.util.RequestSearchParamPase;
import fuliao.fuliaozhijia.core.util.SearchSpecifcation;

public class GenericeController<E extends IEntity> {
	public final static String SEARCH_PERFIX = "search_";
	public final static String ORDERBY_PERFIX = "orderby_";
	public final static String PAGER = "page";
	public final static String PAGE_UTIL = "pageUtil";
	public final static String PARAMS_FIELD = "params_value";
	public final static String ERROR_Msg = "errorMsg";
	public final static String ENTITY = "entity";
	public final static String SHIROUSER = "shiroUser";
	
	public SearchSpecifcation<E> getSpec(){
		return new SearchSpecifcation<E>();
	}
	public SearchSpecifcation<E> getSpec(ServletRequest request){
		return new SearchSpecifcation<E>(getParams(request));
	}
	public Map<String, String> getParams(ServletRequest request){
		return RequestSearchParamPase.parseRequest(request, SEARCH_PERFIX);
	}
	public Map<String,String> getOrderBy(ServletRequest request){
		return RequestSearchParamPase.parseRequest(request, ORDERBY_PERFIX);
	}
	/**
     * 获取每个Controller类注解中的Mapper地址
     * 页面通过${symbol_dollar}{baseMapper}引用相关地址
     *
     * @return
     */
    @ModelAttribute("baseMapper")
    public String generateMapper() {
        String baseMapper = null;
        RequestMapping annotations = getClass().getAnnotation(RequestMapping.class);
        if (null != annotations) {
            String[] values = annotations.value();
            if (null != values && values.length > 0) {
                baseMapper = values[0];
            }
        }
        return baseMapper;
    }
    /**获取登录用户*/
    public ShiroUser getUser(){
    	Subject subject = SecurityUtils.getSubject();
    	if(subject !=null){
    		Object obj = subject.getPrincipal();
    		if(obj !=null && obj instanceof ShiroUser)
    			return (ShiroUser)obj;
    	}
    	return null;
    }
}
