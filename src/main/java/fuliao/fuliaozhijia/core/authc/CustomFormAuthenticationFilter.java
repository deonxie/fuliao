package fuliao.fuliaozhijia.core.authc;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import com.google.common.collect.Maps;

public class CustomFormAuthenticationFilter extends FormAuthenticationFilter{
	private Map<String,String> modelPath = Maps.newHashMap();
	private final String LOGIN = "_login";
	private final String SUCCESS = "_success";
	private final Pattern pattern = Pattern.compile("^/[a-zA-z0-9_]{0,}/*");
	
	private ServletRequest request;
	@Override
	protected boolean preHandle(ServletRequest request, ServletResponse response)
			throws Exception {
		this.request = request;
		return super.preHandle(request, response);
	}

	@Override
	protected void issueSuccessRedirect(ServletRequest request,
			ServletResponse response) throws Exception {
//		WebUtils.redirectToSavedRequest(request, response, getModelName(request,false));
		request.getRequestDispatcher(getModelName(request, false)).forward(request, response);
	}
	
	private String getModelName(ServletRequest request,boolean islogin){
		String path = null;
		if(request instanceof HttpServletRequest){
			HttpServletRequest httpreq = (HttpServletRequest)request;
			String reqUrl = httpreq.getRequestURI().replace(httpreq.getContextPath(), "");
			Matcher m = pattern.matcher(reqUrl);
			if(m.find()){
				String model = StringUtils.replace(m.group(), "/", "", 2);
				if(islogin)
					path = modelPath.get(model+LOGIN);
				else
					path = modelPath.get(model+SUCCESS);
				System.out.println("from authc model:="+model+" path="+path);
			}
		}
		if(StringUtils.isNotBlank(path))
			return path;
		if(islogin)
			return super.getLoginUrl();
		else
			return super.getSuccessUrl();	
	}

	@Override
	public String getLoginUrl() {
		return getModelName(request,true);
	}
	@Override
	public String getSuccessUrl() {
		return getModelName(request,false);
	}
	
	public void setModelPath(Map<String,String> modelPath) {
		if(null == modelPath || modelPath.size()<1)
			return;
		String[] login_success;
		for(String model : modelPath.keySet()){
			String path = modelPath.get(model);
			login_success = StringUtils.split(path, ";");
			if(null != login_success && login_success.length==2){
				this.modelPath.put(StringUtils.trim(model)+LOGIN, StringUtils.trim(login_success[0]));
				this.modelPath.put(StringUtils.trim(model)+SUCCESS, StringUtils.trim(login_success[1]));
			}
		}
	}
	
}